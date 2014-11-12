;; -------------------------------------------------------------------
;; clj-fuzzy Sorensen-Dice Coefficient
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Explanation:
;;     http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient
;;
(ns clj-fuzzy.dice
  (:require clojure.string
            clojure.set)
  (:use [clj-fuzzy.helpers :only [bigrams]]))

;; Utilities
(defn- letter-pairs
  [string]
  (bigrams (-> (clojure.string/replace string #"\s+" "")
               (clojure.string/upper-case))))

;; Main functions
(defn coefficient
  "Compute the Dice coefficient between two [strings]."
  [string1 string2]
  (let [p1 (letter-pairs string1)
        p2 (letter-pairs string2)
        sum (+ (count (set p1)) (count (set p2)))]
    (/ (* 2.0 (count (clojure.set/intersection (set p1) (set p2))))
       sum)))
