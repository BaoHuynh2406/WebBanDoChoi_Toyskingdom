angular.module('admin')
    .controller('doanhthuCtrl',
        function ($scope) {
            console.log('Đã tải trang doanh thu')
            $scope.tongDoanhThu = 0;
            $scope.orders = [];

            $scope.getAll = function () {
                var button = document.getElementById("rotate-button");
                button.classList.add("rotate-icon");
                $http.get('')
                    .then(function (response) {
                        $scope.orders = response.data.data;
                        button.classList.remove("rotate-icon");
                        $scope.orders.forEach(element => {
                            if(element.status === "PAID") {
                                $scope.tongDoanhThu = $scope.tongDoanhThu + element.total;
                            }
                        });
                    })
                    .catch(function (error) {
                        console.error('Error loading items:', error);
                        button.classList.remove("rotate-icon");
                    });
            };
    
            $scope.getAll();






        });
