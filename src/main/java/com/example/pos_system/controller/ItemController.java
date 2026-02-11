package com.example.pos_system.controller;

import com.example.pos_system.dto.ItemDTO;
import com.example.pos_system.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/api/v2/save_item")
    public void saveItem(@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
    }
    @GetMapping("/api/v2/get_items")
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }
    @PutMapping("/api/v2/update_item")
    public void updateItem(@RequestBody ItemDTO itemDTO) {
        itemService.updateItem(itemDTO);
    }
    @DeleteMapping("/api/v2/delete_item")
    public void deleteItem(@RequestBody ItemDTO itemDTO) {
        itemService.deleteItem(itemDTO);
    }

}
