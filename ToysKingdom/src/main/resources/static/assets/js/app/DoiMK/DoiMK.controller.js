angular.module('ToysKingdom').controller('DoiMKCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("DoiMKCtrl Load done");

    $scope.submit = function () {
        if (!$scope.otp || !$scope.newPassword || !$scope.confirmPassword) {
            alert("Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        if ($scope.newPassword !== $scope.confirmPassword) {
            alert("Mật khẩu xác nhận không khớp.");
            return;
        }

        let data =
        {
            email: $rootScope.email,
            otp: $scope.otp,
            password: $scope.newPassword,
        }
        // Gửi yêu cầu đến backend để gửi OTP
        $http.post('/api-public/khoiphuctaikhoan/verify', data) // Chỉnh sửa ở đây
            .then(function (response) {

                if (response.data.success) {
                    Swal.fire({
                        title: "Thành công",
                        text: "Đã thay đổi mật khẩu",
                        icon: "success"
                    });
                    $location.path('/login');
                } else {
                    Swal.fire({
                        title: "Thất bại",
                        text: "OTP không hợp lệ, vui lòng thử lại!",
                        icon: "error"
                    });
                }
            })
            .catch(function (error) {
                // Xử lý khi gửi email thất bại
                Swal.fire({
                    title: "Thất bại",
                    text: "OTP không hợp lệ, vui lòng thử lại!",
                    icon: "error"
                });
                console.error(error);
            });



    };

});
