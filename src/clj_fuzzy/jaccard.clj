;; -------------------------------------------------------------------
;; clj-fuzzy Jaccard Distance
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.jaccard
  (:require clojure.set))

;; Main functions
(defn index
  "Compute the Jaccard index between [set1] and  [set2]."
  [set1 set2]
  (/ (count (clojure.set/intersection set1 set2))
     (count (clojure.set/union set1 set2))))

(defn distance
  "Compute the Jaccard distance between [seq1] and [seq2]."
  [seq1 seq2]
  (let [set1 (set seq1)
        set2 (set seq2)]
    (- 1 (index set1 set2))))
