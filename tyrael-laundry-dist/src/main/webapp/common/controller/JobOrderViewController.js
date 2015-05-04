define(function () {
  return ['$scope', '$modal', '$q', '$filter', 'toaster', 'confirm', 'serviceTypes', 'jobOrder', 'CustomerService', 'ServiceTypeService', 'JobOrderService',
    function ($scope, $modal, $q, $filter, toaster, confirm, serviceTypes, jobOrder, CustomerService, ServiceTypeService, JobOrderService) {

    $scope.jobOrder = jobOrder;
    if ($scope.recentData) {
      $scope.recentData.jobOrder = jobOrder;
    }

    $scope.onStatusChange = function (jobOrder, oldStatus) {
      var proceed = $q.defer();

      if (jobOrder.status === 'PAID_CLAIMED' && jobOrder.totalAmountPaid == 0) {
        confirm.confirm('Confirm paid and claimed', 'This will create a payment record of ' + $filter('currency')(jobOrder.totalAmount, 'Php '))
        .result.then(function (ok) {
          if (ok) {
            jobOrder.totalAmountPaid = jobOrder.totalAmount;
          }
          proceed.resolve(ok);
        });
      } else {
        proceed.resolve(true);
      }

      proceed.promise.then(function (p) {
        if (p) {
          JobOrderService.save(jobOrder, function (savedJobOrder) {
            toaster.pop('success', 'Save success', 'Successfully saved Job Order with tracking no. ' + savedJobOrder.trackingNo);
            $scope.jobOrder = savedJobOrder;
            originalStatus = savedJobOrder.status;
          }, function () {
            toaster.pop('error', 'Error saving Job Order');
          });
        } else {
          $scope.jobOrder.status = oldStatus;
        }
      });
    };

    $scope.lostAndFound = function () {
      $modal.open({
        templateUrl: 'modal-lostandfound',
        controller: ['$scope', '$modalInstance', 'lostAndFound', function($modalScope, $modalInstance, lostAndFound) {
          $modalScope.lostAndFound = lostAndFound;
          $modalScope.proceed = function () {
            $modalInstance.dismiss();
            $scope.jobOrder.lostAndFoundItems.push($modalScope.lostAndFound);
            JobOrderService.save($scope.jobOrder, function (savedJobOrder) {
              toaster.pop('success', 'Lost And Found item added', 'Successfully saved Job Order with tracking no. ' + savedJobOrder.trackingNo);
              $scope.jobOrder = savedJobOrder;
            }, function () {
              toaster.pop('error', 'Error saving Job Order');
            });
          };
          $modalScope.cancel = function () {
            $modalInstance.dismiss();
          };
        }],
        resolve: {
          lostAndFound: function () {
            return {
              status: 'FOUND'
            };
          }
        }
      });
    };

    $scope.onLostAndfoundStatusChange = function () {
      JobOrderService.save($scope.jobOrder, function (savedJobOrder) {
        toaster.pop('success', 'Save success', 'Successfully updated Lost and found item for Job Order with tracking no. ' + savedJobOrder.trackingNo);
      }, function () {
        toaster.pop('error', 'Error saving Job Order');
      });
    };

    //Some utility methods
    $scope.isClosedOrCancelled = function (jobOrder) {
      return jobOrder.status == 'CLOSED' || jobOrder.status == 'CANCELLED';
    };

  }];
});