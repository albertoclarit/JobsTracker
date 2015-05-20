package ph.edu.emis.jobtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import ph.edu.emis.jobtracker.domain.ReferenceNo;

/**
 * Created by albertoclarit on 5/20/15.
 */
public interface ReferenceNoRepository extends JpaRepository<ReferenceNo,Long>,QueryDslPredicateExecutor {
}
