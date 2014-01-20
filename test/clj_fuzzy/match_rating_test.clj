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

(def test-results
  [{:minimum 4
    :similarity 5
    :codex ["BYRN" "BRN"]
    :match true}
    {:minimum 3
     :similarity 5
     :codex ["SMTH" "SMYTH"]
     :match true}
    {:minimum 3
     :similarity 4
     :codex ["CTHRN" "KTHRYN"]
     :match true}
    {:minimum 3
     :similarity 1
     :codex ["WLFRD" "MNG"]
     :match false}])

(deftest mra-comparison-test
  (is (= (test-results 0) (mra-comparison "Byrne" "Boern")))
  (is (= (test-results 1) (mra-comparison "Smith" "Smyth")))
  (is (= (test-results 2) (mra-comparison "Catherine" "Kathryn")))
  (is (= (test-results 3) (mra-comparison "Wilfred" "Manning"))))
