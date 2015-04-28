define(function () {
  return {
    jobOrder: ['JobOrderService', '$stateParams', function (JobOrderService, $stateParams) {
      return JobOrderService.get({trackingNo: $stateParams.trackingNo}).$promise;
    }],
    serviceTypes: ['ServiceTypeService', function (ServiceTypeService) {
      return ServiceTypeService.query().$promise;
    }]
  };
});
