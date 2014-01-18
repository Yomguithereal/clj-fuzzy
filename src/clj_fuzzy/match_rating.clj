;; -------------------------------------------------------------------
;; clj-fuzzy Match Rating Approach
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.match-rating
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [eat
                                  clean-non-alphabetical
                                  distinct-consecutive]]))

(defn- drop-non-leading-vowel [word]
  (apply str
         (first word)
         (clojure.string/replace (eat word) #"[AEIOU]" "")))

(defn- prep-word [word]
  (-> (clojure.string/upper-case word)
      (clean-non-alphabetical)))

(defn- !>3 [integer]
  (if (> integer 3) 3 integer))

(defn- get-codex-letters [pword]
  (let [last-3 (!>3 (- (count pword) 3))]
    (apply str (concat (take 3 pword)
                       (take-last last-3 pword)))))

(defn mra-codex
  "Compute the MRA codex for a [word]."
  [word]
  (-> (prep-word word)
      (drop-non-leading-vowel)
      (distinct-consecutive)
      (get-codex-letters)))
