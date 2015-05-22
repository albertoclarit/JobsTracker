'use strict';

angular.module('jobtrackerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('users', {
                parent: 'site',
                url: '/users',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/users/users.html',
                        controller: 'UsersController'
                    }
                },
                resolve: {
                }
            })
            .state('users_add', {
                parent: 'site',
                url: '/users_add',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/users/useredit.html',
                        controller: 'AddUserController'
                    }
                },
                resolve: {
                }
            })
    });
