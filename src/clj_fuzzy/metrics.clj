;; -------------------------------------------------------------------
;; clj-fuzzy Metrics API
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.metrics
    (:require [clj-fuzzy.levensthein :only [distance]]
              [clj-fuzzy.dice :only [coefficient]]
              [clj-fuzzy.match-rating :only [mra-comparison]]
              [clj-fuzzy.jaccard :only [distance]]
              [clj-fuzzy.hamming :only [distance]]))

(def levensthein clj-fuzzy.levensthein/distance)
(def dice clj-fuzzy.dice/coefficient)
(def mra-comparison clj-fuzzy.match-rating/mra-comparison)
(def jaccard clj-fuzzy.jaccard/distance)
(def hamming clj-fuzzy.hamming/distance)
