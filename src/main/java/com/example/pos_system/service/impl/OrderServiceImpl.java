package com.example.pos_system.service.impl;

import com.example.pos_system.dto.OrderDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.entity.Order;
import com.example.pos_system.repository.CustomerRepo;
import com.example.pos_system.repository.OrderRepo;
import com.example.pos_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;

    @Override
    public void orderSave(OrderDTO orderDTO) {

        Customer customer = customerRepo.findById(orderDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        orderRepo.save(new Order(
                orderDTO.getOrderId(),
                orderDTO.getItemId(),
                customer,
                orderDTO.getDate(),
                orderDTO.getAmount()
        ));
    }

    @Override
    public void orderUpdate(OrderDTO orderDTO) {
    }

    @Override
    public void orderDelete(OrderDTO orderDTO) {
    }

    @Override
    public List<OrderDTO> getOrders() {
        return List.of();
    }
}
