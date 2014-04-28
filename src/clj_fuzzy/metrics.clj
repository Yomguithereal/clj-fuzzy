;; -------------------------------------------------------------------
;; clj-fuzzy Metrics API
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.metrics
    (:require clj-fuzzy.levensthein
              clj-fuzzy.dice
              clj-fuzzy.match-rating
              clj-fuzzy.jaccard
              clj-fuzzy.hamming
              clj-fuzzy.jaro-winkler))

(def levensthein clj-fuzzy.levensthein/distance)
(def dice clj-fuzzy.dice/coefficient)
(def sorensen clj-fuzzy.dice/coefficient)
(def mra-comparison clj-fuzzy.match-rating/mra-comparison)
(def jaccard clj-fuzzy.jaccard/distance)
(def tanimoto clj-fuzzy.jaccard/distance)
(def hamming clj-fuzzy.hamming/distance)
(def jaro clj-fuzzy.jaro-winkler/jaro)
(def jaro-winkler clj-fuzzy.jaro-winkler/jaro-winkler)
