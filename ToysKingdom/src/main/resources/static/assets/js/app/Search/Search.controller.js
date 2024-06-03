angular.module('ToysKingdom').controller('searchCtrl', function($scope, $location, $http, $routeParams) {
    
    $scope.searchKey = $routeParams.query || '';
    $scope.productsData = [];

    $scope.search = function() {
        if ($scope.searchKey) {
            console.log("Searching for:", $scope.searchKey);
            $location.path('/search').search({ query: $scope.searchKey });
            $scope.loadData();
        } else {
            console.log("Product name cannot be null or empty");
        }
    };

    $scope.loadData = function() {
        if ($scope.searchKey) {
            $http.get('http://localhost:8080/api-public/products/getproductbyname', { params: { productName: $scope.searchKey } })
                .then(function(response) {
                    $scope.productsData = response.data.data;
                }, function(error) {
                    console.log(error.message);
                });
        } else {
            console.log("Vui lòng nhập sản phẩm cần tìm kiếm");
        }
    };

    $scope.loadData();
});
