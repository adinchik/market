angular.module('market').controller('storeController', function ($scope, $http, $localStorage) {
    $scope.loadProducts = function () {
        $http.get('http://localhost:5555/core/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.addToCart = function (id) {
        $http.get('http://localhost:5555/cart/api/v1/cart/' + $localStorage.marchMarketGuestCartId + '/add/' + id)
            .then(function (response) {
            });
    }

    $scope.deleteProduct = function (id) {
        $http.delete('http://localhost:5555/core/api/v1/products/' + id)
            .then(function (response) {
                $scope.loadProducts();
            });
    }

    // $scope.createNewProduct = function () {
    //     // console.log($scope.newProduct);
    //     $http.post('http://localhost:5555/core/api/v1/products', $scope.newProduct)
    //         .then(function (response) {
    //             $scope.newProduct = null;
    //             $scope.fillTable();
    //         });
    // }

    $scope.loadProducts();
});