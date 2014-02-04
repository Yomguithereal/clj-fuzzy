;; -------------------------------------------------------------------
;; clj-fuzzy Jaccard Distance Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.jaccard-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.jaccard :refer :all]))

(deftest distance-test
  (is (= 0 (distance "abc" "abc")))
  (is (= 1 (distance "abc" "xyz")))
  (is (= 4/7 (distance "night" "nacht")))
  (is (= 3/7 (distance "context" "contact")))
  (is (= 3/5 (distance "ht" "nacht"))))
