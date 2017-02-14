package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contest_id", nullable = false)
//    private Contest contest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = false)
    private Scenario scenario;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "share_owner_id", nullable = false)
    private Competitor shareOwner;

    private Integer amount;

    private Integer historicPrice;

    private Integer reservedCash;

}


