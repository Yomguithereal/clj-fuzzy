;; -------------------------------------------------------------------
;; clj-fuzzy Sorensen Dice Coefficient Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.dice-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.dice :refer :all]))

(deftest coefficient-test
  (is (= 1.0 (coefficient "healed" "healed")))
  (is (= 0.8 (coefficient "healed" "sealed")))
  (is (= 0.5454545454545454 (coefficient "healed" "healthy")))
  (is (= 0.4444444444444444 (coefficient "healed" "heard")))
  (is (= 0.4 (coefficient "healed" "herded")))
  (is (= 0.25 (coefficient "healed" "help")))
  (is (= 0.0 (coefficient "healed" "sold")))
  (is (= 1.0 (coefficient "tomato" "tomato"))))
