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

(def lancaster clj-fuzzy.lancaster/stem)
(def porter clj-fuzzy.porter/stem)
(def schinke clj-fuzzy.schinke/stem)
