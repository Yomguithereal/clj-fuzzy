;; -----------------------------------------------------------------------------
;; clj-fuzzy Schinke Stemming
;; -----------------------------------------------------------------------------
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;
;;   Source: http://snowball.tartarus.org/otherapps/schinke/intro.html
;;
;;   Description: The Schinke stemming algorithm aims at computing latin words
;;     and stem them into a name and verb form.
;;
(ns clj-fuzzy.schinke
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [clean-non-alphabetical
                                  batch-replace
                                  re-test?
                                  in?]]))

;; Rules
(def ^:private que-rules
  ["atque" "quoque" "neque" "itaque" "absque" "apsque" "abusque" "adaeque" "adusque" "denique"
   "deque" "susque" "oblique" "peraeque" "plenisque" "quandoque" "quisque" "quaeque"
   "cuiusque" "cuique" "quemque" "quamque" "quaque" "quique" "quorumque" "quarumque"
   "quibusque" "quosque" "quasque" "quotusquisque" "quousque" "ubique" "undique" "usque"
   "uterque" "utique" "utroque" "utribique" "torque" "coque" "concoque" "contorque"
   "detorque" "decoque" "excoque" "extorque" "obtorque" "optorque" "retorque" "recoque"
   "attorque" "incoque" "intorque" "praetorque"])

(def ^:private simple-suffixes
  [#"ibus$" #"ius$"  #"ae$"   #"am$"   #"as$"   #"em$"   #"es$"   #"ia$"
   #"is$"   #"nt$"   #"os$"   #"ud$"   #"um$"   #"us$"   #"a$"    #"e$"
   #"i$"    #"o$"    #"u$"])

(def ^:private verb-suffixes
  ['(#"iuntur$" "i")
   '(#"erunt$" "i")
   '(#"untur$" "i")
   '(#"iunt$" "i")
   '(#"unt$" "i")
   '(#"beris$" "bi")
   '(#"bor$" "bi")
   '(#"bo$" "bi")
   '(#"ero$" "eri")
   '(#"mini$")
   '(#"ntur$")
   '(#"stis$")
   '(#"mur$")
   '(#"mus$")
   '(#"ris$")
   '(#"sti$")
   '(#"tis$")
   '(#"tur$")
   '(#"ns$")
   '(#"nt$")
   '(#"ri$")
   '(#"m$")
   '(#"r$")
   '(#"s$")
   '(#"t$")])

;; Utilities
(defn- convert
  "Converts all occurrences of the letter 'j' to 'i' and 'v' to 'u' in the
   given [stem]."
   [stem]
   (batch-replace stem '(#"j" "i" #"v" "u")))

(defn- handle-que
  "Checks whether the given [stem] ends in -que and check whether it applies to
   whitelist or not."
   [stem]
   (if (and (re-test? #"que$" stem)
            (in? stem que-rules))
    false
    (clojure.string/replace stem #"que$" "")))

(defn- drop-simple-suffixes
  "Drop a first array of popular suffixes in the given [stem]."
  [stem]
  (if-let [rule (some #(when (re-test? % stem) %) simple-suffixes)]
    (clojure.string/replace stem rule "")
    stem))

(defn- drop-verb-suffixes
  "Drop a second array of popular suffixes in the given [stem] and apply a
   replacement in some cases."
  [stem]
  (if-let [rule (some #(when (re-test? (first %) stem) %) verb-suffixes)]
    (let [match (first rule)
          replacement (or (second rule) "")]
      (clojure.string/replace stem match replacement))
    stem))

(defn- prep-word
  "Prepare a [word] before its pass through the stemming algorithm."
  [word]
  (-> (clojure.string/lower-case word)
      (clean-non-alphabetical)
      (convert)))

;; Main functions
(defn stem
  "Stem the given latin [word]."
  [word]
  (let [pword (prep-word word)]
    (if-let [stem (handle-que pword)]
      (let [simple-suffix-stem (drop-simple-suffixes stem)
            verb-suffix-stem (drop-verb-suffixes stem)]
        {:noun (if (> (count simple-suffix-stem) 1) simple-suffix-stem stem)
         :verb (if (> (count verb-suffix-stem) 1) verb-suffix-stem stem)})
      {:noun pword
       :verb pword})))
