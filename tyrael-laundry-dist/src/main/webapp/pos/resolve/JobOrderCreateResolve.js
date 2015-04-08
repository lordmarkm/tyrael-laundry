define(function () {
  return {
    serviceTypes: ['ServiceTypeService', function (ServiceTypeService) {
      return ServiceTypeService.query().$promise;
    }]
  };
});
