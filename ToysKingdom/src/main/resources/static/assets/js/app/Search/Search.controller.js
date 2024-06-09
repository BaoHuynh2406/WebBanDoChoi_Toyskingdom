angular.module('ToysKingdom').controller('searchCtrl', function ($scope, $location, $http, $routeParams) {
    console.log('Search controller load');
    $scope.productsData = [];
    $scope.searchQuery = $routeParams.query || '';

    $scope.loadData = function () {
        $http.get('http://localhost:8080/api-public/products/getproductbyname', { params: { productName: $scope.searchQuery } })
            .then(function (response) {
                $scope.productsData = response.data.data;
            }, function (error) {
                console.log(error.message);
            });
    };

    // Khi khởi tạo controller, nếu có tham số query trên URL thì gán vào thanh tìm kiếm và gọi loadData
    if ($scope.searchQuery) {
        $("#searchQuery").val($scope.searchQuery);
        $scope.loadData();
    }
});
