'use strict';

angular.module('jobtrackerApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


