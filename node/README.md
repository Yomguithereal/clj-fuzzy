# clj-fuzzy
You are currently reading the JavaScript specific documentation for the clj-fuzzy library.

If you are searching for the clojure one, you can find it [there](https://github.com/Yomguithereal/clj-fuzzy).

## Available algorithms

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

## Installation

### Client-side JavaScript
To use the clj-fuzzy library client-side, just include the minified JavaScript build of the library available [here](https://raw.github.com/Yomguithereal/clj-fuzzy/master/node/index.js).

### Node.js
To install the latest node version of the library just run

```bash
npm installl clj-fuzzy
```

Else, if you want to include the library in your node.js project, add the following dependency in your package.json file.

```json
{
  "dependencies": {
    "clj-fuzzy": "0.1.6",
  }
}
```

## Usage
clj-fuzzy ships with three API namespaces: `clj_fuzzy.metrics`, `clj_fuzzy.stemmers` and finally `clj_fuzzy.phonetics`. Just require or use those and use the relevant functions to run the algorithms.

**clj-fuzzy.metrics**
* [Sorensen / Dice coefficient](#dice-coefficient)
* [Levensthein distance](#levensthein-distance)
* [Hamming distance](#hamming-distance)
* [Jaccard distance](#jaccard-distance)
* [Jaro-Winkler distance](#jaro-winkler-distance)
* [MRA Comparison](#mra-comparison)

**clj-fuzzy.stemmers**
* [Porter stemming](#porter-stemming)

**clj-fuzzy.phonetics**
* [Metaphone](#metaphone)
* [Double Metaphone](#double-metaphone)
* [Soundex](#soundex)
* [NYSIIS](#nysiis)
* [Caverphone](#caverphone)
* [Cologne Phonetic](#cologne-phonetic)
* [MRA codex](#mra-codex)

[**Clojure counterparts**](https://github.com/Yomguithereal/clj-fuzzy#usage)

**N.B.** For any of the following examples, just drop the require line to make it work client-side, as the `clj_fuzzy` object is exported to `window`.

### Dice coefficient
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Dice coefficient of two words
clj_fuzzy.metrics.dice('healed', 'sealed');
0.8

clj_fuzzy.metrics.dice('healed', 'herded');
0.4
```

### Levensthein distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Levensthein distance between two words
clj_fuzzy.metrics.levensthein('book', 'back');
2

clj_fuzzy.metrics.levensthein('hello', 'helo');
1
```

### Hamming distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Hamming distance between two words
clj_fuzzy.metrics.hamming('ramer', 'cases');
3

clj_fuzzy.metrics.hamming([0, 1, 0, 1], [1, 1, 0, 1]);
1
```

### Jaccard distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Jaccard distance between two words
// 0 meaning two identical strings and 1 two totally different ones
clj_fuzzy.metrics.jaccard('abc', 'xyz');
1

clj_fuzzy.metrics.jaccard('night', 'nacht');
0.5714285714285714
```

### Jaro-Winkler distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Jaro distance between two words
clj_fuzzy.metrics.jaro('Dwayne', 'Duane');
0.8222222222222223

// Compute the Jaro-Winkler distance between two words
clj_fuzzy.metrics.jaro_winkler('Dwayne', 'Duane');
0.8400000000000001
```

### MRA Comparison
```js
var clj_fuzzy = require('clj-fuzzy');

// Compare two string using the Match Rating Approach
clj_fuzzy.metrics.mra_comparison('Byrne', 'Boern');
{
	minimum: 4,
	similarity: 5,
	code: ['BYRN', 'BRN'],
	match: true
}
```

### Porter stemming
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the stem of a word
clj_fuzzy.stemmers.porter('adjective');
"adject"

clj_fuzzy.stemmers.porter('building');
"build"
```

### Metaphone
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the metaphone code for a single word
clj_fuzzy.phonetics.metaphone('hypocrite');
"HPKRT"

clj_fuzzy.phonetics.metaphone('discrimination');
"TSKRMNXN"
```

### Double Metaphone
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the double metaphone of a word
clj_fuzzy.phonetics.double_metaphone('Smith');
["SM0" "XMT"]

clj_fuzzy.phonetics.double_metaphone('Schmidt');
["XMT" "SMT"]
```

### Soundex
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the soundex code of a single name
clj_fuzzy.phonetics.soundex('Ashcroft');
"A261"

clj_fuzzy.phonetics.soundex('Andrew');
"A536"
```

### NYSIIS
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the NYSIIS code of a single name
clj_fuzzy.phonetics.nysiis('Andrew');
"ANDR"

clj_fuzzy.phonetics.nysiis('Mclaughlin');
"MCLAGLAN"
```

### Caverphone
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the caverphone code of a single name
clj_fuzzy.phonetics.caverphone('Henrichsen');
"ANRKSN1111"

clj_fuzzy.phonetics.caverphone('Mclaverty');
"MKLFTA1111"
```

### Cologne Phonetic
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the cologne phonetic code of a single word
clj_fuzzy.phonetics.cologne('Müller-Lüdenscheidt');
"65752682"

clj_fuzzy.phonetics.cologne('Breschnew');
"17863"
```

### MRA Codex
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the MRA codex of a single name
clj_fuzzy.phonetics.mra_codex('Catherine');
"CTHRN"

clj_fuzzy.phonetics.mra_codex('Smith');
"SMTH"
```
## Contribution
Please feel free to contribute by forking this repo. Just be sure to add relevant unit tests and pass them all before submitting any code.
