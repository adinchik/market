angular.module('market').controller('registerController', function ($scope, $http) {
    $scope.createNewUser = function() {
            $http.post('http://localhost:5555/auth/register', $scope.newUser)
                .then(function (response) {
                        $scope.newUser = null;
                        alert("Вы успешно зарегистрировались");
                });
    }

});