define(function () {
  return ['$scope', 'toaster', 'confirm', 'branchInfo', 'BranchInfoService',
    function ($scope, toaster, confirm, branchInfo, BranchInfoService) {

    function setBranchInfo(branchInfo) {
      $scope.branchInfo = {
          id: branchInfo.id,
          name: branchInfo.name,
          minimumJobOrderAmount: branchInfo.minimumJobOrderAmount,
          brand: branchInfo.brand
      };
    }
    branchInfo.$promise.then(function(branchInfo){
      setBranchInfo(branchInfo);
    });

    $scope.saveBranch = function () {
      confirm.confirm('Confirm branch update', 'Update branch info?').result.then(function (proceed) {
        if (proceed) {
          BranchInfoService.save($scope.branchInfo, function (saved) {
            setBranchInfo(saved);
            toaster.pop('success', 'Branch updated');
          });
        }
      });
    };

  }];
});