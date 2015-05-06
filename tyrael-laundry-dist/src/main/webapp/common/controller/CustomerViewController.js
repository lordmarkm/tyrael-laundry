define(function () {
  return ['$scope', '$modal', 'toaster', 'customer', 'CustomerService', 'JobOrderService',
    function ($scope, $modal, toaster, customer, CustomerService, JobOrderService) {

      $scope.customer = customer;
      $scope.recentData.customer = customer;

      $scope.editName = function () {
        showCreateCustomerDialog().result.then(function (customer) {
          if (customer) {
            CustomerService.save(customer, function (savedCustomer) {
              toaster.pop('success', 'Record updated', savedCustomer.formattedName + '\'s customer record has been updated.');
              $scope.customer = savedCustomer;
            }, function () {
              toaster.pop('error', 'Error updating customer');
            });
          }
        });

        function showCreateCustomerDialog() {
          return $modal.open({
            templateUrl: 'core/view/modal_create_customer.html',
            controller: ['$scope', '$modalInstance', 'customer',
              function ($modalScope, $modalInstance, customer) {
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

      $scope.editAddress = function () {
        showEditAddressDialog().result.then(function (customer) {
          if (customer) {
            CustomerService.save(customer, function (savedCustomer) {
              toaster.pop('success', 'Record updated', savedCustomer.formattedName + '\'s customer record has been updated.');
              $scope.customer = savedCustomer;
            }, function () {
              toaster.pop('error', 'Error updating customer');
            });
          }
        });
        
        function showEditAddressDialog() {
          return $modal.open({
            templateUrl: 'common/view/modal_edit_address.html',
            controller: ['$scope', '$modalInstance', 'customer',
              function ($modalScope, $modalInstance, customer) {
                $modalScope.customer = customer;
                $modalScope.modalTitle = 'Edit address';
                $modalScope.proceed = function (valid) {
                  if (!valid) {
                    return;
                  }
                  $scope.customer.address = $modalScope.customer.address;
                  $modalInstance.close($scope.customer);
                };
                $modalScope.cancel = function () {
                  $modalInstance.close(false);
                };
            }],
            resolve: {
              customer: function () {
                return {
                  address: $scope.customer.address ? {
                    addressLine1: $scope.customer.address.addressLine1,
                    addressLine2: $scope.customer.address.addressLine2,
                    city: $scope.customer.address.city,
                    province: $scope.customer.address.province,
                    zip: $scope.customer.address.zip
                  } : {}
                };
              }
            }
          });
        }
      };
      
      $scope.editContactDetails = function () {
        showEditContactDetailsDialog().result.then(function (customer) {
          if (customer) {
            CustomerService.save(customer, function (savedCustomer) {
              toaster.pop('success', 'Record updated', savedCustomer.formattedName + '\'s customer record has been updated.');
              $scope.customer = savedCustomer;
            }, function () {
              toaster.pop('error', 'Error updating customer');
            });
          }
        });
        
        function showEditContactDetailsDialog() {
          return $modal.open({
            templateUrl: 'common/view/modal_edit_contact_details.html',
            controller: ['$scope', '$modalInstance', 'customer',
              function ($modalScope, $modalInstance, customer) {
                $modalScope.customer = customer;
                $modalScope.modalTitle = 'Edit contact details';
                $modalScope.proceed = function (valid) {
                  if (!valid) {
                    return;
                  }
                  $scope.customer.contactDetails = $modalScope.customer.contactDetails;
                  $modalInstance.close($scope.customer);
                };
                $modalScope.cancel = function () {
                  $modalInstance.close(false);
                };
            }],
            resolve: {
              customer: function () {
                return {
                  contactDetails: $scope.customer.contactDetails ? {
                    cellphone: $scope.customer.contactDetails.cellphone,
                    landline: $scope.customer.contactDetails.landline,
                    email: $scope.customer.contactDetails.email
                  } : {}
                };
              }
            }
          });
        }
      };
  }];
});