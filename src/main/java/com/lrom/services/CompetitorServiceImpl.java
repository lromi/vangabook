package com.lrom.services;

import com.google.common.collect.ImmutableList;
import com.lrom.domain.Competitor;
import com.lrom.domain.enums.Role;
import com.lrom.repository.CompetitorRepository;
import com.lrom.services.CompetitorService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;


@Service
public class CompetitorServiceImpl implements CompetitorService, UserDetailsService {

//    @Autowired
//    public void setCompetitorRepository(CompetitorRepository competitorRepository){
//        this.competitorRepository=competitorRepository;
//    }
//    @PersistenceContext
//    private EntityManager em;
    @Autowired
    private CompetitorRepository competitorRepository;

    @PostConstruct
    public void init() {

//        competitorRepository.findByUsername("user").ifPresent(competitor -> {
//            competitor.setPassword(new BCryptPasswordEncoder().encode("password"));
//            competitorRepository.save(competitor);
//        });

        if (!competitorRepository.findByUsername("user").isPresent()) {
            competitorRepository.save(Competitor.builder()
                    .username("user")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .authorities(ImmutableList.of(Role.USER))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build());
        }
    }

    @Override
    public UserDetails loadUserByUsername( @NonNull String username) throws UsernameNotFoundException {
        return competitorRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));
    }
}
