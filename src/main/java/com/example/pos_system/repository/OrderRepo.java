package com.example.pos_system.repository;

import com.example.pos_system.entity.Customer;
import com.example.pos_system.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends CrudRepository<Order, String> {
}
