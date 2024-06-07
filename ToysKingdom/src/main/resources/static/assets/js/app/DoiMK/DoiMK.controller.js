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
                    alert("Thành công")
                    $location.path('/login');
                } else {
                    alert("Sai OTP")
                }
            })
            .catch(function (error) {
                // Xử lý khi gửi email thất bại
                alert("Thất bài: " + error);
                console.error(error);
            });



    };

});
