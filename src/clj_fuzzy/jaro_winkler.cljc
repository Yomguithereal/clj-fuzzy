;; -------------------------------------------------------------------
;; clj-fuzzy Jaro-Winkler Distance
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.jaro-winkler)

;; Utilities
;; OPTIMIZE: I cannot believe this is the most efficient way to do it
(defn- longest-sequence [seq1 seq2]
  (if (>= (count seq1) (count seq2))
    [seq1 seq2]
    [seq2 seq1]))

(defn- match-window [min-len] (max (dec (int (/ min-len 2))) 0))

(defn- submatch [i ch shortest window-start window-end seq1-matches seq2-matches]
  (loop [j window-start
         nseq1-matches seq1-matches
         nseq2-matches seq2-matches]
    (if (and ((complement nil?) j)
             (<= j window-end))
      (if (and (not (get seq2-matches j))
               (= ch (get shortest j)))
        (recur nil
               (assoc nseq1-matches i ch)
               (assoc nseq2-matches j (get shortest j)))
        (recur (inc j) nseq1-matches nseq2-matches))
      [nseq1-matches nseq2-matches])))

(defn- matches [seq1 seq2]
  (let [[longest shortest] (longest-sequence seq1 seq2)
        max-len (count longest)
        min-len (count shortest)
        mwindow (match-window max-len)]
    (loop [i 0
           seq1-matches (vec (repeat max-len nil))
           seq2-matches (vec (repeat max-len nil))]
      (if (< i max-len)
        ;; Recur
        (let [window-start (max (- i mwindow) 0)
              window-end (min (+ i mwindow 1) min-len)
              [nseq1-matches nseq2-matches] (submatch i
                                                      (get longest i)
                                                      shortest
                                                      window-start
                                                      window-end
                                                      seq1-matches
                                                      seq2-matches)]
          (recur (inc i) nseq1-matches nseq2-matches))
        ;; Return
        [(remove nil? seq1-matches) (remove nil? seq2-matches)]))))

(defn- transpositions [longest-matches shortest-matches]
  (let [pad (- (count longest-matches) (count shortest-matches))
        comparison (partition 2 (interleave longest-matches
                                            (concat shortest-matches (repeat pad nil))))]
    (/ (count (filter #(not= (first %) (second %)) comparison)) 2)))

(defn- winkler-prefix [seq1 seq2]
  (loop [i 0
         prefix 0]
    (if (< i 4)
      (if (= (get seq1 i) (get seq2 i))
        (recur (inc i) (inc prefix))
        (recur 5 prefix))
      prefix)))

;; Main Functions
(defn jaro
  "Compute the Jaro distance between two sequences."
  [seq1 seq2]
  (let [[longest-matches shortest-matches] (matches seq1 seq2)
        m (count longest-matches)
        t (transpositions longest-matches shortest-matches)]
    (if (zero? m)
      0
      (/ (+ (/ m (count seq1))
            (/ m (count seq2))
            (/ (- m t) m))
         3.0))))

(defn jaro-winkler
  "Compute the Jaro-Winkler distance between two sequences."
  [seq1 seq2]
  (let [j (jaro seq1 seq2)
        l (winkler-prefix seq1 seq2)
        p 0.1]
    (+ j (* l p (- 1 j)))))
