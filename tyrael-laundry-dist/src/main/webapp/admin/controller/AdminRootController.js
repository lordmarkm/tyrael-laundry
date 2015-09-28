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
          term: composeSearchTerm(term)
      };
      JobOrderService.get(params, function(response) {
        $scope.search.results = response.data;
      });
    };

    function composeSearchTerm(term) {
      var rql = '';
      if (term) {
        if (rql.length) {
          rql += ';';
        }
        rql += '(trackingNo==' + term + ',customerSurname==' + term + '*,customerGivenName==' + term + '*)';
      }

      //Job order is open
      if (rql.length) {
        rql += ';';
      }
      rql += 'status=in=(NEW,CLEANED,PAID_CLAIMED)';

      return rql;
    }

    $scope.onSelect = function (item) {
      $state.go('default.manager.joborder_view', {trackingNo: item.trackingNo});
    };
  }];
});