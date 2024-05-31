angular.module('ToysKingdom').controller('ViewProductsCtrl',
    function ($scope, $http) {

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