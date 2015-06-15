# Compiling ClojureScript
lein cljsbuild once

# Adding export and js vonversion then minifying
echo "Finalizing and minifying..."
cat ./src-js/build.js ./src-js/export.js | uglifyjs > ./src-js/build.min.js

# Banner
echo "Banner..."
cat ./src-js/banner.txt ./src-js/build.min.js > ./src-js/clj-fuzzy.js

# Cleaning
rm ./src-js/build.js
rm ./src-js/build.min.js
