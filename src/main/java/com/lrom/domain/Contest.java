package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
public class Contest {

    private Integer id;
    private String name;
    private Date date;
    private Date startDate;
    private Date expiryDate;
    private Set<Scenario> scenarios;
    private Integer scenarioQuantity;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    @OneToMany(mappedBy = "contest", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Scenario> getScenarios() {
        return scenarios;
    }
    @Temporal(TemporalType.DATE)
    public Date getExpiryDate() {
        return expiryDate;
    }
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate() {
        return date;
    }

}

