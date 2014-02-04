;; -------------------------------------------------------------------
;; clj-fuzzy Hamming Distance
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.hamming)

;; Main function
(defn distance
  "Compute the Hamming distance between [seq1] and [seq2]."
  [seq1 seq2]
  (let [l1 (count seq1)
        l2 (count seq2)]
    (when (= l1 l2)
      (count (remove #(= (first %) (second %))
                     (partition 2 (interleave seq1 seq2)))))))
