let app = angular.module('ToysKingdom', ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/home", { templateUrl: "layout/ViewProduct.html", controller: "viewProductCtrl" })
        .when("/login", { templateUrl: "layout/login.html", controller: "loginCtrl" })
        .when("/sign", { templateUrl: "layout/sign.html", controller: "signCtrl" })
        .when("/forgotpassword", { templateUrl: "layout/forgotpassword.html", controller: "forgotpasswordCtrl" })
        .when("/productout", { templateUrl: "layout/TimKiemLayout.html", controller: "productOutCtrl" })
        .otherwise({ redirectTo: "/home" });
});


// Khai báo card Item loại 1
app.directive('cardItemType1', function () {
    return {
        scope: {
            product: '=product'
        },
        templateUrl: 'components/cardType1.html'
    }
});

app.controller("mainCtrl", function ($scope) {
});


app.controller('viewProductCtrl', function ($scope, $http) {
    $scope.productsData = [];
    $scope.categoriesData = [];

    getData = function () {
        // Lấy danh mục sản phẩm
        $http.get('http://localhost:8080/api-public/categories/getAllCategories')
            .then(function (response) {
                $scope.categoriesData = response.data.data;
            }, function (error) {
                console.log(error.message);
            });

        //Lấy các sản phẩm 
        $http.get('http://localhost:8080/api-public/products/getAllProductsFeature')
            .then(function (response) {
                $scope.productsData = response.data.data;
            }, function (error) {
                console.log(error.message);
            });


    };

    getData();

});

// tìm kiếm chưa hoàn thành

app.controller("loginCtrl", function ($scope) {

});

app.controller("signCtrl", function ($scope) {

});
app.controller("forgotpasswordCtrl", function ($scope) {

});

// tìm kiếm chưa hoàn thành
app.controller("productOutCtrl", function ($scope, $http) {
    $scope.productsData = [];
    $scope.categoriesData = [];



    $scope.search = function(){
        //Lấy các sản phẩm 
        $http.get('http://localhost:8080/api-public/products/getproductbyname', {params: {productName : $scope.searchkey}})
            .then(function (response) {
                $scope.productsData = response.data.data;
            }, function (error) {
                console.log(error.message);
            });

    }
});

