define(function () {
  return ['$scope', '$modal', '$q', '$filter', 'toaster', 'ngTableParams', 'confirm', 'JobOrderService',
          function ($scope, $modal, $q, $filter, toaster, ngTableParams, confirm, JobOrderService) {

    //Filter
    $scope.filter = {
        status: ''
    };

    //List
    $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2, 5,10,25,50,100], //determines pager
      getData: function($defer, params) {

        //filter
        params.$params.term = term($scope.filter.term) || '';
        params.$params.status = $scope.filter.status;

        JobOrderService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    function term(term) {
      if (isAuthorized('ROLE_CUSTOMER')) {
        term += ';customerId='
      }
    }

    $scope.reloadTable = function () {
      if ($scope.tableParams.page() == 1) {
        $scope.tableParams.reload();
      } else {
        $scope.tableParams.page(1);
      }
    };

    //Change status
    $scope.onStatusChange = function (jobOrder) {
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
            toaster.pop('success', 'Save success', 'Successfully saved job order with tracking no. ' + savedJobOrder.trackingNo);
          }, function () {
            toaster.pop('error', 'Save error', 'Could not save Job Order');
          });
        } else {
          $scope.tableParams.reload();
        }
      });
    };

    //Some utility methods
    $scope.isClosedOrCancelled = function (jobOrder) {
      return jobOrder.status == 'CLOSED' || jobOrder.status == 'CANCELLED';
    };

  }];
});