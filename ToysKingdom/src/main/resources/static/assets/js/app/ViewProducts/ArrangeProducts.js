// angular.module('ToysKingdom', [])
//     .controller('mainCtrl', ['$scope', '$http', function ($scope, $http) {
//         $scope.products = [];
//         $scope.sortOrder = 'priceAsc';
//         $scope.isLoading = true;
//
//         $scope.loadProducts = function () {
//             $http.get('http://localhost:8080/api/products/sort', { params: { direction: $scope.sortOrder === 'priceAsc' ? 'asc' : 'desc' } })
//                 .then(function (response) {
//                     $scope.products = response.data;
//                     $scope.isLoading = false;
//                 }, function (error) {
//                     console.error('Error loading products:', error);
//                     $scope.isLoading = false;
//                 });
//         };
//
//         $scope.sortProducts = function () {
//             $scope.isLoading = true;
//             $scope.loadProducts();
//         };
//
//         $scope.loadProducts();
//     }]);
