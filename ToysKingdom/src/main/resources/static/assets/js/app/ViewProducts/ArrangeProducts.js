app.controller('ProductController', function ($scope, $http)
{
   $scope.currentPage = 0;
   $scope.totalPages = 0;
   $scope.productsData = [];
   $scope.sortOrder = 'asc';
   $scope.isLoading = false;

   $scope.loadData = function (page)
   {
       $scope.isLoading = true;
       $http.get('/api/products/sorted',
       {
         params:
             {
                 order: $scope.sortOrder,
                 page: page,
                 size: 10
             }
       }).then(function (response)
       {
        $scope.productsData = response.data.content;
        $scope.totalPages = response.data.totalPages;
        $scope.currentPage = response.data.number;
        $scope.isLoading = false;
       });
   };
        $scope.sortProducts = function ()
        {
            $scope.loadData($scope.currentPage);
        };

        $scope.loadData($scope.currentPage);

});