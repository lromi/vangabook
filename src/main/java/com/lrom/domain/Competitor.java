package com.lrom.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Competitor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String login;

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date date;

    private String password;

    private String firstName;

    private String middleName;

    private String lastName;

    private String country;

    private String state;

    private String city;

//    @Enumerated(EnumType.STRING)
//    private CompetitorRole role;

    private Integer netCash;

    private Integer reservedCash;


}



