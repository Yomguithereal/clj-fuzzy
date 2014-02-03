;; -------------------------------------------------------------------
;; clj-fuzzy Cologne Phonetic
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.cologne
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [clean-non-alphabetical
                                  distinct-consecutive
                                  batch-replace
                                  in?
                                  not-in?]]))

;; Utilities
(defn- partition-with-previous-and-next
  "Partition a [word] by letters with the previous and next one.
   E.g. DAW will yield ((nil D A) (D A W) (A W nil))."
  [word]
  (map-indexed #(vector (get word (dec %1)) %2 (get word (inc %1))) word))

(defn- germanic-substitutions
  [word]
  (batch-replace word '(#"Ä" "A"
                        #"Ö" "O"
                        #"Ü" "U"
                        #"ß" "SS"
                        #"PH" "F")))

;; Main Process
(defn- prep-word
  "Prepare a [word] before passing it through the Cologne Phonetic
   algorithm."
  [word]
  (-> (clojure.string/upper-case word)
      (germanic-substitutions)
      (clean-non-alphabetical)))

(defn- lookup-DT [following]
  (if (in? following '(\C \S \Z)) 8 2))

(defn- lookup-C [previous following]
  (if (or (and (nil? previous)
               (in? following '(\A \H \K \L \O \Q \R \U \X)))
          (and (in? following '(\A \H \K \O \Q \U \X))
               (not-in? previous '(\S \Z))))
    4
    8))

(defn- lookup-X [previous]
  (if (in? previous '(\C \Q \K)) 8 48))

(defn- lookup-letter
  "Get the Cologne phonetic code for a single [letter]."
  [letter-entity]
  (let [previous  (first letter-entity)
        current   (second letter-entity)
        following (last letter-entity)]
    (cond (in? current '(\A \E \I \O \U \J \Y)) 0
          (= \H current) nil
          (in? current '(\B \P)) 1
          (in? current '(\D \T)) (lookup-DT following)
          (in? current '(\F \V \W)) 3
          (in? current '(\G \K \Q)) 4
          (= \C current) (lookup-C previous following)
          (= \X current) (lookup-X previous)
          (= \L current) 5
          (in? current '(\M \N)) 6
          (= \R current) 7
          (in? current '(\S \Z)) 8
          :else nil)))

(defn process
  "Process the Cologne phonetic code for a single [word]."
  [word]
  (let [pword (partition-with-previous-and-next (prep-word word))]
    (apply str (->> (map lookup-letter pword)
                    (remove nil?)
                    (distinct-consecutive)
                    (remove zero?)))))
