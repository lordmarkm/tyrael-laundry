define(function () {
  return ['$scope', '$modal', 'toaster', 'serviceTypes', 'jobOrder', 'CustomerService', 'ServiceTypeService', 'JobOrderService',
    function ($scope, $modal, toaster, serviceTypes, jobOrder, CustomerService, ServiceTypeService, JobOrderService) {

    $scope.jobOrder = jobOrder;

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
      showCreateCustomerDialog().result.then(function (customer) {
        if (customer) {
          CustomerService.save(customer, function (savedCustomer) {
            $scope.customerHolder.customer = savedCustomer;
            toaster.pop('success', 'Customer created', savedCustomer.formattedName + '\'s customer record has been created.');
          }, function () {
            toaster.pop('error', 'Error saving customer');
          });
        }
      });

      function showCreateCustomerDialog() {
        return $modal.open({
          templateUrl: 'modal-create-customer',
          controller: ['$scope', '$modalInstance',
            function ($scope, $modalInstance) {
              $scope.customer = {};
              $scope.proceed = function () {
                $modalInstance.close($scope.customer);
              };
              $scope.cancel = function () {
                $modalInstance.close(false);
              };
          }]
        });
      }
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
                  totalAmount: 0
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
    };





  }];
});