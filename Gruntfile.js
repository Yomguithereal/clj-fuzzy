module.exports = function(grunt) {
  var jsDirectory = 'src-js/';

  // Project configuration.
  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),
    exec: {
      cljsbuild: {
        cmd: 'lein cljsbuild once'
      }
    },
    concat: {
      options: {
        banner: '/* <%= pkg.name %> - v<%= pkg.version %> - Author: <%= pkg.author %>  - Repository: <%= pkg.homepage %> */\n'
      },
      dist: {
        src: [jsDirectory + 'clj-fuzzy.temp.js', jsDirectory + 'export.js'],
        dest: jsDirectory + 'clj-fuzzy.js'
      }
    },
    clean: {
      build: {
        src: [jsDirectory + 'clj-fuzzy.temp.js']
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-exec');
  grunt.loadNpmTasks('grunt-contrib-clean');

  // By default, will check lint, test and minify:
  grunt.registerTask(
    'default',
    ['exec:cljsbuild', 'concat:dist', 'clean:build']
  );
};
