;; -------------------------------------------------------------------
;; clj-fuzzy Tversky Index
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.tversky
  (:require [clojure.set :refer [intersection difference]]))

;; Utilities
(defn- I [X Y] (count (intersection X Y)))
(defn- R [X Y] (count (difference X Y)))

;; Main Functions
(defn- assymmetric-index
  "Compute the original assymmetric Tversky index for the given [sequences] and
   the given [alpha] and [beta]."
  [seq1 seq2 alpha beta]
  (let [X (set seq1)
        Y (set seq2)
        XIY (I X Y)]
    (/ XIY
       (+ XIY
          (* alpha (R X Y))
          (* beta (R Y X))))))

(defn- symmetric-index
  "Compute the symmetric variant of the Tversky undex for the given [sequences] and
   the given [alpha] and [beta]."
  [seq1 seq2 alpha beta]
  (let [X (set seq1)
        Y (set seq2)
        XIY (I X Y)
        X-Y (R X Y)
        Y-X (R Y X)
        a (min X-Y Y-X)
        b (max X-Y Y-X)]
    (/ XIY
       (+ XIY
          (* beta
             (+ (* alpha a)
                (Math/pow (- 1 alpha) b)))))))

(defn index
  "Compute the tversky index for the given [sequences] with given [:alpha] and [:beta]
   and in a [:symmetric] fashion or not."
  [seq1 seq2 & {:keys [alpha beta symmetric] :or {alpha 1 beta 1 symmetric false}}]
  ((if symmetric symmetric-index assymmetric-index) seq1 seq2 alpha beta))
