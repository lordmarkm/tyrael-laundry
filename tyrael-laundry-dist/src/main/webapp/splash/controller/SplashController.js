define(function () {
  return ['$scope', '$state', 'toaster', 'auth', 'JobOrderService', function ($scope, $state, toaster, auth, JobOrderService) {
    $scope.controllerName = "Splash Controller";
    $scope.checkJobOrder = function (trackingNo) {
      $scope.jobOrder = JobOrderService.get({trackingNo: trackingNo}, function (jobOrder) {
        if (!jobOrder.trackingNo) {
          toaster.pop('error', 'Receipt not found', 'Receipt not found. Please check the tracking number and try again.');
        } else {
          toaster.pop('success', 'Receipt found', 'Receipt found.');
        }
      });
    };
  }];
});