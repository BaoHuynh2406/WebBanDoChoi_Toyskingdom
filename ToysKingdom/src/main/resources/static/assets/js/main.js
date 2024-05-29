let app = angular.module('ToysKingdom', ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", { templateUrl: "layout/ViewProduct.html", controller: "viewProductCtrl" })
        .when("/siginUp", { templateUrl: "./SignUp.html", controller: "myCtrl" })
        .otherwise({ redirectTo: "/" });
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

        //Lấy các sản phẩm đang hoạt động
        $http.get('http://localhost:8080/api-public/products/getAllActiveProducts')
            .then(function (response) {
                $scope.productsData = response.data.data;
            }, function (error) {
                console.log(error.message);
            });
        

    };

    getData();






});




