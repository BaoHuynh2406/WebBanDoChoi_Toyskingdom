angular.module('ToysKingdom').controller('mainCtrl', function ($scope, $location){
    console.log("MainCtrl Load Done")
    
    $scope.search = function () {
        $scope.searchQuery = $('#searchQuery').val();
        console.log($scope.searchQuery);
        if ($scope.searchQuery) {
            console.log("Searching for:", $scope.searchQuery);
            // Gán nội dung người dùng tìm kiếm lên URL
            $location.path('/search').search({ query: $scope.searchQuery });
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