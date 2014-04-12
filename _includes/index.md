# clj-fuzzy
clj-fuzzy is a native Clojure library providing a collection of famous algorithms dealing with fuzzy strings and phonetics.

It can be used in Clojure, ClojureScript, client-side JavaScript and Node.js.

---

## Available algorithms
clj-fuzzy embarks three types of algorithms:

* [Distance metrics](http://en.wikipedia.org/wiki/String_metric) for strings and other sequences.
* [Stemmers](http://en.wikipedia.org/wiki/Stemming) trying to extract a radical from given words.
* [Phonetic algorithms](http://en.wikipedia.org/wiki/Phonetic_algorithm) whose goal is to return an approximative phonetic representation of the given string.

### Metrics
* [Sorensen / Dice coefficient](http://en.wikipedia.org/wiki/S%C3%B8rensen%E2%80%93Dice_coefficient)
* [Levensthein distance](http://en.wikipedia.org/wiki/Levenshtein_distance)
* [Hamming distance](http://en.wikipedia.org/wiki/Hamming_distance)
* [Jaccard distance](http://en.wikipedia.org/wiki/Jaccard_index)
* [Jaro-Winkler distance](http://en.wikipedia.org/wiki/Jaro%E2%80%93Winkler_distance)
* [MRA comparison](http://en.wikipedia.org/wiki/Match_rating_approach)

### Stemmers
* [Porter stemming](http://en.wikipedia.org/wiki/Stemming)

### Phonetics
* [Metaphone](http://en.wikipedia.org/wiki/Metaphone)
* [Double Metaphone](http://en.wikipedia.org/wiki/Metaphone#Double_Metaphone)
* [Soundex](http://en.wikipedia.org/wiki/Soundex)
* [NYSIIS](http://en.wikipedia.org/wiki/New_York_State_Identification_and_Intelligence_System)
* [Caverphone](http://en.wikipedia.org/wiki/Caverphone)
* [Cologne Phonetic](http://de.wikipedia.org/wiki/K%C3%B6lner_Phonetik)
* [MRA codex](http://en.wikipedia.org/wiki/Match_rating_approach)

---

## Installation & Usage

* [Clojure]({{ site.baseurl }}/clojure.html)
* [Client-side JavaScript]({{ site.baseurl }}/javascript.html)
* [Node.js]({{ site.baseurl }}/node.html)

---

## Contribution
Please feel free to contribute by forking this repo. Just be sure to add relevant unit tests and pass them all before submitting any code.

```
lein test
```
