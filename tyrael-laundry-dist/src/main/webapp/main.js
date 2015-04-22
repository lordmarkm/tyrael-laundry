require.config({
  paths: {
    'angular': 'lib/angular/angular',
    'angular-animate': 'lib/angular-animate/angular-animate.min',
    'angular-ui-router': 'lib/angular-ui-router/release/angular-ui-router',
    'angular-resource': 'lib/angular-resource/angular-resource.min',
    'angular-ngtable': 'lib/ng-table/dist/ng-table.min',
    'angular-ui-select': 'lib/angular-ui-select/dist/select',
    'angular-sanitize': 'lib/angular-sanitize/angular-sanitize.min',
    'angular-bootstrap': 'lib/angular-bootstrap/ui-bootstrap-tpls.min',
    'toaster': 'lib/angularjs-toaster/toaster',
    'bootstrap': 'lib/bootstrap/dist/js/bootstrap',
    'jquery': 'lib/jquery/dist/jquery.min',
    'metisMenu': 'lib/metisMenu/dist/metisMenu.min'
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
    'angular-ui-select': {
      deps: ['angular']
    },
    'angular-sanitize': {
      deps: ['angular']
    },
    'angular-bootstrap': {
      deps: ['angular']
    },
    'toaster': {
      deps: ['angular']
    },
    'metisMenu': {
      deps: ['jquery']
    },
    'bootstrap': {
      deps: ['jquery']
    }
  }
});

require([
    'angular',
    'angular-resource',
    'angular-animate',
    'angular-ngtable',
    'angular-ui-select',
    'angular-sanitize',
    'angular-bootstrap',
    'toaster',
    'angular-ui-router',
    'jquery',
    'bootstrap',
    'metisMenu',
    'core/core.module.js',
    'common/common.module.js',
    'splash/splash.module.js',
    'manager/manager.module.js',
    'pos/pos.module.js'
  ], function (angular) {
  angular.element().ready(function () {
    angular.bootstrap(document, [
      'ui.router',
      'ngResource',
      'ngAnimate',
      'ngTable',
      'ui.select',
      'ngSanitize',
      'ui.bootstrap',
      'toaster',
      'core.module',
      'common.module',
      'splash.module',
      'manager.module',
      'pos.module'
    ]);
  });
});