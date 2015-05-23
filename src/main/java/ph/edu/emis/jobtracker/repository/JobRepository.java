package ph.edu.emis.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import ph.edu.emis.jobtracker.domain.Job;

import java.util.List;

/**
 * Created by albertoclarit on 5/20/15.
 */
public interface JobRepository extends JpaRepository<Job,Long>,QueryDslPredicateExecutor {

    @Query("select j from Job j where (j.denied is null or j.denied = false ) and (j.finished <> true  or j.finished is null)")
    List<Job> getActive();
}
