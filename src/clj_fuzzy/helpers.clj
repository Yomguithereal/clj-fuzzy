;; -------------------------------------------------------------------
;; clj-fuzzy Double Metaphone
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.helpers
  (:require clojure.string))

(defn slice
  "Slice a [string] from [start] and up to [length]."
  [string start length]
  (apply str (take length (drop start string))))

(defn chop [string]
  (subs string 0 (dec (count string))))

(defn batch-replace
  "Apply several [replacements] to a [string]."
  [string replacements]
  (let [replacement-list (partition 2 replacements)]
    (reduce #(apply clojure.string/replace %1 %2) string replacement-list)))

(defn re-test?
  "Test a [string] against a [regular-expression]."
  [regular-expression string]
  (not (nil? (re-find regular-expression string))))

(defn in?
  "Checks whether a [string] is contained within a [sequence]."
  [string sequence]
  (boolean (some #{string} sequence)))

(defn clean-non-alphabetical
  "Drop every non alphabetical character in [word]."
  [word]
  (clojure.string/replace word #"[^a-zA-Z]" ""))
