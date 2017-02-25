package com.lrom.domain.enums;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;


public enum Role {

    USER { public String toString() {
        return "ROLE_USER";}},

    ADMIN { public String toString() {
        return "ROLE_ADMIN";}}





}


