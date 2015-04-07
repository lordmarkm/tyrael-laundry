define(function () {
  return ['$scope', '$http', function ($scope, $http) {

  
  
  
  
  
    $scope.addresses = [];
    $scope.refreshAddresses = function(address) {
      var params = {address: address, sensor: false};
      return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {params: params})
        .then(function(response) {
          $scope.addresses = response.data.results
        });
    };
  
  
  
  
  
  }];
});