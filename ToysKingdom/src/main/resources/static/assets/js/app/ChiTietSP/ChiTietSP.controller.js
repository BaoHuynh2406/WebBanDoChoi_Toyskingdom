angular.module('ToysKingdom').controller('ChiTietSPCtrl', function($scope, $http, $routeParams) {
    console.log("ChiTietSPCtrl Load done");


    $scope.product;
    var idProduct = $routeParams.id; // Lấy id từ $routeParams
    console.log(idProduct);

    $scope.loadData = function() {
        $http.get('http://localhost:8080/api-public/products/getProductByID', {params: {idProduct: idProduct}} )
        .then(function(response) {
            // Gán dữ liệu sản phẩm nhận được từ API vào $scope.product
            $scope.product = response.data.data;
            console.log($scope.product);
        }, function(error) {
            console.log(error.message);
            $scope.isLoading = false;
        });
    }

    $scope.loadData();
    
    // Hàm chọn ảnh
    $scope.selectImage = function(image) {
        $scope.selectedImage = image;
    };

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
