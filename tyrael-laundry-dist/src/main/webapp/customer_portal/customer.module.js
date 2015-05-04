define([
  'angular',
  'core/service/CustomerService',
  'core/service/CustomerAccountService',
  'customer_portal/controller/CustomerRootController',
  'common/controller/JobOrderListController',
  'common/controller/JobOrderViewController',
  'common/resolve/JobOrderViewResolve'
], function (angular, CustomerService, CustomerAccountService, CustomerRootController, JobOrderListController, JobOrderViewController,
    JobOrderViewResolve) {
  console.debug('Configuring customer.module');
  angular.module('customer.module', [])
    .service('CustomerService', CustomerService)
    .service('CustomerAccountService', CustomerAccountService)
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.customer', {
        url: 'portal',
        templateUrl: 'customer_portal/view/index.html',
        controller: CustomerRootController
      })
      .state('default.customer.joborder_list', {
        url: '/joborders',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController
      })
      .state('default.customer.joborder_view', {
        url: '/joborder/{trackingNo}',
        templateUrl: 'common/view/joborder_view.html',
        controller: JobOrderViewController,
        resolve: JobOrderViewResolve
      });
  }]);
});