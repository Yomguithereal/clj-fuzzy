;; -------------------------------------------------------------------
;; clj-fuzzy Match Rating Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.match-rating-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.match-rating :refer :all]))

(deftest mra-codex-test
  (is (= "BYRN" (mra-codex "Byrne")))
  (is (= "BRN" (mra-codex "Boern")))
  (is (= "SMTH" (mra-codex "Smith")))
  (is (= "SMYTH" (mra-codex "Smyth")))
  (is (= "CTHRN" (mra-codex "Catherine")))
  (is (= "KTHRYN" (mra-codex "Kathryn"))))
