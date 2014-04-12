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

---

{% capture md %}{% include toc.md %}{% endcapture %}
{{ md | markdownify }}

---

{% capture md2 %}{% include javascript_examples.md %}{% endcapture %}
{{ md2 | markdownify }}
