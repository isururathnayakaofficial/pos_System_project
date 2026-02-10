package com.example.pos_system.repository;

import com.example.pos_system.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends CrudRepository<Customer, String> {

    Customer findTopByOrderByCidDesc();
}
