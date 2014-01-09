;; -------------------------------------------------------------------
;; clj-fuzzy Helpers Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.helpers-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.helpers :refer :all]))

(deftest in?-test
  (is (= true (in? "orchid" '("orchid" "blueberry"))))
  (is (= false (in? "rhododendron" '("orchid" "blueberry")))))

(deftest slice-test
  (is (= "b" (slice "abcd" 1 1)))
  (is (= "bcd" (slice "abcd" 1 3)))
  (is (= "abc" (slice "abcd" 0 3)))
  (is (= "bc" (slice "abcd" 1 2))))

(deftest chop-test
  (is (= "hell" (chop "hello")))
  (is (= "wha" (chop "what"))))

(deftest batch-replace-test
  (is (= "tbce" (batch-replace "abcd" '(#"a" "t" #"d" "e"))))
  (is (not= "tbctes" (batch-replace "abcd" '( #"a" "t" #"d" "e")))))
