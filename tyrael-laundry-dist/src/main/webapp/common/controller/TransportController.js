define(function () {
  return ['$scope', 'customer', 'ngTableParams', 'TransportService',
    function ($scope, customer, ngTableParams, TransportService) {

    $scope.customer = customer;

    //List
    $scope.tableParams = new ngTableParams({
      page: 1,
      count: 5
    }, {
      total: 0,
      counts: [2, 5,10,25,50,100], //determines pager
      getData: function($defer, params) {
        //filter
        params.$params.term = term();
        TransportService.get(params.$params, function(response) {
          params.total(response.total);
          $defer.resolve(response.data);
        });
      }
    });

    $scope.filter = {};
    function term() {
      var rql = '';

      //If customer, show only the job orders for the currently logged in customer
      if ($scope.customer.id) {
        console.debug('Filtering by customer. customer=' + JSON.stringify($scope.customer));
        and('customerId==', $scope.customer.id);
      }

      //If status is not ALL, append it
      if ($scope.filter.status) {
        and('status==', $scope.filter.status);
      }

      //Filter by Date received range
      if ($scope.filter.datefrom) {
        and('dateReceived>=', moment($scope.filter.datefrom).format('YYYY-MM-DD'));
      }
      if ($scope.filter.dateto) {
        and('dateReceived<=', moment($scope.filter.dateto).add(1, 'days').format('YYYY-MM-DD'));
      }

      function and(selector, arg) {
        if (rql.length) {
          rql += ';';
        }
        rql += (selector + arg);
      }

      return rql;
    }
  }];
});