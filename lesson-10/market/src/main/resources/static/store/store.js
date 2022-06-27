angular.module('market-front').controller('storeController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';
    let pageDefault = 1;

    $scope.loadProducts = function (pageIndex = 1) {
        pageDefault = pageIndex;
        $http({
            url: contextPath + '/products/',
            method: 'GET',
            params: {
                page: pageIndex
            }
        }).then(function (response) {
            console.log(response);
            $scope.productsPage = response.data;
            $scope.paginationArray = $scope.generatePageIndexes(1, $scope.productsPage.totalPages);
            if (response.data.first) {
                document.getElementById("previewButton").setAttribute("disabled", "true");
                document.getElementById("previewButton").setAttribute("style", "color: grey");
                document.getElementById("nextButton").removeAttribute("disabled");
                document.getElementById("nextButton").setAttribute("style", "color: #0d6efd");
            } else if (response.data.last) {
                document.getElementById("nextButton").setAttribute("disabled", "true");
                document.getElementById("nextButton").setAttribute("style", "color: grey");
                document.getElementById("previewButton").setAttribute("style", "color: #0d6efd");
                document.getElementById("previewButton").removeAttribute("disabled");
            } else {
                document.getElementById("nextButton").removeAttribute("disabled");
                document.getElementById("nextButton").setAttribute("style", "color: #0d6efd");
                document.getElementById("previewButton").removeAttribute("disabled");
                document.getElementById("previewButton").setAttribute("style", "color: #0d6efd");
            }
            console.log(document.getElementById("previewButton"));
        });
    }

    $scope.nextPage = function () {
        pageDefault++;
        $scope.loadProducts(pageDefault);
    }

    $scope.generatePageIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.previewPage = function () {
        pageDefault--;
        $scope.loadProducts(pageDefault);
    }

    $scope.deleteProduct = function (product) {
        $http.delete(contextPath + '/products/' + product.id).then(function (response) {
            console.log(response);
            $scope.products = response.data;
            $scope.loadProducts(pageDefault);
        });
    }

    $scope.navToEditProductPage = function (productId) {
        $location.path('/updateProduct/' + productId);
    }
    $scope.addToCart = function (product) {
        $http.post(contextPath + '/products/addToCart/' + product.id).then(function (response) {
            console.log(response);
            $scope.loadProducts(pageDefault);
        })
    }

    $scope.loadProducts(pageDefault);

});