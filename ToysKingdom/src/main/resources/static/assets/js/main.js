let app = angular.module('ToysKingdom', ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
    .when("/login", { templateUrl: "./login.html", controller: "myCtrl" })
    .when("/siginUp", { templateUrl: "./SignUp.html", controller: "myCtrl" })
    .otherwise({ templateUrl: "page/ViewProduct.html", controller: "mainCtrl" });
});

app.controller('mainCtrl', function ($scope, $http) {

})




