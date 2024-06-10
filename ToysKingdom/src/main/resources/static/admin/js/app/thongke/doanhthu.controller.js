angular.module('admin')
    .controller('doanhthuCtrl', 
        function ($scope, $http) {
            console.log('Đã tải trang doanh thu')
       
            $scope.orders = [];

            $scope.getAll = function () {
                var button = document.getElementById("rotate-button");
                button.classList.add("rotate-icon");
                $http.get('/api-public/orders/getAllByStatus')
                    .then(function (response) {
                        $scope.orders = response.data.data;
                        button.classList.remove("rotate-icon");
                    })
                    .catch(function (error) {
                        console.error('Error loading items:', error);
                        button.classList.remove("rotate-icon");
                    });
            };
    
            $scope.getAll();






        });
