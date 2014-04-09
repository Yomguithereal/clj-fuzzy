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

(def refined (fn [string] (process string :refined)))

(deftest nysiis-test
  (is (= "ANDR" (process "Andrew")))
  (is (= "RABARTSAN" (process "Robertson")))
  (is (= "NALAN" (process "Nolan")))
  (is (= "LASXV" (process "Louis XVI")))
  (is (= "CAS" (process "Case")))
  (is (= "MCLAGLAN" (process "Mclaughlin")))
  (is (= "AWAL" (process "Awale")))
  (is (= "AAGAR" (process "Aegir")))
  (is (= "LANDGRAN" (process "Lundgren")))
  (is (= "FFALBAD" (process "Philbert")))
  (is (= "HARY" (process "Harry")))
  (is (= "MCANSY" (process "Mackenzie"))))

(deftest nysiis-refined-test
  (is (= "ANDR" (refined "Andrew")))
  (is (= "RABARTSAN" (refined "Robertson")))
  (is (= "NALAN" (refined "Nolan")))
  (is (= "LASXV" (refined "Louis XVI")))
  (is (= "CAS" (refined "Case")))
  (is (= "MCLAGHLAN" (refined "Mclaughlin")))
  (is (= "AL" (refined "Awale")))
  (is (= "AGAR" (refined "Aegir")))
  (is (= "LANGRAN" (refined "Lundgren")))
  (is (= "FALBAD" (refined "Philbert")))
  (is (= "HARY" (refined "Harry")))
  (is (= "MCANSY" (refined "Mackenzie"))))
