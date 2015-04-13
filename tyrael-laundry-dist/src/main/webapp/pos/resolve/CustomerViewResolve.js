define(function () {
  return {
    customer: ['CustomerService', '$stateParams', function (CustomerService, $stateParams) {
      return CustomerService.get({id: $stateParams.id});
    }]
  };
});
