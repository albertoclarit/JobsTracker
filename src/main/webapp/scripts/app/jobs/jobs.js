'use strict';

angular.module('jobtrackerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jobs', {
                parent: 'site',
                url: '/jobs',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobs.html',
                        controller: 'JobsController'
                    }
                },
                resolve: {
                }
            })
            .state('addjob', {
                parent: 'site',
                url: '/addjob',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/addjob.html',
                        controller: 'AddJobController'
                    }
                },
                resolve: {
                }
            });
    });
