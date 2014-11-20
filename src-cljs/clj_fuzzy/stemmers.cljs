;; -------------------------------------------------------------------
;; clj-fuzzy Stemmers API
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.stemmers
  (:require clj-fuzzy.lancaster
            clj-fuzzy.porter
            clj-fuzzy.schinke))

(def ^:export lancaster clj-fuzzy.lancaster/stem)
(def ^:export porter clj-fuzzy.porter/stem)

;; JavaScript optimizations
(defn ^:export schinke [word]
  (clj->js (clj-fuzzy.schinke/stem word)))
