package com.example.pos_system.repository;

import com.example.pos_system.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepo extends CrudRepository<Item, String> {

}
