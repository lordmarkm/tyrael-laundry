define(function () {
  return ['$scope', '$modal', '$q', '$filter', 'toaster', 'moment', 'ngTableParams', 'auth', 'confirm', 'JobOrderService', 'CustomerAccountService', 'DeliveryService',
          function ($scope, $modal, $q, $filter, toaster, moment, ngTableParams, auth, confirm, JobOrderService, CustomerAccountService, DeliveryService) {

    var authResolved = $q.defer();

    //Filter
    $scope.dateFormat = 'yyyy MMM-dd';
    $scope.today = moment();
    $scope.filter = {
        status: ''
    };
    $scope.clearFilters = function () {
      delete $scope.filter.datefrom;
      delete $scope.filter.dateto;
      delete $scope.filter.status;
      delete $scope.filter.term;
      $scope.reloadTable();
    };

    $scope.open = function (picker, evt) {
      evt.preventDefault();
      evt.stopPropagation();
      
      if (picker === 'from') {
        $scope.openDatefrom = true;
      } else if (picker === 'to') {
        $scope.openDateto = true;
      }
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
        and('customerId==', $scope.customerAccount.customer.id);
      }

      //If status is not ALL, append it
      if ($scope.filter.status) {
        and('status==', $scope.filter.status);
      }

      //Filter by Date received range
      if ($scope.filter.datefrom) {
        and('dateReceived>=', moment($scope.filter.datefrom).format('YYYY-MM-DD'));
      }
      if ($scope.filter.dateto) {
        and('dateReceived<=', moment($scope.filter.dateto).add(1, 'days').format('YYYY-MM-DD'));
      }

      function and(selector, arg) {
        if (rql.length) {
          rql += ';';
        }
        rql += (selector + arg);
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

    //Request delivery
    $scope.requestDelivery = function (jobOrder) {
      showDeliveryRequestModal().result.then(function (deliveryRequest) {
        DeliveryService.save(deliveryRequest, function (savedDeliveryRequest) {
          jobOrder.deliveryStatus = savedDeliveryRequest.status;
          toaster.pop('success', 'Delivery request created');
        });
      });

      function showDeliveryRequestModal() {
        return $modal.open({
          templateUrl: 'common/view/modal_delivery_request.html',
          controller: ['$scope', '$state', '$modalInstance', 'deliveryRequest', function($scope, $state, $modalInstance, deliveryRequest) {
            $scope.jobOrder = jobOrder;
            $scope.deliveryRequest = deliveryRequest;
            $scope.proceed = function () {
              $modalInstance.close(deliveryRequest);
            };
            $scope.cancel = function () {
              $modalInstance.dismiss();
            };
          }],
          resolve: {
            deliveryRequest: function () {
              return {
                  customer: $scope.customerAccount.customer,
                  jobOrder: jobOrder,
                  address: {
                    addressLine1: $scope.customerAccount.customer.address ? $scope.customerAccount.customer.address.addressLine1 || '' : '',
                    addressLine2: $scope.customerAccount.customer.address ? $scope.customerAccount.customer.address.addressLine2 || '' : ''
                    //city: customer.address.city,
                    //province: customer.address.province,
                    //zip: customer.address.zip
                  },
                  status: 'NEW'
              };
              return jobOrder;
            }
          }
        });
      }
    };

    //Some utility methods
    $scope.isClosedOrCancelled = function (jobOrder) {
      return jobOrder.status == 'CLOSED' || jobOrder.status == 'CANCELLED';
    };

  }];
});