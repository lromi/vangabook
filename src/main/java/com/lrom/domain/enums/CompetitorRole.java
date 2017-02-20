package com.lrom.domain.enums;


import org.springframework.security.core.GrantedAuthority;

public enum CompetitorRole implements GrantedAuthority {
    USER,ADMIN;



    @Override
    public String getAuthority() {
        return name();
    }
}
