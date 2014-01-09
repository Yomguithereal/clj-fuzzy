;; -------------------------------------------------------------------
;; clj-fuzzy Porter Stemming
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   version: 0.1
;;
(ns clj-fuzzy.porter-stemming
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [re-test? chop]]))

;;-----------------
;; Stemmer's lists
;;-----------------
(defn- make-suffixes [& strings]
  (re-pattern (str "(" (clojure.string/join "|" strings) ")$")))

(def ^:private step-2-suffixes
  (make-suffixes "ational" "tional" "enci" "anci" "izer" "bli"
                 "alli" "entli" "ousli" "ization" "ation" "ator"
                 "alism" "iveness" "fulness" "ousness" "aliti"
                 "iviti" "biliti" "logi"))

(def ^:private step-3-suffixes
  (make-suffixes "icate" "ative" "alize" "iciti" "ical" "ful" "ness"))

(def ^:private step-4-suffixes
  (make-suffixes "al" "ance" "ence" "er" "ic" "able" "ible" "ant"
                 "ement" "ment" "ent" "ou" "ism" "ate" "iti" "ous"
                 "ive" "ize"))

(def ^:private step-2-map {"ational" "ate"
                           "tional" "tion"
                           "enci" "ence"
                           "anci" "ance"
                           "izer" "ize"
                           "bli" "ble"
                           "alli" "al"
                           "ization" "ize"
                           "ation" "ate"
                           "ator" "ate"
                           "alism" "al"
                           "iveness" "ive"
                           "fulness" "ful"
                           "ousness" "ous"
                           "aliti" "al"
                           "iviti" "ive"
                           "biliti" "ble"
                           "logi" "log"})

(def ^:private step-3-map {"icate" "ic"
                           "ative" ""
                           "alize" "al"
                           "iciti" "ic"
                           "ical" "ic"
                           "ful" ""
                           "ness" ""})

;;---------
;; Regexes
;;---------
(def ^:private c "[^aeiou]")
(def ^:private v "[aeiouy]")
(def ^:private cc (str c "(?>" c "*)"))
(def ^:private vv (str v "(?>" v "*)"))

(defn- re-test-fn [pattern-string]
  (fn [string] (re-test? (re-pattern pattern-string) string)))

(def ^:private mgr0?
  (re-test-fn (str "^(" cc ")?" vv cc)))
(def ^:private meq1?
  (re-test-fn (str "^(" cc ")?" vv cc "(" vv ")?$" )))
(def ^:private mgr1?
  (re-test-fn (str "^(" cc ")?" vv cc vv cc)))
(def ^:private vowel-in-stem?
  (re-test-fn (str "^(" cc ")?" v)))
(def ^:private steps?
  (re-test-fn (str "^" cc v "[^aeiouwxy]$")))

;;-----------
;; Utilities
;;-----------
(defn- remove-last-of [string kind]
  (let [index (.lastIndexOf string kind)]
    (if (neg? index)
      string
      (str (subs string 0 index) (subs string (+ index (count kind)))))))

(defn $match [regex word]
  (let [[matched group1 :as found] (re-find regex word)]
    (if-not (nil? found)
      [(remove-last-of word (str matched)) group1]
      [nil nil])))

;;-------
;; Steps
;;-------
(defn- step-0 [word]
  (if (re-test? #"^y" word)
    (clojure.string/capitalize word)
    word))

(defn- step-1a [word]
  (let [[f1 v1] ($match #"(ss|i)es$" word)
        [f2 v2] ($match #"([^s])s$" word)]
    (cond
      f1 (str f1 v1)
      f2 (str f2 v2)
      :else word)))

(defn- step-1b-1 [word]
  (if (mgr0? (subs word 0 (- (count word)) 3))
    (chop word)
    word))

(defn- step-1b-2 [word]
  (let [[stem _] ($match #"(ed|ing)$" word)]
    (if (vowel-in-stem? stem)
      (cond
        (re-test? #"(at|bl|iz)$" stem) (str stem "e")
        (re-test? #"([^aeiouylsz])\1$" stem) (chop stem)
        (steps? stem) (str stem "e")
        :else stem)
      word)))

(defn- step-1b [word]
  (cond
    (re-test? #"eed$" word) (step-1b-1 word)
    (re-test? #"(ed|ing)$" word) (step-1b-2 word)
    :else word))

(defn- step-1c [word]
  (let [f (re-find #"y$" word)
        stem (chop word)]
    (if (and f (vowel-in-stem? stem))
      (str stem "i")
      word)))

(defn- step-23-procedure [word regex step-map]
  (let [[stem suffix] ($match regex word)]
    (if (and stem (mgr0? stem))
      (str stem (get step-map suffix))
      word)))

(defn- step-2 [word]
  (step-23-procedure word step-2-suffixes step-2-map))

(defn- step-3 [word]
  (step-23-procedure word step-3-suffixes step-3-map))

(defn- step-4a [word]
  (let [[stem suffix] ($match #"(s|t)(ion)$" word)
        ss (str stem suffix)]
    (if (and stem (mgr1? ss))
      ss
      word)))

(defn- step-4 [word]
  (let [[stem suffix] ($match step-4-suffixes word)]
    (if (and stem (mgr1? stem))
      stem
      (step-4a word))))

(defn- step-5a [word]
  (if (re-test? #"e$" word)
    (let [stem (chop word)]
      (if (or (mgr1? stem)
              (and (meq1? stem)
                   (not (steps? stem))))
        stem
        word))
    word))

(defn- step-5b [word]
  (if (and (re-test? #"ll$" word) (mgr1? word))
    (chop word)
    word))

(defn- step-5c [word]
  (if (= "Y" (first word))
    (str "y" (drop 1 word))
    word))

;;-----------------
;; Public function
;;-----------------
(defn stem [word]
  (if (< (count word) 3)
    word
    (-> (clojure.string/lower-case word)
        step-0
        step-1a step-1b step-1c
        step-2
        step-3
        step-4
        step-5a step-5b step-5c)))
