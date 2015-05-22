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
    .controller('AddUserController', function ($scope, Principal,Restangular,SweetAlert,$timeout,$state) {
        $scope.user = {};
        $scope.param = {};
        $scope.authorities = [];

        $scope.beforesubmit = function(form){
            $scope.$broadcast('show-errors-check-validity');
        }

        Restangular.all("api/users2/getauthorities").getList().then(function(data){
            $scope.authorities=data;
        });

        $scope.save= function(form){


            Restangular.all("api/users2").post($scope.user,{role:$scope.param.role}).then(function(){
                $state.go('users');
                $timeout(function(){
                    SweetAlert.swal("Success!", "Record saved successfully!", "success");
                },100);


            },function(){
                SweetAlert.swal("Error!", "Record failed to save !", "error");
            });
        }

    })
    .controller('EditUserController', function ($scope, Principal,Restangular,SweetAlert,$timeout,$state,$stateParams) {

        $scope.user = {};
        $scope.param = {};
        $scope.authorities = [];

        $scope.beforesubmit = function(form){
            $scope.$broadcast('show-errors-check-validity');
        }

        Restangular.all("api/users2/getauthorities").getList().then(function(data){
            $scope.authorities=data;
        });

        Restangular.one("api/users2",$stateParams.id).get().then(function(data){
            $scope.user=data;
        });


        $scope.save= function(form){


            $scope.user.save({role:$scope.param.role}).then(function(){
                $state.go('users');
                $timeout(function(){
                    SweetAlert.swal("Success!", "Record saved successfully!", "success");
                },100);


            },function(){
                SweetAlert.swal("Error!", "Record failed to save !", "error");
            });
        }

    })
    .controller('ChangePasswordController', function ($scope, Principal,Restangular,SweetAlert,$timeout,$state,$stateParams) {

        $scope.user = {};
        $scope.param = {};
        $scope.authorities = [];

        Restangular.one("api/users2",$stateParams.id).get().then(function(data){
            $scope.user=data;
        });

        $scope.changePassword=function(){

            $scope.user.post("changepassword",null,{
                newpassword:$scope.param.newpassword
            }).then(function(){

                $timeout(function(){
                    SweetAlert.swal("Success!", "Record saved successfully!", "success");
                },100);


            },function(){
                SweetAlert.swal("Error!", "Record failed to save new password!", "error");
            });
        }
    });
