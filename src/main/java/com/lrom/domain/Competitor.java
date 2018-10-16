package com.lrom.domain;


import com.lrom.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    private String email;
    private String confirmationToken;
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

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
    @NotEmpty(message = "Please provide an e-mail")
    public String getEmail() {
        return email;
    }

    @Column(name = "username", nullable = false, unique = true)
    @NotEmpty(message = "Please provide your username")
    @Size(min = 2, max = 30)
    public String getUsername() {
        return username;
    }

//    @Transient
//    public String getPassword(){return password;}

    @NotEmpty(message = "Please provide your first name")
    public String getFirstName() {
        return firstName;
    }

    @NotEmpty(message = "Please provide your last name")
    public String getLastName() {
        return lastName;
    }

    @NotEmpty(message = "Please provide your country")
    public String getCountry() {
        return country;
    }

    @NotEmpty(message = "Please provide your city")
    public String getCity() {
        return city;
    }

    @Enumerated(EnumType.STRING)
    public Role getAuthorities() {
        return authorities;
    }

    @Temporal(TemporalType.DATE)
    @Column(insertable = false, updatable =  false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getDate(){   return date;}
}



