define(function () {
  return ['$scope', '$q', '$modal', 'toaster', 'customer', 'CustomerService', 'JobOrderService',
    function ($scope, $q, $modal, toaster, customer, CustomerService, JobOrderService) {

      $scope.customer = customer;

      $scope.editName = function () {
        var defer = $q.defer();

        showCreateCustomerDialog().result.then(function (customer) {
          if (customer) {
            CustomerService.save(customer, function (savedCustomer) {
              toaster.pop('success', 'Record updated', savedCustomer.formattedName + '\'s customer record has been updated.');
              defer.resolve(savedCustomer);
            }, function () {
              toaster.pop('error', 'Error updating customer');
            });
          }
        });

        return defer.promise;

        function showCreateCustomerDialog() {
          return $modal.open({
            templateUrl: 'core/view/modal_create_customer.html',
            controller: ['$scope', '$modalInstance', 'customer',
              function ($modalScope, $modalInstance, customer) {
                console.debug('resovled cs: ' + JSON.stringify(customer));
                $modalScope.customer = customer;
                $modalScope.modalTitle = 'Edit customer name';
                $modalScope.proceed = function () {
                  $scope.customer.name = $modalScope.customer.name;
                  $modalInstance.close($scope.customer);
                };
                $modalScope.cancel = function () {
                  $modalInstance.close(false);
                };
            }],
            resolve: {
              customer: function () {
                return {
                  name: {
                    surname: $scope.customer.name.surname,
                    givenName: $scope.customer.name.givenName,
                    middleName: $scope.customer.name.middleName
                  }
                };
              }
            }
          });
        }
      };

  }];
});