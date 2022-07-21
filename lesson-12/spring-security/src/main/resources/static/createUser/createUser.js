angular.module('market-front').controller('createUserController', function ($scope, $http, $routeParams, $location) {
    const contextPath = 'http://localhost:8189/market/api/v1';

    $scope.createNewUser = function () {
        if ($scope.new_user == null) {
            alert("The form is not completed to the end");
            return;
        }
        $http.post(contextPath + '/users/', $scope.new_user)
            .then(function successCallback(response) {
                $scope.new_user = null;
                alert("The user was successfully created");
                $location.path('/');
            }, function failCallback(response) {
                alert(response.data.messages);
            });
    }
});
