angular.module('ToysKingdom').controller('mainCtrl', function ($scope, $location, $rootScope) {
    console.log("MainCtrl Load Done")

    // Khởi tạo thông tin người dùng từ $rootScope
    $scope.userIn = $rootScope.customer ? angular.copy($rootScope.customer) : {};


    // chuyển hướng
    $scope.logout = function () {
        $rootScope.isLoggedIn = false;
        $rootScope.userName = "";
        $location.path('/login');
    };

    $scope.submitDK = function () {
        $location.path('/signUp');
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

    $scope.redirectToLogin = function () {
        $location.path('/login');
    };


    $scope.submitProfile = function () {
        const { fullName, email, address, birthday, phone } = $scope.customer;

        // Kiểm tra các trường để validate
        if (!fullName || !email || !address || !birthday || !phone) {
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

        // Nếu thông tin hợp lệ, gửi request cập nhật
        $http.post('/api-public/user/update', { fullName, email, address, birthday, phone })
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

// Directive Thẻ Sản phẩm loại 1
angular.module('ToysKingdom').directive('cardItemType1', function () {
    return {
        scope: {
            product: '=product'
        },
        templateUrl: 'components/cardType1.html',
        restrict: 'AE'
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


