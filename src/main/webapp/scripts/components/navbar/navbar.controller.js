'use strict';

angular.module('jobtrackerApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal,$rootScope) {
        $scope.$state = $state;
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.isInRole = Principal.isInRole;

        Principal.identity().then(function(account) {
            $scope.account = account;

        });



        $scope.logout = function () {
            Auth.logout();
            $rootScope.currentlogin='';
            $state.go('home');
        };
    });
