;; -------------------------------------------------------------------
;; clj-fuzzy NYSIIS Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.nysiis-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.nysiis :refer :all]))

(deftest nysiis-test
  (is (= "ANDR" (original "Andrew")))
  (is (= "RABARTSAN" (original "Robertson")))
  (is (= "NALAN" (original "Nolan")))
  (is (= "LASXV" (original "Louis XVI")))
  (is (= "CAS" (original "Case")))
  (is (= "MCLAGLAN" (original "Mclaughlin")))
  (is (= "MCANSY" (original "Mackenzie"))))
