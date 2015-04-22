define([
   'angular',
   'manager/service/JobOrderAuditRecordService',
   'manager/controller/ManagerRootController',
   'manager/controller/JobOrderViewController',
   'common/controller/JobOrderListController',
   'common/resolve/JobOrderViewResolve'
], function (angular,
    JobOrderAuditRecordService,
    ManagerRootController, JobOrderViewController, JobOrderListController,
    JobOrderViewResolve) {

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
        url: '',
        templateUrl: 'manager/view/dashboard.html'
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
      });

    }]);

});