;; -------------------------------------------------------------------
;; clj-fuzzy Phonetics API
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.phonetics
  (:require [clj-fuzzy.metaphone :only [process]]
            [clj-fuzzy.double-metaphone :only [process]]
            [clj-fuzzy.soundex :only [process]]
            [clj-fuzzy.nysiis :only [original]]
            [clj-fuzzy.caverphone :only [process]]
            [clj-fuzzy.match-rating :only [mra-codex]]))

(def metaphone clj-fuzzy.metaphone/process)
(def double-metaphone clj-fuzzy.double-metaphone/process)
(def soundex clj-fuzzy.soundex/process)
(def nysiis clj-fuzzy.nysiis/original)
(def caverphone clj-fuzzy.caverphone/process)
(def mra-codex clj-fuzzy.match-rating/mra-codex)
