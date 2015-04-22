define(function () {
  return ['$scope', '$state', 'toaster', 'auth', 'JobOrderService', function ($scope, $state, toaster, auth, JobOrderService) {

    //Check user authorities and redirect where appropriate
    auth.then(function(authentication) {
      var principal = authentication.principal;
      if (!principal) {
        $state.go('default.anon.splash');
        return;
      }
      for (var i in principal.authorities) {
        var authority = principal.authorities[i].authority;
        console.debug('found authority:' + authority);
        
        switch (authority) {
        case 'ROLE_POS':
          console.debug('Redirecting to POS splash.');
          $state.go('default.pos.splash');
          return;
        case 'ROLE_MANAGER':
          console.debug('Redirecting to Manager splash.');
          $state.go('default.manager.splash');
          return;
        }
      }
    });

  }];
});