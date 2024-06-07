angular.module('ToysKingdom', [])
.controller('ChiTietSPCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("ChiTietSPCtrl Load done");
    
    // data test
    $scope.product = {
        images: ['product1.jpg', 'product2.jpg', 'product3.jpg'],
        image: 'product1.jpg',
        productName: 'Tên sản phẩm',
        price: 500000,
        discountPercent: 10,
        description: 'Đây là mô tả chi tiết của sản phẩm.'
    };

    // ảnh được chọn
    $scope.selectedImage = $scope.product.images[0];

    // hàm chọn ảnh
    $scope.selectImage = function(image) {
        $scope.selectedImage = image;
    };

    // số lượng = 1
    $scope.quantity = 1;

    // tăng
    $scope.increaseQuantity = function() {
        $scope.quantity++;
    };

    // giảm
    $scope.decreaseQuantity = function() {
        if ($scope.quantity > 1) {
            $scope.quantity--;
        }
    };

    // Sample related products data
    $scope.relatedProducts = [
        {
            id: 1,
            image: 'related1.jpg',
            productName: 'Sản phẩm liên quan 1',
            price: 300000,
            discountPercent: 5
        },
        {
            id: 2,
            image: 'related2.jpg',
            productName: 'Sản phẩm liên quan 2',
            price: 400000,
            discountPercent: 15
        },
        {
            id: 3,
            image: 'related3.jpg',
            productName: 'Sản phẩm liên quan 3',
            price: 200000,
            discountPercent: 0
        },
        {
            id: 4,
            image: 'related4.jpg',
            productName: 'Sản phẩm liên quan 4',
            price: 350000,
            discountPercent: 10
        }
    ];
});
