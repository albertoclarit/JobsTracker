package ph.edu.emis.jobtracker.web.rest;

import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ph.edu.emis.jobtracker.domain.RestEntity;
import ph.edu.emis.jobtracker.web.rest.exception.CannotDeleteException;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by albertoclarit on 3/30/15.
 */

public abstract class RestResource<T extends RestEntity,ID extends Serializable> {
    private JpaRepository<T, ID> repository;
    private QueryDslPredicateExecutor queryDslPredicateExecutor;
    private final Logger log = LoggerFactory.getLogger(RestResource.class);
    protected RestResource(JpaRepository<T, ID> repository) {
        this.repository = repository;
        queryDslPredicateExecutor  = (QueryDslPredicateExecutor) repository;
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public ResponseEntity<List<T>> getAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize,
            @RequestParam(required = false) String filter,
            HttpServletRequest request
    ) {

        Sort sort = getSort(request);


        if (page != null && pageSize != null) {
            Pageable pageable = new PageRequest(page-1,pageSize,sort);

            Page<T> domainPage  = null;


            if(StringUtils.isNotBlank(filter)){

                domainPage=queryDslPredicateExecutor.findAll(getFilterExpression(filter,request)
                        ,pageable);


            }else {
                domainPage=repository.findAll(pageable);

            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("totalPages", ""+domainPage.getTotalPages());
            headers.add("totalElements", "" + domainPage.getTotalElements());

            return new ResponseEntity<>(domainPage.getContent(), headers, HttpStatus.OK);

        }

        List<T> list=null;

        if(sort!=null)
            list=repository.findAll(sort);
        else
            list=repository.findAll();

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", "1");
        headers.add("totalElements", "" + list.size());

        return new ResponseEntity<>(list, headers, HttpStatus.OK);

    }

    protected abstract Sort getSort(HttpServletRequest request);

    protected abstract BooleanExpression getFilterExpression(String query, HttpServletRequest request);



    @Transactional
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody T entity,
                                       HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save entity : {}", entity);
        if (entity.getId() != null) {
            return ResponseEntity.badRequest().header("Failure", "A record cannot already have an ID").build();
        }


        beforeSaved(entity,request);
        entity=  repository.save(entity);


        afterSaved(entity,request);

        HttpHeaders headers= new HttpHeaders();
        headers.add("location","/api/customers/" + entity.getId());
        headers.add("id",entity.getId()+"");

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }



    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<T> get(@PathVariable ID id){
        T entity = repository.findOne(id);
        if(entity==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(entity, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@RequestBody T entity,
                                       HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to update entity : {}", entity);
        if (entity.getId() == null) {
            return create(entity,request);
        }

        beforeSavedUpdate(entity,request);
        entity= repository.save(entity);
        afterSavedUpdate(entity,request);
        return ResponseEntity.ok().build();
    }



    @Transactional
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable ID id,
                                       HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to delete entity : {}");

        T entity = repository.findOne(id);

        if(entity==null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        try{

            beforeDelete(entity);
            repository.delete(entity);
            afterDelete(entity);

        }catch (PersistenceException e) {
            HttpHeaders headers= new HttpHeaders();
            headers.add("message",e.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }catch (CannotDeleteException e){
            HttpHeaders headers= new HttpHeaders();
            headers.add("message",e.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }

        return ResponseEntity.ok().build();
    }

    protected abstract void afterDelete(T entity);

    protected abstract void beforeDelete(T entity) throws CannotDeleteException;

    protected abstract void afterSaved(T entity, HttpServletRequest request);

    protected abstract void beforeSaved(T entity, HttpServletRequest request);

    protected abstract void afterSavedUpdate(T entity, HttpServletRequest request);

    protected abstract void beforeSavedUpdate(T entity, HttpServletRequest request);

}
