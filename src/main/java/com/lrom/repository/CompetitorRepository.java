package com.lrom.repository;


import com.lrom.domain.Competitor;
import lombok.NonNull;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CompetitorRepository extends CrudRepository<Competitor,Integer> {

   Optional<Competitor> findByUsername(@NonNull String username);


}
