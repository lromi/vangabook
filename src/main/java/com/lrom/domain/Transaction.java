package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = false)
    private Scenario scenario;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private Competitor buyer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private Competitor seller;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyers_order_id", nullable = false)
    private OrderX buyersOrderX;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellers_order_id", nullable = false)
    private OrderX sellersOrderX;

    private Integer quantity;

    private Integer price;

    private Integer amount;

}