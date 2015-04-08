define(function () {
  return ['$resource', function ($resource) {
    var service = $resource('servicetype');
    service.serviceTypes = service.query();
    return service;
  }];
});