angular.module('ToysKingdom').controller('mainCtrl', function ($scope){
    console.log("MainCtrl Load Done")
    $scope.search = function () {
        console.log($scope.searchKey);
        if ($scope.searchKey) {
            console.log("Searching for:", $scope.searchKey);
            // Gán nội dung người dùng tìm kiếm lên URL
            $location.path('/search').search({ query: $scope.searchKey });
        } else {
            console.log("Product name cannot be null or empty");
        }
    };
})


// Khai báo 

// Directive Thẻ Sản phẩm loại 1
angular.module('ToysKingdom').directive('cardItemType1', function () {
    return {
        scope: {
            product: '=product'
        },
        templateUrl: 'components/cardType1.html',
        restrict: 'AE'
    }
});