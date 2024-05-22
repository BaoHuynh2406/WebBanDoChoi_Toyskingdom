let app = angular.module('ToysKingdom', ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {templateUrl: "page/ViewProduct.html", controller: "mainCtrl"})
        .when("/siginUp", {templateUrl: "./SignUp.html", controller: "myCtrl"})
        .otherwise({templateUrl: "layout/error.html", controller: "myCtrl"});
});

app.controller('mainCtrl', function ($scope, $http) {
    $scope.productsData = []

    const getData = async () => {
        await $http.get('http://localhost:8080/api-public/products/getAllActiveProducts')
            .then(function (response){
                $scope.productsData = response.data.data;
                console.log('getAllActiveProducts')
            }).catch(function (error) {
            console.log(error)

        })
    }

    getData();



})




