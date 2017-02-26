package com.lrom.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Transaction {

    private Long id;
    private Date date;
    private Scenario scenario;
    private OrderX buyersOrderX;
    private OrderX sellersOrderX;
    private Integer quantity;
    private Integer price;
    private Integer amount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellers_order_id", nullable = false)
    public OrderX getSellersOrderX() {
        return sellersOrderX;
    }
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyers_order_id", nullable = false)
    public OrderX getBuyersOrderX() {
        return buyersOrderX;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id", nullable = false)
    public Scenario getScenario() {
        return scenario;
    }
    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate() {
        return date;
    }
}
