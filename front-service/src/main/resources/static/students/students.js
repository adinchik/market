angular.module('market').controller('studentController', function ($scope, $http, $localStorage) {
    $scope.loadStudents = function () {
        $http.get('http://localhost:5555/core/api/v1/students/')
        .then(function (response) {
            $scope.students = response.data;
        });
    };


    $scope.deleteStudent = function (id) {
        $http.delete('http://localhost:5555/core/api/v1/students/' + id)
            .then(function (response) {
                $scope.loadStudents();
            });
    }


     $scope.createNewStudent = function () {
         $http.post('http://localhost:5555/core/api/v1/students', $scope.newStudent)
             .then(function (response) {
                 $scope.newStudent = null;
                 $scope.loadStudents();
             });
     }

    $scope.loadStudents();
});