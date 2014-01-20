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
              [clj-fuzzy.match-rating :only [mra-comparison]]))

(def levensthein clj-fuzzy.levensthein/distance)
(def dice clj-fuzzy.dice/coefficient)
(def mra-comparison clj-fuzzy.match-rating/mra-comparison)
