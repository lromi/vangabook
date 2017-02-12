package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contest_id", nullable = false)
    private Contest contest;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    private Transaction lastTransaction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_buy_order_id")
    private OrderX bestBuy;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_sell_order_id")
    private OrderX bestSell;


}




