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

(def metaphone clj-fuzzy.metaphone/process)
(def double-metaphone clj-fuzzy.double-metaphone/process)
(def soundex clj-fuzzy.soundex/process)
(def nysiis clj-fuzzy.nysiis/process)
(def caverphone clj-fuzzy.caverphone/process)
(def mra-codex clj-fuzzy.match-rating/mra-codex)
(def cologne clj-fuzzy.cologne/process)
