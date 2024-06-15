angular.module('ToysKingdom').controller('SignUpCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("SignUpCtrl Load done");

    $scope.user = {};

    $scope.register = function () {
        const { fullname, password, email, phoneNumber, address, birthday } = $scope.user;

        console.log("User data:", $scope.user);  // Log dữ liệu người dùng trước khi gửi yêu cầu

        $http.post('/api-public/user/register', { fullname, password, email, phoneNumber, address, birthday })
            .then(response => {
                const { success, message } = response.data;
                console.log("Dữ liệu đăng ký:", response.data);

                if (success) {
                    toastr.success("Đăng ký thành công!");
                    $location.path('/login');
                } else {
                    toastr.error(message || "Đã xảy ra lỗi. Vui lòng thử lại sau.");
                }
            })
            .catch(error => {
                console.error("Error:", error);
                toastr.error("Đã xảy ra lỗi. Vui lòng thử lại sau.");
            });
    };
});
