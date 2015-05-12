define(function () {
  return ['$scope', '$modal', 'toaster', 'confirm', 'customer', 'PickupService', 'DeliveryService',
    function ($scope, $modal, toaster, confirm, customer, PickupService, DeliveryService) {

    $scope.customer = customer;

    PickupService.get({
      term: 'customerId==' + customer.id + ';status=in=(NEW,QUEUED,IN_TRANSIT)',
      page: 0,
      size: 1
    }, function (pickupPage) {
      $scope.pickup = pickupPage.data.length ? pickupPage.data[0] : undefined;
    });

    $scope.deliveries = DeliveryService.get({
      term: 'customerId==' + customer.id + ';status=in=(NEW,QUEUED,IN_TRANSIT)'
    }).data;

    $scope.requestPickup = function (existingPickupRequest) {
      showRequestPickupModal().result.then(function (pickupRequest) {
        if (pickupRequest) {
          PickupService.save(pickupRequest, function (savedPickupRequest) {
            toaster.pop('success', 'Pickup Request created');
            $scope.pickup = savedPickupRequest;
          });
        }
      });

      function showRequestPickupModal() {
        return $modal.open({
          templateUrl: 'modal-request-pickup',
          background: 'static',
          controller: ['$scope', '$modalInstance', 'pickupRequest', function($scope, $modalInstance, pickupRequest) {
            $scope.pickupRequest = pickupRequest;
            $scope.proceed = function (valid) {
              if (!valid) {
                return;
              }
              $modalInstance.close(pickupRequest);
            };
            $scope.cancel = function () {
              $modalInstance.close(false);
            };
          }],
          resolve: {
            pickupRequest: function () {
              return {
                id: existingPickupRequest ? existingPickupRequest.id : undefined,
                customer: customer,
                address: {
                  addressLine1: existingPickupRequest ? existingPickupRequest.address.addressLine1 : customer.address ? customer.address.addressLine1 || '' : '',
                  addressLine2: existingPickupRequest && existingPickupRequest.address.addressLine2 ? existingPickupRequest.address.addressLine2 : customer.address ? customer.address.addressLine2 || '' : '',
                  //city: customer.address.city,
                  //province: customer.address.province,
                  //zip: customer.address.zip
                },
                status: 'NEW'
              };
            }
          }
        });
      }
    };

    $scope.cancelPickup = function (pickupRequest) {
      confirm.confirm('Confirm cancel pickup request', 'Are you sure you want to cancel this pickup request?', 'Yes', 'No').result.then(function (conf) {
        pickupRequest.status = 'CANCELLED';
        PickupService.save(pickupRequest, function (savedPickupRequest) {
          toaster.pop('success', 'Pickup Request cancelled');
          delete $scope.pickup;
        });
      });
    };

  }];
});