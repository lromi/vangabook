package com.lrom.domain;

import com.lrom.domain.enums.OrderStatus;
import com.lrom.domain.enums.OrderType;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "contest_id",  nullable = false)
//    private Contest contest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id",  nullable = false)
    private Scenario scenario;

    private Short shareQuantity;

    private Short price;

    private Integer amount;

    @Enumerated(EnumType.STRING)
    private OrderType type;

    private Integer reservedCash;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Temporal(TemporalType.DATE)
    private Date executedDate;

    @Temporal(TemporalType.DATE)
    private Date cancelledDate;

}


