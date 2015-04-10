define([
   'angular',
   'core/service/AuthenticationService',
   'core/service/JobOrderService',
   'pos/controller/PosRootController',
   'pos/controller/JobOrderViewController',
   'pos/controller/JobOrderListController',
   'pos/controller/JobOrderCreateController',
   'pos/resolve/JobOrderViewResolve',
   'pos/resolve/JobOrderCreateResolve'
], function (angular,
    AuthenticationService, JobOrderService,
    PosRootController, JobOrderViewController, JobOrderListController, JobOrderCreateController,
    JobOrderViewResolve, JobOrderCreateResolve) {
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
      .state('default.pos.joborder_view', {
        url: '/joborder/view/{trackingNo}',
        templateUrl: 'pos/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      })
      .state('default.pos.joborder_list', {
        url: '/joborder/list',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController
      })
      .state('default.pos.joborder_create', {
        url: '/joborder/new',
        templateUrl: 'pos/view/joborder_create.html',
        controller: JobOrderCreateController,
        resolve: JobOrderCreateResolve
      });

    }]);

});