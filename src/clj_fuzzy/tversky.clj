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
(defn- assymetric-index
  "Compute the original assymetric Tversky index for the given [sequences] and
   the given [alpha] and [beta]."
  [seq1 seq2 alpha beta]
  (let [X (set seq1)
        Y (set seq2)
        XIY (I X Y)]
    (/ XIY
       (+ XIY
          (* alpha (R X Y))
          (* beta (R Y X))))))

(defn- symetric-index
  "Compute the symetric variant of the Tversky undex for the given [sequences] and
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
  "Compute the tversky index for the given [sequences] and using the desired [method]."
  [seq1 seq2 & {:keys [alpha beta symetric] :or {alpha 1 beta 1 symetric false}}]
  ((if symetric symetric-index assymetric-index) seq1 seq2 alpha beta))

;(process "healed" "sold" :symetric false :alpha 0.5 :beta 0.5)
