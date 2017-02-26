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

    private Long id;
    private Date date;
    private Competitor creator;
    private Scenario scenario;
    private Short shareQuantity;
    private Short price;
    private Integer amount;
    private OrderType type;
    private Integer reservedCash;
    private OrderStatus status;
    private Date executedDate;
    private Date cancelledDate;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate() {
        return date;
    }
    @Temporal(TemporalType.DATE)
    public Date getCancelledDate() {
        return cancelledDate;
    }
    @Temporal(TemporalType.DATE)
    public Date getExecutedDate() {
        return executedDate;
    }
    @Enumerated(EnumType.STRING)
    public OrderStatus getStatus() {
        return status;
    }
    @Enumerated(EnumType.STRING)
    public OrderType getType() {
        return type;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id",  nullable = false)
    public Scenario getScenario() {
        return scenario;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creator_id",  nullable = false)
    public Competitor getCreator() {
        return creator;
    }

}


