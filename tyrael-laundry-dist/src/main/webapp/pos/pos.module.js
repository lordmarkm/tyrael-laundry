define([
   'angular',
   'core/service/AuthenticationService',
   'core/service/JobOrderService',
   'pos/controller/PosRootController'
], function (angular, AuthenticationService, JobOrderService, PosRootController) {
  console.debug('Configuring pos.module');
  angular.module('pos.module', [])
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
      });

    }]);

});