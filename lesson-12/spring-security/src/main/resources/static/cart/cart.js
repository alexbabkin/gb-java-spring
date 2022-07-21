angular.module('market-front').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';
    let pageDefault = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        pageDefault = pageIndex;
        $http.get(contextPath + '/cart/')
            .then(function (response) {
                console.log(response);
                $scope.productsInCart = response;
            });
    }
    $scope.deleteProduct = function (product) {
        $http.post(contextPath + '/cart/', product).then(function (response) {
            console.log(response);
            $scope.loadProducts(pageDefault);
        });
    }
    $scope.loadProducts(pageDefault);

});