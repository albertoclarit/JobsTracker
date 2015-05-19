'use strict';

angular.module('jobtrackerApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });
