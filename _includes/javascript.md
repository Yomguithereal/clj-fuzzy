---

## Installation
To use the clj-fuzzy library client-side, just include the JavaScript build of the library available [here](https://raw.github.com/Yomguithereal/clj-fuzzy/master/src-js/clj-fuzzy.js).

```html
<script type="text/javascript" src="/path/to/your/clj-fuzzy.js" />
```

Alternatively, you can use [bower](http://bower.io/) to install the library.

```bash
bower install clj-fuzzy
```

Or, for the development version.

```bash
bower install https://github.com/Yomguithereal/clj-fuzzy.git#master
```

The file you'll have to include client-side would then be `src-js/clj-fuzzy.js` into the clj-fuzzy folder downloaded by bower.

Finally, if needed, the [node version]({{ site.baseurl }}/node.html) already works with `browserify` and any other tools enabling you to use `require` for client-side assets.

---

## Usage
When the clj-fuzzy library is included client-side, a object called `clj_fuzzy` is exported to the global scope so you can use it afterwards.

The library ships with three API namespaces: `clj_fuzzy.metrics`, `clj_fuzzy.stemmers` and finally `clj_fuzzy.phonetics`, embarking the relevant algorithms.

---

{% capture md %}{% include toc.md %}{% endcapture %}
{{ md | markdownify }}

---

{% capture md2 %}{% include javascript_examples.md %}{% endcapture %}
{{ md2 | markdownify }}
