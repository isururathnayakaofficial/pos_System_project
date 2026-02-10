package com.example.pos_system.controller;

import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    private  CustomerService customerService;

    @PostMapping("api/v1/customer")
    public void saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
    }
    @GetMapping("api/v1/get_customers")
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomers();
    }
}

