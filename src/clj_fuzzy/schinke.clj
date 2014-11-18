;; -----------------------------------------------------------------------------
;; clj-fuzzy Schinke Stemming
;; -----------------------------------------------------------------------------
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;
;;   Source: http://snowball.tartarus.org/otherapps/schinke/intro.html
;;
;;   Description: The Schinke stemming algorithm aims at compute latin words
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

(def ^:private first-suffixes
  [#"ibus$" #"ius$"  #"ae$"   #"am$"   #"as$"   #"em$"   #"es$"   #"ia$"
   #"is$"   #"nt$"   #"os$"   #"ud$"   #"um$"   #"us$"   #"a$"    #"e$"
   #"i$"    #"o$"    #"u$"])

(def ^:private second-suffixes
  ['(#"iuntur$" "erunt")
   '(#"beris$" "bor")
   '(#"erunt$")
   '(#"untur$" "iunt")
   '(#"iunt$")
   '(#"mini$")
   '(#"ntur$")
   '(#"stis$")
   '(#"bor$")
   '(#"ero$" "eri")
   '(#"mur$")
   '(#"mus$")
   '(#"ris$")
   '(#"sti$")
   '(#"tis$")
   '(#"tur$")
   '(#"unt$" "i")
   '(#"bo$" "bi")
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
    stem))

(defn- drop-first-suffixes
  "Drop a first array of popular suffixes in the given [stem]."
  [stem]
  (if-let [rule (some #(re-test? % stem) first-suffixes)]
    (clojure.string/replace stem rule "")
    stem))

(defn- drop-second-suffixes
  "Drop a second array of popular suffixes in the given [stem] and apply a
   replacement in some cases."
  [stem]
  (if-let [rule (some #(re-test? % stem) second-suffixes)]
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
  (if-let [stem (handle-que word)]
    (let [first-suffix-stem (drop-first-suffixes stem)
          second-suffix-stem (drop-second-suffixes first-suffix-stem)]
      ({:noun (if (> (count first-suffix-stem) 1) first-suffix-stem word)
        :verb (if (> (count second-suffix-stem) 1) second-suffix-stem word)}))
    {:noun word
     :verb word}))
