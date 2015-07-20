define(function () {
  return ['$scope', '$http', 'toaster',
    function ($scope, $http, toaster) {

    $scope.extracting = false;

    $scope.doExtract = function () {
      $scope.extracting = true;
      $http.get('extraction').success(function () {
        $scope.extracting = false;
        toaster.pop('success', 'Data Extraction Successful', 'Reports Data successfully loaded into the reports database');
      });
    };

  }];
});