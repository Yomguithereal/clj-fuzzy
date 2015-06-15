;; -------------------------------------------------------------------
;; clj-fuzzy JavaScript Helpers
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;
(ns clj-fuzzy.js)

(defn js->clj-keywordize
  "Shortcut to perform a js->clj while converting keys to keywords."
  [o]
  (js->clj o :keywordize-keys true))

(defn- _keyword-fn->js
  [func pos-nb]
  (fn [& args]
    (let [pos-args (take pos-nb args)
          o (-> (drop pos-nb args)
                (first)
                (js->clj-keywordize)
                (seq)
                (flatten))]
      (apply func (concat pos-args o)))))

(defn ^:export convert-keyword-fn
  "Make a function with named arguments exportable to js."
  ([func]
    (_keyword-fn->js func 0))
  ([func pos-nb]
    (_keyword-fn->js func pos-nb)))

(def ^:export clj-to-js clj->js)
