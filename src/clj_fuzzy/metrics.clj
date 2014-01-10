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
              [clj-fuzzy.dice :only [coefficient]]))

(def levensthein clj-fuzzy.levensthein/distance)
(def dice clj-fuzzy.dice/coefficient)
