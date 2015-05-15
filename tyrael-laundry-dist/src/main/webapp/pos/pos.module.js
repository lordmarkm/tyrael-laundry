define([
   'angular',
   'pos/controller/PosRootController',
   'common/controller/JobOrderViewController',
   'common/controller/JobOrderListController',
   'pos/controller/JobOrderCreateController',
   'common/controller/CustomerViewController',
   'pos/controller/CustomerViewJobOrdersController',
   'common/controller/TransportController',
   'common/resolve/JobOrderViewResolve',
   'pos/resolve/JobOrderCreateResolve',
   'common/resolve/CustomerViewResolve'
], function (angular,
    PosRootController, JobOrderViewController, JobOrderListController, JobOrderCreateController, CustomerViewController, CustomerViewJobOrdersController, TransportController,
    JobOrderViewResolve, JobOrderCreateResolve, CustomerViewResolve) {
  console.debug('Configuring pos.module');
  angular.module('pos.module', ['ui.select', 'ngSanitize'])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.pos', {
        url: 'pos',
        templateUrl: 'pos/view/pos.html',
        controller: PosRootController
      })
      .state('default.pos.splash', {
        url: '/dashboard',
        templateUrl: 'pos/view/dashboard.html'
      })
      .state('default.pos.joborder_view', {
        url: '/joborder/view/{trackingNo}',
        templateUrl: 'common/view/joborder_view.html',
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
      })

      //Customer management
      .state('default.pos.customer_view', {
        url: '/customer/{id}',
        templateUrl: 'common/view/customer_view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve
      })
      .state('default.pos.customer_view_joborders', {
        url: '/customer/{id}/joborders',
        templateUrl: 'pos/view/customer_view_joborders.html',
        controller: CustomerViewJobOrdersController,
        resolve: CustomerViewResolve
      })

      //Transport
      .state('default.pos.transport', {
        url: '/transport',
        templateUrl: 'common/view/transport.html',
        controller: TransportController
      });
    }]);

});