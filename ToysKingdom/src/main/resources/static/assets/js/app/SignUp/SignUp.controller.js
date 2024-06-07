angular.module('ToysKingdom').controller('SignUpCtrl', function($scope, $http, $location, $rootScope) {
    console.log("SignUpCtrl Load done");

    $scope.submitSignUp = function () {
        const { fullName, email, address, birthday, phone, password } = $scope.customer; 

        // Kiểm tra các trường để validate
        if (!fullName || !email || !address || !birthday || !phone || !password) {
            alert("Vui lòng điền đầy đủ thông tin.");
            return;
        }

        // Kiểm tra định dạng email
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            alert("Email không hợp lệ.");
            return;
        }

        // Kiểm tra số điện thoại
        const phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phone)) {
            alert("Số điện thoại không hợp lệ.");
            return;
        }

        // Kiểm tra mật khẩu có 6 số không
        const passwordPattern = /^\d{6}$/;
        if (!passwordPattern.test(password)) {
            alert("Mật khẩu phải là 6 số.");
            return;
        }

        // Nếu thông tin hợp lệ, gửi request đăng ký
        $http.post('/api-public/user/save', { fullName, email, address, birthday, phone, password }) 
           .then(response => {
                const { success, message, data } = response.data;
                console.log("Dữ liệu của user:", response.data);

                if (success) {
                    alert("Đăng ký thành công");
                } else {
                    alert("Đăng ký thất bại");
                    $scope.message = message;
                }
            })
           .catch(error => {
                console.log(error);
            });
    };
});
