//Dependencies
var gulp = require('gulp'),
    sass = require('gulp-sass'),
    browserSync = require('browser-sync'),
    config = require('./config/build_config.js'),
    browserReload = browserSync.reload;

/* Read scss files and transform it into css */
gulp.task('sass', function() {
    gulp.src(config.app_folder + '/sass/**/*.scss')
        .pipe(sass())
        .pipe(gulp.dest(config.app_folder + '/css/'))
        .pipe(browserReload({stream:true}));
});

//BrowserSync for reloading browser
gulp.task('browser-sync', function() {
    browserSync({
        startPath : config.app_folder + "/index.html",
        server: {
            baseDir: "./"
        }
    });
});

//Manually reload the browser
gulp.task('bs-reload', function() {
    browserReload();
});

//Watch html, scss an js files for modification
gulp.task('watch', ['browser-sync'], function() {
    gulp.watch(config.app_folder + '/**/*.html', ['bs-reload']);
    gulp.watch(config.app_folder + '/sass/**/*.scss', ['sass']);
    gulp.watch(config.app_folder + '/js/**/*.js',['bs-reload']);
});

gulp.task('default',['sass','watch'],function(){});