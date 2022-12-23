angular.module('appBasketd', []).controller('basketController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080/app/basket';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath,
            method: 'GET'
        }).then(function (response) {
                    console.log(response.data);
                    $scope.ProductPage = response.data;
                })
        };

    $scope.deleteFromBasket = function () {
        $http.delete(contextPath + '/delete')
            .then(function (response) {
                $scope.loadProducts();
            });
    };


    $scope.loadProducts();
})