'use strict';

angular.module('jobtrackerApp')
    .controller('JobsController', function ($scope, Principal,Restangular,JobsFormatterService) {
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


                JobsFormatterService.format(job);

            }
        }


    })
    .controller('JobDeniedController', function ($scope, Restangular,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.job= {};
        $scope.param= {};



        Restangular.one("api/jobs",$stateParams.id).get().then(function(data){

            $scope.job = data;

            JobsFormatterService.format($scope.job);


        });


        $scope.deny= function(job){


             if(!$scope.param.reason){
                 SweetAlert.swal("Error!", "Please specify Reason", "error");
                 return;
             }
            SweetAlert.swal({
                    title: "Deny This Job?",
                    text: "Please confirm this command",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, deny it!",
                    closeOnConfirm: true},
                function(isConfirm){

                    if(isConfirm){


                        job.post("deny",null,{reason:$scope.param.reason}).then(function(){

                            $state.go('jobs');
                        },function(){

                        });

                    }



                });

        }

    })
    .controller('JobPendingController', function ($scope, Restangular,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.job= {};
        $scope.param= {};
        Restangular.one("api/jobs",$stateParams.id).get().then(function(data){

            $scope.job = data;

            JobsFormatterService.format($scope.job);



        });

        $scope.pending= function(job){


            if(!$scope.param.reason){
                SweetAlert.swal("Error!", "Please specify Reason", "error");
                return;
            }

            if(!$scope.param.personofficeconcerned){
                SweetAlert.swal("Error!", "Please specify Person/Office Concerned", "error");
                return;
            }

            SweetAlert.swal({
                    title: "Mark Job as PENDING?",
                    text: "Please confirm this command",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, mark it!",
                    closeOnConfirm: true},
                function(isConfirm){

                    if(isConfirm){


                        job.post("pending",null,{
                            reason:$scope.param.reason,
                            personofficeconcerned:$scope.param.personofficeconcerned
                        }).then(function(){

                            $state.go('jobs');
                        },function(){

                        });

                    }



                });

        }



    })

    .controller('JobApprovedController', function ($scope, Restangular,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.job= {};
        $scope.param= {};

        $scope.openDateDialog = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.param.expectedDateCompletionopened = true;

        };

        Restangular.one("api/jobs",$stateParams.id).get().then(function(data){

            $scope.job = data;

            JobsFormatterService.format($scope.job);



        });

        //moment($scope.expenses.currentDate).local().format("MM-DD-YYYY")
        $scope.approve= function(job){


            if(!$scope.param.expectedDateCompletion){
                SweetAlert.swal("Error!", "Please specify Expected Date of Completion", "error");
                return;
            }

            if(!$scope.param.implementingpersonoffice){
                SweetAlert.swal("Error!", "Please specify Implementing Person/Office", "error");
                return;
            }

            if(!$scope.param.remarks){
                SweetAlert.swal("Error!", "Please specify Remarks", "error");
                return;
            }



            SweetAlert.swal({
                    title: "Mark Job as APPROVED?",
                    text: "Please confirm this command",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, approve it!",
                    closeOnConfirm: true},
                function(isConfirm){

                    if(isConfirm){


                        job.post("approve",null,{
                            remarks:$scope.param.remarks,
                            implementingpersonoffice:$scope.param.implementingpersonoffice,
                            expectedDateCompletion :moment($scope.param.expectedDateCompletion).local().format("MM-DD-YYYY")
                        }).then(function(){

                            $state.go('jobs');
                        },function(){

                        });

                    }



                });

        }


    })
    .controller('JobReviewController', function ($scope, Restangular,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.job= {};
        $scope.param= {};

        $scope.openDateDialog = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.param.expectedDateCompletionopened = true;

        };

        $scope.openDateDialogdateStarted = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.param.dateStartedopened = true;

        };

        Restangular.one("api/jobs",$stateParams.id).get().then(function(data){

            $scope.job = data;

            JobsFormatterService.format($scope.job);



        });

        $scope.review= function(job){


            if(!$scope.param.dateStarted){
                SweetAlert.swal("Error!", "Please specify  Date Started", "error");
                return;
            }

            if(!$scope.param.expectedDateCompletion){
                SweetAlert.swal("Error!", "Please specify Expected Date Completion", "error");
                return;
            }

            if(!$scope.param.remarks){
                SweetAlert.swal("Error!", "Please specify Remarks", "error");
                return;
            }



            SweetAlert.swal({
                    title: "Mark Job as Reviewed?",
                    text: "Please confirm this command",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, do it!",
                    closeOnConfirm: true},
                function(isConfirm){

                    if(isConfirm){


                        job.post("review",null,{
                            remarks:$scope.param.remarks,
                            dateStarted:moment($scope.param.dateStarted).local().format("MM-DD-YYYY"),
                            expectedDateCompletion :moment($scope.param.expectedDateCompletion).local().format("MM-DD-YYYY")
                        }).then(function(){

                            $state.go('jobs');
                        },function(){

                        });

                    }



                });

        }


    })
    .controller('JobFinishedController', function ($scope, Restangular,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.job = {};
        $scope.param = {};


        $scope.openDateDialog = function($event) {
            $event.preventDefault();
            $event.stopPropagation();
            $scope.param.dateFinishedopened = true;

        };



        Restangular.one("api/jobs",$stateParams.id).get().then(function(data){

            $scope.job = data;

            JobsFormatterService.format($scope.job);



        });


        $scope.finished= function(job){


            if(!$scope.param.dateFinished){
                SweetAlert.swal("Error!", "Please specify  'Date Finished and Turned Over to the Requester' field", "error");
                return;
            }



            if(!$scope.param.remarks){
                SweetAlert.swal("Error!", "Please specify Remarks", "error");
                return;
            }



            SweetAlert.swal({
                    title: "Mark Job as Finished?",
                    text: "Please confirm this command",
                    type: "warning",
                    showCancelButton: true,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "Yes, do it!",
                    closeOnConfirm: true},
                function(isConfirm){

                    if(isConfirm){


                        job.post("finished",null,{
                            remarks:$scope.param.remarks,
                            dateFinished:moment($scope.param.dateFinished).local().format("MM-DD-YYYY")
                        }).then(function(){

                            $state.go('jobs');
                        },function(){

                        });

                    }



                });

        }



    })
    .controller('AddJobController', function ($scope, Restangular,SweetAlert,$state,$timeout) {

        $scope.job= {};

        $scope.beforesubmit = function(form){
            $scope.$broadcast('show-errors-check-validity');
        }


        $scope.save= function(form){


            Restangular.all("api/jobs").post($scope.job).then(function(){
                $state.go('jobs');
                $timeout(function(){
                    SweetAlert.swal("Success!", "Record saved successfully!", "success");
                },1000);


            },function(){
                SweetAlert.swal("Error!", "Record failed to save !", "error");
            });
        }


    })
    .controller('ViewFinishedController', function ($scope, Restangular,RestangularFull,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.finishedjobspaging = { maxSize: 10, currentPage: 1, itemsPerPage: 9, totalItems: 0 }


        $scope.jobs = [];
        $scope.finishedjobspagingChanged = function() {
            $scope.loadFinishedJob();
        };

        $scope.loadFinishedJob=function(){


            $scope.finishedjobsPromise=RestangularFull.all('api/jobs/allactive/').getList({
                page:$scope.finishedjobspaging.currentPage,
                pageSize:$scope.finishedjobspaging.itemsPerPage
            });

            $scope.finishedjobsPromise.then(function(response){
                $scope.jobs.length = 0;
                $scope.jobs = response.data;
                var totalElements = response.headers('totalElements');
                $scope.finishedjobspaging.totalItems =totalElements;

                for(var i=0;i<$scope.jobs.length;i++){

                    var job = $scope.jobs[i];


                    JobsFormatterService.format(job);

                }

            });



        }
        $scope.loadFinishedJob();

    })
    .controller('ViewDeniedController', function ($scope, Restangular,RestangularFull,SweetAlert,$state,$stateParams,JobsFormatterService) {

        $scope.deniedjobspaging = { maxSize: 10, currentPage: 1, itemsPerPage: 9, totalItems: 0 }


        $scope.jobs = [];
        $scope.deniedjobspagingChanged = function() {
            $scope.loadDeniedJob();
        };

        $scope.loadDeniedJob=function(){


            $scope.deniedjobsPromise=RestangularFull.all('api/jobs/alldenied/').getList({
                page:$scope.deniedjobspaging.currentPage,
                pageSize:$scope.deniedjobspaging.itemsPerPage
            });

            $scope.deniedjobsPromise.then(function(response){
                $scope.jobs.length = 0;
                $scope.jobs = response.data;
                var totalElements = response.headers('totalElements');
                $scope.deniedjobspaging.totalItems =totalElements;

                for(var i=0;i<$scope.jobs.length;i++){

                    var job = $scope.jobs[i];


                    JobsFormatterService.format(job);

                }

            });



        }
        $scope.loadDeniedJob();


    });