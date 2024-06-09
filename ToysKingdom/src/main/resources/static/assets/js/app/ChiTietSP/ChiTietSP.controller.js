angular.module('ToysKingdom').controller('ChiTietSPCtrl', function($scope, $http, $routeParams, $location) {
    console.log("ChiTietSPCtrl Load done");

    $scope.product = {};
    $scope.isLoadingProduct = true;
    var idProduct = $routeParams.id; 

    $scope.chuyentrang = function(id) {
        console.log(id);
        $location.path('/chiteiSP/' + id);
    };

    $scope.loadData = function() {
        $http.get('http://localhost:8080/api-public/products/getProductByID', { params: { idProduct: idProduct } })
            .then(function(response) {
                // Gán dữ liệu sản phẩm nhận được từ API vào $scope.product
                $scope.product = response.data.data;
                console.log($scope.product);
                $scope.isLoadingProduct = false;
            }, function(error) {
                console.log(error.message);
                $scope.isLoadingProduct = false;
            });
    };

    $scope.loadData();

    $scope.relatedProducts = [];
    $scope.isLoadingRelatedProducts = true;

    $scope.loadRelatedProducts = function() {
        $http.get('http://localhost:8080/api-public/products/homePageProduct', { params: { page: 1 } })
            .then(function(response) {
                $scope.relatedProducts = response.data.data;
                $scope.isLoadingRelatedProducts = false;
            }, function(error) {
                console.error(error.message);
                $scope.isLoadingRelatedProducts = false;
            });
    };

    $scope.loadRelatedProducts();



    // Số lượng mặc định
    $scope.quantity = 1;

    // Tăng số lượng
    $scope.increaseQuantity = function() {
        $scope.quantity++;
    };

    // Giảm số lượng
    $scope.decreaseQuantity = function() {
        if ($scope.quantity > 1) {
            $scope.quantity--;
        }
    };
});
