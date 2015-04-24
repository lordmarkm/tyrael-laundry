define([
  'angular',
  'anon/controller/AnonymousRootController',
  'anon/controller/JobOrderViewController',
  'anon/controller/JobOrderClaimController',
  'common/resolve/JobOrderViewResolve'
], function (angular, AnonymousRootController, JobOrderViewController, JobOrderClaimController,
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
        url: '/joborder/view/{trackingNo}',
        templateUrl: 'anon/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      })
      .state('default.anon.joborder_claim', {
        url: '/joborder/claim/{trackingNo}',
        templateUrl: 'anon/view/joborder_claim_and_register.html',
        controller: JobOrderClaimController,
        resolve: JobOrderViewResolve
      });
  }]);
});