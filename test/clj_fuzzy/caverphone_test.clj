;; -------------------------------------------------------------------
;; clj-fuzzy Caverphone Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.caverphone-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.caverphone :refer :all]))

(def process-revisited (fn [word] (process word :revisited)))

(deftest process-test
  (is (= "ANRKSN1111" (process "Henrichsen")))
  (is (= "ANRKSN1111" (process "Henricsson")))
  (is (= "ANRKSN1111" (process "Henriksson")))
  (is (= "ANRKSN1111" (process "Hinrichsen")))
  (is (= "ASKKA11111" (process "Izchaki")))

  (is (= "MKLFTA1111" (process "Maclaverty")))
  (is (= "MKLFTA1111" (process "Macleverty")))
  (is (= "MKLFTA1111" (process "Mcclifferty")))
  (is (= "MKLFTA1111" (process "Mclafferty")))
  (is (= "MKLFTA1111" (process "Mclaverty")))

  (is (= "SLKMP11111" (process "Slocomb")))
  (is (= "SLKMP11111" (process "Slocombe")))
  (is (= "SLKMP11111" (process "Slocumb")))

  (is (= "WTLM111111" (process "Whitlam"))))

(deftest process-revisited-test
  (is (= "PTA1111111" (process-revisited "Peter")))
  (is (= "ANRKSN1111" (process-revisited "Henrichsen")))
  (is (= "STFNSN1111" (process-revisited "Stevenson"))))
