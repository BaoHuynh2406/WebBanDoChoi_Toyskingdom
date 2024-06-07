// Khai báo các điều hướng
angular.module('admin')
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when("/dashboard", {
        templateUrl: "/admin/404.html",
        controller: "dashboardCtrl",
        resolve: {
          loadMyCtrl:
            [
              '$ocLazyLoad',
              function ($ocLazyLoad) {
                return $ocLazyLoad.load('/admin/js/app/dashboard/dashboard.controller.js');
              }
            ]
        },
        controller: "dashboardCtrl"
      })
      .when("/users", {
        templateUrl: "/admin/user.html",
        controller: "usersCtrl",
        resolve: {
          loadMyCtrl:
            [
              '$ocLazyLoad',
              function ($ocLazyLoad) {
                return $ocLazyLoad.load('/admin/js/app/users/users.controller.js');
              }
            ]
        },
        controller: "usersCtrl"
      })
      .otherwise({
        redirectTo: '/dashboard'
      });
  }]);
