package com.lrom.services.impementations;

import com.lrom.domain.Competitor;
import com.lrom.domain.enums.CompetitorRole;
import com.lrom.repository.CompetitorRepository;
import com.lrom.services.CompetitorService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
public class CompetitorServiceImpl implements CompetitorService, UserDetailsService {
    @Autowired
    private CompetitorRepository competitorRepository;

    @PostConstruct
    public void init() {

//        competitorRepository.findByUsername("user").ifPresent(competitor -> {
//            competitor.setPassword(new BCryptPasswordEncoder().encode("password"));
//            competitorRepository.save(competitor);
//        });

//        if (!competitorRepository.findByUsername("user").isPresent()) {
//            competitorRepository.save(Competitor.builder()
//                    .username("user")
//                    .password(new BCryptPasswordEncoder().encode("password"))
//                    .authorities( CompetitorRole.USER)
//                    .accountNonExpired(true)
//                    .accountNonLocked(true)
//                    .credentialsNonExpired(true)
//                    .enabled(true)
//                    .build());
//        }
    }

    @Override
    public UserDetails loadUserByUsername( @NonNull String username) throws UsernameNotFoundException {
        return competitorRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}
