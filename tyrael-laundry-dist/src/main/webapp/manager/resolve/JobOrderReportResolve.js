define(function () {
  return {
    reportParams: [function () {
      return {
        title: 'Branch Job Order Report',
        reportName: 'Job Order Report',
        description: 'All job orders for this branch',
        startDateSelection: true,
        endDateSelection: true
      };
    }]
  };
});
