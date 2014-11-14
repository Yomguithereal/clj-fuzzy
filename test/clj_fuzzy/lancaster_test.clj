;; -------------------------------------------------------------------
;; clj-fuzzy Lancaster Stemming Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.lancaster-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.lancaster :refer :all]))

(def tests
  ['("worker" "work")
   '("marks" "mark")
   '("MARKS" "mark")
   '("living" "liv")
   '("thing" "thing")
   '("ear" "ear")
   '("string" "string")
   '("triplicate" "triply")
   '("classified" "class")
   '("maximum" "maxim")
   '("presumably" "presum")
   '("exceed" "excess")
   '("anguish" "anct")
   '("affluxion" "affluct")
   '("discept" "disceiv")])

(deftest stem-test
  (doseq [pair tests]
    (let [original-word (first pair)
          stemmed-word (second pair)]
      (is (= stemmed-word (stem original-word))))))
