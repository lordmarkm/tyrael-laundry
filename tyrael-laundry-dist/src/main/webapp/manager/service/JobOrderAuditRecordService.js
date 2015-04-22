define(function () {
  return ['$resource', function ($resource) {
    return $resource('audit/joborder/:trackingNo');
  }];
});