define(function () {
  return {
    customerAccount: ['CustomerAccountService', function (CustomerAccountService) {
      return CustomerAccountService.getCurrent();
    }]
  };
});
