angular.module('ToysKingdom').controller('mainCtrl', function ($scope){
    console.log("MainCtrl Load Done")
})


// Khai báo 

// Directive Thẻ Sản phẩm loại 1
angular.module('ToysKingdom').directive('cardItemType1', function () {
    return {
        scope: {
            product: '=product'
        },
        templateUrl: 'components/cardType1.html'
    }
});