define(function () {
  return ['$scope', 'toaster', 'confirm', 'branchInfo',
    function ($scope, toaster, confirm, branchInfo) {

    $scope.branchInfo = branchInfo;

  }];
});