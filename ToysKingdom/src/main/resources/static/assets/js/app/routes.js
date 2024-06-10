angular.module('ToysKingdom').config(function ($routeProvider) {
    $routeProvider
        .when("/home",
            {
                templateUrl: "layout/ViewProduct.html",
                controller: "ViewProductsCtrl",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/ViewProducts/ViewProducts.controller.js');
                    }]
                }

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
        .when("/khoiphucTK",
            {
                templateUrl: "layout/khoiphucTK.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/KhoiphucTK/KhoiphucTK.controller.js');
                    }]
                },
                controller: "KhoiphucTKCtrl"
            })
        .when("/doiMK",
            {
                templateUrl: "layout/doiMK.html",
                resolve:
                {
                    loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                        return $ocLazyLoad.load('/assets/js/app/DoiMK/DoiMK.controller.js');
                    }]
                },
                controller: "DoiMKCtrl"
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
        .when("/chiteiSP/:id", {
            templateUrl: "layout/ChiTietSP.html",
            controller: "ChiTietSPCtrl",
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/assets/js/app/ChiTietSP/ChiTietSP.controller.js');
                }]
            }
        })
        .when("/cart", {
            templateUrl: "layout/Cart.html",
            controller: "CartCtrl",
            resolve: {
                loadMyCtrl: ['$ocLazyLoad', function ($ocLazyLoad) {
                    return $ocLazyLoad.load('/assets/js/app/Cart/Cart.controller.js');
                }]
            }
        })
        .otherwise({ redirectTo: "/home" });
});