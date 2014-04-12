---

<h3 id="metrics">clj-fuzzy.metrics</h4>

<h4 id="dice">Dice coefficient</h4>
```js
// Compute the Dice coefficient of two words
clj_fuzzy.metrics.dice('healed', 'sealed');
0.8

clj_fuzzy.metrics.dice('healed', 'herded');
0.4
```

<h4 id="levensthein">Levensthein distance</h4>
```js
// Compute the Levensthein distance between two words
clj_fuzzy.metrics.levensthein('book', 'back');
2

clj_fuzzy.metrics.levensthein('hello', 'helo');
1
```

<h4 id="hamming">Hamming distance</h4>
```js
// Compute the Hamming distance between two words
clj_fuzzy.metrics.hamming('ramer', 'cases');
3

clj_fuzzy.metrics.hamming([0, 1, 0, 1], [1, 1, 0, 1]);
1
```

<h4 id="jaccard">Jaccard distance</h4>
```js
// Compute the Jaccard distance between two words
// 0 meaning two identical strings and 1 two totally different ones
clj_fuzzy.metrics.jaccard('abc', 'xyz');
1

clj_fuzzy.metrics.jaccard('night', 'nacht');
0.5714285714285714
```

<h4 id="jaro">Jaro-Winkler distance</h4>
```js
// Compute the Jaro distance between two words
clj_fuzzy.metrics.jaro('Dwayne', 'Duane');
0.8222222222222223

// Compute the Jaro-Winkler distance between two words
clj_fuzzy.metrics.jaro_winkler('Dwayne', 'Duane');
0.8400000000000001
```

<h4 id="mra">MRA Comparison</h4>
```js
// Compare two string using the Match Rating Approach
clj_fuzzy.metrics.mra_comparison('Byrne', 'Boern');
{
	minimum: 4,
	similarity: 5,
	code: ['BYRN', 'BRN'],
	match: true
}
```

---

<h3 id="stemmers">clj-fuzzy.stemmers</h4>

<h4 id="porter">Porter stemming</h4>
```js
// Compute the stem of a word
clj_fuzzy.stemmers.porter('adjective');
"adject"

clj_fuzzy.stemmers.porter('building');
"build"
```

---

<h3 id="phonetics">clj-fuzzy.phonetics</h4>

<h4 id="metaphone">Metaphone</h4>
```js
// Compute the metaphone code for a single word
clj_fuzzy.phonetics.metaphone('hypocrite');
"HPKRT"

clj_fuzzy.phonetics.metaphone('discrimination');
"TSKRMNXN"
```

<h4 id="double_metaphone">Double Metaphone</h4>
```js
// Compute the double metaphone of a word
clj_fuzzy.phonetics.double_metaphone('Smith');
["SM0" "XMT"]

clj_fuzzy.phonetics.double_metaphone('Schmidt');
["XMT" "SMT"]
```

<h4 id="soundex">Soundex</h4>
```js
// Compute the soundex code of a single name
clj_fuzzy.phonetics.soundex('Ashcroft');
"A261"

clj_fuzzy.phonetics.soundex('Andrew');
"A536"
```

<h4 id="nysiis">NYSIIS</h4>
```js
// Compute the NYSIIS code of a single name
clj_fuzzy.phonetics.nysiis('Andrew');
"ANDR"

clj_fuzzy.phonetics.nysiis('Mclaughlin');
"MCLAGLAN"

;; Compute the refined NYSIIS code of a single name
clj_fuzzy.phonetics.nysiis('Aegir', 'refined');
"AGAR"
```

<h4 id="caverphone">Caverphone</h4>
```js
// Compute the caverphone code of a single name
clj_fuzzy.phonetics.caverphone('Henrichsen');
"ANRKSN1111"

clj_fuzzy.phonetics.caverphone('Mclaverty');
"MKLFTA1111"
```

<h4 id="cologne">Cologne Phonetic</h4>
```js
// Compute the cologne phonetic code of a single word
clj_fuzzy.phonetics.cologne('Müller-Lüdenscheidt');
"65752682"

clj_fuzzy.phonetics.cologne('Breschnew');
"17863"
```

<h4 id="mra_codex">MRA Codex</h4>
```js
// Compute the MRA codex of a single name
clj_fuzzy.phonetics.mra_codex('Catherine');
"CTHRN"

clj_fuzzy.phonetics.mra_codex('Smith');
"SMTH"
```