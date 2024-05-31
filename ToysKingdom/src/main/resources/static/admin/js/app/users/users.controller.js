// users/users.controller.js
angular.module('admin')
  .controller('usersCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
    console.log('userCtrl');
    $scope.users = [];

    function parseDate(dateString) {
        var parts = dateString.split(' ');
        var month = parts[1];
        var day = parseInt(parts[2].replace(',', ''));
        var year = parseInt(parts[3]);
        return new Date(year, month - 1, day);
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

    $scope.openEditModal = function (user) {
        console.log('Selected user:', user);
        $scope.editedUser = angular.copy(user);
        $('#editUserModal').modal('show');
    };

    $scope.saveChanges = function () {
        $('#editUserModal').modal('hide');
    };

    $scope.deleteUser = function () {
        $('#editUserModal').modal('hide');
    };
  });
