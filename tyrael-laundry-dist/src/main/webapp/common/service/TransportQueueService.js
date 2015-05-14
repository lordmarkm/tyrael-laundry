define(function () {
  return ['$resource', function ($resource) {
    return $resource('transportq', null, {
      put: {
        method: 'PUT'
      }
    });
  }];
});