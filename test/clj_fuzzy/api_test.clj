;; -------------------------------------------------------------------
;; clj-fuzzy Public API Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.api-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.phonetics :as p]
            [clj-fuzzy.metrics :as m]
            [clj-fuzzy.stemmers :as s]))

(deftest phonetics-test
  (is (= "TSKRMNXN" (p/metaphone "discrimination")))
  (is (= "A261" (p/soundex "Ashcroft")))
  (is (= "ANDARSAN" (p/nysiis "Anderson")))
  (is (= "ANRKSN1111" (p/caverphone "Henricsson")))
  (is (= "CTHRN" (p/mra-codex "Catherine")))
  (is (= ["SM0" "XMT"] (p/double-metaphone "Smith")))
  (is (= "65752682" (p/cologne "Müller-Lüdenscheidt"))))

(deftest metrics-test
  (is (= 2 (m/levensthein "book" "back")))
  (is (= 0.8 (m/dice "healed" "sealed")))
  (is (= 4/7 (m/jaccard "night" "nacht")))
  (is (= 3 (m/hamming "2143896" "2233796")))
  (is (= 0.8222222222222223 (m/jaro "Dwayne" "Duane")))
  (is (= 0.8400000000000001 (m/jaro-winkler "Dwayne" "Duane")))
  (is (= {:minimum 4
          :similarity 5
          :codex ["BYRN" "BRN"]
          :match true} (m/mra-comparison "Byrne" "Boern"))))

(deftest stemmers-test
  (is (= "abil" (s/porter "ability"))))
