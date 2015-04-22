define([
   'angular',
   'manager/controller/ManagerRootController'
], function (angular,
    ManagerRootController) {

  console.debug('Configuring manager.module');
  angular.module('manager.module', ['ui.select', 'ngSanitize'])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.manager', {
        url: 'manage',
        templateUrl: 'manager/view/manager.html',
        controller: ManagerRootController
      })
      .state('default.manager.splash', {
        url: '/splash',
        templateUrl: 'manager/view/splash.html'
      });

    }]);

});