define([
   'angular'
], function (angular) {
  console.debug('Configuring core.module');
  angular.module('core.module', [])
    .config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {

      $urlRouterProvider
        .when('', '/');

      $stateProvider.state('default', {
        url: '/',
        template: '<ui-view></ui-view>',
        abstract: true
      })
      $stateProvider.state('default.splash', {
        url: '',
        templateUrl: 'core/view/core.html'
      });

    }]);

});