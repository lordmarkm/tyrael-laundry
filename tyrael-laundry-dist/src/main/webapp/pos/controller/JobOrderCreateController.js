define(function () {
  return ['$scope', '$modal', 'toaster', 'serviceTypes', 'CustomerService', 'ServiceTypeService', 'JobOrderService',
    function ($scope, $modal, toaster, serviceTypes, CustomerService, ServiceTypeService, JobOrderService) {

    function resetPage() {
      $scope.customerHolder = {};
      ServiceTypeService.query().$promise.then(function (serviceTypes) {
        $scope.serviceTypes = serviceTypes;
        $scope.serviceTypeHolder = {
            serviceType: $scope.serviceTypes[0]
        };
      });
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

    //Create new customer
    $scope.createNewCustomer = function () {
      CustomerService.createCustomer().then(function (customer) {
        $scope.customerHolder.customer = customer;
      });
    };

    //Initialize/process service types
    $scope.serviceTypes = serviceTypes;
    $scope.serviceTypeHolder = {
        serviceType: $scope.serviceTypes[0]
    };
    $scope.setWeight = function (weight) {
      $scope.serviceTypeHolder.serviceType.weight = weight;
      toaster.pop('success', 'Service added', $scope.serviceTypeHolder.serviceType.label + ': ' + weight + ' Kg');
    };

    //Validate & Submit job order
    $scope.createJobOrder = function () {
      if (!validateJobOrder()) {
        return;
      }

      showConfirmJobOrderDialog().result.then(function(jobOrder) {
        if (jobOrder) {
          JobOrderService.save(jobOrder, function (savedJob) {
            toaster.pop('success', 'Job Order saved', 'Job order w/ tracking number ' + savedJob.trackingNo + ' saved.');
            resetPage();
            showSaveCompleteDialog(savedJob);
          }, function () {
            toaster.pop('error', 'Error saving Job order');
          });
        }
      });

      function validateJobOrder() {
        if (!$scope.customerHolder.customer) {
          toaster.pop('error', 'Customer required', 'Please select an existing customer record or create a new one');
          return false;
        }

        var totalWeight = 0;
        for (var i in $scope.serviceTypes) {
          totalWeight += $scope.serviceTypes[i].weight || 0;
        }
        if (!totalWeight) {
          toaster.pop('error', 'Services required', 'Please select at least one service and enter the corresponding weight');
          return false;
        }

        return true;
      }

      function showConfirmJobOrderDialog() {
        return $modal.open({
          templateUrl: 'modal-confirm-joborder',
          controller: ['$scope', '$modalInstance', 'jobOrder', function($scope, $modalInstance, jobOrder) {
            $scope.jobOrder = jobOrder;
            $scope.proceed = function () {
              $modalInstance.close(jobOrder);
            };
            $scope.cancel = function () {
              $modalInstance.close(false);
            };
          }],
          resolve: {
            jobOrder: function () {
              var jobOrder = {
                  customer: $scope.customerHolder.customer,
                  jobServices: [],
                  jobItems: [],
                  totalAmount: 0,
                  totalAmountPaid: 0,
                  status: 'NEW'
              };
              for (var i in $scope.serviceTypes) {
                var serviceType = $scope.serviceTypes[i];
                if (!serviceType.weight) {
                  continue;
                }

                var amount = serviceType.weight * serviceType.pricePerKilo;
                jobOrder.totalAmount += amount;
                jobOrder.jobServices.push({
                  serviceType: serviceType,
                  weightInKilos: serviceType.weight,
                  pricePerKilo: serviceType.pricePerKilo,
                  amount: amount
                });
              }
              return jobOrder;
            }
          }
        });
      }

      function showSaveCompleteDialog(savedJob) {
        $modal.open({
          templateUrl: 'modal-create-success',
          controller: ['$scope', '$state', '$modalInstance', function($scope, $state, $modalInstance) {
            $scope.jobOrder = savedJob;
            $scope.backToDashboard = function () {
              $modalInstance.close();
              $state.go('default.pos.splash');
            };
            $scope.viewJobOrder = function () {
              $modalInstance.close();
              $state.go('default.pos.joborder_view', {trackingNo: savedJob.trackingNo});
            };
            $scope.createNew = function () {
              $modalInstance.close();
            };
          }]
        });
      }
    };





  }];
});