package com.example.pos_system.service;

import com.example.pos_system.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    void orderSave(OrderDTO orderDTO);
    void orderUpdate(OrderDTO orderDTO);
    void orderDelete(OrderDTO orderDTO);
    List<OrderDTO> getOrders();

}
