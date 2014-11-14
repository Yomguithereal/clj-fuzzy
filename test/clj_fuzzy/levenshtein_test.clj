;; -------------------------------------------------------------------
;; clj-fuzzy Levenshtein Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.levenshtein-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.levenshtein :refer :all]))

(deftest levenshtein-test
  (is (= 2 (distance "book" "back")))
  (is (= 1 (distance "hello" "helo")))
  (is (= 8 (distance "good sir" "baal")))
  (is (= 5 (distance "say" "shiver"))))
