angular.module('ToysKingdom').controller('mainCtrl', function ($scope, $location, $rootScope, $http) {
    console.log("MainCtrl Load Done")

    $rootScope.isLoggedIn = false;
    $rootScope.customer = null;
    $rootScope.cart = [];
    $rootScope.soLuongSanPham = 0;
    $rootScope.tongTien = 0;

    // Lấy thông tin giỏ hàng theo id người dùng
    $rootScope.getCart = function () {
        if (!$rootScope.isLoggedIn) {
            $rootScope.cart = [];
            $rootScope.soLuongSanPham = 0;
            $rootScope.tongTien = 0;
            return;
        }
        $http.get('/api-public/cart/getCart', {
            params: {
                idUser: $rootScope.customer.idUser
            }
        }).then(function (response) {
            $rootScope.cart = response.data.data;
            $rootScope.soLuongSanPham = 0;
            $rootScope.tongTien = 0;
            $rootScope.cart.forEach(e => {
                $rootScope.tongTien += e.price * e.quantity;
                $rootScope.soLuongSanPham += e.quantity;
            });
        }).catch(function (e) {
            console.log(e);
        });
    }

    // Khi load trang web kiểm tra nếu đã đăng nhập thì lấy thông tin 
    if (localStorage.getItem('isLoggedIn') === 'true') {
        $rootScope.isLoggedIn = true;
        $rootScope.customer = JSON.parse(localStorage.getItem('customer'));
        console.log(localStorage.getItem('isLoggedIn'));
        $rootScope.getCart();
    }



    // chuyển hướng
    $scope.logout = function () {
        // Xóa thông tin
        localStorage.setItem('isLoggedIn', false);
        localStorage.removeItem('customer');

        $rootScope.isLoggedIn = false;
        $rootScope.customer = null;

        $location.path('/login');
        $rootScope.getCart();

        $http.get('/api-public/user/signOut').then(function (response) {
            console.log(response);
        }).catch(function (e) {
            console.log(e);
        })};

    $scope.submitDK = function () {
        $location.path('/signUp');
    };

    $scope.backHome = function () {
        $location.path('/home');
    };

    $scope.search = function () {
        // Sữ dụng jquery để lấy dữ liệu thay vì ng-model
        $scope.searchQuery = $('#searchQuery').val();
        console.log($scope.searchQuery);
        if ($scope.searchQuery) {
            console.log("Searching for:", $scope.searchQuery);
            // Gán nội dung người dùng tìm kiếm lên URL
            $location.path('/search').search({ query: $scope.searchQuery });
        } else {
            console.log("Product name cannot be null or empty");
        }
    };

    $scope.toCart = function () {
        $location.path('/cart');
    };
    // nhấn enter có key = 13
    $scope.handleKeyPress = function (event) {
        var keycode = (event.keyCode ? event.keyCode : event.which);
        if (keycode == '13') { // Kiểm tra nếu phím được nhấn là Enter
            $scope.search();
        }
    };

    $scope.redirectToLogin = function () {
        $location.path('/login');
    };


    $scope.submitProfile = function () {
        const { fullName, email, address } = $scope.customer;

        // Kiểm tra các trường để validate
        // if (!fullName || !email || !address || !birthday || !phone) {
        //     alert("Vui lòng điền đầy đủ thông tin.");
        //     return;
        // }

        if (!fullName || !email || !address) {
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
        if (!phonePattern.test(phoneNumber)) {
            alert("Số điện thoại không hợp lệ.");
            return;
        }

        // Nếu thông tin hợp lệ, gửi request cập nhật
        $http.post('/api-public/user/save', { fullName, email, address })
            .then(response => {
                const { success, message, data } = response.data;
                console.log("Dữ liệu của user:", response.data);

                if (success) {
                    alert("Cập nhật thành công");
                    $rootScope.customer = $scope.customer;
                } else {
                    alert("Cập nhật thất bại");
                    $scope.message = message;
                }
            })
            .catch(error => {
                alert("Error");
                console.log(error);
            });
    };


});

angular.module('ToysKingdom').controller('itemType1Ctrl', function ($scope, $rootScope, $http, $location) {
    $scope.addToCart = function (idProduct) {
        console.log($rootScope.customer);
        console.log(idProduct);
        if (!$rootScope.isLoggedIn) {
            Swal.fire({
                title: "Bạn chưa đăng nhập á?",
                text: "Đăng nhập rồi mua hàng tiếp nhe, mất vài giây thoi à :33",
                icon: "question"
            });

            $location.path('/login')
            return;
        }

        var product;
        try {
            product = $rootScope.cart.find(function (item) {
                return item.idProduct === idProduct;
            });
        } catch (e) {
            console.log(e);
        }

        var quantity = 1;

        //Nếu có thì tăng lên 1
        if (product) {
            quantity = product.quantity + 1;
        }

        //Gọi api
        var data = {
            idUser: $rootScope.customer.idUser,
            idProduct: idProduct,
            quantity: quantity,
        }

        $http.post('/api-public/cart/addToCart', data)
            .then(function (response) {
                Toastify({
                    text: "Đã thêm vào giỏ hàng.",
                    close: true,
                    duration: 1000,
                    gravity: 'bottom'
                }).showToast();
                $rootScope.getCart();
            })
            .catch(function (error) {
                console.error(error.message);
            });
    }


    $scope.chuyentrang = function (id) {
        $location.path('/chiteiSP/' + id);
    };
});

// Directive Thẻ Sản phẩm loại 1
angular.module('ToysKingdom').directive('cardItemType1', function () {
    return {
        scope: {
            product: '=product'
        },
        templateUrl: 'components/cardType1.html',
        restrict: 'AE',
        controller: 'itemType1Ctrl',
    }
});

angular.module('ToysKingdom').directive('pagination', function () {
    return {
        restrict: 'E',
        scope: {
            currentPage: '=',
            totalPages: '=',
            loadData: '&'
        },
        template: `
            <nav aria-label="..." class="mt-5">
                <ul class="pagination pagination-lg justify-content-center">
                    <li class="page-item" ng-click="backPage()">
                        <span class="page-link"><i class="fas fa-chevron-left"></i></span>
                    </li>
                    <li ng-repeat="page in getPages()" ng-click="changePage(page)" ng-class="{'active': currentPage === page}" class="page-item">
                        <span class="page-link">{{page}}</span>
                    </li>
                    <li class="page-item" ng-click="nextPage()">
                        <span class="page-link"><i class="fas fa-chevron-right"></i></span>
                    </li>
                </ul>
            </nav>
        `,
        link: function (scope) {
            scope.getPages = function () {
                let pages = [];

                let maxPagesToShow = 3;
                let startPage, endPage;

                if (scope.totalPages <= maxPagesToShow) {
                    // If total pages are less than max pages to show, display all pages
                    startPage = 1;
                    endPage = scope.totalPages;
                } else {
                    // Calculate start and end pages
                    if (scope.currentPage <= Math.ceil(maxPagesToShow / 2)) {
                        startPage = 1;
                        endPage = maxPagesToShow;
                    } else if (scope.currentPage + Math.floor(maxPagesToShow / 2) >= scope.totalPages) {
                        startPage = scope.totalPages - maxPagesToShow + 1;
                        endPage = scope.totalPages;
                    } else {
                        startPage = scope.currentPage - Math.floor(maxPagesToShow / 2);
                        endPage = scope.currentPage + Math.floor(maxPagesToShow / 2);
                    }
                }

                for (let i = startPage; i <= endPage; i++) {
                    pages.push(i);
                }

                if (endPage < scope.totalPages) {
                    pages.push('...');
                    pages.push(scope.totalPages);
                }
                return pages;
            };

            scope.changePage = function (page) {
                if (page !== '...') {
                    scope.currentPage = page;
                    scope.loadData({ page: page });
                }
            };

            scope.nextPage = function () {
                if (scope.currentPage < scope.totalPages) {
                    scope.changePage(scope.currentPage + 1);
                }
            };

            scope.backPage = function () {
                if (scope.currentPage > 1) {
                    scope.changePage(scope.currentPage - 1);
                }
            };
        }
    };
});


