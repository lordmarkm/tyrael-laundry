define(function () {
  return ['$scope', '$stateParams', function ($scope, $stateParams) {
    $scope.controllerName = "Authentication Root Controller";
    $scope.params = $stateParams;
  }];
});