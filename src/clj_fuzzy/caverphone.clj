;; -------------------------------------------------------------------
;; clj-fuzzy Caverphone
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;   Revisited version: http://caversham.otago.ac.nz/files/working/ctp150804.pdf
;;
(ns clj-fuzzy.caverphone
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [clean-non-alphabetical
                                  batch-replace]]))

(def ^:private replacements
  {:original
    '(#"e$" ""
      #"^(cou|rou|tou|enou|trou)gh" "$12f"
      #"^gn" "2n"
      #"^mb" "m2"
      #"cq" "2q"
      #"ci" "si"
      #"ce" "se"
      #"cy" "sy"
      #"tch" "2ch"
      #"c" "k"
      #"q" "k"
      #"x" "k"
      #"v" "f"
      #"dg" "2g"
      #"tio" "sio"
      #"tia" "sia"
      #"d" "t"
      #"ph" "fh"
      #"b" "p"
      #"sh" "s2"
      #"z" "s"
      #"^[aieou]" "A"
      #"[aeiou]" "3"
      #"i" "y"
      #"^y3" "Y3"
      #"^y" "A"
      #"y" "3"
      #"3gh3" "3kh3"
      #"gh" "22"
      #"g" "k"
      #"s+" "S"
      #"t+" "T"
      #"p+" "P"
      #"k+" "K"
      #"f+" "F"
      #"m+" "M"
      #"n+" "N"
      #"w3" "W3"
      #"wh3" "Wh3"
      #"w$" "3"
      #"w" "2"
      #"^h" "A"
      #"h" "2"
      #"r3" "R3"
      #"r$" "3"
      #"r" "2"
      #"l3" "L3"
      #"l$" "3"
      #"l" "2"
      #"2" ""
      #"3$" "A"
      #"3" "")
   :revisited
    '(#"e$" "",
      #"^(cou|rou|tou|enou|trou)gh" "$12f",
      #"^gn" "2n",
      #"mb$" "mb",
      #"cq" "2q",
      #"c([iey])" "s\1",
      #"tch" "2ch",
      #"[cqx]" "k",
      #"v" "f",
      #"dg" "2g",
      #"ti([oa])" "si\1",
      #"d" "t",
      #"ph" "fh",
      #"b" "p",
      #"sh" "s2",
      #"z" "s",
      #"^[aeiou]" "A",
      #"[aeiou]" "3",
      #"j" "y",
      #"^y3" "Y3",
      #"^y" "A",
      #"y" "3",
      #"3gh3" "3kh3",
      #"gh" "22",
      #"g" "k",
      #"s+" "S",
      #"t+" "T",
      #"p+" "P",
      #"k+" "K",
      #"f+" "F",
      #"m+" "M",
      #"n+" "N",
      #"w3" "W3",
      #"wh3" "Wh3",
      #"w$" "3",
      #"w" "2",
      #"^h" "A",
      #"h" "2",
      #"r3" "R3",
      #"r$" "3",
      #"r" "2",
      #"l3" "L3",
      #"l$" "3",
      #"l" "2",
      #"2" "",
      #"3$" "A",
      #"3" "")})

(defn- prep-word
  "Prep a [word] for its passage through the Caverphone algorithm."
  [word]
  (clean-non-alphabetical (clojure.string/lower-case word)))

(defn- pad [word] (subs (str word (apply str (repeat 10 "1"))) 0 10))

(defn- apply-algorithm
  [word method]
  (let [pword (prep-word word)]
    (pad (batch-replace pword (replacements method)))))

(defn process
  "Apply the Caverphone algorithm to a [word] following the wanted [method]."
  ([word]
    (apply-algorithm word :original))
  ([word method]
    (apply-algorithm word method)))
