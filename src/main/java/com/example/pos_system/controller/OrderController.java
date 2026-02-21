package com.example.pos_system.controller;

import com.example.pos_system.dto.OrderDTO;
import com.example.pos_system.service.ItemService;
import com.example.pos_system.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
 OrderService orderService;
    public OrderController() {}
    @Autowired
    ItemService itemService;

    @PostMapping("/api/v3/place_order")
    public void placeOrder(@RequestBody OrderDTO orderDTO) {
     orderService.orderSave(orderDTO);
    }
    @GetMapping("/api/v3/item_stock/{id}")
    public int getItemStock(@PathVariable String id) {
       int qty= itemService.getItemStock(id);
        return qty;
    }
    @GetMapping("/api/v3/fetchAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getOrders();
    }


}
