angular.module('admin')
  .controller('discountCtrl', function ($scope, $location, $http, $routeParams, $rootScope) {
    console.log('discountCtrl');

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
            $scope.discountE = angular.copy(d);
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

    $scope.select = function(p){
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
        var product = angular.copy($scope.productE);

        var fileInput = document.getElementById('imageFile');
        var file = fileInput.files[0];

        if (file) {
            try {
                var formData = new FormData();
                formData.append('file', file);

                let response = await fetch('/api-public/products/uploadImage', {
                    method: 'POST',
                    body: formData,
                    headers: {
                        'Accept': 'application/json'
                    }
                });
                let data = await response.json();
                if (response.ok) {
                    console.log('File upload success:', data);
                    product.image = file.name;  // Đặt tên tệp đã tải lên vào product.image
                } else {
                    throw new Error(data.message || 'File upload failed');
                }
            } catch (error) {
                console.error('Error:', error);
                Swal.fire({
                    title: "Lỗi!",
                    text: "Không thể tải ảnh lên!",
                    icon: "error"
                });
                return;  // Dừng lại nếu upload file thất bại
            }
        } else {
            product.image = null;  // Nếu không có tệp nào được chọn, đặt product.image bằng null
        }

        try {
            let response = await $http.post('/api-public/products/save', product);
            if (response.data.success) {
                Swal.fire({
                    title: "Thành công",
                    text: "Đã lưu thay đổi!",
                    icon: "success"
                });
                $scope.getAll();
                $('#editUserModal').modal('hide');
                setTimeout($scope.getAll(), 3000);
            } else {
                Swal.fire({
                    title: "Lỗi",
                    text: "Có lỗi xảy ra trong quá trình Lưu!",
                    icon: "error"
                });
                console.error('Error saving product:', error);
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
                $http.delete('http://localhost:8080/api-public/products/disable', {
                    params: {
                        idProduct: $scope.productE.idProduct
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