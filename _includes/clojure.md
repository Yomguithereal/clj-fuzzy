# Clojure Bindings

## Installation

---

## Usage
clj-fuzzy ships with three API namespaces: `clj-fuzzy.metrics`, `clj-fuzzy.stemmers` and finally `clj-fuzzy.phonetics`.

Just require or use those and their relevant functions to run the algorithms.

---

### clj-fuzzy.metrics

#### Dice coefficient
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Dice coefficient of two words
(dice "healed" "sealed")
0.8

(dice "healed" "herded")
0.4
```

#### Levensthein distance
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Levensthein distance between two words
(levensthein "book" "back")
2

(levensthein "hello" "helo")
1
```

#### Hamming distance
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Hamming distance between two words
(hamming "ramer" "cases")
3

(hamming '(0 1 0 1) '(1 1 0 1))
1
```

#### Jaccard distance
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

#### Jaro-Winkler distance
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

#### MRA Comparison
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

### clj-fuzzy.stemmers

#### Porter stemming
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

### clj-fuzzy.phonetics

#### Metaphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the metaphone code for a single word
(metaphone "hypocrite")
"HPKRT"

(metaphone "discrimination")
"TSKRMNXN"
```

#### Double Metaphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the double metaphone of a word
(double-metaphone "Smith")
["SM0" "XMT"]

(double-metaphone "Schmidt")
["XMT" "SMT"]
```

#### Soundex
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the soundex code of a single name
(soundex "Ashcroft")
"A261"

(soundex "Andrew")
"A536"
```

#### NYSIIS
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

#### Caverphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the caverphone code of a single name
(caverphone "Henrichsen")
"ANRKSN1111"

(caverphone "Mclaverty")
"MKLFTA1111"
```

#### Cologne Phonetic
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the cologne phonetic code of a single word
(cologne "Müller-Lüdenscheidt")
"65752682"

(cologne "Breschnew")
"17863"
```

#### MRA Codex
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the MRA codex of a single name
(mra-codex "Catherine")
"CTHRN"

(mra-codex "Smith")
"SMTH"
```
