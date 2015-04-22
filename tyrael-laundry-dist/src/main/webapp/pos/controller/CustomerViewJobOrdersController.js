define(function () {
  return ['$scope', '$modal', '$q', '$filter', 'confirm', 'toaster', 'ngTableParams', 'customer', 'CustomerService', 'JobOrderService',
    function ($scope, $modal, $q, $filter, confirm, toaster, ngTableParams, customer, CustomerService, JobOrderService) {

      $scope.customer = customer;
      $scope.recentData.customer = customer;

      //List
      $scope.tableParams = new ngTableParams({
        page: 1,
        count: 5
      }, {
        total: 0,
        counts: [2, 5,10,25,50,100], //determines pager
        getData: function($defer, params) {

          //search
          params.$params.term = '';
          params.$params.status = '';
          params.$params.customer = customer.id;
          JobOrderService.get(params.$params, function(response) {
            params.total(response.total);
            $defer.resolve(response.data);
          });
        }
      });

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
              toaster.pop('error');
            });
          } else {
            $scope.tableParams.reload();
          }
        });
      };

  }];
});