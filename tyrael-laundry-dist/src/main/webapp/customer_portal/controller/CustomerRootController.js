define(function () {
  return ['$scope', 'auth', 'CustomerService', function ($scope, auth, CustomerService) {
    $scope.controllerName = 'CustomerRootController';
    
    auth.then(function (authentication) {
      $scope.customer = CustomerService.findByUsername({username: authentication.name});
    });
  }];
});