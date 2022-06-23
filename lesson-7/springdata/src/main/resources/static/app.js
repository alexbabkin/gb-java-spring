angular.module('market-front', []).controller('appController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market/';

     //$scope.loadProducts = function (){
     //    $http.get(contextPath + 'products')
     //        .then(function (response) {
     //            console.log(response);
     //            $scope.products = response.data;
     //        });
     //}

    $scope.loadProducts = function (pageIndex=1) {
        $http({
            url: contextPath + 'products',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
        });
    }

    $scope.deleteProduct = function (product) {
        $http.get(contextPath + 'products/delete/' + product.id)
            .then(function (response) {
                console.log("product deleted: " + response)
            });
    }

    $scope.showInfo = function (product) {
        alert(product.title);
    }

    $scope.loadProducts(1);
});