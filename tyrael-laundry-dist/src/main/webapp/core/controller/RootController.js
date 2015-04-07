define(function () {
  return ['$scope', 'auth', function ($scope, auth) {
    $scope.controllerName = "Root Controller";

    //Check user authorities and redirect where appropriate
    auth.then(function(authentication) {
      $scope.principal = authentication.principal;
    });
  }];
});