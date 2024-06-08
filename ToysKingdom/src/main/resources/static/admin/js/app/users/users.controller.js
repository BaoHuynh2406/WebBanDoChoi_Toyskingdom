angular.module('admin')
    .controller('usersCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
        console.log('userCtrl');
        $scope.users = [];

        function parseDate(dateString) {
            if (!dateString) return null;
            var parts = dateString.split('/');
            return new Date(parts[2], parts[1] - 1, parts[0]);  // Tháng trong JavaScript bắt đầu từ 0
        }

        function formatDate(date) {
            if (!date) return null;
            var d = new Date(date),
                month = '' + (d.getMonth() + 1),
                day = '' + d.getDate(),
                year = d.getFullYear();

            if (month.length < 2) month = '0' + month;
            if (day.length < 2) day = '0' + day;

            return [day, month, year].join('/');  // Định dạng dd/MM/yyyy
        }

        $scope.getAll = function () {
            var button = document.getElementById("rotate-button");
            button.classList.add("rotate-icon");
            $scope.users = [];
            $http.get('http://localhost:8080/api-public/user/getAllUsersActive')
                .then(function (response) {
                    $scope.users = response.data.data.map(function (user) {
                        user.birthday = parseDate(user.birthday);
                        return user;
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

            if (!user) {
                $scope.editedUser = null;
            } else {
                $scope.editedUser = angular.copy(user);
            }

            $('#editUserModal').modal('show');
        };

        $scope.saveChanges = function () {
            var user = angular.copy($scope.editedUser);
            user.birthday = formatDate(user.birthday);

            $http.post('/api-public/user/save', user).then(function (response) {
                Swal.fire({
                    title: "Thành công",
                    text: "Đã lưu thay đổi!",
                    icon: "success"
                });
                $scope.getAll();
            }).catch(function (error) {
                Swal.fire({
                    title: "Lỗi",
                    text: "Có lỗi xảy ra trong quá trình Lưu!",
                    icon: "error"
                });
                console.error('Error loading items:', error);
            });
            $('#editUserModal').modal('hide');
        };

        $scope.deleteUser = function () {
            Swal.fire({
                title: "Bạn có chắc?",
                text: "Xóa người dùng này sẽ không thể khôi phục!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Đúng, hãy xóa nó!"
            }).then((result) => {
                $http.delete('http://localhost:8080/api-public/user/disable', {
                    params: {
                        idUser: $scope.editedUser.idUser
                    }
                })
                    .then(function (r) {
                        $scope.getAll();
                        Swal.fire({
                            title: "Đã xóa!",
                            text: "Xóa thành công",
                            icon: "success"
                        });
                    })
                    .catch(function (error) {
                        Swal.fire({
                            title: "Lỗi!",
                            text: "Xóa thất bại",
                            icon: "error"
                        });
                    });

                $('#editUserModal').modal('hide');
            });
        };
    });
