package com.lrom.repository;


import com.lrom.domain.OrderX;
import com.lrom.domain.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderX,Long> {

}
