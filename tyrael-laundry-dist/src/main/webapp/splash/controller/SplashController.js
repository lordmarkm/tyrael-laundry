define(function () {
  return ['$scope', '$state', 'toaster', 'auth', 'JobOrderService', function ($scope, $state, toaster, auth, JobOrderService) {

    //Check user authorities and redirect where appropriate
    auth.then(function(authentication) {
      var principal = authentication.principal;
      for (var i in principal.authorities) {
        var authority = principal.authorities[i].authority;
        console.debug('found authority:' + authority);
        
        switch (authority) {
        case 'ROLE_POS':
          console.debug('Redirecting to POS splash.');
          $state.go('default.pos.splash');
          return;
        }
      }
    });

    $scope.controllerName = "Splash Controller";
    $scope.checkJobOrder = function (trackingNo) {
      $scope.jobOrder = JobOrderService.get({trackingNo: trackingNo}, function (jobOrder) {
        if (!jobOrder.trackingNo) {
          toaster.pop('error', 'Receipt not found', 'Receipt not found. Please check the tracking number and try again.');
        } else {
          toaster.pop('success', 'Receipt found', 'Receipt found.');
        }
      });
    };
  }];
});