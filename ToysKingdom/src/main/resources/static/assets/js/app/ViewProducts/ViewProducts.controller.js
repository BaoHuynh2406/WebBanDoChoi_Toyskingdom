// angular.module('ToysKingdom').controller('ViewProductsCtrl', function($scope, $http) {
//
//     $scope.productsData = [];
//     $scope.categoriesData = [];
//     $scope.currentPage = 1;
//     $scope.totalPages = 1;
//     $scope.isLoading = false;
//
//     $scope.loadData = function(page) {
//         $scope.isLoading = true;
//         $scope.productsData = [];
//         $http.get('http://localhost:8080/api-public/products/homePageProduct', { params: { page: page } })
//             .then(function(response) {
//                 $scope.productsData = response.data.data;
//                 $scope.isLoading = false;
//             }, function(error) {
//                 console.log(error.message);
//                 $scope.isLoading = false;
//             });
//     };
//
//     $scope.getTotalProducts = function() {
//         $scope.isLoading = true;
//         $http.get('http://localhost:8080/api-public/products/countFeatureProducts')
//             .then(function(response) {
//                 const totalProducts = response.data.data;
//                 $scope.totalPages = Math.ceil(totalProducts / 12); // 10 products per page
//                 $scope.isLoading = false;
//             }, function(error) {
//                 console.log(error.message);
//                 $scope.isLoading = false;
//             });
//     };
//
//     $scope.getData = function() {
//         $scope.isLoading = true;
//         $http.get('http://localhost:8080/api-public/categories/getAllCategories')
//             .then(function(response) {
//                 $scope.categoriesData = response.data.data;
//                 $scope.isLoading = false;
//             }, function(error) {
//                 console.log(error.message);
//                 $scope.isLoading = false;
//             });
//
//         $scope.getTotalProducts();
//         $scope.loadData(1);
//     };
//
//     $scope.getData();
// });
angular.module('ToysKingdom', [])
    .controller('ViewProductsCtrl', ['$scope', '$http', function ($scope, $http) {
        $scope.productsData = [];
        $scope.categoriesData = [];
        $scope.currentPage = 1;
        $scope.totalPages = 1;
        $scope.isLoading = true;
        $scope.sortOrder = 'priceAsc';

        // Load products with sorting
        $scope.loadProducts = function (sortOrder) {
            $http.get('http://localhost:8080/api/products/sort', { params: { direction: sortOrder === 'priceAsc' ? 'asc' : 'desc' } })
                .then(function (response) {
                    console.log('Product data:', response.data); // Debugging line
                    $scope.productsData = response.data.data; // Ensure this matches your API response structure
                    $scope.isLoading = false;
                }, function (error) {
                    console.error('Error loading products:', error);
                    $scope.isLoading = false;
                });
        };

        // Sort products
        $scope.sortProducts = function () {
            $scope.isLoading = true;
            $scope.loadProducts($scope.sortOrder);
        };

        // Load products by page
        $scope.loadData = function (page) {
            $scope.isLoading = true;
            $http.get('http://localhost:8080/api-public/products/homePageProduct', { params: { page: page } })
                .then(function (response) {
                    $scope.productsData = response.data.data;
                    $scope.isLoading = false;
                }, function (error) {
                    console.log(error.message);
                    $scope.isLoading = false;
                });
        };

        // Get total products count
        $scope.getTotalProducts = function () {
            $scope.isLoading = true;
            $http.get('http://localhost:8080/api-public/products/countFeatureProducts')
                .then(function (response) {
                    const totalProducts = response.data.data;
                    $scope.totalPages = Math.ceil(totalProducts / 12); // 12 products per page
                    $scope.isLoading = false;
                }, function (error) {
                    console.log(error.message);
                    $scope.isLoading = false;
                });
        };

        // Get categories and initial products
        $scope.getData = function () {
            $scope.isLoading = true;
            $http.get('http://localhost:8080/api-public/categories/getAllCategories')
                .then(function (response) {
                    $scope.categoriesData = response.data.data;
                    $scope.isLoading = false;
                }, function (error) {
                    console.log(error.message);
                    $scope.isLoading = false;
                });

            $scope.getTotalProducts();
            // Ensure products are sorted and loaded when the page is first loaded
            $scope.loadProducts($scope.sortOrder);
        };

        $scope.getData();
    }]);






