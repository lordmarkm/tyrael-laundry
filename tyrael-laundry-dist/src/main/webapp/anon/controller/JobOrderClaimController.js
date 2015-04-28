define(function () {
  return ['$scope', '$state', 'toaster', 'jobOrder', 'RegistrationService',
    function ($scope, $state, toaster, jobOrder, RegistrationService) {

    $scope.jobOrder = jobOrder;
    $scope.regForm = {
      jobOrderTrackingNo: $scope.jobOrder.trackingNo
    };

    $scope.submitForm = function () {
      RegistrationService.save($scope.regForm, function () {
        toaster.pop('success', 'Registration and claim successful');
        $state.go('default.login', {msg: 'reg_success'});
      }, function (response) {
        toaster.pop('error', 'Registartion and claim error', response.data.message);
      });
    };

  }];
});