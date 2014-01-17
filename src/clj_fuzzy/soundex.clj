;; -------------------------------------------------------------------
;; clj-fuzzy Soundex
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.soundex
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [clean-non-alphabetical
                                  distinct-consecutive]]))

;; Utilities
(def ^:private translation
  (zipmap "AEIOUYWHBPFVCSKGJQXZDTLMNR" "000000DD111122222222334556"))

(defn- get-code [character] (translation character))

(defn- pad [word] (subs (str word "000") 0 4))

(defn- word-tail [word] (apply str (drop 1 word)))

(defn- compute-code-sequence
  "Compute the initial soundex code sequence for a [processed-word] tail."
  [processed-word]
  (filter #(not= \D %)
          (map get-code processed-word)))

(defn- clean-code-sequence
  "Clean the [code-sequence] by checking [first-letter] collocation."
  [code-sequence first-letter]
  (if (= (first code-sequence) (translation first-letter))
    (drop 1 code-sequence)
    code-sequence))

;; Main
(defn process
  "Return the soundex code of a specific [word]."
  [word]
  (let [processed-word (clean-non-alphabetical(clojure.string/upper-case word))
        first-letter (first processed-word)
        code-sequence (compute-code-sequence (word-tail processed-word))
        cleaned-sequence (clean-code-sequence code-sequence first-letter)]
    (pad (str first-letter
              (apply str (filter #(not= \0 %)
                                 (distinct-consecutive cleaned-sequence)))))))
