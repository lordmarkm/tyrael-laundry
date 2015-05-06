define(function () {
  return ['$scope', 'billingInfo',
    function ($scope, billingInfo) {

    $scope.billingInfo = billingInfo;

  }];
});