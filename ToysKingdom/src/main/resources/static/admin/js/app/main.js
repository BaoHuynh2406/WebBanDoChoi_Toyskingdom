var app = angular.module('admin', ["ngRoute"]);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/dashboard", {
            templateUrl: "/admin/404.html",
            controller: "adminCtrl"
        })
        .when("/users", {
            templateUrl: "/admin/user.html",
            controller: "userCtrl"
        })
        .when("/video", {
            templateUrl: "Admin/video.jsp",
            controller: "videoCtrl"
        })
        .otherwise({
            redirectTo: '/dashboard'
        });
});
app.controller('adminCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
    console.log('loading');

});

app.controller('videoCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
    console.log('Load video');
    $scope.videos = [];

    $scope.getAll = function () {
        var button = document.getElementById("rotate-button");
        button.classList.add("rotate-icon");
        $http.get('/PhimNew/admin/allVideo')
            .then(function (response) {
                $scope.videos = response.data;
                button.classList.remove("rotate-icon");
            })
            .catch(function (error) {
                console.error('Error loading items:', error);
                button.classList.remove("rotate-icon");
            });
    }

    $scope.getAll();

    // Function to open edit modal and populate user data
    $scope.openEditModal = function (video) {
        console.log('Selected video:', video);
        $scope.vd = angular.copy(video); // Clone user object to prevent modifying original data
        $('#editUserModal').modal('show');
    };

    // Function to save changes to user data
    $scope.saveChanges = function () {
        // Code to update user details in database
        // $scope.editedUser contains updated user data
        // After updating, close modal
        $('#editUserModal').modal('hide');
    };

    // Function to delete user
    $scope.deleteUser = function () {
        // Code to delete user from database
        // After deleting, close modal
        $('#editUserModal').modal('hide');
    };

});

app.controller('userCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
    console.log('userCtrl');
    $scope.users = [];

    function parseDate(dateString) {
        // Example format: "thg 4 3, 2024"
        var parts = dateString.split(' ');
        var month = parts[1]; // "4"
        var day = parseInt(parts[2].replace(',', '')); // "3" without comma
        var year = parseInt(parts[3]); // "2024"
        return new Date(year, month - 1, day); // month index is 0-based
    }


    $scope.getAll = function () {
        var button = document.getElementById("rotate-button");
        button.classList.add("rotate-icon");
        $http.get('/PhimNew/admin/allUser')
            .then(function (response) {
                $scope.users = response.data;
                $scope.users.forEach(function (user) {
                    user.birthday = parseDate(user.birthday);
                });
                button.classList.remove("rotate-icon");
            })
            .catch(function (error) {
                console.error('Error loading items:', error);
                button.classList.remove("rotate-icon");
            });
    };

    $scope.getAll();

    // Function to open edit modal and populate user data
    $scope.openEditModal = function (user) {
        console.log('Selected user:', user);
        $scope.editedUser = angular.copy(user); // Clone user object to prevent modifying original data
        $('#editUserModal').modal('show');
    };

    // Function to save changes to user data
    $scope.saveChanges = function () {
        // Code to update user details in database
        // $scope.editedUser contains updated user data
        // After updating, close modal
        $('#editUserModal').modal('hide');
    };

    // Function to delete user
    $scope.deleteUser = function () {
        // Code to delete user from database
        // After deleting, close modal
        $('#editUserModal').modal('hide');
    };

});


app.filter('dateFormat', function ($filter) {
    return function (input) {
        if (input == null) { return ""; }

        var _date = $filter('date')(new Date(input), 'dd/MM/yyyy');

        return _date.toUpperCase();

    };
});