define(function () {
  return ['$scope', '$state', '$stateParams', 'JobOrderService', function ($scope, $state, $stateParams, JobOrderService) {
    $scope.params = $stateParams;
    $scope.recentData = {
      customer: {}
    };
    $scope.search = {
      results: []
    };

    //Side Menu
    $('#side-menu').metisMenu();

    //Search
    $scope.doSearch = function (term) {
      var params = {
          page: 1,
          count: 5,
          term: term,
          status: 'OPEN'
      };
      JobOrderService.get(params, function(response) {
        $scope.search.results = response.data;
      });
    };

    $scope.onSelect = function (item) {
      $state.go('default.manager.joborder_view', {trackingNo: item.trackingNo});
    };
  }];
});