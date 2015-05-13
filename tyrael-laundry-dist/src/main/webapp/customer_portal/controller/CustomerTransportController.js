define(function () {
  return ['$scope', '$modal', '$q', 'toaster', 'confirm', 'customer', 'PickupService', 'DeliveryService', 'JobOrderService',
    function ($scope, $modal, $q, toaster, confirm, customer, PickupService, DeliveryService, JobOrderService) {

    $scope.customer = customer;

    PickupService.get({
      term: 'customerId==' + customer.id + ';status=in=(NEW,QUEUED,IN_TRANSIT)',
      page: 0,
      size: 1
    }, function (pickupPage) {
      $scope.pickup = pickupPage.data.length ? pickupPage.data[0] : undefined;
    });

    //Job orders that have delivery requests or can get delivery requests
    JobOrderService.get({
      page: 1,
      count: 1000,
      term: 'customerId==' + customer.id + ';(status==CLEANED,deliveryStatus=in=(NEW,QUEUED,IN_TRANSIT))'
    }, function (deliverablesPage) {
      $scope.deliverables = deliverablesPage.data;
    });

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

    //Request delivery
    $scope.requestDelivery = function (jobOrder, existingRequest) {
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
                  id: existingRequest ? existingRequest.id : undefined,
                  customer: $scope.customer,
                  jobOrder: jobOrder,
                  address: {
                    addressLine1: existingRequest ? existingRequest.address.addressLine1 : $scope.customer.address ? $scope.customer.address.addressLine1 || '' : '',
                    addressLine2: existingRequest && existingRequest.address ? existingRequest.address.addressLine2 : $scope.customer.address ? $scope.customer.address.addressLine2 || '' : ''
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

    $scope.cancelDelivery = function (jobOrder) {
      confirm.confirm('Confirm cancel delivery request', 'Are you sure you want to cancel this delivery request?', 'Yes', 'No').result.then(function (conf) {
        DeliveryService.remove({jobOrderId: jobOrder.id}, function () {
          toaster.pop('success', 'Delivery request cancelled');
          delete jobOrder.deliveryStatus;
        });
      });
    };
    
    $scope.viewDeliveryDetails = function (jobOrder) {
      $modal.open({
        templateUrl: 'modal-delivery-request-details',
        controller: ['$scope', '$modalInstance', 'deliveryRequest', function($modalScope, $modalInstance, deliveryRequest) {
          $modalScope.jobOrder = jobOrder;
          $modalScope.deliveryRequest = deliveryRequest;
          $modalScope.ok = function () {
            $modalInstance.dismiss();
          };
          $modalScope.edit = function () {
            $modalInstance.dismiss();
            $scope.requestDelivery(jobOrder, deliveryRequest);
          };
        }],
        resolve: {
          deliveryRequest: function () {
            var defer = $q.defer();
            DeliveryService.get({term: 'jobOrderTrackingNo==' + jobOrder.trackingNo + ';status=in=(NEW,QUEUED,IN_TRANSIT)'}, function (deliveryRequests) {
              defer.resolve(deliveryRequests.data.length ? deliveryRequests.data[0] : undefined);
            });
            return defer.promise;
          }
        }
      });
    };
    
  }];
});