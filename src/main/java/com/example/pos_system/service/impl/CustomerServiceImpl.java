package com.example.pos_system.service.impl;

import com.example.pos_system.dto.CustomerDTO;
import com.example.pos_system.entity.Customer;
import com.example.pos_system.repository.CustomerRepo;
import com.example.pos_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepo customerRepo;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {


       customerRepo.save(
               new Customer(
                       customerDTO.getCid(),
                       customerDTO.getCname(),
                       customerDTO.getCaddress(),
                       customerDTO.getCphone()
               )
       );
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        // 1. Find the existing customer by ID
        Customer existingCustomer = customerRepo.findById(customerDTO.getCid())
                .orElseThrow(() -> new RuntimeException(
                        "Customer not found with ID: " + customerDTO.getCid()
                ));

        // 2. Update the fields
        existingCustomer.setCname(customerDTO.getCname());
        existingCustomer.setCaddress(customerDTO.getCaddress());
        existingCustomer.setCphone(customerDTO.getCphone());

        // 3. Save the updated customer
        customerRepo.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(CustomerDTO customerDTO) {
        Customer selected =customerRepo.findById(customerDTO.getCid()).orElseThrow(() -> new RuntimeException(
                "Customer not found with ID: " + customerDTO.getCid()
        ));
        customerRepo.delete(selected);


    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        // fetch all customers from DB
        List<Customer> customers = (List<Customer>) customerRepo.findAll();

        // convert to DTO
        List<CustomerDTO> customerDTOs = customers.stream()
                .map(c -> new CustomerDTO(
                        c.getCid(),
                        c.getCname(),
                        c.getCaddress(),
                        c.getCphone()
                ))
                .collect(Collectors.toList());

        return customerDTOs;
    }
}
