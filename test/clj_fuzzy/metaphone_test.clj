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

(deftest process-word-test
  (is (= "TSKRMNXN" (process-word "discrimination")))
  (is (= "HL" (process-word "hello")))
  (is (= "TRT" (process-word "droid")))
  (is (= "HPKRT" (process-word "hypocrite")))
  (is (= "WL" (process-word "well")))
  (is (= "AM" (process-word "am")))
  (is (= "S" (process-word "say")))
  (is (= "FSNT" (process-word "pheasant")))
  (is (= "KT" (process-word "god"))))
