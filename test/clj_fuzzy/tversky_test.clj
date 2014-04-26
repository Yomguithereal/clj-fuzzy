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
            [clj-fuzzy.dice]))

(defn jaccard [s1, s2] (- 1 (clj-fuzzy.jaccard/distance s1 s2)))
(defn tversky-jaccard [s1 s2] (index s1 s2 :alpha 1 :beta 1))
(defn compare-jaccard [s1 s2] (is (= (jaccard s1 s2) (tversky-jaccard s1 s2))))


(deftest assymetric-index-test
  (compare-jaccard "abc" "abc")
  (compare-jaccard "abc" "xyz")
  (compare-jaccard "night" "nacht")
  (compare-jaccard "context" "contact")
  (compare-jaccard "ht" "nacht"))
