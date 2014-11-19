;; -------------------------------------------------------------------
;; clj-fuzzy Schinke Stemming Tests
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.schinke-test
  (:require [clojure.test :refer :all]
            [clj-fuzzy.schinke :refer :all]))

(def tests
  ['("apparebunt" "apparebu" "apparebi")
   '("aquila" "aquil" "aquila")
   '("colluxisset" "colluxisset" "colluxisse")
   '("deprehendebatur" "deprehendebatur" "deprehendeba")
   '("dexisse" "dexiss" "dexisse")
   '("ducibus" "duc" "ducibu")
   '("ducimus" "ducim" "duci")
   '("elucidatione" "elucidation" "elucidatione")
   '("fratre" "fratr" "fratre")
   '("fratrem" "fratr" "fratre")
   '("fratres" "fratr" "fratre")
   '("fratri" "fratr" "frat")
   '("fratrum" "fratr" "fratru")
   '("legum" "leg" "legu")])

(deftest stem-test
  (doseq [[word noun verb] tests]
    (is (= {:noun noun :verb verb} (stem word)))))
