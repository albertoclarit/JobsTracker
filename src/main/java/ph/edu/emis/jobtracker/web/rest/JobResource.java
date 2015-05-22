package ph.edu.emis.jobtracker.web.rest;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ph.edu.emis.jobtracker.domain.Job;
import ph.edu.emis.jobtracker.domain.QJob;
import ph.edu.emis.jobtracker.domain.ReferenceNo;
import ph.edu.emis.jobtracker.domain.RestEntity;
import ph.edu.emis.jobtracker.repository.JobRepository;
import ph.edu.emis.jobtracker.repository.ReferenceNoRepository;
import ph.edu.emis.jobtracker.web.rest.exception.CannotDeleteException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by albertoclarit on 5/20/15.
 */
@RestController
@RequestMapping("/api/jobs")
@Transactional
public class JobResource  extends RestResource<Job,Long>{



    @Autowired
    JobRepository jobRepository;


    @Autowired
    ReferenceNoRepository referenceNoRepository;


    @Autowired
    protected JobResource(JpaRepository<Job, Long> repository) {
        super(repository);
    }

    @Override
    protected Sort getSort(HttpServletRequest request) {
        return new Sort(Sort.Direction.DESC,"daterequested");
    }


    @Override
    protected BooleanExpression getFilterExpression(String query, HttpServletRequest request) {
        return null;
    }

    @Override
    protected void afterDelete(Job entity) {

    }

    @Override
    protected void beforeDelete(Job entity) throws CannotDeleteException {

    }

    @Override
    protected void afterSaved(Job entity, HttpServletRequest request) {

    }

    @Override
    protected void beforeSaved(Job entity, HttpServletRequest request) {


        if(entity.getId()==null){
           // entity.setPending(true);
           // entity.setReasonpending("Waiting for approval");
            entity.setDaterequested(DateTime.now().toLocalDateTime());

            //requestnum
           ReferenceNo referenceNo= Iterables.getFirst(referenceNoRepository.findAll(),null);

            if(referenceNo==null){
                referenceNo= new ReferenceNo();
                referenceNo.setRequestnum(1000L);
                referenceNo= referenceNoRepository.save(referenceNo);
            }

            entity.setRequestnum(referenceNo.getRequestnum());

            referenceNo.setRequestnum(referenceNo.getRequestnum()+1);
            referenceNoRepository.save(referenceNo);


           entity.setRequestedby(WordUtils.capitalize(entity.getRequestedby()));

        }
    }

    @Override
    protected void afterSavedUpdate(Job entity, HttpServletRequest request) {

    }

    @Override
    protected void beforeSavedUpdate(Job entity, HttpServletRequest request) {

    }

    //=============

    @Override
    public ResponseEntity<List<Job>> getAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize, @RequestParam(required = false) String filter, HttpServletRequest request) {

        QJob qJob=QJob.job;
        List<Job> jobList= jobRepository.getActive();

        return ResponseEntity.ok(jobList);
    }


    @RequestMapping(value = "/{id}/deny",method = RequestMethod.POST)
    public ResponseEntity deny(
            @PathVariable Long id,
            @RequestParam String reason
    )
    {
        Job job=jobRepository.findOne(id);


        job.setDenied(true);
        job.setPending(false);
        job.setOngoing(false);
        job.setApproved(false);
        job.setFinished(false);
        job.setDatedenied(DateTime.now().toLocalDateTime());
        job.setReasondenied(reason);

        job=jobRepository.save(job);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/{id}/pending",method = RequestMethod.POST)
    public ResponseEntity pending(
            @PathVariable Long id,
            @RequestParam String reason,
            @RequestParam String personofficeconcerned
    )
    {
        Job job=jobRepository.findOne(id);


        job.setDenied(false);
        job.setPending(true);
        job.setOngoing(false);
        job.setApproved(false);
        job.setFinished(false);
        job.setPersonofficeconcerned(personofficeconcerned);
        job.setReasonpending(reason);

        job=jobRepository.save(job);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/{id}/approve",method = RequestMethod.POST)
    public ResponseEntity approve(
            @PathVariable Long id,
            @RequestParam String remarks,
            @RequestParam String implementingpersonoffice,
            @RequestParam String expectedDateCompletion
    )
    {
        Job job=jobRepository.findOne(id);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy");

        job.setDenied(false);
        job.setPending(false);
        job.setOngoing(false);
        job.setApproved(true);
        job.setFinished(false);
        job.setImplementingentity(implementingpersonoffice);
        job.setApproveremarks(remarks);
        job.setExpecteddatecompletion(formatter.parseLocalDateTime(expectedDateCompletion));

        job.setDateapproved(DateTime.now().toLocalDateTime());
        job=jobRepository.save(job);

        return ResponseEntity.ok().build();

    }

    @RequestMapping(value = "/{id}/review",method = RequestMethod.POST)
    public ResponseEntity review(
            @PathVariable Long id,
            @RequestParam String remarks,
            @RequestParam String dateStarted,
            @RequestParam String expectedDateCompletion
    )
    {
        Job job=jobRepository.findOne(id);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy");

        job.setDenied(false);
        job.setPending(false);
        job.setOngoing(true);
        job.setApproved(false);
        job.setFinished(false);

        job.setOngoingexpecteddatecompletion(formatter.parseLocalDateTime(expectedDateCompletion));
        job.setOngoingremarks(remarks);
        job.setDatestarted(formatter.parseLocalDateTime(dateStarted));

        job=jobRepository.save(job);

        return ResponseEntity.ok().build();

    }


    @RequestMapping(value = "/{id}/finished",method = RequestMethod.POST)
    public ResponseEntity finished(
            @PathVariable Long id,
            @RequestParam String remarks,
            @RequestParam String dateFinished
    )
    {
        Job job=jobRepository.findOne(id);

        DateTimeFormatter formatter = DateTimeFormat.forPattern("MM-dd-yyyy");

        job.setDenied(false);
        job.setPending(false);
        job.setOngoing(false);
        job.setApproved(false);
        job.setFinished(true);

        job.setDatefinished(formatter.parseLocalDateTime(dateFinished));
        job.setFinishedremarks(remarks);

        job=jobRepository.save(job);

        return ResponseEntity.ok().build();

    }

    @RequestMapping("/allactive")
    public ResponseEntity<List<Job>> getallActive(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String filter,
            HttpServletRequest request
    ){

        Pageable pageable = new PageRequest(page-1,pageSize,new Sort(Sort.Direction.DESC,"daterequested"));

        Page<Job> jobPage  = null;

        QJob qJob = QJob.job;

       jobPage= jobRepository.findAll(qJob.finished.isTrue(),pageable);


        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", ""+jobPage.getTotalPages());
        headers.add("totalElements", "" + jobPage.getTotalElements());

        return new ResponseEntity<>(jobPage.getContent(), headers, HttpStatus.OK);


    }

    @RequestMapping("/alldenied")
    public ResponseEntity<List<Job>> alldenied(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String filter,
            HttpServletRequest request
    ){

        Pageable pageable = new PageRequest(page-1,pageSize,new Sort(Sort.Direction.DESC,"daterequested"));

        Page<Job> jobPage  = null;

        QJob qJob = QJob.job;

        jobPage= jobRepository.findAll(qJob.denied.isTrue(),pageable);


        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", ""+jobPage.getTotalPages());
        headers.add("totalElements", "" + jobPage.getTotalElements());

        return new ResponseEntity<>(jobPage.getContent(), headers, HttpStatus.OK);


    }

}
