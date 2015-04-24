define(function () {
  return ['$scope', 'toaster', 'jobOrder', 'RegistrationService',
    function ($scope, toaster, jobOrder, RegistrationService) {

    $scope.jobOrder = jobOrder;
    $scope.regForm = {};

    $scope.submitForm = function () {
      RegistrationService.save($scope.regForm, function () {
        toaster.pop('success', 'Registration and claim successful');
      }, function (response) {
        toaster.pop('error', 'Registartion and claim error', response.data.message);
      });
    };

  }];
});