;; -------------------------------------------------------------------
;; clj-fuzzy Jaro-Winkler Distance
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.jaro-winkler)

(defn- longest-sequence [seq1 seq2]
  (if (>= (count seq1) (count seq2))
    [seq1 seq2]
    [seq2 seq1]))

(defn- match-window [min-len] (max (dec (int (/ min-len 2))) 0))

(defn- submatch [ch shortest window-start window-end seq1-matches seq2-matches]
  (loop [j 0]
    (if (and (< j window-end)
             (not (seq1-matches j))
             (= ch (get shortest j)))
      [(conj seq1-matches ch)
       (conj seq2-matches (get shortest j))]
      (recur (inc j)))))

;; Change for fors
(defn- matches [seq1 seq2]
  (let [[longest shortest] (longest-sequence seq1 seq2)
        max-len (count longest)
        min-len (count shortest)
        mwindow (match-window min-len)]
    (loop [i 0
           seq1-matches []
           seq2-matches []]
      (if (< i max-len)
        ;; Return
        [seq1-matches seq2-matches]
        ;; Recur
        (let [window-start (max (dec mwindow) 0)
              window-end (min (+ i mwindow) min-len)
              [nseq1-matches nseq2-matches] (submatch (get longest i)
                                                       shortest
                                                       window-start
                                                       window-end
                                                       seq1-matches
                                                       seq2-matches)]
          (recur (inc i) nseq1-matches nseq2-matches))))))

