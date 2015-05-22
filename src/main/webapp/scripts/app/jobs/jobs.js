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
            })
            .state('jobdenied', {
                parent: 'site',
                url: '/jobdenied/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobdenied.html',
                        controller: 'JobDeniedController'
                    }
                },
                resolve: {
                }
            })
            .state('jobpending', {
                parent: 'site',
                url: '/jobpending/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobpending.html',
                        controller: 'JobPendingController'
                    }
                },
                resolve: {
                }
            })
            .state('jobapproved', {
                parent: 'site',
                url: '/jobapproved/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobapproved.html',
                        controller: 'JobApprovedController'
                    }
                },
                resolve: {
                }
            })
            .state('jobreview', {
                parent: 'site',
                url: '/jobreview/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobreview.html',
                        controller: 'JobReviewController'
                    }
                },
                resolve: {
                }
            })
            .state('jobfinished', {
                parent: 'site',
                url: '/jobfinished/:id',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/jobfinished.html',
                        controller: 'JobFinishedController'
                    }
                },
                resolve: {
                }
            })
            .state('viewfinishedjobs', {
                parent: 'site',
                url: '/viewfinishedjobs',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/viewfinishedjobs.html',
                        controller: 'ViewFinishedController'
                    }
                },
                resolve: {
                }
            }).state('viewdeniedjobs', {
                parent: 'site',
                url: '/viewdeniedjobs',
                data: {
                    roles: []
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/jobs/viewdeniedjobs.html',
                        controller: 'ViewDeniedController'
                    }
                },
                resolve: {
                }
            });
    });
