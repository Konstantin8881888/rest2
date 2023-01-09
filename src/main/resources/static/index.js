angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                part_title: $scope.filter ? $scope.filter.part_title : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null,
            }
        }).then(function (response) {
                    console.log(response.data);
                    $scope.ProductPage = response.data;
                })
        };

    $scope.deleteProduct = function (productId) {
        $http.get(contextPath + '/products/delete' + productId)
            .then(function (response) {
                $scope.loadProducts();
            });
    };


    $scope.loadProducts();
})