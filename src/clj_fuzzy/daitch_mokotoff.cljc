;; -------------------------------------------------------------------
;; clj-fuzzy Daitch-Mokotoff Soundex
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.daitch-mokotoff
  (:require clojure.string))

;; Coding table
(def ^:private table
  [[#"^(AI|AJ|AY)" \0   \1   nil]
   [#"^AU"         \0   \7   nil]
   [#"^Ą"          nil nil [\6 nil]]
   [#"^A"          \0   nil nil]
   [#"^B"          \7   \7   \7]])

;; Utilities
(defn- pad [word] (subs (apply str word (repeat 5 "0")) 0 6))

(defn- compact
  "Drop the spaces of the given [word]."
  [word]
  (clojure.string/replace word #"\s+" ""))

(defn- treat
  "Apply some transformations to the given [word] before its passage into the
   algorithm."
  [word]
  (compact word))

;; Main process
(defn apply-rule
  "Apply a single [rule] to the current [code]."
  [code rule]
  (clojure.string/replace code (first rule) (or (second rule) "")))

;; TODO: treat -> process -> take 6 -> pad
(defn process
  "Compute the Daitch-Mokotoff soundex code for the given [word]."
  [word]
  (reduce apply-rule word table))
