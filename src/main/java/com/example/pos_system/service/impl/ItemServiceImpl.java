package com.example.pos_system.service.impl;

import com.example.pos_system.dto.ItemDTO;
import com.example.pos_system.entity.Item;
import com.example.pos_system.repository.ItemRepo;
import com.example.pos_system.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepo itemRepo;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepo.save(
                new Item(
                        itemDTO.getIid(),
                        itemDTO.getIName(),
                        itemDTO.getIQuantity(),
                        itemDTO.getIPrice()
                )
        );

    }
    @Override
    public void updateItem(ItemDTO itemDTO) {
       Item existingItem = itemRepo.findById(itemDTO.getIid()).orElseThrow(()->new RuntimeException(
               "Item not found"
       ));
       existingItem.setIname(itemDTO.getIName());
       existingItem.setIquantity(itemDTO.getIQuantity());
       existingItem.setIprice(itemDTO.getIPrice());
       itemRepo.save(existingItem);

    }

    @Override
    public void deleteItem(ItemDTO itemDTO) {
        Item item = itemRepo.findById(itemDTO.getIid()).orElseThrow(()->new RuntimeException(
                "Item not found"
        ));
        itemRepo.delete(item);

    }

    @Override
    public List<ItemDTO> getAllItems() {
        //fetch all customers from db
        List<Item> items=(List<Item>)itemRepo.findAll();
          //convert to dto
        List<ItemDTO> itemDTOS=items.stream()
                .map(c-> new ItemDTO(
                        c.getIid(),
                        c.getIname(),
                        c.getIquantity(),
                        c.getIprice()
                ))
                .collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    public int getItemStock(String id) {
        return itemRepo.findById(id)
                .map(item -> {
                    try {
                        return Integer.parseInt(item.getIquantity());
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .orElse(0);
    }


}
