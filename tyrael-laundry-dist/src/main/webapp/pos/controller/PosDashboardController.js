define(function () {
  return ['$scope', 'BranchInfoService',
    function ($scope, BranchInfoService) {

    $scope.branchInfo = BranchInfoService.get();


  }];
});