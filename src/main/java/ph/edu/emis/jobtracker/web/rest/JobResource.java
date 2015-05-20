package ph.edu.emis.jobtracker.web.rest;

import com.google.common.collect.Iterables;
import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;

import org.apache.commons.lang.WordUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.edu.emis.jobtracker.domain.Job;
import ph.edu.emis.jobtracker.domain.ReferenceNo;
import ph.edu.emis.jobtracker.domain.RestEntity;
import ph.edu.emis.jobtracker.repository.JobRepository;
import ph.edu.emis.jobtracker.repository.ReferenceNoRepository;
import ph.edu.emis.jobtracker.web.rest.exception.CannotDeleteException;

import javax.servlet.http.HttpServletRequest;

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
}
