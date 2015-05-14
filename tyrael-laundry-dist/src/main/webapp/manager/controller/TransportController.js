define(function () {
  return ['$scope', '$modal', 'ngTableParams', 'toaster', 'confirm', 'PickupService', 'DeliveryService', 'TransportQueueService',
    function ($scope, $modal, ngTableParams, toaster, confirm, PickupService, DeliveryService, TransportQueueService) {

    $scope.statuses = {
      NEW: true,
      QUEUED: false,
      IN_TRANSIT: false
    };

    function statusesString() {
      var temp = new Array();
      for (var i in $scope.statuses) {
        if ($scope.statuses[i]) {
          temp.push(i);
        }
      }
      return temp.join(',');
    }

    //Pickup List
    $scope.pickupTable = new ngTableParams({
      page: 1,
      count: 5,
      sort: 'created,DESC'
    }, {
      total: 0,
      counts: [2, 5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        params.$params.term = 'status=in=(' + statusesString() + ')';
        PickupService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    //Delivery List
    $scope.deliveryTable = new ngTableParams({
      page: 1,
      count: 5,
      sort: 'created,DESC'
    }, {
      total: 0,
      counts: [2, 5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        params.$params.term = 'status=in=(' + statusesString() + ')';
        DeliveryService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    $scope.reloadTables = function () {
      if ($scope.deliveryTable.page() == 1) {
        $scope.deliveryTable.reload();
      } else {
        $scope.deliveryTable.page(1);
      }
      if ($scope.pickupTable.page() == 1) {
        $scope.pickupTable.reload();
      } else {
        $scope.pickupTable.page(1);
      }
    };

    //Transport Queue list
    $scope.transportQueueTable = new ngTableParams({
      page: 1,
      count: 5,
      term: '',
      sort: 'created,DESC'
    }, {
      total: 0,
      counts: [2, 5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        TransportQueueService.get(params.$params, function(response) {
          params.total(response.total);
          if (response.data.length && params.$params.page == 1 && !$scope.queue) {
            $scope.currentQueue = response.data[0];
            $scope.queue = response.data[0];
          } else if (response.data.length === 0) {
            $scope.newQueue();
          }
          $defer.resolve(response.data);
        });
      }
    });

    //Add to queue
    $scope.addToQueue = function (transportRequest) {
      var type, typeLabel, queueCollection;
      if (transportRequest.jobOrder) {
        type = 'DELIVERY';
        typeLabel = 'Delivery';
        queueCollection = $scope.currentQueue.deliveryRequests;
      } else {
        type = 'PICKUP';
        typeLabel = 'Pickup';
        queueCollection = $scope.currentQueue.pickupRequests;
      }

      TransportQueueService.put({
        type: type,
        transportRequestId: transportRequest.id,
        queueId: $scope.currentQueue.id
      }, null, function (saved) {
        queueCollection.push(transportRequest);
        transportRequest.status = 'QUEUED';
        $scope.transportQueueTable.reload();
        toaster.pop('success', typeLabel + ' request added to queue.');
      });
    };

    $scope.newQueue = function () {
      TransportQueueService.save({
        created: new Date(),
        deliveryRequests: [],
        pickupRequests: []
      }, function (newQ) {
        $scope.currentQueue = newQ;
        $scope.queue = newQ;
        $scope.transportQueueTable.reload();
        toaster.pop('success', 'New queue created');
      });
    };

    $scope.showQueue = function (queue) {
      $scope.queue = queue;
    };

    $scope.removeFromQueue = function (transportRequest) {
      var type, typeLabel;
      if (transportRequest.jobOrder) {
        type = 'DELIVERY';
        typeLabel = 'Delivery';
      } else {
        type = 'PICKUP';
        typeLabel = 'Pickup';
      }

      TransportQueueService.remove({
        type: type,
        transportRequestId: transportRequest.id,
        queueId: $scope.queue.id
      }, null, function (updatedQueue) {
        $scope.queue = updatedQueue;
        if ($scope.currentQueue.id === updatedQueue.id) {
          $scope.currentQueue = updatedQueue;
        }
        transportRequest.status = 'NEW';
        $scope.transportQueueTable.reload();
        $scope.reloadTables();
        toaster.pop('success', typeLabel + ' request removed from queue.');
      });
    };


  }];
});