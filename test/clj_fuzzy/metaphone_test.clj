;; -------------------------------------------------------------------
;; clj-fuzzy Metaphone Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.metaphone-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.metaphone :refer :all]))

(deftest process-test
  (is (= "TSKRMNXN" (process "discrimination")))
  (is (= "HL" (process "hello")))
  (is (= "TRT" (process "droid")))
  (is (= "HPKRT" (process "hypocrite")))
  (is (= "WL" (process "well")))
  (is (= "AM" (process "am")))
  (is (= "S" (process "say")))
  (is (= "FSNT" (process "pheasant")))
  (is (= "KT" (process "god"))))
