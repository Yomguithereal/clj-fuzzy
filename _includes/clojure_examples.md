<h3 id="metrics">clj-fuzzy.metrics</h4>

<h4 id="dice">Dice coefficient</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Dice coefficient of two words
(dice "healed" "sealed")
0.8

(dice "healed" "herded")
0.4
```

<h4 id="levensthein">Levensthein distance</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Levensthein distance between two words
(levensthein "book" "back")
2

(levensthein "hello" "helo")
1
```

<h4 id="hamming">Hamming distance</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Hamming distance between two words
(hamming "ramer" "cases")
3

(hamming '(0 1 0 1) '(1 1 0 1))
1
```

<h4 id="jaccard">Jaccard distance</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Jaccard distance between two words
;; 0 meaning two identical strings and 1 two totally different ones
(jaccard "abc" "xyz")
1

(jaccard "night" "nacht")
4/7
```

<h4 id="jaro">Jaro-Winkler distance</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Jaro distance between two words
(jaro "Dwayne" "Duane")
0.8222222222222223

;; Compute the Jaro-Winkler distance between two words
(jaro-winkler "Dwayne" "Duane")
0.8400000000000001
```

<h4 id="mra">MRA Comparison</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compare two string using the Match Rating Approach
(mra-comparison "Byrne" "Boern")
{:minimum 4
 :similarity 5
 :codex ["BYRN" "BRN"]
 :match true}
```

---

<h3 id="stemmers">clj-fuzzy.stemmers</h4>

<h4 id="porter">Porter stemming</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.stemmers))

;; Compute the stem of a word
(porter "adjective")
"adject"

(porter "building")
"build"
```

---

<h3 id="phonetics">clj-fuzzy.phonetics</h4>

<h4 id="metaphone">Metaphone</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the metaphone code for a single word
(metaphone "hypocrite")
"HPKRT"

(metaphone "discrimination")
"TSKRMNXN"
```

<h4 id="double_metaphone">Double Metaphone</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the double metaphone of a word
(double-metaphone "Smith")
["SM0" "XMT"]

(double-metaphone "Schmidt")
["XMT" "SMT"]
```

<h4 id="soundex">Soundex</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the soundex code of a single name
(soundex "Ashcroft")
"A261"

(soundex "Andrew")
"A536"
```

<h4 id="nysiis">NYSIIS</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the NYSIIS code of a single name
(nysiis "Andrew")
"ANDR"

(nysiis "Mclaughlin")
"MCLAGLAN"

;; Compute the refined NYSIIS code of a single name
(nysiis "Aegir" :refined)
"AGAR"
```

<h4 id="caverphone">Caverphone</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the caverphone code of a single name
(caverphone "Henrichsen")
"ANRKSN1111"

(caverphone "Mclaverty")
"MKLFTA1111"
```

<h4 id="cologne">Cologne Phonetic</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the cologne phonetic code of a single word
(cologne "Müller-Lüdenscheidt")
"65752682"

(cologne "Breschnew")
"17863"
```

<h4 id="mra_codex">MRA Codex</h4>
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the MRA codex of a single name
(mra-codex "Catherine")
"CTHRN"

(mra-codex "Smith")
"SMTH"
```
