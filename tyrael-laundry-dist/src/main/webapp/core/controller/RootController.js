define(function () {
  return ['$scope', 'auth', function ($scope, auth) {
    $scope.contextPath = 'laundry';
    $scope.isAuthorized = function (permission) {
      if (!$scope.principal) {
        return false;
      }
      for (var i in $scope.principal.authorities) {
        if ($scope.principal.authorities[i].authority === permission) {
          return true;
        }
      }
      return false;
    };

    //Check user authorities and redirect where appropriate
    auth.then(function(authentication) {
      $scope.principal = authentication.principal;
    });
  }];
});