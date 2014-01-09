;; -------------------------------------------------------------------
;; clj-fuzzy Levensthein
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Source:
;;     http://www.learningclojure.com/2010/11/
;;            levenshtein-distance-edit-distance.html
;;
(ns clj-fuzzy.levensthein)

(defn distance
  "Compute the levensthein distance between two [sequences]."
  [sequence1 sequence2]
  (cond
    (empty? sequence1) (count sequence2)
    (empty? sequence2) (count sequence1)
    :else (min
            (+ (if (= (first sequence1) (first sequence2)) 0 1)
                (#'distance (rest sequence1) (rest sequence2)))
            (inc (#'distance (rest sequence1) sequence2))
            (inc (#'distance sequence1 (rest sequence2))))))
