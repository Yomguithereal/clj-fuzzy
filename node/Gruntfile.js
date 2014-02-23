module.exports = function(grunt) {

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
        src: ['clj-fuzzy.min.js', 'export.js'],
        dest: 'index.js'
      }
    }
  });

  grunt.loadNpmTasks('grunt-contrib-concat');
  grunt.loadNpmTasks('grunt-exec');

  // By default, will check lint, test and minify:
  grunt.registerTask('default', ['exec:cljsbuild', 'concat:dist']);
};
