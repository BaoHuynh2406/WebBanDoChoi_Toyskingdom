angular.module('ToysKingdom').controller('KhoiphucTKCtrl', function($scope, $http, $location, $rootScope) {
    // Hàm gửi yêu cầu OTP
    $scope.sendOTP = function() {
        console.log($scope.email);

        // Gửi yêu cầu đến backend để gửi OTP
        $http.post('/api-public/khoiphuctaikhoan/getotp', $scope.email) // Chỉnh sửa ở đây
            .then(function(response) {
                if(response.data.success) {
                    alert("OTP đã được gửi tới email của bạn.");
                    $rootScope.email = $scope.email;
                    $location.path('/doiMK');
                }else{
                    alert("Email không tồn tài")
                }
            })
            .catch(function(error) {
                // Xử lý khi gửi email thất bại
                alert("Có lỗi xảy ra. Vui lòng thử lại.");
                console.error(error);
            });
    };
});
