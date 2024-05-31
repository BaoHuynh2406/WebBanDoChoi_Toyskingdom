angular.module('ToysKingdom').controller("SearchCtrl", function ($scope, $http) {
    $scope.productsData = [];

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