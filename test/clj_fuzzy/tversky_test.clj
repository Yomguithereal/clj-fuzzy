;; -------------------------------------------------------------------
;; clj-fuzzy Tversky Index Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.tversky-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.tversky :refer :all]
            [clj-fuzzy.jaccard]
            [clj-fuzzy.dice])
  (:use [clj-fuzzy.helpers :only [bigrams]]))

;; Jaccard equivalent
(defn jaccard [s1, s2] (- 1 (clj-fuzzy.jaccard/distance s1 s2)))
(defn tversky-jaccard [s1 s2] (index s1 s2 :alpha 1 :beta 1))
(defn compare-jaccard [s1 s2] (is (= (jaccard s1 s2) (tversky-jaccard s1 s2))))

;; Dice equivalent
(defn tversky-dice [s1 s2] (index (bigrams s1) (bigrams s2) :alpha 0.5 :beta 0.5))
(defn compare-dice [s1 s2] (is (= (clj-fuzzy.dice/coefficient s1 s2) (tversky-dice s1 s2))))

(deftest assymetric-index-test
  (compare-jaccard "abc" "abc")
  (compare-jaccard "abc" "xyz")
  (compare-jaccard "night" "nacht")
  (compare-jaccard "context" "contact")
  (compare-jaccard "ht" "nacht")
  (compare-dice "healed" "healed")
  (compare-dice "healed" "sealed")
  (compare-dice "healed" "healthy")
  (compare-dice "healed" "heard")
  (compare-dice "healed" "herded")
  (compare-dice "healed" "help")
  (compare-dice "healed" "sold"))
