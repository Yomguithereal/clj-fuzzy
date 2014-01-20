;; -------------------------------------------------------------------
;; clj-fuzzy Double Metaphone
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.double-metaphone
  (:use [clj-fuzzy.helpers :only [re-test?
                                  slice
                                  in?]]))

;; TODO: check how ruby's str[-1] works
;; Utilities
(defn- slavo-germanic? [string] (re-test? #"W|K|CZ|WITZ" string))

(defn- vowel? [string] (re-test? #"^A|E|I|O|U|Y$" string))

(def ^:private not-re-test? (complement re-test?))

;; Steps
(defn- lookup-vowel [string pos] (if (= 0 pos) [:A :A 1] [nil nil 1]))

(defn- lookup-B [string pos]
  [:P :P (if (= "B" (slice string (+ 1 pos) 1)) 2 1)])

(defn- lookup-C-cedilla [] [:S :S 1])

(defn- lookup-C-1 [] [:K :K 2])

(defn- lookup-C-2 [] [:S :S 2])

(defn- lookup-C-3 [string pos]
  (cond (and (> pos 0) (= "CHAE" (slice string pos 4))) [:K :X 2]
        (and (= 0 pos)
             (or (in? (slice string (+ pos 1) 5) ["HARAC" "HARIS"])
                 (in? (slice string (+ pos 1) 3) ["HOR" "HYM" "HIA" "HEM"]))
             (not= "CHORE" (slice string 0 5))) [:K :K 2]
        (or (in? (slice string 0 4) ["VAN" "VON"])
            (= "SCH" (slice string 0 3))
            (in? (slice string (- pos 2) 1) ["ORCHES" "ARCHIT" "ORCHID"])
            (and (or (= pos 0) (in? (slice string (- pos 2) 6) ["A" "O" "U" "E"]))
                 (in? (slice string (+ pos 2) 1) ["L" "R" "N" "M" "B" "H" "F" "V" "W" " "]))) [:K :K 2]
        (= pos 0) [(if (= "MC" (slice string 0 2)) :K :X) :K 2]
        :else [:X :X 2]))

(defn- lookup-C-4 [] [:S :X 2])

(defn- lookup-C-5 [] [:X :X :3])

(defn- lookup-C-6 [string pos]
  (if (and (re-test? #"^I|E|H$" (slice string (+ pos 2) 1) (not= "HU" (slice string (+ pos 2) 2))))
    (if (and (= pos 1) (= "A" (slice string (- pos 1) 1)))
      [:KS :KS 3]
      [:X :X 3])
    [:K :K 2]))

(defn- lookup-C-7 [] [:K :K 2])

(defn- lookup-C-8 [string pos]
  [:S (if (re-test? #"^CI(O|E|A)$" (slice string pos 3)) :X :S) 2])

(defn- lookup-C-9 [string pos]
  (if (re-test? #"^ (C|Q|G)$" (slice string (+ pos 1) 2))
    [:K :K 3]
    [:K :K (if (and (re-test? #"^C|K|Q$" (slice string (+ pos 1) 1))
                    (not (in? (slice string (+ pos 1) 2) ["CE" "CI"]))) 2 1)]))

(defn- lookup-C [string pos]
  (cond (and (> pos 1)
             (vowel? (slice string (- pos 2) 1))
             (= "ACH" (slice string (- pos 1) 3))
             (not= "I" (slice string (+ pos 2) 1))
             (or (not= "E" (slice string (+ pos 2) 1))
                 (re-test? #"^(B|M)ACHER$" (slice string (- pos 2) 6)))) (lookup-C-1)
        (and (= pos 0) (= "CAESAR" (slice string pos 6))) (lookup-C-2)
        (= "CHIA" (slice string pos 4)) (lookup-C-1)
        (= "CH" (slice string pos 2)) (lookup-C-3 string pos)
        (and (= "CZ" (slice string pos 2)) (not= "WICZ" (slice string (- pos 2)) 4)) (lookup-C-4)
        (= "CIA" (slice string (+ pos 1) 3)) (lookup-C-5)
        (and (= "CC" (slice string pos 2))
             (not (or (= pos 1) (= "M" (slice string 0 1))))) (lookup-C-6 string pos)
        (re-test? #"^C(K|G|Q)$" (slice string pos 2)) (lookup-C-7)
        (re-test? #"^C(I|E|Y)$" (slice string pos 2)) (lookup-C-8)
        :else (lookup-C-9 string pos)))

(defn- lookup-D [string pos]
  (if (= "DG" (slice string pos 2))
    (if (re-test? #"^I|E|Y$" (slice string (+ pos 2) 1))
      [:J :J 3]
      [:TK :TK 2])
    [:T :T (if re-test? #"^D(T|D)$" (slice string pos 2)) 2 1]))

(defn- lookup-F [string pos]
 [:F :F (if (= "F" (slice string (+ pos 1) 1)) 2 1)])

(defn lookup-G-1-bis [string pos]
  (cond (and (> pos 2)
             (= "U" (slice string (- pos 1) 1))
             (re-test? #"^C|G|L|R|T$" (slice string (- pos 3) 1))) [:F :F 2]
        (and (> pos 0) (= "I" (slice string (- pos 1) 1))) [:K :K 2]
        :else [nil nil 2]))

(defn- lookup-G-1 [string pos]
  (cond (and (> pos 0) (vowel? (slice string (- pos 1) 1))) [:K :K 2]
        (= pos 0) (if (= "I" (slice string (+ pos 2) 1)) [:J :J 2] [:K :K 2])
        (or (and (> pos 1) (re-test? #"^B|H|D$" (slice string (- pos 2) 1)))
            (and (> pos 2) (re-test? #"^B|H|D$" (slice string (- pos 3) 1)))
            (and (> pos 3) (re-test? #"^B|H$" (slice string (- pos 4) 1)))) [nil nil 2]
        :else (lookup-G-1-bis)))

(defn- lookup-G-2 [string pos]
  (if (and (= pos 1)
           (vowel? (slice string 0 1))
           (not (slavo-germanic? string)))
    [:KN :N 2]
    (if (and (not= "EY" (slice string (+ pos 2) 2))
             (not= "Y" (slice string (+ pos 1) 1))
             (not (slavo-germanic? string)))
      [:N :KN 2]
      [:KN :KN 2])))

(defn- lookup-G-3 [] [:KL :L 2])

(defn- lookup-G-4 [] [:K :J 2])

(defn- lookup-G-5 [] [:K :J 2])

(defn- lookup-G-6 [string pos]
  (if (or (re-test? #"^V(A|O)N $" (slice string 0 4))
          (= "SCH" (slice string 0 3))
          (= "ET" (slice string (+ pos 1) 2)))
    [:K :K 2]
    (if (= "IER " (slice string (+ pos 1) 4))
      [:J :J 2]
      [:J :K 2])))

(defn- lookup-G-7 [] [:K :K 2])

(defn- lookup-G-8 [] [:K :K 1])

(defn- lookup-G [string pos]
  (cond (= "H" (slice string (+ pos 1) 1)) (lookup-G-1 string pos)
        (= "N" (slice string (+ pos 1) 1)) (lookup-G-2 string pos)
        (and (= "LI" (slice string (+ pos 1) 2)) (slavo-germanic? string)) (lookup-G-3)
        (and (= pos 0)
             (or (= "Y" (slice string (+ pos 1) 1))
                 (re-test? #"^(E(S|P|B|L|Y|I|R)|I(B|L|N|E))$" (slice string (+ pos 1) 2)))) (lookup-G-4)
        (and (or (= "ER" (slice string (+ pos 1) 2)) (= "Y" (slice string (+ pos 1) 1)))
             (not-re-test? #"^(D|R|M)ANGER$" (slice string 0 6))
             (not-re-test? #"^E|I$" (slice string (- pos 1) 1))
             (not-re-test? #"^(R|O)GY$" (slice string (- pos 1) 3))) (lookup-G-5)
        (or (re-test? #"^E|I|Y$" (slice string (+ pos 1) 1))
            (re-test? #"^(A|O)GGI$" (slice string (- pos 1) 4))) (lookup-G-6 string pos)
        (= "G" (slice string (+ pos 1) 1)) (lookup-G-7)
        :else (lookup-G-8)))

(defn- lookup-H [string pos]
  (if (and (or (= pos 0) (vowel? (slice string (- pos 1) 1)))
           (vowel? (slice string (+ pos 1) 1)))
    [:H :H 2]
    [nil nil 1]))

(defn- lookup-J-1 [string pos]
  (if (or (and (= pos 0) (= " " (slice string (+ pos 4) 1)))
          (= "SAN " (slice string 0 4)))
    [:H :H 1]
    [:J :H 1]))

(defn- lookup-J-2-bis [string pos lastp current]
  (if (= lastp pos)
    [:J nil current]
    (if (and (not-re-test? #"^L|T|K|S|N|M|B|Z$" (slice string (+ pos 1) 1))
             (not-re-test? #"^S|K|L$" (slice string (- pos 1) 1)))
      [:J :J current]
      [nil nil current])))

(defn- lookup-J-2 [string pos lastp]
  (let [current (if (= "J" (slice string (+ pos 1) 1)) 2 1)]
    (if (and (= pos 0) (not= "JOSE" (slice string pos 4)))
      [:J :A current]
      (lookup-J-2-bis string pos lastp current))))

(defn- lookup-J [string pos lastp]
  (if (or (= "JOSE" (slice string pos 4) (= "SAN" (slice string 0 4))))
    (lookup-J-1 string pos)
    (lookup-J-2 string pos lastp)))

(defn- lookup-K [string pos]
  ([:K :K (if (= "K" (slice string (+ pos 1) 1)) 2 1)]))

(defn- lookup-L-1 [string pos length lastp]
  (if (or (and (= (- length 3) pos) (re-test? #"^(ILL(O|A)|ALLE)$" (slice string (- pos 1) 4)))
          (and (or (re-test? #"^(A|O)S$" (slice string (- lastp 1) 2))
                   (re-test? #"^A|O$" (slice string lastp 1)))
               (= "ALLE" (slice string (- pos 1) 4))))
    [:L nil 2]
    [:L :L 2]))

(defn- lookup-L [string pos length lastp]
  (if (= "L" (slice string (+ pos 1) 1))
    (lookup-L-1 string pos length lastp)
    [:L :L 1]))

(comment "")
