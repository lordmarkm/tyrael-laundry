define([
   'angular',
   'manager/service/JobOrderAuditRecordService',
   'manager/controller/ManagerRootController',
   'manager/controller/JobOrderViewController',
   'common/controller/JobOrderListController',
   'common/controller/CustomerViewController',
   'pos/controller/CustomerViewJobOrdersController',
   'manager/controller/PriceManagementController',
   'common/controller/TransportController',
   'manager/controller/ManagerDashboardController',
   'common/resolve/JobOrderViewResolve',
   'manager/resolve/PriceManagementResolve',
   'common/resolve/CustomerViewResolve'
], function (angular,
    JobOrderAuditRecordService,
    ManagerRootController, JobOrderViewController, JobOrderListController, CustomerViewController, CustomerViewJobOrdersController, PriceManagementController, TransportController, ManagerDashboardController,
    JobOrderViewResolve, PriceManagementResolve, CustomerViewResolve) {

  console.debug('Configuring manager.module');
  angular.module('manager.module', ['ui.select', 'ngSanitize'])
    .service('JobOrderAuditRecordService', JobOrderAuditRecordService)
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.manager', {
        url: 'manage',
        templateUrl: 'manager/view/manager.html',
        controller: ManagerRootController
      })
      .state('default.manager.splash', {
        url: '/dashboard',
        templateUrl: 'manager/view/dashboard.html',
        controller: ManagerDashboardController
      })
      .state('default.manager.joborder_view', {
        url: '/joborder/view/{trackingNo}',
        templateUrl: 'manager/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      })
      .state('default.manager.joborder_list', {
        url: '/joborder/list',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController
      })

      //Customer management
      .state('default.manager.customer_view', {
        url: '/customer/{id}',
        templateUrl: 'common/view/customer_view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve
      })
      .state('default.manager.customer_view_joborders', {
        url: '/customer/{id}/joborders',
        templateUrl: 'manager/view/customer_view_joborders.html',
        controller: CustomerViewJobOrdersController,
        resolve: CustomerViewResolve
      })

      //Pickup/delivery
      .state('default.manager.transport', {
        url: '/transport',
        templateUrl: 'common/view/transport.html',
        controller: TransportController
      })

      //Price management
      .state('default.manager.price_management', {
        url: '/prices',
        templateUrl: 'manager/view/price_management.html',
        controller: PriceManagementController,
        resolve: PriceManagementResolve
      });

    }]);

});