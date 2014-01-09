;; -------------------------------------------------------------------
;; clj-fuzzy White Similarity
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Source: http://www.catalysoft.com/articles/strikeamatch.html
;;   Original Author: Simon White
;;

(ns clj-fuzzy.white
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [slice in?]]
        [clojure.set :only [intersection]]))

(defn- pairing [word]
  (map #(slice word % 2) (range 0 (- (count word) 1))))

(defn- letter-pairs [string]
  (let [ts (clojure.string/split (clojure.string/upper-case string) #"\s+")]
    (flatten (map #(pairing %) ts))))

;; OPTIMIZE: do we need to round up the result?
(defn similarity
  "Compute the White similarity between two [strings]."
  [string1 string2]
  (let [p1 (letter-pairs string1)
        p2 (letter-pairs string2)
        sum (+ (count p1) (count p2))]
    (/ (* 2.0 (count (intersection (set p1) (set p2))))
       sum)))
