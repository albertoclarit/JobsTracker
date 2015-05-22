package ph.edu.emis.jobtracker.web.rest;

import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ph.edu.emis.jobtracker.domain.User;
import ph.edu.emis.jobtracker.web.rest.exception.CannotDeleteException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by albertoclarit on 5/22/15.
 */
@RestController
@RequestMapping("/api/users2")
public class UserResource2 extends RestResource<User,Long> {


    @Autowired
    protected UserResource2(JpaRepository<User, Long> repository) {
        super(repository);
    }



    @Override
    protected Sort getSort(HttpServletRequest request) {
        return null;
    }

    @Override
    protected BooleanExpression getFilterExpression(String query, HttpServletRequest request) {
        return null;
    }

    @Override
    protected void afterDelete(User entity) {

    }

    @Override
    protected void beforeDelete(User entity) throws CannotDeleteException {

    }

    @Override
    protected void afterSaved(User entity, HttpServletRequest request) {

    }

    @Override
    protected void beforeSaved(User entity, HttpServletRequest request) {

    }

    @Override
    protected void afterSavedUpdate(User entity, HttpServletRequest request) {

    }

    @Override
    protected void beforeSavedUpdate(User entity, HttpServletRequest request) {

    }
}
