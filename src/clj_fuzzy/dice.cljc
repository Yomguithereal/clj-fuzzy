;; -------------------------------------------------------------------
;; clj-fuzzy Sorensen-Dice Coefficient
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Explanation:
;;     http://en.wikipedia.org/wiki/Sørensen–Dice_coefficient
;;
(ns clj-fuzzy.dice
  (:require clojure.string
            clojure.set)
  (:use [clj-fuzzy.helpers :only [n-grams]]))

;; Utilities
(defn- letter-sets
  [n string]
  (set (n-grams n (-> (clojure.string/replace string #"\s+" "")
                      (clojure.string/upper-case)))))

;; Main functions
(defn coefficient
  "Compute the Dice coefficient between two [strings]."
  [string1 string2 & {:keys [n] :or {n 2}}]
  (cond (= string1 string2) 1.0
        (and (< (count string1) 2)
             (< (count string2) 2)) 0.0
        :else (let [p1 (letter-sets n string1)
                    p2 (letter-sets n string2)
                    sum (+ (count p1) (count p2))]
                (/ (* 2.0 (count (clojure.set/intersection p1 p2)))
                   sum))))
