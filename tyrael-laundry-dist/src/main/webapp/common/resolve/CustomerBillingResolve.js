define(function () {
  return {
    billingInfo: ['$stateParams', 'CustomerBillingInfoService', function ($stateParams, CustomerBillingInfoService) {
      if ($stateParams.customerId) {
        //TODO (for pos, manager, etc)
      } else {
        return CustomerBillingInfoService.get();
      }
    }]
  };
});
