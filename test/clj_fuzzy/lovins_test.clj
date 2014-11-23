;; -----------------------------------------------------------------------------
;; clj-fuzzy Lovins Stemmer Tests
;; -----------------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.lovins-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.lovins :refer :all]))

(deftest stem-test
  (is (= "nat" (stem "nationally")))
  (is (= "sit" (stem "sitting")))
  (is (= "matric" (stem "matrix")))
  (is (= "matric" (stem "matrices")))
  (is (= "rub" (stem "rubbing")))
  (is (= "rub" (stem "rubb")))
  (is (= "belief" (stem "believe")))
  (is (= "consum" (stem "consumption")))
  (is (= "induc" (stem "induction")))
  (is (= "absorb" (stem "absorption")))
  (is (= "recur" (stem "recursive")))
  (is (= "administer" (stem "administrate")))
  (is (= "parameter" (stem "parametric")))
  (is (= "dissolut" (stem "dissolved")))
  (is (= "angl" (stem "angular")))
  (is (= "vibic" (stem "vibex")))
  (is (= "indic" (stem "index")))
  (is (= "apic" (stem "apex")))
  (is (= "cortic" (stem "cortex")))
  (is (= "anthrac" (stem "anthrax")))
  (is (= "persuas" (stem "persuade")))
  (is (= "evas" (stem "evade")))
  (is (= "dec" (stem "decide")))
  (is (= "el" (stem "elide")))
  (is (= "der" (stem "deride")))
  (is (= "expans" (stem "expand")))
  (is (= "defens" (stem "defend")))
  (is (= "respons" (stem "respond")))
  (is (= "collus" (stem "collusion")))
  (is (= "obstrus" (stem "obstrusion")))
  (is (= "adhes" (stem "adhesion")))
  (is (= "remis" (stem "remit")))
  (is (= "extens" (stem "extent")))
  (is (= "convers" (stem "converted")))
  (is (= "parenthes" (stem "parenthetic")))
  (is (= "analys" (stem "analytic")))
  (is (= "analys" (stem "analyzed"))))
