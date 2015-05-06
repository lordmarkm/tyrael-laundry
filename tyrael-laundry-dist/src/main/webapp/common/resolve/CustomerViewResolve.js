define(function () {
  return {
    customer: ['CustomerService', '$stateParams', function (CustomerService, $stateParams) {
      if ($stateParams.id) {
        //Find by id if id is provided
        return CustomerService.get({id: $stateParams.id});
      } else {
        //Get currently logged in customer
        return CustomerService.get();
      }
    }]
  };
});
