define(function () {
  return ['$scope', '$state', '$timeout', '$window', 'moment', 'reportParams', 'toaster', 'confirm',
    function ($scope, $state, $timeout, $window, moment, reportParams, toaster, confirm) {

    $scope.reportParams = reportParams;

    //Filter
    $scope.dateFormat = 'yyyy MMM-dd';
    $scope.today = moment();
    $scope.filter = {
        reportName: reportParams.reportName,
        datefrom: moment().startOf('month').format('YYYY MMM-DD'),
        dateto: moment().format('YYYY MMM-DD')
    };

    $scope.open = function (picker, evt) {
      console.debug('opening' + picker);
      evt.preventDefault();
      evt.stopPropagation();
      
      if (picker === 'from') {
        $scope.openDateto = false;
        $scope.openDatefrom = true;
      } else if (picker === 'to') {
        $scope.openDatefrom = false;
        $scope.openDateto = true;
      }
    };

    $scope.generateReportButtonDisabled = false;
    $scope.generateReport = function () {
      var filter = $scope.filter;
      var datefrom = moment(filter.datefrom);
      var dateto = moment(filter.dateto);
      $window.open('/laundry/reports?reportName=' + filter.reportName + '&datefrom=' + datefrom.format('MMM-DD-YYYY') + '&dateto=' + dateto.format('MMM-DD-YYYY'), '_blank');

      $scope.generateReportButtonDisabled = true;
      $timeout(function () {
        $scope.generateReportButtonDisabled = false;
      }, 3000);
    }
  }];
});