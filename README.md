[![Build Status](https://travis-ci.org/Yomguithereal/clj-fuzzy.svg)](https://travis-ci.org/Yomguithereal/clj-fuzzy)

# clj-fuzzy
clj-fuzzy is a native Clojure library providing a collection of famous algorithms dealing with fuzzy strings and phonetics.

It can be used in Clojure, ClojureScript, client-side JavaScript and Node.js.

[![Clojars Project](http://clojars.org/clj-fuzzy/latest-version.svg)](http://clojars.org/clj-fuzzy)

## Full documentation
The full documentation for this library is available [there](http://yomguithereal.github.io/clj-fuzzy/).

## Available algorithms

### Metrics
* [Sorensen / Dice coefficient](http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient)
* [Levenshtein distance](http://en.wikipedia.org/wiki/Levenshtein_distance)
* [Hamming distance](http://en.wikipedia.org/wiki/Hamming_distance)
* [Jaccard / Tanimoto distance](http://en.wikipedia.org/wiki/Jaccard_index)
* [Jaro-Winkler distance](http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance)
* [MRA comparison](http://en.wikipedia.org/wiki/Match_rating_approach)
* [Tversky index](http://en.wikipedia.org/wiki/Tversky_index)

### Stemmers
* [Lancaster stemmer](http://www.comp.lancs.ac.uk/computing/research/stemming/) (en)
* [Lovins stemmer](http://snowball.tartarus.org/algorithms/lovins/stemmer.html) (en)
* [Porter stemmer](http://tartarus.org/martin/PorterStemmer/index-old.html) (en)
* [Schinke stemmer](http://snowball.tartarus.org/otherapps/schinke/intro.html) (latin)

### Phonetics
* [Metaphone](http://en.wikipedia.org/wiki/Metaphone)
* [Double Metaphone](http://en.wikipedia.org/wiki/Metaphone#Double_Metaphone)
* [Soundex](http://en.wikipedia.org/wiki/Soundex)
* [NYSIIS](http://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System) (original & refined)
* [Caverphone](http://en.wikipedia.org/wiki/Caverphone) (original & revisited)
* [Cologne Phonetic](http://de.wikipedia.org/wiki/K%C3%B6lner_Phonetik)
* [MRA codex](http://en.wikipedia.org/wiki/Match_rating_approach)

## Contribution
Please feel free to contribute by forking this repo. Just be sure to add relevant unit tests and pass them all before submitting any code.

## License
MIT
