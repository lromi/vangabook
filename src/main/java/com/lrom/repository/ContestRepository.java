package com.lrom.repository;


import com.lrom.domain.Competitor;
import com.lrom.domain.Contest;
import org.springframework.data.repository.CrudRepository;

public interface ContestRepository extends CrudRepository<Contest,Integer> {

}
