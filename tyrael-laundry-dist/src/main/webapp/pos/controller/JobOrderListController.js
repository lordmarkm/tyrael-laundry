define(function () {
  return ['$scope', '$modal', 'toaster', 'ngTableParams', 'JobOrderService',
    function ($scope, $modal, toaster, ngTableParams, JobOrderService) {

      //List
      $scope.tableParams = new ngTableParams({
        page: 1,
        count: 5
      }, {
        total: 0,
        counts: [5,10,25,50,100], //determines pager
        getData: function($defer, params) {

          //filter
          params.$params.term = $scope.term || '';

          JobOrderService.get(params.$params, function(response) {
            params.total(response.total);
            $defer.resolve(response.data);
          });
        }
      });

  }];
});