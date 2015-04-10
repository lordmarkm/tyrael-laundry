define([
   'angular',
   'common/service/GenericConfirmService',
   'core/service/JobOrderService',
   'core/service/CustomerService',
   'core/service/ServiceTypeService',
   'common/controller/JobOrderListController'
], function (angular, GenericConfirmService, JobOrderService, CustomerService, ServiceTypeService,
    JobOrderListController) {
  console.debug('Configuring common.module');
  angular.module('common.module', [])
    .service('confirm', GenericConfirmService)
    .service('JobOrderService', JobOrderService)
    .service('CustomerService', CustomerService)
    .service('ServiceTypeService', ServiceTypeService)
    .config(['$stateProvider', function ($stateProvider) {

      $stateProvider.state('default.joborder_list', {
        url: '/joborder/list',
        templateUrl: 'common/view/joborder_list.html',
        controller: JobOrderListController
      });

    }]);

});