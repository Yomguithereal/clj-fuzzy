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

(deftest stem-test
  (is (= "work" (stem "worker"))))
