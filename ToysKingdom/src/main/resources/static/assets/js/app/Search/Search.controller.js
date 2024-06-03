angular.module('ToysKingdom').controller('searchCtrl', function ($scope ,$location, $http, $routeParams) {
    $scope.productsData = [];
    $scope.searchKey = $routeParams.query || '';

    $scope.loadData = function () {
        console.log("Loaddata...");
        if ($scope.searchKey) {
            $http.get('http://localhost:8080/api-public/products/getproductbyname', { params: { productName: $scope.searchKey } })
                .then(function (response) {
                    $scope.productsData = response.data.data;
                }, function (error) {
                    console.log(error.message);
                });
        } else {
            console.log("Vui lòng nhập sản phẩm cần tìm kiếm");
        }
    };

    // Khi khởi tạo controller, nếu có tham số query trên URL thì gán vào thanh tìm kiếm và gọi loadData
    if ($scope.searchKey) {
        $scope.loadData();
    }

    console.log('Search controller load')
});
