;; -------------------------------------------------------------------
;; clj-fuzzy Double Metaphone
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.double-metaphone
  (:use clj-fuzzy.helpers))

;; Utilities
(defn slavo-germanic?
  "Is the string kinda slavo-germanic?"
  [string]
  (re-test? #"W|K|CZ|WITZ" string))

(defn vowel?
  "Is the string a vowel?"
  [string]
  (re-test? #"^A|E|I|O|U|Y$" string))

;; Lookup
(defn lookup-vowel [pos]
  (if (= pos 0)
    [:A :A 1]
    [nil nil 1]))

(defn lookup-B [string pos]
  [:P :P (if (= "B" (slice string (+ pos 1) 1)) 2 1)])

(defn lookup-C-cedilla []
  [:S :S 1])

(defn lookup-C [string pos]
  (cond

    ;; First Case
    (and
      (> pos 1)
      (not (vowel? (slice string (- pos 2) 1)))
      (= "ACH" (slice string (- pos 1) 3))
      (not= "I" (slice string (+ pos 2) 1))
      (or
        (not= "E" (slice string (+ pos 2) 1))
        (re-test? #"^(B|M)ACHER$" (slice string (- pos 2) 6))))
     [:K :K 2]

     ;; Second Case
     (and
      (= pos 0)
      (= "CAESAR" (slice string pos 6)))
     [:S :S 2]

     ;; Third Case
     (= "CHIA" (slice string pos 4))
     [:K :K 2]

     ;; Fourth Case
     (= "CH" (slice string pos 2))
     (cond

      ;; Subcase
      (and (= 0 pos) (= "CHAE" (slice string pos 4)))
      [:K :X 2]

      ;; Subcase
      (and
        (= 0 pos)
        (or
          (in? (slice string (+ 1 pos) 5) '("HARAC" "HARIS"))
          (in? (slice string (+ 1 pos) 3) '("HOR" "HYM" "HIA" "HEM")))
        (not= "CHORE" (slice string 0 5)))
      [:K :K 2]

      ;; Subcase
      (or
        (in? (slice string 0 4) '("VAN " "VON "))
        (= "SCH" (slice string 0 3))
        (in? (slice string (- 2 pos) 6) '("ORCHES" "ARCHIT" "ORCHID"))
        (in? (slice string (+ 2 pos) 1) '("T", "S"))
        (and
          (or (not= pos 0) (in? (slice string (- 1 pos) 1) '("A" "O" "U" "E")))
          (in? (slice string (+ 2 pos) 1) '("L" "R" "N" "M" "B" "H" "F" "V" "W" " "))))
      [:K :K 2]

      ;; Subcase
      (= 0 pos)
      [(if (= "MC" (slice string 0 2)) "K" "X") "K" 2]

      ;; Fallback
      :else [:X :X 2])

    ;; Fifth Case
    (and
      (= "CZ" (slice string pos 2))
      (not= "WICZ" (slice string (- 2 pos) 4)))
    [:S :X 2]

    ;; Sixth Case
    (= "CIA" (slice string (+ 1 pos) 3))
    [:X :X 3]

    ;; Seventh Case
    (and
      (= "CC" (slice string pos 2))
      (not
        (and
          (= pos 1)
          (= "M" (slice string 0 1)))))
    (if
      (and
        (re-test? #"^I|E|H$" (slice string (+ 2 pos 1)))
        (not= "HU" (slice string (+ 2 pos) 2)))
      (if
        (or
          (and (= 1 pos) (= "A" (slice string (- 1 pos) 1)))
          (re-test? #"^UCCE(E|S)" (slice string (- 1 pos) 5)))
        [:KS :KS 3]
        [:X :X 3])
      [:K :K 2])

    ;; Eight Case
    (re-test? #"^C(K|G|Q)$" (slice string pos 2))
    [:K :K 2]

    ;; Ninth Case
    (re-test? #"^C(I|E|Y)$" (slice string pos 2))
    [:S (if (re-test? #"^CI(O|E|A)$" (slice string pos 3)) :X :S) 2]

    ;; Global Fallback
    :else (if (re-test? #"^ (C|Q|G)$" (slice string (+ 1 pos) 2))
            [:K :K 3]
            [:K :K
              (if
                (and
                  (re-test? #"^C|K|Q$" (slice string (+ 1 pos) 1))
                  (in? (slice string (+ 1 pos) 2) '("CE" "CI")))
                2 
                1)])))

(defn lookup-D [string pos]
  (if (= "DG" (slice string pos 2))
    (if (re-test? #"^I|E|Y$" (slice string (+ 2 pos) 1))
      [:J :J 3]
      [:TK :TK 2])
    [:T :T (if (re-test? #"^D(T|D)$" (slice string pos 2)) 2 1)]))

(defn lookup-F [string pos]
  [:F :F (if (= "F" (slice string (+ 1 pos) 1)) 2 1)])

;; Do a map, this is more elegant and simple
(defn lookup
  "The actual letter worker."
  [string pos length end]
  (case (slice string pos 1)
    vowel? (lookup-vowel pos)
    "B" (lookup-B string pos)
    "Ç" (lookup-C-cedilla)
    "C" (lookup-C string pos)
    "D" (lookup-D string pos)
    "F" (lookup-F string pos)))

;; Execution
(defn phonetic-code
  "Main package function."
  [string]
  string)
