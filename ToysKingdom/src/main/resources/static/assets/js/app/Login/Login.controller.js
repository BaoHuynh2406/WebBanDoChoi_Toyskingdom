angular.module('ToysKingdom').controller('LoginCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("LoginCtrl Load done");

    $scope.login = function () {
        const { email, password } = $scope.customer;

        $http.post('/api-public/user/checkUserLogin', { email, password })
            .then(response => {
                console.log("Dữ liệu của user:", response.data.data);
                if (response.data.success) {
                    $rootScope.isLoggedIn = true;
                    $rootScope.customer = response.data.data[0]; // Lưu thông tin user vào $rootScope

                    // Lưu thông tin người dùng vào sessionStorage
                    localStorage.setItem('isLoggedIn', true);
                    localStorage.setItem('customer', JSON.stringify(response.data.data[0]));

                    Swal.fire({
                        title: "Đăng nhập thành công!",
                        text: "Chào, " + response.data.data[0].fullName,
                        icon: "success"
                    });
                    $location.path('/home');
                } else {
                    console.error("Error:" + response.data.message);
                    Swal.fire({
                        title: "Sai Email hoặc mật khẩu!",
                        text: "Vui lòng thử lại",
                        icon: "error"
                    });
                }
            })
            .catch(error => {
                console.error("Error: lỗi: ", error);
            });
    };
});
