define([
  'angular',
  'anon/controller/AnonymousRootController',
  'anon/controller/JobOrderViewController',
  'common/resolve/JobOrderViewResolve'
], function (angular, AnonymousRootController, JobOrderViewController,
    JobOrderViewResolve) {
  console.debug('Configuring anon.module');
  angular.module('anon.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.anon', {
        url: 'anon',
        templateUrl: 'anon/view/anon.html',
        controller: AnonymousRootController
      })
      .state('default.anon.splash', {
        url: '/splash',
        templateUrl: 'anon/view/splash_anon.html',
      })
      .state('default.anon.joborder_view', {
        url: '/joborder/{trackingNo}',
        templateUrl: 'anon/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      });
  }]);
});