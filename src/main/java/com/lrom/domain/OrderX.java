package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class OrderX {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id",  nullable = false)
    private Competitor creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id",  nullable = false)
    private Contest contest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id",  nullable = false)
    private Scenario scenario;

    private Short shareQuantity;

    private Short price;

    private Integer amount;

    private String type;

    private Integer reservedCash;

    private  String status;

    @Temporal(TemporalType.DATE)
    private Date executedDate;

    @Temporal(TemporalType.DATE)
    private Date cancelledDate;

}


