require.config({
  paths: {
    'angular': 'lib/angular/angular',
    'angular-animate': 'lib/angular-animate/angular-animate.min',
    'angular-ui-router': 'lib/angular-ui-router/release/angular-ui-router',
    'angular-resource': 'lib/angular-resource/angular-resource.min',
    'angular-ngtable': 'lib/ng-table/dist/ng-table.min',
    'toaster': 'lib/angularjs-toaster/toaster',
    'bootstrap': 'lib/bootstrap/dist/js/bootstrap',
    'jquery': 'lib/jquery/dist/jquery.min'
  },
  shim: {
    'angular': {
      exports: 'angular',
      deps: ['jquery']
    },
    'angular-resource': {
      deps: ['angular']
    },
    'angular-ui-router': {
      deps: ['angular']
    },
    'angular-animate': {
      deps: ['angular']
    },
    'angular-ngtable': {
      deps: ['angular']
    },
    'toaster': {
      deps: ['angular']
    },
  }
});

require([
    'angular',
    'angular-resource',
    'angular-animate',
    'angular-ngtable',
    'toaster',
    'angular-ui-router',
    'bootstrap',
    'core/core.module.js'
  ], function (angular) {
  angular.element().ready(function () {
    angular.bootstrap(document, [
      'ui.router',
      'ngResource',
      'ngAnimate',
      'ngTable',
      'toaster',
      'core.module'
    ]);
  });
});