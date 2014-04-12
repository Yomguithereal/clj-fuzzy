---

## Installation
To install the lastest version from [clojars](https://clojars.org/), just add the following vector to the `:dependencies` section of your `project.clj` file.

```clj
[clj-fuzzy "0.1.7"]
```

Then run `lein deps` to process your dependencies.

If you would rather install the latest version from the current source, clone the repository and install with leiningen.

```
git clone https://github.com/Yomguithereal/clj-fuzzy.git
cd clj-fuzzy
lein install
```

Then include the same vector within your `project.clj` and you should be good to go.

---

## Usage
clj-fuzzy ships with three API namespaces: `clj-fuzzy.metrics`, `clj-fuzzy.stemmers` and finally `clj-fuzzy.phonetics`.

Just require or use those and their relevant functions to run the algorithms.

In order to be the simplest possible, the following examples `:use` the clj-fuzzy namespaces. But you should really rely on a cleaner `:require`.

---

{% capture md %}{% include toc.md %}{% endcapture %}
{{ md | markdownify }}

---

{% capture md2 %}{% include clojure_examples.md %}{% endcapture %}
{{ md2 | markdownify }}