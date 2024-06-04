angular.module('ToysKingdom').config(function ($routeProvider) {
    $routeProvider
        .when("/home",
            {
                templateUrl: "layout/ViewProduct.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/ViewProducts/ViewProducts.controller.js');
                    }]
                },
                controller: "ViewProductsCtrl"

            })
        .when("/login",
            {
                templateUrl: "layout/login.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/Login/Login.controller.js');
                    }]
                },
                controller: "LoginCtrl"
            })
        .when("/signUp",
            {
                templateUrl: "layout/signUp.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/SignUp/SignUp.controller.js');
                    }]
                },
                controller: "SignUpCtrl"
            })
        .when("/ForgotPassword",
            {
                templateUrl: "layout/forgotpassword.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/ForgotPassword/ForgotPassword.controller.js');
                    }]
                },
                controller: "ForgotPasswordCtrl"
            })
        .when("/search", {
            templateUrl: "layout/TimKiemLayout.html",
            controller: "searchCtrl",
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/assets/js/app/Search/Search.controller.js');
                }]
            }
        })
        .otherwise({ redirectTo: "/home" });
});