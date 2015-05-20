'use strict';

angular.module('jobtrackerApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;


        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };
    });
