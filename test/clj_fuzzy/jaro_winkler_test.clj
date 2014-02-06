;; -------------------------------------------------------------------
;; clj-fuzzy Jaro Winkler Distance Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.jaro-winkler-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.jaro-winkler :refer :all]))

(deftest jaro-test
  (is (= 1.0 (jaro "Duane" "Duane")))
  (is (= 0.8222222222222223 (jaro "Dwayne" "Duane")))
  (is (= 0.9444444444444443 (jaro "Martha" "Marhta")))
  (is (= 0.7666666666666666 (jaro "Dixon" "Dicksonx")))
  (is (= 0.4166666666666667 (jaro "Duane" "Freakishlylongstring"))))

(deftest jaro-winkler-test
  (is (= 1.0 (jaro "Duane" "Duane")))
  (is (= 0.8400000000000001 (jaro-winkler "Dwayne" "Duane")))
  (is (= 0.961111111111111 (jaro-winkler "Martha" "Marhta")))
  (is (= 0.8133333333333332 (jaro-winkler "Dixon" "Dicksonx")))
  (is (= 0.4166666666666667 (jaro-winkler "Duane" "Freakishlylongstring"))))
