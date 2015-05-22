'use strict';

angular.module('jobtrackerApp')
    .controller('UsersController', function ($scope, Principal,Restangular) {

        $scope.users=[];
        Restangular.all("api/users2").getList().then(
            function(data){
                $scope.users=data;

                for(var i=0;i<$scope.users.length;i++){
                    var user=$scope.users[i];

                    if(user.login=='system')
                       user.visible=false;
                       else
                       user.visible=true;
                }
            }
        );

    })
    .controller('AddUserController', function ($scope, Principal,Restangular) {

    });
