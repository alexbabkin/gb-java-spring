/** Описание создаваемого модуля, принимает 3 параметра:
 1. Название модуля (market-frontApp)
 2. Набор других модулей в вите строкового массива. от которых данный модуль зависит
 3. Конфигурационные настройки модуля (необязательный параметр)

 controller(name, constructor): создает контроллер
 - appController - имя контроллера
 - function ($scope, $http), где
 - $scope - сервис через который контроллер передает данные в предстваление
 - $http - сервис для взаимодействия с удаленным HTTP-сервром через JSON

 **/

angular.module('market-frontApp', []).controller('appController', function ($scope, $http) {
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
    $scope.loadCategories = function () {
        $http.get(contextPath + '/categories/').then(function (response) {
            console.log(response);
            $scope.categories = response;
        })
    }
    $scope.createNewProduct = function () {
        console.log($scope.new_product);
        $http.post(contextPath + '/products/', $scope.new_product)
            .then(function successCallback(response) {
                $scope.loadProducts(pageDefault);
                $scope.new_product = null;
            }, function failCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.updateProduct = function () {
        console.log($scope.update_product);
        $http.put(contextPath + '/products/', $scope.update_product)
            .then(function successCallback(response) {
                $scope.loadProducts(pageDefault);
                $scope.update_product = null;
            }, function failCallback(response) {
                alert(response.data.message);
            });
    }

    $scope.prepareProductForUpdate = function (productId){
        $http.get(contextPath + '/products/' + productId)
            .then(function successCallback (response){
                $scope.update_product = response.data;
            }, function failCallback (response){
                alert(response.data.message);
            })

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
    $scope.loadProducts(pageDefault);
    $scope.loadCategories();

});