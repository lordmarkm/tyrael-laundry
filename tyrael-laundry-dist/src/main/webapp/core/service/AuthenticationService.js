define(function () {
  return ['$resource', '$state', function ($resource, $state) {

    this.service = $resource('auth');

    this.principal = this.service.get(function(principal) {
      //Uncomment this to autoredirect to login on anonymous user
      //if(!principal.name && !$state.includes('default.login')) {
      //  $state.go('default.login');
      //}
    });

    this.login = function (username, password) {
      this.service.save({username: username, password: password}, function (principal) {
        this.principal = principal;
      });
    };
    return this;
  }];
});