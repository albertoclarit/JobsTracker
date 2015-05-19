'use strict';

angular.module('jobtrackerApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('jobs', {
                parent: 'site',
                url: '/',
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
            });
    });
