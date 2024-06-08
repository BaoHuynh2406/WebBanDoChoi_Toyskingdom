angular.module('admin')
    .controller('discountCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
        console.log('discountCtrl');

        $scope.discountE = {};

        function convertToISODate(dateStr) {
            if (!dateStr) return null;
            var parts = dateStr.split(/[\s/:]+/);
            return `${parts[2]}-${parts[1].padStart(2, '0')}-${parts[0].padStart(2, '0')}T${parts[3].padStart(2, '0')}:${parts[4].padStart(2, '0')}`;
        }

        function convertToCustomFormat(dateStr) {
            if (!dateStr) return null;
            var date = new Date(dateStr);
            return `${date.getDate().toString().padStart(2, '0')}/${(date.getMonth() + 1).toString().padStart(2, '0')}/${date.getFullYear()} ${date.getHours().toString().padStart(2, '0')}:${date.getMinutes().toString().padStart(2, '0')}`;
        }

        $scope.discounts = [];
        $scope.products = [];

        $scope.getAll = function () {
            var button = document.getElementById("rotate-button");
            button.classList.add("rotate-icon");
            $http.get('http://localhost:8080/api-public/discount/getAllActive')
                .then(function (response) {
                    $scope.discounts = response.data.data;
                    button.classList.remove("rotate-icon");
                })
                .catch(function (error) {
                    console.error('Error loading items:', error);
                    button.classList.remove("rotate-icon");
                });
        };

        $scope.getAll();

        $scope.openEditModal = function (d) {
            console.log('Selected:', d);

            $('#editUserModal').modal('show');

            if (!d) {
                $scope.discountE = null;
            } else {
                var startDay = new Date(convertToISODate(d.startDay));
                var endDay = new Date(convertToISODate(d.endDay));
                $scope.discountE.startDay = startDay;
                $scope.discountE.endDay = endDay;
                $scope.discountE.idPrduct = d.idPrduct;
                $scope.discountE.productName = d.productName;
                $scope.discountE.idDiscount = d.idDiscount;
                $scope.discountE.discountPercent = d.discountPercent;
                $scope.discountE.price = d.price;
            }

        };


        $scope.openChooseModal = function () {
            $('#editUserModal').modal('hide');
            $('#chooseProduct').modal('show');
            $scope.getAllProduct();
        }

        $scope.closeChooseModal = function () {
            $('#chooseProduct').modal('hide');
            $('#editUserModal').modal('show');
        }

        $scope.select = function (p) {
            $scope.discountE.idPrduct = p.idProduct;
            $scope.discountE.productName = p.productName;
            $scope.closeChooseModal();
        }

        $scope.getAllProduct = function () {
            var button = document.getElementById("rotate-button");
            button.classList.add("rotate-icon");
            $http.get('http://localhost:8080/api-public/products/getAllActiveProducts')
                .then(function (response) {
                    $scope.products = response.data.data;
                    button.classList.remove("rotate-icon");
                })
                .catch(function (error) {
                    console.error('Error loading items:', error);
                    button.classList.remove("rotate-icon");
                });
        };


        $scope.saveChanges = async function () {
            // Convert dates back to dd/MM/yyyy HH:mm before sending to server
            var startDay = convertToCustomFormat($scope.discountE.startDay);
            var endDay = convertToCustomFormat($scope.discountE.endDay);

            var data = {
                idDiscount: $scope.discountE.idDiscount,
                idPrduct: $scope.discountE.idPrduct,
                discountPercent: $scope.discountE.discountPercent,
                startDay: startDay,
                endDay: endDay
            };
            try {
                let response = await $http.post('/api-public/discount/save', data);
                if (response.data.success) {
                    Swal.fire({
                        title: "Thành công",
                        text: "Đã lưu thay đổi!",
                        icon: "success"
                    });
                    $scope.getAll();
                    $('#editUserModal').modal('hide');
                } else {
                    Swal.fire({
                        title: "Lỗi",
                        text: "Có lỗi xảy ra trong quá trình Lưu!",
                        icon: "error"
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Lỗi",
                    text: "Có lỗi xảy ra trong quá trình Lưu!",
                    icon: "error"
                });
                console.error('Error saving product:', error);
            }
        };



        $scope.deleteProduct = function () {
            Swal.fire({
                title: "Bạn có chắc?",
                text: "Xóa sản phẩm này sẽ không thể khôi phục!",
                icon: "warning",
                showCancelButton: true,
                confirmButtonColor: "#3085d6",
                cancelButtonColor: "#d33",
                confirmButtonText: "Đúng, hãy xóa nó!"
            }).then((result) => {
                if (result.isConfirmed) {
                    $http.delete('/api-public/discount/disable', {
                        params: {
                            idDiscount: $scope.discountE.idDiscount
                        }
                    })
                        .then(function (r) {
                            $scope.getAll();
                            Swal.fire({
                                title: "Đã xóa!",
                                text: "Xóa thành công",
                                icon: "success"
                            });
                        })
                        .catch(function (error) {
                            Swal.fire({
                                title: "Lỗi!",
                                text: "Xóa thất bại",
                                icon: "error"
                            });
                            console.error('Error loading items:', error);
                        });

                    $('#editUserModal').modal('hide');
                }
            });


        };

    });