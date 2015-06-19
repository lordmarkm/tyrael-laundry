define(function () {
  return ['$scope', '$state', '$stateParams', 'JobOrderService', 'BranchInfoService', function ($scope, $state, $stateParams, JobOrderService, BranchInfoService) {

    //Moved here from dashboard controller so that create jo controller can
    //use minimum amount
    $scope.branchInfo = BranchInfoService.get();

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
      $state.go('default.pos.joborder_view', {trackingNo: item.trackingNo});
    };
  }];
});