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
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [slice in?]]
        [clojure.set :only [intersection]]))

(defn- pairing [word]
  (map #(slice word % 2) (range 0 (- (count word) 1))))

(defn- letter-pairs [string]
  (let [ts (clojure.string/split (clojure.string/upper-case string) #"\s+")]
    (flatten (map #(pairing %) ts))))

;; OPTIMIZE: do we need to round up the result?
(defn coefficient
  "Compute the Dice coefficient between two [strings]."
  [string1 string2]
  (let [p1 (letter-pairs string1)
        p2 (letter-pairs string2)
        sum (+ (count p1) (count p2))]
    (/ (* 2.0 (count (intersection (set p1) (set p2))))
       sum)))
