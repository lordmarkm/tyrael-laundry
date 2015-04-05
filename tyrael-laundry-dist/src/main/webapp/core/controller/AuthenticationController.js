define(function () {
  return ['$scope', '$state', '$stateParams', 'auth', function ($scope, $state, $stateParams, auth) {
    $scope.controllerName = "Auth Controller";
    $scope.params = $stateParams;
  }];
});