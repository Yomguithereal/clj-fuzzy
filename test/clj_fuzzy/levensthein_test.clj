;; -------------------------------------------------------------------
;; clj-fuzzy Levensthein Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.levensthein-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.levensthein :refer :all]))

(deftest levensthein-test
  (is (= 2 (distance "book" "back")))
  (is (= 1 (distance "hello" "helo")))
  (is (= 8 (distance "good sir" "baal")))
  (is (= 5 (distance "say" "shiver"))))
