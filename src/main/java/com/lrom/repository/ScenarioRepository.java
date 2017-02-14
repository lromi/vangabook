package com.lrom.repository;


import com.lrom.domain.Scenario;
import com.lrom.domain.Share;
import org.springframework.data.repository.CrudRepository;

public interface ScenarioRepository extends CrudRepository<Scenario,Integer> {

}
