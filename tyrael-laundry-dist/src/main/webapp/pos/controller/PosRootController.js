define(function () {
  return ['$scope', '$state', '$stateParams', function ($scope, $state, $stateParams) {
    $scope.controllerName = "POS Root Controller";
    $scope.params = $stateParams;
    $scope.recentData = {
        customer: {}
    };

    $('#side-menu').metisMenu();
  }];
});