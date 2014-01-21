# clj-fuzzy
clj-fuzzy is a Clojure library providing a collection of famous algorithms dealing with fuzzy strings and phonetics.

## Available algorithms

### Metrics
* [Sorensen / Dice coefficient](http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient)
* [Levensthein distance](http://en.wikipedia.org/wiki/Levenshtein_distance)

### Stemmers
* [Porter stemming](http://en.wikipedia.org/wiki/Stemming)

### Phonetics
* [Metaphone](http://en.wikipedia.org/wiki/Metaphone)
* [Double Metaphone](http://en.wikipedia.org/wiki/Metaphone#Double_Metaphone)
* [Soundex](http://en.wikipedia.org/wiki/Soundex)
* [NYSIIS](http://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System)
* [Caverphone](http://en.wikipedia.org/wiki/Caverphone)
* [Match Rating Approach Codex](http://en.wikipedia.org/wiki/Match_rating_approach)

## Installation
To install the lastest version from [clojars](https://clojars.org/), just add the following vector to the `:dependencies` section of your `project.clj` file.

```clj
[clj-fuzzy "0.1.3"]
```

Then run `lein deps` to process your dependencies.

To install the latest version from the current source, clone the repository and install with leiningen.

```
git clone https://github.com/Yomguithereal/clj-fuzzy.git
cd clj-fuzzy
lein install
```

Then include the same vector within your `project.clj` and you should be good to go.

## Usage
clj-fuzzy ships with three API namespaces: `clj-fuzzy.metrics`, `clj-fuzzy.stemmers` and finally `clj-fuzzy.phonetics`. Just require or use those and use the relevant functions to run the algorithms.

**clj-fuzzy.metrics**
* [Sorensen / Dice coefficient](#dice-coefficient)
* [Levensthein distance](#levensthein-distance)

**clj-fuzzy.stemmers**
* [Porter stemming](#porter-stemming)

**clj-fuzzy.phonetics**
* [Metaphone](#metaphone)
* [Double Metaphone](#double-metaphone)
* [Soundex](#soundex)
* [NYSIIS](#nysiis)
* [Caverphone](#caverphone)
* [MRA Codex](#mra-codex)

In order to be the simplest possible, the following examples `:use` the clj-fuzzy namespaces. But you should really rely on a cleaner `:require`.


### Dice coefficient
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the Dice coefficient of two words
(dice "healed" "sealed")
0.8

(dice "healed" "herded")
0.4
```

### Levensthein distance
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.metrics))

;; Compute the levensthein distance between two words
(levensthein "book" "back")
2

(levensthein "hello" "helo")
1
```

### Porter stemming
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.stemmers))

;; Compute the stem of a word
(porter "adjective")
"adject"

(porter "building")
"build"
```

### Metaphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the metaphone code for a single word
(metaphone "hypocrite")
"HPKRT"

(metaphone "discrimination")
"TSKRMNXN"
```

### Double Metaphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the double metaphone of a word
(double-metaphone "Smith")
["SM0", "XMT"]

(double-metaphone "Schmidt")
["XMT" "SMT"]
```

### Soundex
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the soundex code of a single name
(soundex "Ashcroft")
"A261"

(soundex "Andrew")
"A536"
```

### NYSIIS
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the NYSIIS code of a single name
(nysiis "Andrew")
"ANDR"

(nysiis "Mclaughlin")
"MCLAGLAN"
```

### Caverphone
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the caverphone code of a single name
(caverphone "Henrichsen")
"ANRKSN1111"

(caverphone "Mclaverty")
"MKLFTA1111"
```

### MRA Codex
```clj
(ns my.clojure-namespace
  (:use clj-fuzzy.phonetics))

;; Compute the MRA codex of a single name
(mra-codex "Catherine")
"CTHRN"

(mra-codex "Smith")
"SMTH"
```

## Warnings
The library is very young and subject to API changes.

## Todo
* Add algorithms dealing with other languages than English.
* Add more algorithms to the library.
* Optimize the algorithms' implementations.
* Offer a better API.

## Contribution
Please feel free to contribute by forking this repo. Just be sure to add relevant unit tests and pass them all before submitting any code.
