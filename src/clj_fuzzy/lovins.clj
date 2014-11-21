;; -----------------------------------------------------------------------------
;; clj-fuzzy Lovins Stemming
;; -----------------------------------------------------------------------------
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;
;;   Source: http://snowball.tartarus.org/algorithms/lovins/stemmer.html
;;
;;   Description: One of the oldest stemming algorithm for the English language
;;     designed by Julie Beth Lovins in 1968.
;;
;;   Note: A vast majority of the algorithm can be improved codewise.
;;
(ns clj-fuzzy.lovins
  (:require [clojure.string])
  (:use [clj-fuzzy.helpers :only [re-test?
                                  batch-replace]]))

;; Endings
(def ^:private endings
  '(#"alistically$" :B #"arizability$" :A #"izationally$" :B

    #"antialness$" :A  #"arisations$" :A  #"arizations$" :A  #"entialness$" :A

    #"allically$" :C   #"antaneous$" :A   #"antiality$" :A   #"arisation$" :A
    #"arization$" :A   #"ationally$" :B   #"ativeness$" :A   #"eableness$" :E
    #"entations$" :A   #"entiality$" :A   #"entialize$" :A   #"entiation$" :A
    #"ionalness$" :A   #"istically$" :A   #"itousness$" :A   #"izability$" :A
    #"izational$" :A

    #"ableness$" :A    #"arizable$" :A    #"entation$" :A    #"entially$" :A
    #"eousness$" :A    #"ibleness$" :A    #"icalness$" :A    #"ionalism$" :A
    #"ionality$" :A    #"ionalize$" :A    #"iousness$" :A    #"izations$" :A
    #"lessness$" :A

    #"ability$" :A     #"aically$" :A     #"alistic$" :B     #"alities$" :A
    #"ariness$" :E     #"aristic$" :A     #"arizing$" :A     #"ateness$" :A
    #"atingly$" :A     #"ational$" :B     #"atively$" :A     #"ativism$" :A
    #"elihood$" :E     #"encible$" :A     #"entally$" :A     #"entials$" :A
    #"entiate$" :A     #"entness$" :A     #"fulness$" :A     #"ibility$" :A
    #"icalism$" :A     #"icalist$" :A     #"icality$" :A     #"icalize$" :A
    #"ication$" :G     #"icianry$" :A     #"ination$" :A     #"ingness$" :A
    #"ionally$" :A     #"isation$" :A     #"ishness$" :A     #"istical$" :A
    #"iteness$" :A     #"iveness$" :A     #"ivistic$" :A     #"ivities$" :A
    #"ization$" :F     #"izement$" :A     #"oidally$" :A     #"ousness$" :A

    #"aceous$" :A      #"acious$" :B      #"action$" :G      #"alness$" :A
    #"ancial$" :A      #"ancies$" :A      #"ancing$" :B      #"ariser$" :A
    #"arized$" :A      #"arizer$" :A      #"atable$" :A      #"ations$" :B
    #"atives$" :A      #"eature$" :Z      #"efully$" :A      #"encies$" :A
    #"encing$" :A      #"ential$" :A      #"enting$" :C      #"entist$" :A
    #"eously$" :A      #"ialist$" :A      #"iality$" :A      #"ialize$" :A
    #"ically$" :A      #"icance$" :A      #"icians$" :A      #"icists$" :A
    #"ifully$" :A      #"ionals$" :A      #"ionate$" :D      #"ioning$" :A
    #"ionist$" :A      #"iously$" :A      #"istics$" :A      #"izable$" :E
    #"lessly$" :A      #"nesses$" :A      #"oidism$" :A

    #"acies$" :A       #"acity$" :A       #"aging$" :B       #"aical$" :A
    #"alist$" :A       #"alism$" :B       #"ality$" :A       #"alize$" :A
    #"allic$" :BB      #"anced$" :B       #"ances$" :B       #"antic$" :C
    #"arial$" :A       #"aries$" :A       #"arily$" :A       #"arity$" :B
    #"arize$" :A       #"aroid$" :A       #"ately$" :A       #"ating$" :I
    #"ation$" :B       #"ative$" :A       #"ators$" :A       #"atory$" :A
    #"ature$" :E       #"early$" :Y       #"ehood$" :A       #"eless$" :A
    #"elity$" :A       #"ement$" :A       #"enced$" :A       #"ences$" :A
    #"eness$" :E       #"ening$" :E       #"ental$" :A       #"ented$" :C
    #"ently$" :A       #"fully$" :A       #"ially$" :A       #"icant$" :A
    #"ician$" :A       #"icide$" :A       #"icism$" :A       #"icist$" :A
    #"icity$" :A       #"idine$" :I       #"iedly$" :A       #"ihood$" :A
    #"inate$" :A       #"iness$" :A       #"ingly$" :B       #"inism$" :J
    #"inity$" :CC      #"ional$" :A       #"ioned$" :A       #"ished$" :A
    #"istic$" :A       #"ities$" :A       #"itous$" :A       #"ively$" :A
    #"ivity$" :A       #"izers$" :F       #"izing$" :F       #"oidal$" :A
    #"oides$" :A       #"otide$" :A       #"ously$" :A

    #"able$" :A        #"ably$" :A        #"ages$" :B        #"ally$" :B
    #"ance$" :B        #"ancy$" :B        #"ants$" :B        #"aric$" :A
    #"arly$" :K        #"ated$" :I        #"ates$" :A        #"atic$" :B
    #"ator$" :A        #"ealy$" :Y        #"edly$" :E        #"eful$" :A
    #"eity$" :A        #"ence$" :A        #"ency$" :A        #"ened$" :E
    #"enly$" :E        #"eous$" :A        #"hood$" :A        #"ials$" :A
    #"ians$" :A        #"ible$" :A        #"ibly$" :A        #"ical$" :A
    #"ides$" :L        #"iers$" :A        #"iful$" :A        #"ines$" :M
    #"ings$" :N        #"ions$" :B        #"ious$" :A        #"isms$" :B
    #"ists$" :A        #"itic$" :H        #"ized$" :F        #"izer$" :F
    #"less$" :A        #"lily$" :A        #"ness$" :A        #"ogen$" :A
    #"ward$" :A        #"wise$" :A        #"ying$" :B        #"yish$" :A

    #"acy$" :A         #"age$" :B         #"aic$" :A         #"als$" :BB
    #"ant$" :B         #"ars$" :O         #"ary$" :F         #"ata$" :A
    #"ate$" :A         #"eal$" :Y         #"ear$" :Y         #"ely$" :E
    #"ene$" :E         #"ent$" :C         #"ery$" :E         #"ese$" :A
    #"ful$" :A         #"ial$" :A         #"ian$" :A         #"ics$" :A
    #"ide$" :L         #"ied$" :A         #"ier$" :A         #"ies$" :P
    #"ily$" :A         #"ine$" :M         #"ing$" :N         #"ion$" :Q
    #"ish$" :C         #"ism$" :B         #"ist$" :A         #"ite$" :AA
    #"ity$" :A         #"ium$" :A         #"ive$" :A         #"ize$" :F
    #"oid$" :A         #"one$" :R         #"ous$" :A

    #"ae$" :A          #"al$" :BB         #"ar$" :X          #"as$" :B
    #"ed$" :E          #"en$" :F          #"es$" :E          #"ia$" :A
    #"ic$" :A          #"is$" :A          #"ly$" :B          #"on$" :S
    #"or$" :T          #"um$" :U          #"us$" :V          #"yl$" :R
    #"s'"  :A          #"'s$" :A

    #"a$" :A           #"e$" :A           #"i$" :A           #"o$" :A
    #"s$" :W           #"y$" :B))

;; Conditions
(def ^:private conditions
  {:A (fn [stem] true)
   :B (fn [stem] (> (count stem) 2))
   :C (fn [stem] (> (count stem) 3))
   :D (fn [stem] (> (count stem) 4))
   :E (fn [stem] (not (re-test? #"e$" stem)))
   :F (fn [stem] (and ((conditions :B) stem) (conditions :E stem)))
   :G (fn [stem] (and ((conditions :B) stem) (re-test? #"f$" stem)))
   :H (fn [stem] (re-test? #"(t|ll)$" stem))
   :I (fn [stem] (not (re-test? #"[oe]$" stem)))
   :J (fn [stem] (not (re-test? #"[ae]$" stem)))
   :K (fn [stem] (and ((conditions :B) stem) (re-test? #"(l|i|(u\we))$" stem)))
   :L (fn [stem] (not (re-test? #"(u|x|([^o]s))$" stem)))
   :M (fn [stem] (not (re-test? #"[acem]$" stem)))
   :N (fn [stem] (if (re-test? #"s\w{2}$" stem) ((conditions :C) stem) ((conditions :B) stem)))
   :O (fn [stem] (re-test? #"[li]$" stem))
   :P (fn [stem] (not (re-test? #"c$" stem)))
   :Q (fn [stem] (and ((conditions :B) stem) (not (re-test? #"[ln]$" stem))))
   :R (fn [stem] (re-test? #"[nr]$" stem))
   :S (fn [stem] (re-test? #"(dr|[^t]t)$" stem))
   :T (fn [stem] (re-test? #"(s|[^o]t)$" stem))
   :U (fn [stem] (re-test? #"[lmnr]$" stem))
   :V (fn [stem] (re-test? #"c$" stem))
   :W (fn [stem] (not (re-test? #"[su]$" stem)))
   :X (fn [stem] (re-test? #"(l|i|u\we)" stem))
   :Y (fn [stem] (re-test? #"in$" stem))
   :Z (fn [stem] (not (re-test? #"f$" stem)))
   :AA (fn [stem] (re-test? #"([dflt]|ph|th|er|or|es)$" stem))
   :BB (fn [stem] (and ((conditions :B) stem) (not (re-test? #"(met|ryst)" stem))))
   :CC (fn [stem] (re-test? #"l$" stem))})

;; Transformations
(defn- dedouble
  "Drop double occurences of certain letters in the given [stem]."
  [stem]
  (clojure.string/replace stem #"([bdglmnprst])\1{1,}" "$1"))

(def ^:private transformations
  '(#"iev$" "ief"
    #"uct$" "uc"
    #"umpt$" "um"
    #"rpt$" "rb"
    #"urs$" "ur"
    #"istr$" "ister"
    #"metr$" "meter"
    #"olv$" "olut"
    #"[^aoi]ul$" "l"
    #"bex$" "bic"
    #"dex$" "dic"
    #"pex$" "pic"
    #"tex$" "tic"
    #"ax$" "ac"
    #"ex$" "ec"
    #"ix$" "ic"
    #"lux$" "luc"
    #"uad$" "uas"
    #"vad$" "vas"
    #"cid$" "cis"
    #"lid$" "lis"
    #"erid$" "eris"
    #"pand$" "pans"
    #"[^s]end$" "ens"
    #"ond$" "ons"
    #"lud$" "lus"
    #"rud$" "rus"
    #"[^pt]her$" "hes"
    #"mit$" "mis"
    #"[^m]ent$" "ens"
    #"ert$" "ers"
    #"[^n]et$" "es"
    #"(yt|yz)$" "ys"))

;; Helpers
(defn- clean
  "Clean a [word] of characters unsupported by the stemmer"
  [word]
  (clojure.string/replace word #"[^a-zA-Z']" ""))

;; Main functions
(defn- prep-word
  "Prepare a [word] for its passage through the stemmer."
  [word]
  (-> (clojure.string/lower-case word)
      (clean)))

(defn- drop-suffix
  "Drop the longest suffix we can find in the given [word]."
  [word]
  (if-let [[match condition] (some #(when (re-test? (first %) word) %) (partition 2 endings))]
    (let [stem (clojure.string/replace word match "")]
      (if (and ((conditions condition) stem)
               (> (count stem) 1))
        stem
        word))
    word))

(defn apply-transformations
  "Apply the algorithm's transformations to the given [stem]."
  [stem]
  (if-let [[match replacement] (some #(when (re-test? (first %) stem) %) (partition 2 transformations))]
    (clojure.string/replace stem match replacement)
    stem))

(defn stem
  "Stem the given [word] according to the algorithm"
  [word]
  (-> (prep-word word)
      (drop-suffix)
      (dedouble)
      (apply-transformations)))
