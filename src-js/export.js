if (typeof exports !== 'undefined') {
  if (typeof module !== 'undefined' && module.exports)
    exports = module.exports = clj_fuzzy;
  exports.clj_fuzzy = clj_fuzzy;
}
else if (typeof define === 'function' && define.amd)
  define('clj_fuzzy', [], function() {
    return clj_fuzzy;
  });
