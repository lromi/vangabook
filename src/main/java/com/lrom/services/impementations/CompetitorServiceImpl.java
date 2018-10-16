package com.lrom.services.impementations;

import com.google.common.collect.ImmutableList;
import com.lrom.domain.Competitor;
import com.lrom.domain.enums.Role;
import com.lrom.repository.CompetitorRepository;
import com.lrom.services.CompetitorService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class CompetitorServiceImpl implements CompetitorService, UserDetailsService {


    @Autowired
    private CompetitorRepository competitorRepository;

    @PostConstruct
    public void init() {

        if (!competitorRepository.findByUsername("user").isPresent()) {
            competitorRepository.save(Competitor.builder()
                    .username("user")
                    .email("lromi@mail.ru")
                    .password(new BCryptPasswordEncoder().encode("password"))
                    .authorities(Role.USER)
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .firstName("Roman")
                    .lastName("Lyapkalo")
                    .country("Ukraine")
                    .city("Dnipro")
                    .build());
        }
    }

    @Override
    public UserDetails loadUserByUsername( @NonNull String username) throws UsernameNotFoundException {


        Competitor competitor = competitorRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("user " + username + " was not found!"));

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


        grantedAuthorities.add(new SimpleGrantedAuthority(competitor.getAuthorities().name()));

        return new org.springframework.security.core.userdetails.User(competitor.getUsername(), competitor.getPassword(), grantedAuthorities);
    }

    @Autowired
    public CompetitorServiceImpl(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public Competitor findByEmail(String email) {
        return competitorRepository.findByEmail(email);
    }

    public Competitor findByConfirmationToken(String confirmationToken) {
        return competitorRepository.findByConfirmationToken(confirmationToken);
    }

    public Optional<Competitor> findByUsername(String username) {
        return competitorRepository.findByUsername(username);
    }

//    public Competitor findByLogin(String username) {return competitorRepository.findByUsername(username);}

    public void saveCompetitor(Competitor competitor) {
        competitorRepository.save(competitor);
    }

}
