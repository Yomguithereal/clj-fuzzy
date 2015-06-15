// Making JavaScript friendly functions
;(function() {
  var js = clj_fuzzy.js,
      mra = clj_fuzzy.metrics.mra_comparison,
      tversky = clj_fuzzy.metrics.tversky,
      dm = clj_fuzzy.phonetics.double_metaphone,
      schinke = clj_fuzzy.stemmers.schinke;

  // MRA
  clj_fuzzy.metrics.mra_comparison = function() {
    return js.clj_to_js(mra.apply(null, arguments));
  };

  // Double Metaphone
  clj_fuzzy.phonetics.double_metaphone = function() {
    return js.clj_to_js(dm.apply(null, arguments));
  };

  // Schinke stemmer
  clj_fuzzy.stemmers.schinke = function() {
    return js.clj_to_js(schinke.apply(null, arguments));
  };

  // Tversky
  clj_fuzzy.metrics.tversky = js.convert_keyword_fn(tversky, 2);

  // Deleting now useless helpers
  delete clj_fuzzy.js;
})();

// Exporting to various module systems
if (typeof exports !== 'undefined') {
  if (typeof module !== 'undefined' && module.exports)
    exports = module.exports = clj_fuzzy;
  exports.clj_fuzzy = clj_fuzzy;
}
else if (typeof define === 'function' && define.amd)
  define('clj_fuzzy', [], function() {
    return clj_fuzzy;
  });
