define([
  'angular',
  'core/service/CustomerService',
  'core/service/CustomerAccountService',
  'common/service/CustomerBillingInfoService',
  'customer_portal/controller/CustomerRootController',
  'common/controller/JobOrderListController',
  'common/controller/JobOrderViewController',
  'common/controller/CustomerViewController',
  'common/controller/CustomerBillingController',
  'common/resolve/JobOrderViewResolve',
  'common/resolve/CustomerViewResolve',
  'common/resolve/CustomerBillingResolve'
], function (angular, CustomerService, CustomerAccountService, CustomerBillingInfoService,
    CustomerRootController, JobOrderListController, JobOrderViewController, CustomerViewController, CustomerBillingController,
    JobOrderViewResolve, CustomerViewResolve, CustomerBillingResolve) {
  console.debug('Configuring customer.module');
  angular.module('customer.module', [])
    .service('CustomerService', CustomerService)
    .service('CustomerAccountService', CustomerAccountService)
    .service('CustomerBillingInfoService', CustomerBillingInfoService)
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
      })
      .state('default.customer.profile', {
        url: '/profile',
        templateUrl: 'common/view/customer_view.html',
        controller: CustomerViewController,
        resolve: CustomerViewResolve
      })
      .state('default.customer.billing_details', {
        url: '/billing',
        templateUrl: 'common/view/customer_billing.html',
        controller: CustomerBillingController,
        resolve: CustomerBillingResolve
      });
  }]);
});