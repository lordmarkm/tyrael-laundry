define([
   'angular',
   'common/service/GenericConfirmService',
   'core/service/JobOrderService',
   'core/service/CustomerService',
   'core/service/CustomerAccountService',
   'core/service/ServiceTypeService',
   'common/service/DeliveryService',
   'common/controller/JobOrderListController'
], function (angular, GenericConfirmService, JobOrderService, CustomerService, CustomerAccountService, ServiceTypeService, DeliveryService,
    JobOrderListController) {
  console.debug('Configuring common.module');
  angular.module('common.module', [])
    .service('confirm', GenericConfirmService)
    .service('JobOrderService', JobOrderService)
    .service('CustomerService', CustomerService)
    .service('CustomerAccountService', CustomerAccountService)
    .service('ServiceTypeService', ServiceTypeService)
    .service('DeliveryService', DeliveryService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.joborder_list', {
        url: '/joborder/list',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController
      });

    }]);

});