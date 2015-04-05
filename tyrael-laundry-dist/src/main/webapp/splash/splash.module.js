define([
  'angular',
  'splash/controller/SplashController'
], function (angular, SplashController) {
  console.debug('Configuring splash.module');
  angular.module('splash.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.splash', {
        url: '',
        templateUrl: 'splash/view/splash.html',
        controller: SplashController
      });
  }]);
});