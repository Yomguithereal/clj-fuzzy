;; -------------------------------------------------------------------
;; clj-fuzzy Match Rating Approach
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Version: 0.1
;;

;; TODO: Optimize. I cannot believe this cannot be written
;; in a more efficient and elegant way.
(ns clj-fuzzy.match-rating
  (:require clojure.string)
  (:use [clj-fuzzy.helpers :only [eat
                                  clean-non-alphabetical
                                  distinct-consecutive]]))

;; Utilities
(defn- drop-non-leading-vowel [word]
  (apply str
         (first word)
         (clojure.string/replace (eat word) #"[AEIOU]" "")))

(defn- prep-word [word]
  (-> (clojure.string/upper-case word)
      (clean-non-alphabetical)))

(defn drop-nil [sequence] (remove nil? sequence))

(defn- get-codex-letters [pword]
  (let [last-3 (min 3 (- (count pword) 3))]
    (apply str (concat (take 3 pword)
                       (take-last last-3 pword)))))

(defn- minimum-rating [codex-1 codex-2]
  (let [length (+ (count codex-1) (count codex-2))]
    (cond (<= length 4) 5
          (and (> length 4) (<= length 7)) 4
          (and (> length 7) (<= length 11)) 3
          :else 2)))

(defn- compute-longest [codex-1 codex-2]
  (let [count-1 (count codex-1)
        count-2 (count codex-2)]
    (if (> count-1 count-2)
      [codex-1 codex-2]
      [codex-2 codex-1])))

(defn- make-aggregate [longest shortest]
  (partition 2 (interleave longest
                           (concat shortest
                                   (repeat (- (count longest)
                                              (count shortest)) nil)))))

(defn- reverse-aggregate [aggregate]
  (let [longest (drop-nil (map first aggregate))
        shortest (drop-nil (map second aggregate))]
    (make-aggregate (reverse longest) (reverse shortest))))

(defn- codex-comparison [aggregate]
    (filter #(not= (first %) (second %)) aggregate))

(defn- unmatched-characters [similarity-sequence]
  (count (drop-nil similarity-sequence)))

(defn- similarity-rating [codex-1 codex-2]
  (let [[longest shortest] (compute-longest codex-1 codex-2)
        similarity-sequence (-> (codex-comparison (make-aggregate longest
                                                                  shortest))
                                (reverse-aggregate)
                                (codex-comparison))]
     (- 6 (unmatched-characters similarity-sequence))))

;; Main Functions
(defn mra-codex
  "Compute the MRA codex for a [word]."
  [word]
  (-> (prep-word word)
      (drop-non-leading-vowel)
      (distinct-consecutive)
      (get-codex-letters)))

(defn mra-comparison
  "Compare two [words] using the Match Rating Approach"
  [word-1 word-2]
  (let [codex-1 (mra-codex word-1)
        codex-2 (mra-codex word-2)
        difference (Math/abs
                      (- (count codex-1) (count codex-2)))]
    (when (< difference 3)
      (let [minimum (minimum-rating codex-1 codex-2)
            similarity (similarity-rating codex-1 codex-2)]
        {:minimum minimum
         :similarity similarity
         :codex [codex-1 codex-2]
         :match (>= similarity minimum)}))))
