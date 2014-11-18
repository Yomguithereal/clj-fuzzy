;; -------------------------------------------------------------------
;; clj-fuzzy Metaphone3
;; -------------------------------------------------------------------
;;
;;
;;   Author: PLIQUE Guillaume (Yomguithereal)
;;   Algorithm author: Lawrence Philips
;;   Source: https://github.com/OpenRefine/OpenRefine/blob/master/main/src/com/google/refine/clustering/binning/Metaphone3.java
;;

;; Copyright 2010, Lawrence Philips
;; All rights reserved.
;; Redistribution and use in source and binary forms, with or without
;; modification, are permitted provided that the following conditions are
;; met:
;;     * Redistributions of source code must retain the above copyright
;; notice, this list of conditions and the following disclaimer.
;;     * Redistributions in binary form must reproduce the above
;; copyright notice, this list of conditions and the following disclaimer
;; in the documentation and/or other materials provided with the
;; distribution.
;;     * Neither the name of Google Inc. nor the names of its
;; contributors may be used to endorse or promote products derived from
;; this software without specific prior written permission.
;; THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
;; "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
;; LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
;; A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
;; OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
;; SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
;; LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
;; DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
;; THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
;; (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
;; OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

(ns clj-fuzzy.metaphone3
  (:use [clj-fuzzy.helpers :only [slice]]))

;; String utilities
(defn- char-at
  "Safe char-at function."
  [s at]
  (if (or (< at 0)
          (> at (- (count s) 1)))
    \0
    (.charAt s at)))

(defn- compare-slices
  "Compare the slice of the given [s] to a batch of other [comparisons] slices."
  [s start length & comparisons]
  (if (or (< start 0)
          (> start (- (count s) 1))
          (> (- (+ start length) 1) (- (count s) 1)))
    false
    (let [target (slice s start length)]
      (any? true? (map #(= % target) comparisons)))))

;; Sonority checks
(defn- front-vowel?
  "Is the [s] character [at] given position a relevant vowel?"
  [s at]
  (let [c (char-at s at)]
    (or (= \E c)
        (= \I c)
        (= \Y c))))

(defn- slavo-germanic?
  "Determine whether the given [word] as a slavo-germanic tone."
  [word]
  (or (compare-slices word 0 3 "SCH" "")
      (compare-slices word 0 2 "SW" "")
      (= (char-at word 0) \J)
      (= (char-at word 0) \W)))
