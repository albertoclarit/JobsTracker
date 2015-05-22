package ph.edu.emis.jobtracker.web.rest;

import com.mysema.query.types.expr.BooleanExpression;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ph.edu.emis.jobtracker.domain.Authority;
import ph.edu.emis.jobtracker.domain.RestEntity;
import ph.edu.emis.jobtracker.domain.User;
import ph.edu.emis.jobtracker.repository.AuthorityRepository;
import ph.edu.emis.jobtracker.repository.UserRepository;
import ph.edu.emis.jobtracker.web.rest.exception.CannotDeleteException;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Created by albertoclarit on 5/22/15.
 */
@RestController
@RequestMapping("/api/users2")
public class UserResource2 extends RestResource<User,Long> {


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthorityRepository authorityRepository;


    @Autowired
    UserRepository userRepository;

    @Autowired
    protected UserResource2(JpaRepository<User, Long> repository) {
        super(repository);
    }


    @RequestMapping("/getauthorities")
    public ResponseEntity<List<Authority>> getauthorities(){


        return ResponseEntity.ok(authorityRepository.findAll());

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
    public ResponseEntity<Void> update(@RequestBody User entity, HttpServletRequest request) throws URISyntaxException {

        User user = userRepository.findOne(entity.getId());

        beforeSaved(entity,request);


        user.setLogin(entity.getLogin());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setEmail(entity.getEmail());
        user.setAuthorities(entity.getAuthorities());

        user = userRepository.save(user);
        afterSaved(user, request);
        return ResponseEntity.ok().build();
    }

    @Override
    protected void beforeSaved(User entity, HttpServletRequest request) {

        if(entity.getId()==null){
            entity.setLangKey("en");
            entity.setPassword(passwordEncoder.encode(UUID.randomUUID().toString()));
            entity.setActivated(true);
        }


        Authority authority = authorityRepository.findOne("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);

        if(StringUtils.isNotBlank(request.getParameter("role")))
        {
            Authority target = authorityRepository.findOne(request.getParameter("role"));
            if(target!=null)
                authorities.add(target);
        }
        entity.setAuthorities(authorities);





    }

    @Override
    protected void afterSavedUpdate(User entity, HttpServletRequest request) {

    }

    @Override
    protected void beforeSavedUpdate(User entity, HttpServletRequest request) {

    }

    @RequestMapping("/{id}/changepassword")
    public ResponseEntity<Void>changepassword(
            @PathVariable Long id,
            @RequestParam String newpassword
    ){

        User user = userRepository.findOne(id);

        user.setPassword(passwordEncoder.encode(newpassword));

        userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
