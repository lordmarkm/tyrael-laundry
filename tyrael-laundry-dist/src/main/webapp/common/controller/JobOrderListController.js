define(function () {
  return ['$scope', '$modal', '$q', '$filter', 'toaster', 'ngTableParams', 'auth', 'confirm', 'JobOrderService', 'CustomerAccountService',
          function ($scope, $modal, $q, $filter, toaster, ngTableParams, auth, confirm, JobOrderService, CustomerAccountService) {

    var authResolved = $q.defer();

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
        authResolved.promise.then(function () {
          params.$params.term = term($scope.filter.term);
          JobOrderService.get(params.$params, function(response) {
            params.total(response.total);
            $defer.resolve(response.data);
          });
        });
      }
    });

    auth.then(function () {
      if ($scope.isAuthorized('ROLE_CUSTOMER')) {
        $scope.customerAccount = CustomerAccountService.getCurrent(function () {
          authResolved.resolve();
        });
      } else {
        authResolved.resolve();
      }
    });
    function term(term) {
      var rql = '';
      if (term) {
        if (rql.length) {
          rql += ';';
        }
        rql += '(trackingNo==' + term + ',customerSurname==' + term + '*,customerGivenName==' + term + '*)';
      }

      //If customer, show only the job orders for the currently logged in customer
      if ($scope.customerAccount && $scope.customerAccount.customer) {
        if (rql.length) {
          rql += ';';
        }
        rql += ('customerId==' + $scope.customerAccount.customer.id);
      }

      //If status is not ALL, append it
      if ($scope.filter.status) {
        if (rql.length) {
          rql += ';';
        }
        rql += ('status==' + $scope.filter.status);
      }
      return rql;
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