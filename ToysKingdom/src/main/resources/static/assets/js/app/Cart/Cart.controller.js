angular.module('ToysKingdom').controller('CartCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("CartCtrl Load done");
    $rootScope.getCart();

    $scope.onQuantityChange = function (itemId, newQuantity) {
        console.log('ID:', itemId, 'New Quantity:', newQuantity);
        $scope.addToCart(itemId, newQuantity);
    };

    $scope.decrementQuantity = function (item) {
        if (item.quantity > 0) {
            item.quantity--;
            $scope.onQuantityChange(item.idProduct, item.quantity);
        }
    };

    $scope.incrementQuantity = function (item) {
        item.quantity++;
        $scope.onQuantityChange(item.idProduct, item.quantity);
    };

    $scope.xoa = function (idProduct) {
        $scope.addToCart(idProduct, 0);
    }


    $scope.addToCart = function (idProduct, quantity) {
        console.log($rootScope.customer);
        console.log(idProduct);
        if (!$rootScope.isLoggedIn) {
            Swal.fire({
                title: "Bạn chưa đăng nhập á?",
                text: "Đăng nhập rồi mua hàng tiếp nhe, mất vài giây thoi à :33",
                icon: "question"
            });

            $location.path('/login')
            return;
        }

        //Gọi api
        var data = {
            idUser: $rootScope.customer.idUser,
            idProduct: idProduct,
            quantity: quantity,
        }

        $http.post('/api-public/cart/addToCart', data)
            .then(function (response) {
                $rootScope.getCart();
            })
            .catch(function (error) {
                console.error(error.message);
            });
    }


});