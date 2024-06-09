angular.module('ToysKingdom').controller('SignUpCtrl', function ($scope, $http, $location, $rootScope) {
    console.log("SignUpCtrl Load done");

    $scope.submitSignUp = function () {
        const { fullName, email, address, birthday, phoneNumber, password, confirmPassword } = $scope.customer;

        // Kiểm tra các trường để validate
        if (!fullName || !email || !password || !confirmPassword) {
            Swal.fire({
                title: "Thiếu thông tin!",
                text: "Vui lòng điền đầy đủ thông tin.",
                icon: "warning"
            });
            return;
        }

        // Kiểm tra định dạng email
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            Swal.fire({
                title: "Email không hợp lệ!",
                text: "Vui lòng thử lại.",
                icon: "warning"
            });
            return;
        }

        // Kiểm tra số điện thoại
        const phonePattern = /^[0-9]{10}$/;
        if (!phonePattern.test(phoneNumber)) {
            Swal.fire({
                title: "Số điện thoại không hợp lệ!",
                text: "Vui lòng thử lại.",
                icon: "warning"
            });
            return;
        }

        // Kiểm tra mật khẩu có 6 số không
        const passwordPattern = /^\d{6}$/;
        if (!passwordPattern.test(password)) {
            Swal.fire({
                title: "Mật khẩu phải hơn hoặc bằng 6 ký tự!",
                text: "Vui lòng thử lại.",
                icon: "warning"
            });
            return;
        }

        if (password !== confirmPassword) {
            Swal.fire({
                title: "Mật khẩu không chính xác!",
                text: "Vui lòng thử lại.",
                icon: "warning"
            });
            return;
        }

        const formattedBirthday = formatDate(birthday);

        // Nếu thông tin hợp lệ, gửi request đăng ký
        $http.post('/api-public/user/save', { fullName, email, address, birthday: formattedBirthday, phoneNumber, password })
            .then(response => {
                const { success, message } = response.data;
                if (success) {
                    Swal.fire({
                        title: "Đăng ký thành công",
                        text: "Tiếp theo hãy đăng nhập.",
                        icon: "success"
                    });
                    $location.path('/login');
                } else {
                    Swal.fire({
                        title: "Đăng ký thất bại.",
                        text: "Vui lòng thử lại.",
                        icon: "error"
                    });
                    $scope.message = message;
                }
            })
            .catch(error => {
                console.log(error);
            });
    };

    // Hàm để chuyển đổi định dạng ngày tháng
    function formatDate(date) {
        if (!date) return null;
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [day, month, year].join('/'); // Định dạng dd/MM/yyyy
    }
});
