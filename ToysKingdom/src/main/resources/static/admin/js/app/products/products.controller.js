angular.module('admin')
    .controller('productsCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
        console.log('productsCtrl');

        $scope.products = [];


        $scope.getAll = function () {
            var button = document.getElementById("rotate-button");
            button.classList.add("rotate-icon");
            $http.get('http://localhost:8080/api-public/products/getAllActiveProducts')
                .then(function (response) {
                    $scope.products = response.data.data;
                    button.classList.remove("rotate-icon");
                })
                .catch(function (error) {
                    console.error('Error loading items:', error);
                    button.classList.remove("rotate-icon");
                });
        };

        $scope.getAll();

        $scope.openEditModal = function (product) {
            console.log('Selected:', product);
            if (!product) {
                $scope.productE = null;
            } else {
                $scope.productE = angular.copy(product);
            }
            $('#editUserModal').modal('show');
        };

        $scope.saveChanges = function () {
            var user = angular.copy($scope.editedUser);
            user.birthday = formatDate(user.birthday);

            $http.post('/api-public/user/save', user).then(function (response) {
                alert("Đã lưu thay đổi");
                $scope.getAll();
            }).catch(function (error) {
                console.error('Error loading items:', error);
            });
            $('#editUserModal').modal('hide');
        };

        $scope.deleteUser = function () {
            $http.delete('http://localhost:8080/api-public/user/disable', {
                params: {
                    idUser: $scope.editedUser.idUser
                }
            })
                .then(function (r) {
                    $scope.getAll();
                    console.log('Deleted user:', r.data.message);
                })
                .catch(function (error) {
                    console.error('Error loading items:', error);
                });

            $('#editUserModal').modal('hide');
        };
    });
