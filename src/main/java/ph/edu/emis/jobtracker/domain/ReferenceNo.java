package ph.edu.emis.jobtracker.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by albertoclarit on 5/20/15.
 */
@Entity
@Table(name = "referenceno")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReferenceNo extends AbstractAuditingEntity implements RestEntity<Long>{


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "requestnum", columnDefinition = "int4")
    private Long requestnum;





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRequestnum() {
        return requestnum;
    }

    public void setRequestnum(Long requestnum) {
        this.requestnum = requestnum;
    }
}
