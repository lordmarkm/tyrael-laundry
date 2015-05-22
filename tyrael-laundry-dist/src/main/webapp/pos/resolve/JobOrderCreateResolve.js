define(function () {
  return {
    serviceTypes: ['ServiceTypeService', function (ServiceTypeService) {
      return ServiceTypeService.query().$promise;
    }],
    jobItemTypes: ['JobItemTypeService', function (JobItemTypeService) {
      return JobItemTypeService.query();
    }]
  };
});
