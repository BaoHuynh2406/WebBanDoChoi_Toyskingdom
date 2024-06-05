angular.module('ToysKingdom').controller('mainCtrl', function ($scope, $location){
    console.log("MainCtrl Load Done")
    $scope.redirectToLogin = function () {
        $location.href = '#!/login'
    };
})

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