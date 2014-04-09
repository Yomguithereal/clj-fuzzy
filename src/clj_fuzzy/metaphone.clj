;; -------------------------------------------------------------------
;; clj-fuzzy Metaphone
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Inspiration:
;;     https://github.com/threedaymonk/text/blob/master/lib/text/metaphone.rb
;;
(ns clj-fuzzy.metaphone
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [batch-replace clean-non-alphabetical]]))

;; Rules to apply to the given word
(def ^:private rules
  '(#"([bcdfhjklmnpqrstvwxyz])\1+" "$1"
    #"^ae" "E"
    #"^[gkp]n" "N"
    #"^wr" "R"
    #"^x" "S"
    #"^wh" "W"
    #"mb$" "M"
    #"(?!^)sch" "SK"
    #"th" "0"
    #"t?ch|sh" "X"
    #"c(?=ia)" "X"
    #"[st](?=i[ao])" "X"
    #"s?c(?=[iey])" "S"
    #"[cq]" "K"
    #"dg(?=[iey])" "J"
    #"d" "T"
    #"g(?=h[^aeiou])" ""
    #"gn(ed)?" "N"
    #"([^g]|^)g(?=[iey])" "$1J"
    #"g+" "K"
    #"ph" "F"
    #"([aeiou])h(?=\b|[^aeiou])" "$1"
    #"[wy](?![aeiou])" ""
    #"z" "S"
    #"v" "F"
    #"(?!^)[aeiou]+" ""))

;; The metaphone process itself
(defn process
  "Apply the metaphone substitution on one [word]."
  [word]
  (let [s (clojure.string/lower-case (clean-non-alphabetical word))]
    (clojure.string/upper-case (batch-replace s rules))))

