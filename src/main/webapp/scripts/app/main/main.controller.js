'use strict';

angular.module('jobtrackerApp')
    .controller('MainController', function ($scope, Principal,$rootScope) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;

            $rootScope.currentlogin = ' [' + account.login + ']';
        });



    });
