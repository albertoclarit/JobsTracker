/* globals $ */
'use strict';

angular.module('jobtrackerApp')
    .directive('jobtrackerAppPagination', function() {
        return {
            templateUrl: 'scripts/components/form/pagination.html'
        };
    });
