define(function () {
  return ['$scope', '$state', 'reportParams', 'toaster', 'confirm',
    function ($scope, $state, reportParams, toaster, confirm) {

    $scope.reportParams = reportParams;

  }];
});