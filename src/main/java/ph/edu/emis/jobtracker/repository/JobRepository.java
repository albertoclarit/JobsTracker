package ph.edu.emis.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import ph.edu.emis.jobtracker.domain.Job;

/**
 * Created by albertoclarit on 5/20/15.
 */
public interface JobRepository extends JpaRepository<Job,Long>,QueryDslPredicateExecutor {
}
