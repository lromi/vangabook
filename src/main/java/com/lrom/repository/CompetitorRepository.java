package com.lrom.repository;


import com.lrom.domain.Competitor;
import lombok.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface CompetitorRepository extends CrudRepository<Competitor,Integer> {

    Optional<Competitor> findByUsername(@NonNull String username);


}
