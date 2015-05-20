package ph.edu.emis.jobtracker.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;

/**
 * Created by albertoclarit on 5/20/15.
 */
@Entity
@Table(name = "jobs")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Job extends AbstractAuditingEntity implements RestEntity<Long>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "requestnum", columnDefinition = "int4")
    private Long requestnum;

    @Column(name = "daterequested", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime daterequested;

    @Column(name = "title", columnDefinition = "varchar")
    private String title;

    @Column(name = "description", columnDefinition = "varchar")
    private String description;

    @Column(name = "requestedby", columnDefinition = "varchar")
    private String requestedby;

    @Column(name = "denied", columnDefinition = "bool")
    private Boolean denied;

    @Column(name = "datedenied", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime datedenied;

    @Column(name = "reasondenied", columnDefinition = "varchar")
    private String reasondenied;


    @Column(name = "pending", columnDefinition = "bool")
    private Boolean pending;

    @Column(name = "reasonpending", columnDefinition = "varchar")
    private String reasonpending;


    @Column(name = "personofficeconcerned", columnDefinition = "varchar")
    private String personofficeconcerned;


    @Column(name = "approved", columnDefinition = "bool")
    private Boolean approved;

    @Column(name = "dateapproved", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime dateapproved;

    @Column(name = "implementingentity", columnDefinition = "varchar")
    private String implementingentity;


    @Column(name = "expecteddatecompletion", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime expecteddatecompletion;


    @Column(name = "approveremarks", columnDefinition = "varchar")
    private String approveremarks;

    @Column(name = "ongoing", columnDefinition = "bool")
    private Boolean ongoing;


    @Column(name = "datestarted", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime datestarted;

    @Column(name = "ongoingexpecteddatecompletion", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime ongoingexpecteddatecompletion;

    @Column(name = "ongoingremarks", columnDefinition = "varchar")
    private String ongoingremarks;


    @Column(name = "finished", columnDefinition = "bool")
    private Boolean finished;


    @Column(name = "datefinished", columnDefinition = "timestamp")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
    private LocalDateTime datefinished;


    @Column(name = "finishedremarks", columnDefinition = "varchar")
    private String finishedremarks;



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

    public LocalDateTime getDaterequested() {
        return daterequested;
    }

    public void setDaterequested(LocalDateTime daterequested) {
        this.daterequested = daterequested;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDenied() {
        return denied;
    }

    public void setDenied(Boolean denied) {
        this.denied = denied;
    }

    public LocalDateTime getDatedenied() {
        return datedenied;
    }

    public void setDatedenied(LocalDateTime datedenied) {
        this.datedenied = datedenied;
    }

    public String getReasondenied() {
        return reasondenied;
    }

    public void setReasondenied(String reasondenied) {
        this.reasondenied = reasondenied;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public String getReasonpending() {
        return reasonpending;
    }

    public void setReasonpending(String reasonpending) {
        this.reasonpending = reasonpending;
    }

    public String getPersonofficeconcerned() {
        return personofficeconcerned;
    }

    public void setPersonofficeconcerned(String personofficeconcerned) {
        this.personofficeconcerned = personofficeconcerned;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public LocalDateTime getDateapproved() {
        return dateapproved;
    }

    public void setDateapproved(LocalDateTime dateapproved) {
        this.dateapproved = dateapproved;
    }

    public String getImplementingentity() {
        return implementingentity;
    }

    public void setImplementingentity(String implementingentity) {
        this.implementingentity = implementingentity;
    }

    public LocalDateTime getExpecteddatecompletion() {
        return expecteddatecompletion;
    }

    public void setExpecteddatecompletion(LocalDateTime expecteddatecompletion) {
        this.expecteddatecompletion = expecteddatecompletion;
    }

    public String getApproveremarks() {
        return approveremarks;
    }

    public void setApproveremarks(String approveremarks) {
        this.approveremarks = approveremarks;
    }

    public Boolean getOngoing() {
        return ongoing;
    }

    public void setOngoing(Boolean ongoing) {
        this.ongoing = ongoing;
    }

    public LocalDateTime getDatestarted() {
        return datestarted;
    }

    public void setDatestarted(LocalDateTime datestarted) {
        this.datestarted = datestarted;
    }

    public LocalDateTime getOngoingexpecteddatecompletion() {
        return ongoingexpecteddatecompletion;
    }

    public void setOngoingexpecteddatecompletion(LocalDateTime ongoingexpecteddatecompletion) {
        this.ongoingexpecteddatecompletion = ongoingexpecteddatecompletion;
    }

    public String getOngoingremarks() {
        return ongoingremarks;
    }

    public void setOngoingremarks(String ongoingremarks) {
        this.ongoingremarks = ongoingremarks;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public LocalDateTime getDatefinished() {
        return datefinished;
    }

    public void setDatefinished(LocalDateTime datefinished) {
        this.datefinished = datefinished;
    }

    public String getFinishedremarks() {
        return finishedremarks;
    }

    public void setFinishedremarks(String finishedremarks) {
        this.finishedremarks = finishedremarks;
    }

    public String getRequestedby() {
        return requestedby;
    }

    public void setRequestedby(String requestedby) {
        this.requestedby = requestedby;
    }
}
