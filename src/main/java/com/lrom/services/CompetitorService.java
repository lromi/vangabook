package com.lrom.services;

import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;


public interface CompetitorService {
    UserDetails loadUserByUsername( @NonNull String username);
}
