define([
   'angular',
   'pos/controller/PosRootController',
   'pos/controller/JobOrderViewController',
   'common/controller/JobOrderListController',
   'pos/controller/JobOrderCreateController',
   'pos/controller/CustomerViewController',
   'pos/controller/CustomerViewJobOrdersController',
   'pos/resolve/JobOrderViewResolve',
   'pos/resolve/JobOrderCreateResolve',
   'pos/resolve/CustomerViewResolve'
], function (angular,
    PosRootController, JobOrderViewController, JobOrderListController, JobOrderCreateController, CustomerViewController, CustomerViewJobOrdersController,
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
        url: '',
        templateUrl: 'pos/view/dashboard.html'
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
      })

      //Customer management
      .state('default.pos.customer_view', {
        url: '/customer/{id}',
        templateUrl: 'pos/view/customer_view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve
      })
      .state('default.pos.customer_view_joborders', {
        url: '/customer/{id}/joborders',
        templateUrl: 'pos/view/customer_view_joborders.html',
        controller: CustomerViewJobOrdersController,
        resolve: CustomerViewResolve
      });

    }]);

});