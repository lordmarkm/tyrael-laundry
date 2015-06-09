define(function () {
  return {
    reportParams: [function () {
      return {
        title: 'Branch Summary Report',
        reportName: 'Summary',
        description: 'Summary report with statistics from the entire branch',
        startDateSelection: true,
        endDateSelection: true
      };
    }]
  };
});
