'use strict';

angular.module('jobtrackerApp')
    .controller('LoginController', function ($rootScope, $scope, $state, $timeout, Auth) {
        $scope.user = {};
        $scope.errors = {};

        $scope.rememberMe = false;
        $timeout(function (){angular.element('[ng-model="username"]').focus();});
        $scope.login = function () {
            Auth.login({
                username: $scope.username,
                password: $scope.password,
                rememberMe: $scope.rememberMe
            }).then(function () {
                $scope.authenticationError = false;

                $state.go('jobs');
                /* if ($rootScope.previousStateName === 'register') {
                    $state.go('jobs');
                } else {
                    $rootScope.back();
                }*/
            }).catch(function () {
                $scope.authenticationError = true;
            });
        };
    });
