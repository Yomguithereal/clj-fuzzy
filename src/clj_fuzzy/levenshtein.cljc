;; -------------------------------------------------------------------
;; clj-fuzzy Levenshtein
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Source: https://gist.github.com/vishnuvyas/958488
;;
(ns clj-fuzzy.levenshtein)

(defn- next-row
  [previous current other-seq]
  (reduce
    (fn [row [diagonal above other]]
      (let [update-val (if (= other current)
                          diagonal
                          (inc (min diagonal above (peek row))))]
        (conj row update-val)))
    [(inc (first previous))]
    (map vector previous (next previous) other-seq)))

(defn distance
  "Compute the levenshtein distance between two [sequences]."
  [sequence1 sequence2]
  (cond
    (and (empty? sequence1) (empty? sequence2)) 0
    (empty? sequence1) (count sequence2)
    (empty? sequence2) (count sequence1)
    :else (peek
            (reduce (fn [previous current] (next-row previous current sequence2))
                    (map #(identity %2) (cons nil sequence2) (range))
                    sequence1))))
