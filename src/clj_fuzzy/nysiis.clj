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
                clean-non-alphabetical
                eat]]))

(def ^:private patterns
  {:original
   {:first  '(#"JR$" ""
              #"SR$" ""
              #"^MAC" "MCC"
              #"^KN" "NN"
              #"^K" "C"
              #"^(PH|PF)" "FF"
              #"^SCH" "SSS"
              #"(EE|IE)$" "Y"
              #"(DT|RT|RD|NT|ND)$" "D")

    :second '(#"EV" "AF"
              #"[EIOU]" "A"
              #"Q" "G"
              #"Z" "S"
              #"(M|KN)" "N"
              #"K" "C"
              #"SCH" "SSS"
              #"PH" "FF"
              #"([^A])H" "$1"
              #"(.)H[^A]" "$1"
              #"AW" "A"
              #"S$" ""
              #"AY$" "Y"
              #"A$" "")}
   :refined
   {:first  '(#"JR$" ""
              #"SR$" ""
              #"(S|Z)$" ""
              #"MAC" "MC"
              #"PH" "F"
              #"IX$" "IC"
              #"EX$" "EC"
              #"(YE|EE|IE)" "Y"
              #"(DT|RT|RD|NT|ND)$" "D"
              #"(.+)EV" "$1EF")

    :second '(#"([AEIOU]+)W" "$1"
              #"[EIOU]" "A"
              #"AA+" "A"
              #"GHT" "GT"
              #"DG" "G"
              #"PH" "F"
              #"(.+)HA" "$1A"
              #"A+H" "A"
              #"KN" "N"
              #"K" "C"
              #"(.+)M" "$1N"
              #"(.+)Q" "$1G"
              #"(SH|SCH)" "S"
              #"YW" "Y"
              #"(.+)Y(.+)" "$1A$2"
              #"WR" "R"
              #"(.+)Z" "$1S"
              #"AY$" "Y"
              #"A+$" ""
              #"^\w" "")}})

(defn- prep-string
  "Prepare the [string] before its NYSIIS encoding."
  [string]
  (-> (s/upper-case string) (s/trim) (clean-non-alphabetical)))

(defn- first-step
  "Compute the first step of the NYSIIS encoding for a [string]."
  [string patterns]
  (batch-replace string patterns))

(defn- second-step
  "Compute the second and last step of the NYSIIS encoding for a [string]."
  [string patterns]
  (distinct-consecutive (batch-replace string patterns)))

(defn- get-patterns
  "Dynamically get the desired set of patterns according to a [method] and an [order]."
  [method order]
  ((patterns method) order))

(defn- create-encoding-fn
  "Create an encoding function according to the desired [method]."
  [method]
  (fn [string]
    (let [first-encoding (first-step (prep-string string) (get-patterns method :first))]
      (apply str (concat (take 1 first-encoding)
                         (second-step (if (= method :original) (eat first-encoding) first-encoding)
                                      (get-patterns method :second)))))))

(def ^:private encoding-functions
  {:original (create-encoding-fn :original)
   :refined (create-encoding-fn :refined)})

(defn process
  "Compute the NYSIIS encoding of a [string] following the desired [method]."
  ([string]
    ((encoding-functions :original) string))
  ([string method]
    ((encoding-functions (keyword method)) string)))
