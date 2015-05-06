define(function () {
  return ['$resource', function ($resource) {
    return $resource('billing/customer/:customerId');
  }];
});