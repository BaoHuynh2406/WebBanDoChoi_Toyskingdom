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
                }
            })
            .when("/product", {
                templateUrl: "/admin/product.html",
                controller: "productsCtrl",
                resolve: {
                    loadMyCtrl:
                        [
                            '$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load('/admin/js/app/products/products.controller.js');
                            }
                        ]
                }
            })
            .when("/discount", {
                templateUrl: "/admin/discount.html",
                controller: "discountCtrl",
                resolve: {
                    loadMyCtrl:
                        [
                            '$ocLazyLoad',
                            function ($ocLazyLoad) {
                                return $ocLazyLoad.load('/admin/js/app/discount/discount.controller.js');
                            }
                        ]
                }
            })
            .otherwise({
                redirectTo: '/dashboard'
            });
    }]);
