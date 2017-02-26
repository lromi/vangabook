package com.lrom.domain;


import com.lrom.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Competitor {

    private Integer id;
    private String username;
    private String password;
    private Role authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    private Date date;
    private String firstName;
    private String middleName;
    private String lastName;
    private String country;
    private String state;
    private String city;
    private Integer netCash;
    private Integer reservedCash;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    @Enumerated(EnumType.STRING)
    public Role getAuthorities() {
        return authorities;
    }
    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate(){
        return date;
    }
}



