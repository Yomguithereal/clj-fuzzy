;; -------------------------------------------------------------------
;; clj-fuzzy White Similarity Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.white-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.white :refer :all]))

(deftest similarity-test
  (is (= 1.0 (similarity "healed" "healed")))
  (is (= 0.8 (similarity "healed" "sealed")))
  (is (= 0.5454545454545454 (similarity "healed" "healthy")))
  (is (= 0.4444444444444444 (similarity "healed" "heard")))
  (is (= 0.4 (similarity "healed" "herded")))
  (is (= 0.25 (similarity "healed" "help")))
  (is (= 0.0 (similarity "healed" "sold"))))
