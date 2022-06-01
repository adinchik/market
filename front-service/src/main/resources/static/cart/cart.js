angular.module('market').controller('cartController', function ($scope, $http, $localStorage) {
    $scope.loadCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
            });
    };

    $scope.createOrder = function () {
        $http.post('http://localhost:5555/core/api/v1/orders', $scope.newOrder)
            .then(function (response) {
                $scope.newOrder = null;
                $scope.loadCart();
            });
    }

    $scope.guestCreateOrder = function () {
        alert('Для оформления заказа необходимо войти в учетную запись');
    }

    $scope.clearShoppingCart = function () {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/clear')
            .then(function(response) {
                $scope.loadCart();
            });
    }

    $scope.deleteProductFromShoppingCart = function(id) {
        $http.delete('http://localhost:5555/cart/api/v1/cart/'+ $localStorage.marchMarketGuestCartId + '/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.addProductToShoppingCart = function(id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/add/' + id)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.loadCart();
});