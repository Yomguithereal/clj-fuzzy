;; -------------------------------------------------------------------
;; clj-fuzzy Double Metaphone Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.double-metaphone-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.double-metaphone :refer :all]))

(deftest slavo-germanic?-test
  (is (= true (slavo-germanic? "CZ")))
  (is (= true (slavo-germanic? "WITZ")))
  (is (= false (slavo-germanic? "A")))
  (is (= false (slavo-germanic? "XR"))))

(deftest vowel?-test
  (is (= true (vowel? "A")))
  (is (= true (vowel? "E")))
  (is (= false (vowel? "C")))
  (is (= false (vowel? "X"))))

(deftest lookup-vowel-test
  (is (= [:A :A 1] (lookup-vowel 0)))
  (is (= [nil nil 1] (lookup-vowel 1))))

(deftest lookup-B-test
  (is (= [:P :P 2] (lookup-B "BBB" 0)))
  (is (= [:P :P 1] (lookup-B "BTT" 0))))
