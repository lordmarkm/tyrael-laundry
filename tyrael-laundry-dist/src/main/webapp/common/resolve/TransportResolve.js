define(function () {
  return {
    customer: ['CustomerService', function (CustomerService) {
      return CustomerService.get();
    }]
  };
});
