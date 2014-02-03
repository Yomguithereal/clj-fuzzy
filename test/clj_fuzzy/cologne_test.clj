;; -------------------------------------------------------------------
;; clj-fuzzy Cologne Phonetic Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.cologne-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.cologne :refer :all]))

(deftest process-test
  (is (= "65752682" (process "Müller-Lüdenscheidt")))
  (is (= "17863" (process "Breschnew")))
  (is (= "3412" (process "Wikipedia")))
  (is (not= (process "Meyer") (process "Müller")))
  (is (= (process "Meyer") (process "Mayr"))))
