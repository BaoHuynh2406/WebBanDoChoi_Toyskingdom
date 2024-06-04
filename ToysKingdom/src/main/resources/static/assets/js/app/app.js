angular.module('ToysKingdom').controller('mainCtrl', function ($scope) {
    console.log("MainCtrl Load Done")

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
})


// Khai báo 

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


