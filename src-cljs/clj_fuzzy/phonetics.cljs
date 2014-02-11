;; -------------------------------------------------------------------
;; clj-fuzzy Phonetics API
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.phonetics
  (:require clj-fuzzy.metaphone
            clj-fuzzy.double-metaphone
            clj-fuzzy.soundex
            clj-fuzzy.nysiis
            clj-fuzzy.caverphone
            clj-fuzzy.match-rating
            clj-fuzzy.cologne))

(def ^:export metaphone clj-fuzzy.metaphone/process)
(def ^:export soundex clj-fuzzy.soundex/process)
(def ^:export nysiis clj-fuzzy.nysiis/original)
(def ^:export caverphone clj-fuzzy.caverphone/process)
(def ^:export mra-codex clj-fuzzy.match-rating/mra-codex)
(def ^:export cologne clj-fuzzy.cologne/process)

;; JavaScript optimizations
(defn ^:export double-metaphone [word]
  (clj->js (clj-fuzzy.double-metaphone/process word)))