'use strict';

angular.module('jobtrackerApp')
    .controller('JobsController', function ($scope, Principal,Restangular) {
        $scope.showNewJob = false;
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            $scope.showNewJob = Principal.isInAnyRole(['ROLE_ADMIN','ROLE_USER']);
        });


        $scope.jobs = [];



        Restangular.all("api/jobs").getList().then(function(data){

            $scope.jobs = data;

            $scope.countJob=data.length;
            $scope.processjobs($scope.jobs);
        });


        $scope.processjobs = function(jobs){

            for(var i=0;i<jobs.length;i++){

                var job = jobs[i];

                job.panelClass="panel panel-default";
                job.progressbartype="default";
                job.progressbarvalue="55";
                job.statusClass="text-default";

                job.statusdesc = "New";




                job.showDenied=true;
                job.showPending=true;
                job.showApprove=true;
                job.showOngoing=true;
                job.showFinished=true;


                job.showDeniedDetail=true;
                job.showPendingDetail=true;
                job.showApproveDetail=true;
                job.showOngoingDetail=true;
                job.showFinishedDetail=true;


                if(!job.pending && !job.denied && !job.approved && !job.finished && !job.ongoing){
                    job.showOngoing=false;
                    job.showFinished=false;

                }

            }
        }


    })
    .controller('AddJobController', function ($scope, Restangular,SweetAlert,$state) {

        $scope.job= {};

        $scope.beforesubmit = function(form){
            $scope.$broadcast('show-errors-check-validity');
        }


        $scope.save= function(form){


            Restangular.all("api/jobs").post($scope.job).then(function(){
                SweetAlert.swal("Success!", "Record saved successfully!", "success");
                $state.go('jobs');
            },function(){
                SweetAlert.swal("Error!", "Record failed to save !", "error");
            });
        }


    });