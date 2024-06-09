angular.module('ToysKingdom').controller('ViewProductsCtrl', function($scope, $http) {


    $scope.productsData = [];
    $scope.categoriesData = [];
    $scope.currentPage = 1;
    $scope.totalPages = 1;
    $scope.isLoading = false;
    $scope.sortOrder = 'priceAsc'; // Default sort order

    $scope.loadData = function(page) {
        $scope.isLoading = true;
        $scope.productsData = [];
        $http.get('http://localhost:8080/api-public/products/homePageProduct', { params: { page: page } })
            .then(function(response) {
                $scope.productsData = response.data.data;
                $scope.isLoading = false;
            }, function(error) {
                console.error(error.message);
                $scope.isLoading = false;
            });
    };


    $scope.getTotalProducts = function() {
        $scope.isLoading = true;
        $http.get('http://localhost:8080/api-public/products/countFeatureProducts')
            .then(function(response) {
                const totalProducts = response.data.data;

                $scope.totalPages = Math.ceil(totalProducts / 12); // 12 sản phẩm mỗi trang

                // $scope.totalPages = Math.ceil(totalProducts / 12); // 12 products per page

                $scope.isLoading = false;
            }, function(error) {
                console.error(error.message);
                $scope.isLoading = false;
            });
    };

    //sắp xếp theo giá
    $scope.sortProducts = function() {
        if ($scope.sortOrder === 'priceAsc') {
            $scope.productsData.sort(function(a, b) {
                return (a.price * (1 - a.discountPercent / 100)) - (b.price * (1 - b.discountPercent / 100));
            });
        } else if ($scope.sortOrder === 'priceDesc') {
            $scope.productsData.sort(function(a, b) {
                return (b.price * (1 - b.discountPercent / 100)) - (a.price * (1 - a.discountPercent / 100));
            });
        }
    };



    $scope.getData = function() {
        $scope.isLoading = true;
        $http.get('http://localhost:8080/api-public/categories/getAllCategories')
            .then(function(response) {
                $scope.categoriesData = response.data.data;
                $scope.isLoading = false;
            }, function(error) {
                console.error(error.message);
                $scope.isLoading = false;
            });

        $scope.getTotalProducts();
        $scope.loadData(1);
    };

    $scope.getData();

    $scope.chuyentrang = function(id) {
        $location.path('/chiteiSP/' + id);
    };
});







