define(function () {
  return ['$scope', '$state', '$stateParams', 'toaster', 'confirm',
    function ($scope, $state, $stateParams, toaster, confirm) {

    console.debug('state access: ' + $state.access);
    console.debug('$stateParams: ' + JSON.stringify($stateParams));

  }];
});