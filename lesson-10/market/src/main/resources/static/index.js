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
(function () {
    angular
        .module('market-front', ['ngRoute']) //подключаем дополнительный модуль 'ngRoute'
        .config(config) // конфигурируем приложение
        .run(run); //запускаем приложение

    function config($routeProvider) {
        $routeProvider
            .when('/', { //если путь '/'
                templateUrl: 'welcome/welcome.html', // подставляем страничку по пути 'welcome/welcome.html'
                controller: 'welcomeController' // и выбираем контроллер 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/updateProduct/:productId', {
                templateUrl: 'updateProduct/updateProduct.html',
                controller: 'updateProductController'
            })
            .when('/createProduct', {
                templateUrl: 'createProduct/createProduct.html',
                controller: 'createProductController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .otherwise({
                redirectTo: '/' //если указываем путь, которого нету в перечисление делаем редирект на '/'
            });
    }

    function run($rootScope, $http) {
    }
})();
angular.module('market-front').controller('indexController', function ($rootScope, $scope, $http) {
    const contextPath = 'http://localhost:8189/market/api/v1';
});