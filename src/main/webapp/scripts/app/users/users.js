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
            .state('usersedit', {
                parent: 'site',
                url: '/users/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/users/useredit.html',
                        controller: 'EditUserController'
                    }
                },
                resolve: {
                }
            })
            .state('changepassword', {
                parent: 'site',
                url: '/changepassword/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/users/changepassword.html',
                        controller: 'ChangePasswordController'
                    }
                },
                resolve: {
                }
            })
    });
