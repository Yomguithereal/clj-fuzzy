;; -------------------------------------------------------------------
;; clj-fuzzy Lancaster Stemming
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   version: 0.1
;;
(ns clj-fuzzy.lancaster
  (:use [clj-fuzzy.helpers :only [re-test?
                                  clean-non-alphabetical]]))

;; Rules
(def ^:private rules-per-letter
  {\a ['("ia" "" :intact) '("a" "" :intact)]
   \b ['("bb" "b" :stop)]
   \c ['("ytic" "ys" :stop) '("ic" "" :continue) '("nc" "nt" :continue)]
   \d ['("dd" "d" :stop) '("ied" "y" :continue) '("ceed" "cess" :stop) '("eed" "ee" :stop) '("ed" "" :continue) '("hood" "" :continue)]
   \e ['("e" "" :continue)]
   \f ['("lief" "liev" :stop) '("if" "" :continue)]
   \g ['("ing" "" :continue) '("iag" "y" :stop) '("ag" "" :continue) '("gg" "g" :stop)]
   \h ['("th" "" :intact) '("guish" "ct" :stop) '("ish" "" :continue)]
   \i ['("i" "" :intact) '("i" "y" :continue)]
   \j ['("ij" "id" :stop) '("fuj" "fus" :stop) '("uj" "ud" :stop) '("oj" "od" :stop) '("hej" "her" :stop) '("verj" "vert" :stop) '("misj" "mit" :stop) '("nj" "nd" :stop) '("j" "s" :stop)]
   \l ['("ifiabl" "" :stop) '("iabl" "y" :stop) '("abl" "" :continue) '("ibl" "" :stop) '("bil" "bl" :continue) '("cl" "c" :stop) '("iful" "y" :stop) '("ful" "" :continue) '("ul" "" :stop) '("ial" "" :continue) '("ual" "" :continue) '("al" "" :continue) '("ll" "l" :stop)]
   \m ['("ium" "" :stop) '("um" "" :intact) '("ism" "" :continue) '("mm" "m" :stop)]
   \n ['("sion" "j" :continue) '("xion" "ct" :stop) '("ion" "" :continue) '("ian" "" :continue) '("an" "" :continue) '("een" "" :protect) '("en" "" :continue) '("nn" "n" :stop)]
   \p ['("ship" "" :continue) '("pp" "p" :stop)]
   \r ['("er" "" :continue) '("ear" "" :protect) '("ar" "" :stop) '("ior" "" :continue) '("or" "" :continue) '("ur" "" :continue) '("rr" "r" :stop) '("tr" "t" :continue) '("ier" "y" :continue)]
   \s ['("ies" "y" :continue) '("sis" "s" :stop) '("is" "" :continue) '("ness" "" :continue) '("ss" "" :protect) '("ous" "" :continue) '("us" "" :intact) '("s" "" :continue) '("s" "" :stop)]
   \t ['("plicat" "ply" :stop) '("at" "" :continue) '("ment" "" :continue) '("ent" "" :continue) '("ant" "" :continue) '("ript" "rib" :stop) '("orpt" "orb" :stop) '("duct" "duc" :stop) '("sumpt" "sum" :stop) '("cept" "ceiv" :stop) '("olut" "olv" :stop) '("sist" "" :protect) '("ist" "" :continue) '("tt" "t" :stop)]
   \u ['("iqu" "" :stop) '("ogu" "og" :stop)]
   \v ['("siv" "j" :continue) '("eiv" "" :protect) '("iv" "" :continue)]
   \y ['("bly" "bl" :continue) '("ily" "y" :continue) '("ply" "" :protect) '("ly" "" :continue) '("ogy" "og" :stop) '("phy" "ph" :stop) '("omy" "om" :stop) '("opy" "op" :stop) '("ity" "" :continue) '("ety" "" :continue) '("lty" "l" :stop) '("istry" "" :stop) '("ary" "" :continue) '("ory" "" :continue) '("ify" "" :stop) '("ncy" "nt" :continue) '("acy" "" :continue)]
   \z ['("iz" "" :continue) '("yz" "ys" :stop)]})

;; Utilities
(defn- vowel?
  [string]
  (re-test? #"[aeiouy]" (str string)))

(defn- acceptable?
  [stem]
  (if (vowel? (first stem))
    (> (count stem) 1)
    (and (> (count stem) 2)
         (vowel? stem))))

(defn apply-letter-rules
  [stem rules intact]
  (loop [i 0]
    (let [rule (get rules i '())
          match (first rule)
          replacement (second rule)
          kind (last rule)
          breakpoint (- (count stem) (count match))]
      (cond (nil? (seq rule)) [false stem]
            (and (not intact)
                 (= kind :intact)) (recur (inc i))
            (or (neg? breakpoint)
                (not= (subs stem breakpoint) match)) (recur (inc i))
            (= kind :protect) [false stem]
            :else (let [new-stem (str (subs stem 0 breakpoint) replacement)]
                    (cond (not (acceptable? new-stem)) (recur (inc i))
                          (= kind :continue) [true new-stem]
                          :else [false new-stem]))))))

(defn- apply-rules
  "Applying the above rules to a correctly prepped [string]."
  [string]
  (loop [stem string
         intact true]
    (if-let [rules (rules-per-letter (last stem))]
      (let [[continue new-stem] (apply-letter-rules stem rules intact)]
        (if continue
          (recur new-stem false)
          new-stem))
      stem)))

;; Main
(defn- prep-string
  "Prepare the given [string] for the stemmer by stripping it of any
  non-alphabetical characters and applying a lowercase transformation."
  [string]
  (-> (clean-non-alphabetical string)
      (clojure.string/lower-case)))

(defn stem
  "Stem the given [word] with the Lancaster stemming algorithm."
  [word]
  (apply-rules (prep-string word)))
