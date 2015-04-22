define(function () {
  return ['$scope', '$modal', '$q', '$filter', '$stateParams', 'toaster', 'confirm', 'serviceTypes', 'jobOrder', 'CustomerService', 'ServiceTypeService', 'JobOrderService', 'JobOrderAuditRecordService',
    function ($scope, $modal, $q, $filter, $stateParams, toaster, confirm, serviceTypes, jobOrder, CustomerService, ServiceTypeService, JobOrderService, JobOrderAuditRecordService) {

    //Audit records
    function refreshAudit() {
      $scope.auditRecords = JobOrderAuditRecordService.query({trackingNo: $stateParams.trackingNo});
    }
    refreshAudit();

    // + '' is to force creation of a new object instead of a reference. There must be a better way to do this.
    $scope.jobOrder = jobOrder;
    if ($scope.recentData) {
      $scope.recentData.jobOrder = jobOrder;
    }

    //Search for existing customer
    $scope.customerHolder = {};
    $scope.customers = [];
    $scope.refreshCustomers = function(term) {
      var params = {term: term, page: 1, count: 10};
      CustomerService.get(params, function (page) {
        $scope.customers = page.data;
      });
    };

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
            refreshAudit();
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
              refreshAudit();
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
        refreshAudit();
      }, function () {
        toaster.pop('error', 'Error saving Job Order');
      });
    };

  }];
});