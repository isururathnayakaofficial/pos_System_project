package com.example.pos_system.controller;

import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.repository.CustomerRepo;
import com.example.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepo customerRepository; // needed for next ID

    // Save customer
    @PostMapping("api/v1/customer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
    }

    // Get all customers
    @GetMapping("/api/v1/get_customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Get next customer ID
    @GetMapping("/api/v1/next-id")
    public String getNextCustomerId() {
        // Get last customer from DB ordered by ID descending
        Customer lastCustomer = customerRepository.findTopByOrderByCidDesc();
        if (lastCustomer == null) {
            return "C001"; // first customer
        }

        String lastId = lastCustomer.getCid(); // e.g., "C003"
        int num = Integer.parseInt(lastId.substring(1)); // 3
        num++;
        return String.format("C%03d", num); // C004
    }
    @PutMapping("/api/v1/update-customer")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
    }
}
