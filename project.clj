(defproject clj-fuzzy "0.3.3"
  :description "A handy collection of algorithms dealing with fuzzy strings and phonetics."
  :url "http://yomguithereal.github.io/clj-fuzzy/"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :profiles {:dev {:dependencies [[org.clojure/clojurescript "0.0-3308"]]}}
  :plugins [[lein-kibit "0.0.8"]
            [lein-cljsbuild "1.0.6"]]
  :cljsbuild {
    :builds [{
        :source-paths ["src"]
        :compiler {
          :output-to "src-js/build.js"
          :optimizations :advanced}}]})
