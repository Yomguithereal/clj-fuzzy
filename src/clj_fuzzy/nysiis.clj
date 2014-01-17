;; -------------------------------------------------------------------
;; clj-fuzzy NYSIIS
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.nysiis
  (:require [clojure.string :as s])
  (:use [clj-fuzzy.helpers
         :only [batch-replace
                distinct-consecutive
                clean-non-alphabetical]]))

(def ^:private original-first-patterns
  '(#"JR$" ""
    #"SR$" ""
    #"^MAC" "MCC"
    #"^KN" "NN"
    #"^K" "C"
    #"^(PH|PF)" "FF"
    #"^SCH" "SSS"
    #"(EE|IE)$" "Y"
    #"(DT|RT|RD|NT|ND)$" "D"))

(def ^:private original-second-patterns
  '(#"EV" "AF"
    #"[AEIOU]" "A"
    #"Q" "G"
    #"Z" "S"
    #"(M|KN)" "N"
    #"K" "C"
    #"SCH" "SSS"
    #"PH" "FF"
    #"([^AEIOU])H" "$1"
    #"(.)H[^AEIOU]" "$1"
    #"AW" "A"
    #"S$" ""
    #"AY$" "Y"
    #"A$" ""))

(defn- prep-string
  "Prepare the [string] before its NYSIIS encoding."
  [string]
  (-> (s/upper-case string) (s/trim) (clean-non-alphabetical)))

(defn- first-step [string]
  (batch-replace string original-first-patterns))

(defn- second-step [string]
  (apply str
    (distinct-consecutive
      (batch-replace (apply str (drop 1 string)) original-second-patterns))))

(defn original
  "Compute the NYSIIS encoding of a [string] following the original method."
  [string]
  (let [first-encoding (first-step (prep-string string))]
    (str (first first-encoding) (second-step first-encoding))))
