define([
   'angular'
], function (angular) {

  console.debug('Configuring admin.module');
  angular.module('admin.module', [])
    .config(['$stateProvider', function ($stateProvider) {
      $stateProvider.state('default.admin', {
        url: 'admin',
        templateUrl: 'admin/view/admin.html',
        controller: AdminRootController,
        access: 'ROLE_ADMIN'
      });
    }]);

});