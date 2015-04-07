define([
   'angular',
   'core/controller/RootController',
   'core/controller/AuthenticationController',
   'core/service/AuthenticationService',
   'core/service/JobOrderService'
], function (angular, RootController, AuthenticationController, AuthenticationService, JobOrderService) {
  console.debug('Configuring core.module');
  angular.module('core.module', [])
    .service('auth', AuthenticationService)
    .service('JobOrderService', JobOrderService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider
        .when('', '/');

      $stateProvider.state('default', {
        url: '/',
        templateUrl: 'core/view/default.html',
        controller: RootController,
        abstract: true
      })
      .state('default.login', {
        url: 'login?msg',
        templateUrl: 'core/view/login.html',
        controller: AuthenticationController
      });

    }]);

});