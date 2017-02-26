package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Scenario {

    private Integer id;
    private Date date;
    private String name;
    private Contest contest;
    private Transaction lastTransaction;
    private OrderX bestBuy;
    private OrderX bestSell;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_sell_order_id")
    public OrderX getBestSell() {
        return bestSell;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id")
    public Transaction getLastTransaction() {
        return lastTransaction;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "best_buy_order_id")
    public OrderX getBestBuy() {
        return bestBuy;
    }
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contest_id", nullable = false)
    public Contest getContest() {
        return contest;
    }
    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate() {
        return date;
    }
}




