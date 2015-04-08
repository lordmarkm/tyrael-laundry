define([
   'angular',
   'core/service/AuthenticationService',
   'core/service/JobOrderService',
   'pos/controller/PosRootController',
   'pos/controller/JobOrderCreateController',
   'pos/resolve/JobOrderCreateResolve'
], function (angular, AuthenticationService, JobOrderService, PosRootController, JobOrderCreateController, JobOrderCreateResolve) {
  console.debug('Configuring pos.module');
  angular.module('pos.module', ['ui.select', 'ngSanitize'])
    .service('auth', AuthenticationService)
    .service('JobOrderService', JobOrderService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.pos', {
        url: 'pos',
        templateUrl: 'pos/view/pos.html',
        controller: PosRootController
      })
      .state('default.pos.splash', {
        url: '/splash',
        templateUrl: 'pos/view/splash.html'
      })
      .state('default.pos.joborder_create', {
        url: '/joborder/new',
        templateUrl: 'pos/view/joborder_create.html',
        controller: JobOrderCreateController,
        resolve: JobOrderCreateResolve
      });

    }]);

});