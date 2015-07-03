define([
   'angular',
   'manager/service/JobOrderAuditRecordService',
   'manager/controller/ManagerRootController',
   'manager/controller/JobOrderViewController',
   'common/controller/JobOrderListController',
   'common/controller/CustomerViewController',
   'pos/controller/CustomerViewJobOrdersController',
   'manager/controller/PriceManagementController',
   'manager/controller/BranchManagementController',
   'common/controller/TransportController',
   'manager/controller/ManagerDashboardController',
   'manager/controller/ReportsController',
   'common/resolve/JobOrderViewResolve',
   'manager/resolve/PriceManagementResolve',
   'common/resolve/CustomerViewResolve',
   'manager/resolve/BranchManagementResolve',
   'manager/resolve/SummaryReportResolve',
   'manager/resolve/IncomeReportResolve',
   'manager/resolve/JobOrderReportResolve'
], function (angular,
    JobOrderAuditRecordService,
    ManagerRootController, JobOrderViewController, JobOrderListController, CustomerViewController, CustomerViewJobOrdersController, PriceManagementController, BranchManagementController, TransportController, ManagerDashboardController, ReportsController,
    JobOrderViewResolve, PriceManagementResolve, CustomerViewResolve, BranchManagementResolve, SummaryReportResolve, IncomeReportResolve, JobOrderReportResolve) {

  console.debug('Configuring manager.module');
  angular.module('manager.module', ['ui.select', 'ngSanitize'])
    .service('JobOrderAuditRecordService', JobOrderAuditRecordService)
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.manager', {
        url: 'manage',
        templateUrl: 'manager/view/manager.html',
        controller: ManagerRootController,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.splash', {
        url: '/dashboard',
        templateUrl: 'manager/view/dashboard.html',
        controller: ManagerDashboardController,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.joborder_view', {
        url: '/joborder/view/{trackingNo}',
        templateUrl: 'manager/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.joborder_list', {
        url: '/joborder/list',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController,
        access: 'ROLE_MANAGER'
      })

      //Customer management
      .state('default.manager.customer_view', {
        url: '/customer/{id}',
        templateUrl: 'common/view/customer_view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.customer_view_joborders', {
        url: '/customer/{id}/joborders',
        templateUrl: 'manager/view/customer_view_joborders.html',
        controller: CustomerViewJobOrdersController,
        resolve: CustomerViewResolve,
        access: 'ROLE_MANAGER'
      })

      //Pickup/delivery
      .state('default.manager.transport', {
        url: '/transport',
        templateUrl: 'common/view/transport.html',
        controller: TransportController,
        access: 'ROLE_MANAGER'
      })

      //Reports
      .state('default.manager.reports', {
        url: '/reports',
        template: '<ui-view></ui-view>',
        abstract: true,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.reports.summary', {
        url: '/summary',
        templateUrl: 'manager/view/reports.html',
        controller: ReportsController,
        resolve: SummaryReportResolve,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.reports.income', {
        url: '/income',
        templateUrl: 'manager/view/reports.html',
        controller: ReportsController,
        resolve: IncomeReportResolve,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.reports.joborders', {
          url: '/joborders',
          templateUrl: 'manager/view/reports.html',
          controller: ReportsController,
          resolve: JobOrderReportResolve,
          access: 'ROLE_MANAGER'
      })

      //More management options
      .state('default.manager.price_management', {
        url: '/prices',
        templateUrl: 'manager/view/price_management.html',
        controller: PriceManagementController,
        resolve: PriceManagementResolve,
        access: 'ROLE_MANAGER'
      })
      .state('default.manager.branch_management', {
        url: '/branch',
        templateUrl: 'manager/view/branch_management.html',
        controller: BranchManagementController,
        resolve: BranchManagementResolve,
        access: 'ROLE_MANAGER'
      });

    }]);

});