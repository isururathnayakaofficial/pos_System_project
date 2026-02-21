package com.example.pos_system.service.impl;

import com.example.pos_system.dto.OrderDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.entity.Order;
import com.example.pos_system.repository.CustomerRepo;
import com.example.pos_system.repository.OrderRepo;
import com.example.pos_system.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    @Transactional
    @Override
    public void orderSave(OrderDTO dto) {

        // 🔥 Fetch customer from database
        Customer customer = customerRepo.findById(dto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Create Order
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setCustomer(customer);
        order.setDate(dto.getDate());
        order.setAmount(dto.getAmount());

        orderRepo.save(order);
    }

    @Override
    public void orderUpdate(OrderDTO orderDTO) {
    }

    @Override
    public void orderDelete(OrderDTO orderDTO) {
    }

    @Transactional
    @Override
    public List<OrderDTO> getOrders() {

        List<Order> orders = (List<Order>) orderRepo.findAll();

        return orders.stream()
                .map(o -> new OrderDTO(
                        o.getOrderId(),
                        o.getCustomer().getCid(),
                        o.getAmount(),
                        o.getDate()
                ))
                .collect(Collectors.toList());
    }
}

