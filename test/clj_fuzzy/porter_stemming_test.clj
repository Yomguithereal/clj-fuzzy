;; -------------------------------------------------------------------
;; clj-fuzzy Porter Stemming Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.porter-stemming-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.porter-stemming :refer :all]))

(deftest stem-test
  (is (= "catastroph" (stem "catastrophe")))
  (is (= "anathema" (stem "anathema")))
  (is (= "mathemat" (stem "mathematics")))
  (is (= "adject" (stem "adjective")))
  (is (= "mushroom" (stem "mushroom")))
  (is (= "build" (stem "building")))
  (is (= "spite" (stem "spiteful")))
  (is (= "etern" (stem "eternal")))
  (is (= "exterior" (stem "exterior")))
  (is (= "coffe" (stem "coffee"))))
