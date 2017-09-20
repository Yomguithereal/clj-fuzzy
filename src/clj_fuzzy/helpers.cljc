;; -------------------------------------------------------------------
;; clj-fuzzy Helper Functions
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.helpers
  (:refer-clojure :exclude [any?])
  (:require clojure.string))

;; Strings helpers
;;----------------
(defn slice
  "Slice a [string] from [start] and up to [length]."
  [string start length]
  (let [offset (if (neg? start) (+ (count string) start) start)]
    (apply str (take length (drop offset string)))))

(defn chop
  "Drop the last character of a [string]."
  [string]
  (subs string 0 (dec (count string))))

(defn eat
  "Drop the first letter of a [string]."
  [string]
  (apply str (drop 1 string)))

(defn batch-replace
  "Apply several [replacements] to a [string]."
  [string replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply clojure.string/replace %1 %2) string replacement-list)))

(defn clean-non-alphabetical
  "Drop every non alphabetical character in [word]."
  [word]
  (clojure.string/replace word #"[^a-zA-Z]" ""))

;; Regex helpers
;;--------------
(defn re-test?
  "Test a [string] against a [regular-expression]."
  [regular-expression string]
  (not (nil? (re-find regular-expression string))))

;; Sequences helpers
;;------------------
(defn distinct-consecutive
  "Drop consecutive duplicates in sequence"
  [sequence] (map first (partition-by identity sequence)))

(defn n-grams
  "Lazily compute the n-grams of a sequence."
  [n s]
  (partition n 1 s))

(defn bigrams [s] (n-grams 2 s))
(defn trigrams [s] (n-grams 3 s))
(defn quadrigrams [s] (n-grams 4 s))

(defn- any?
  "Is any of the [coll] item true according to the given [predicate]?"
  [pred coll]
  (boolean (some pred coll)))

(defn in?
  "Checks whether a [string] is contained within a [sequence]."
  [string sequence]
  (boolean (some #{string} sequence)))

(def not-in? (complement in?))
