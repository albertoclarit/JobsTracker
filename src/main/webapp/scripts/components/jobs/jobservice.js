'use strict';

angular.module('jobtrackerApp')
    .factory('JobsFormatterService',function(Principal){
        return {

            format:function(job){

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


                job.showDeniedDetail=false;
                job.showPendingDetail=false;
                job.showApproveDetail=false;
                job.showOngoingDetail=false;
                job.showFinishedDetail=false;


                if(!job.pending && !job.denied && !job.approved && !job.finished && !job.ongoing){
                    job.showOngoing=false;
                    job.showFinished=false;
                    job.progressbarvalue=16;

                }

                if(job.denied){
                    job.showDenied=false;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showOngoing=false;
                    job.showFinished=false;


                    job.panelClass="panel panel-danger";
                    job.progressbartype="danger";
                    job.progressbarvalue="0";
                    job.statusClass="text-danger";

                    job.statusdesc = "Denied";

                    job.showDeniedDetail=true;

                }

                if(job.pending){
                    job.showDenied=true;
                    job.showPending=false;
                    job.showApprove=true;
                    job.showOngoing=false;
                    job.showFinished=false;


                    job.panelClass="panel panel-warning";
                    job.progressbartype="warning";
                    job.progressbarvalue="17";
                    job.statusClass="text-warning";

                    job.statusdesc = "Pending";

                    job.showPendingDetail=true;

                }

                if(job.approved){
                    job.showDenied=true;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showOngoing=true;
                    job.showFinished=false;


                    job.panelClass="panel panel-primary";
                    job.progressbartype="primary";
                    job.progressbarvalue="35";
                    job.statusClass="text-primary";

                    job.statusdesc = "Approved by PPM";

                    job.showApproveDetail=true;

                }

                if(job.ongoing){
                    job.showDenied=false;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showOngoing=false;
                    job.showFinished=true;


                    job.panelClass="panel panel-info";
                    job.progressbartype="info";
                    job.progressbarvalue="70";
                    job.statusClass="text-info";

                    job.statusdesc = "Reviewed";

                    job.showOngoingDetail=true;

                }

                if(job.finished){
                    job.showDenied=false;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showOngoing=false;
                    job.showFinished=false;


                    job.panelClass="panel panel-success";
                    job.progressbartype="success";
                    job.progressbarvalue="100";
                    job.statusClass="text-success";

                    job.statusdesc = "Finished";

                    job.showFinishedDetail=true;

                }


                //hide command based on ROLES



                //ROLE_ADMIN
                if(Principal.isInRole('ROLE_ADMIN')){
                 //show all
                }
                else
                if(Principal.isInRole('ROLE_MANAGER')){
                     // no ongoing
                    job.showDenied=false;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showFinished=false;
                }else
                //ROLE_USER
                if(Principal.isInRole('ROLE_USER')){
                    job.showDenied=false;
                    job.showPending=false;
                    job.showApprove=false;
                    job.showOngoing=false;
                    job.showFinished=false;
                }
            }
        }
        })
    .directive('jobstatus', function() {
        return {
            restrict: 'E',
            replace: 'true',
            templateUrl: 'scripts/components/jobs/jobstatus.html',
            scope:{
                job:"="
            },
            link: function (scope, element, attrs) {

            },
            controller: function(){

            }
        };
    });