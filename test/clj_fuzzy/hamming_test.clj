;; -------------------------------------------------------------------
;; clj-fuzzy Hamming Distance Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.hamming-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.hamming :refer :all]))

(deftest distance-test
  (is (= nil (distance "abc" "abcd")))
  (is (= 2 (distance "1011101" "1001001")))
  (is (= 3 (distance "2143896" "2233796")))
  (is (= 3 (distance "ramer" "cases")))
  (is (= 0 (distance "abc" "abc")))
  (is (= 1 (distance "abc" "adc")))
  (is (= 4 (distance "nigth" "nacht")))
  (is (= 1 (distance '(0 1 0 1) '(1 1 0 1)))))
