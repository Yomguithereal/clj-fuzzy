---

## Installation
To install the latest node version of the library just run

```bash
npm install clj-fuzzy
```

If you want the latest development version

```bash
npm install git+https://github.com/Yomguithereal/clj-fuzzy.git
```

Finally, if you need to include the library in your node.js project, add the following dependency to your package.json file.

```json
{
  "dependencies": {
    "clj-fuzzy": "0.1.7",
  }
}
```

The npm version already works with `browserify` and any other tools enabling you to use `require` for client-side assets.

---

## Usage
clj-fuzzy ships with three API namespaces: `clj_fuzzy.metrics`, `clj_fuzzy.stemmers` and finally `clj_fuzzy.phonetics`, embarking the relevant algorithms.

Just require the library and you are good to go.

```js
var clj_fuzzy = require('clj-fuzzy');
```

{% capture md %}{% include toc.md %}{% endcapture %}
{{ md | markdownify }}

---

### clj-fuzzy.metrics

#### Dice coefficient
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Dice coefficient of two words
clj_fuzzy.metrics.dice('healed', 'sealed');
0.8

clj_fuzzy.metrics.dice('healed', 'herded');
0.4
```

#### Levensthein distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Levensthein distance between two words
clj_fuzzy.metrics.levensthein('book', 'back');
2

clj_fuzzy.metrics.levensthein('hello', 'helo');
1
```

#### Hamming distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Hamming distance between two words
clj_fuzzy.metrics.hamming('ramer', 'cases');
3

clj_fuzzy.metrics.hamming([0, 1, 0, 1], [1, 1, 0, 1]);
1
```

#### Jaccard distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Jaccard distance between two words
// 0 meaning two identical strings and 1 two totally different ones
clj_fuzzy.metrics.jaccard('abc', 'xyz');
1

clj_fuzzy.metrics.jaccard('night', 'nacht');
0.5714285714285714
```

#### Jaro-Winkler distance
```js
var clj_fuzzy = require('clj-fuzzy');

// Compute the Jaro distance between two words
clj_fuzzy.metrics.jaro('Dwayne', 'Duane');
0.8222222222222223

// Compute the Jaro-Winkler distance between two words
clj_fuzzy.metrics.jaro_winkler('Dwayne', 'Duane');
0.8400000000000001
```

#### MRA Comparison
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

---

{% capture md2 %}{% include javascript_examples.md %}{% endcapture %}
{{ md2 | markdownify }}
