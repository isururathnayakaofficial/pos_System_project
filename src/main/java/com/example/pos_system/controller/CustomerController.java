package com.example.pos_system.controller;

import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.repository.CustomerRepo;
import com.example.pos_system.service.CustomerService;
import com.example.pos_system.util.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRepo customerRepository; // needed for next ID

    // Save customer
    @PostMapping("/api/v1/customer")
    public ResponseEntity<ApiResponse<String>> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return new ResponseEntity(new ApiResponse<>(
                201,"Customer Saved",null
        ), HttpStatus.CREATED);
    }

    // Get all customers
    @GetMapping("/api/v1/get_customers")
    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }


    @PutMapping("/api/v1/update-customer")
    public void updateCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/api/v1/delete-customer")
    public void deleteCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.deleteCustomer(customerDTO);
    }
    // Get next customer ID
    @GetMapping("/api/v1/next-id")
    public String  getNextCustomerId() {

        Customer lastCustomer = customerRepository.findTopByOrderByCidDesc();
        if (lastCustomer == null) {
            return "C001";//first
        }

        String lastId = lastCustomer.getCid();
        int num = Integer.parseInt(lastId.substring(1));
        num++;
        return String.format("C%03d", num);
    }
}
