define([
   'angular',
   'core/controller/AuthenticationController',
   'core/service/AuthenticationService',
   'core/service/JobOrderService'
], function (angular, AuthenticationController, AuthenticationService, JobOrderService) {
  console.debug('Configuring core.module');
  angular.module('core.module', [])
    .service('auth', AuthenticationService)
    .service('JobOrderService', JobOrderService)
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider
        .when('', '/');

      $stateProvider.state('default', {
        url: '/',
        template: '<ui-view></ui-view>',
        abstract: true
      })
      .state('default.login', {
        url: 'login?msg',
        templateUrl: 'core/view/login.html',
        controller: AuthenticationController
      });

    }]);

});