package com.example.pos_system.service;

import com.example.pos_system.dto.ItemDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
    void saveItem(ItemDTO itemDTO);
    void updateItem(ItemDTO itemDTO);
    void deleteItem(ItemDTO itemDTO);
    List<ItemDTO> getAllItems();
}
