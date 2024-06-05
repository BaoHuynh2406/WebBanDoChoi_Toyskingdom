angular.module('ToysKingdom').controller('LoginCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("LoginCtrl Load done");

    $scope.login = function () {
        const { email, password } = $scope.customer;

        $http.post('/api-public/user/checkUserLogin', { email, password })
            .then(response => {
                const { success, message, data } = response.data;
                console.log("Dữ liệu của user:", response.data);

                if (success) {
                    // Handle successful login
                    $rootScope.isLoggedIn = true;
                    $rootScope.userName = data[0].fullname || data[0].email;
                    console.log("Đăng nhập thành công !");
                    $location.path('/index');
                } else {
                    toastr.error(message || "Đã xảy ra lỗi. Vui lòng thử lại sau.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                console.log("Email hoặc mật khẩu không hợp lệ. Vui lòng thử lại sau.");
            });
    };
});
