'use strict';

angular.module('jobtrackerApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal,$rootScope) {
        $scope.$state = $state;

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $rootScope.currentlogin = ' [' + account.login + ']';
        });



        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };
    });
