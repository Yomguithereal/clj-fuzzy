# clj-fuzzy
clj-fuzzy is a Clojure library providing a compilation of famous algorithms dealing with fuzzy strings and phonetics.

## Available algorithms

### Distances and fuzzy matching
* [Sorensen / Dice coefficient](http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient)
* [Levensthein distance](http://en.wikipedia.org/wiki/Levenshtein_distance)
* [Porter stemming](http://en.wikipedia.org/wiki/Stemming)

### Phonetics
* [Metaphone](http://en.wikipedia.org/wiki/Metaphone)
* [Soundex](http://en.wikipedia.org/wiki/Soundex)

## Installation
To install the lastest version from [clojars](https://clojars.org/), just add the following vector to the `:dependencies` section of your `project.clj` file.

```clj
[clj-fuzzy "0.1.0"]
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
* [Sorensen / Dice coefficient](#dice-coefficient)
* [Levensthein distance](#levensthein-distance)
* [Porter stemming](#porter-stemming)
* [Metaphone](#metaphone)
* [Soundex](#soundex)

### Dice coefficient
```clj
(ns my.clojure-namespace
  (:require clj-fuzzy.dice))

;; Compute the Dice coefficient of two words
(clj-fuzzy.dice/coefficient "healed" "sealed")
0.8

(clj-fuzzy.dice/coefficient "healed" "herded")
0.4
```

### Levensthein distance
```clj
(ns my.clojure-namespace
  (:require clj-fuzzy.levensthein))

;; Compute the levensthein distance between two words
(clj-fuzzy.levensthein/distance "book" "back")
2

(clj-fuzzy.levensthein/distance "hello" "helo")
1
```

### Porter stemming
```clj
(ns my.clojure-namespace
  (:require clj-fuzzy.porter-stemming))

;; Compute the stem of a word
(clj-fuzzy.porter-stemming/stem "adjective")
"adject"

(clj-fuzzy.porter-stemming/stem "building")
"build"
```

### Metaphone
```clj
(ns my.clojure-namespace
  (:require clj-fuzzy.metaphone))

;; Compute the metaphone code for a single word
(clj-fuzzy.metaphone/process-word "hypocrite")
"HPKRT"

(clj-fuzzy.metaphone/process-word "discrimination")
"TSKRMNXN"
```

### Soundex
```clj
(ns my.clojure-namespace
  (:require clj-fuzzy.soundex))

;; Compute the soundex code of a single name
(clj-fuzzy.soundex/process "Ashcroft")
"A261"

(clj-fuzzy.soundex/process "Andrew")
"A536"
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
