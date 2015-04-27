define(function () {
  return ['$scope', '$state', 'toaster', 'JobOrderService', function ($scope, $state, toaster, JobOrderService) {

    $scope.checkJobOrder = function (trackingNo) {
      $scope.jobOrder = JobOrderService.get({trackingNo: trackingNo}, function (jobOrder) {
        if (!jobOrder.trackingNo) {
          toaster.pop('error', 'Receipt not found', 'Receipt not found. Please check the tracking number and try again.');
        } else {
          toaster.pop('success', 'Receipt found', 'Receipt found.');
          $state.go('default.anon.joborder_view', {trackingNo: trackingNo});
        }
      }, function () {
        toaster.pop('error', 'Could not retrieve job order', 'Job Order may be owned by a registered user. If you own this job order, please log in to see it.');
      });
    };

  }];
});