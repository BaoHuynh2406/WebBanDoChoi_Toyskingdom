
angular.module('ToysKingdom').controller('searchCtrl', function($scope, $location, $http, $routeParams) {


    $scope.searchKey = $routeParams.query || '';
    $scope.search = function() {
        if ($scope.searchKey) {
            $location.path('/search').search({ query: $scope.searchKey });
        }
    };

    $scope.productsData = [];

    $scope.loadData = function() {
        if ($scope.searchKey) {
            $http.get('http://localhost:8080/api-public/products/getproductbyname', { params: { productName: $scope.searchKey } })
                .then(function(response) {
                    $scope.productsData = response.data.data;
                }, function(error) {
                    console.log(error.message);
                });
        }
    };
    $scope.loadData();
});
