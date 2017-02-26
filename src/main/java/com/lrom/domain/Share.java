package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;


@Entity
public class Share {

    private Long id;
    private Scenario scenario;
    private Competitor shareOwner;
    private Integer amount;
    private Integer historicPrice;
    private Integer reservedCash;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = false)
    public Scenario getScenario() {
        return scenario;
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "share_owner_id", nullable = false)
    public Competitor getShareOwner() {
        return shareOwner;
    }

}


