package com.example.pos_system.service;


import com.example.pos_system.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void saveCustomer(CustomerDTO CustomerDTO);
    void updateCustomer(CustomerDTO CustomerDTO);
    void deleteCustomer(CustomerDTO CustomerDTO);
    List<CustomerDTO> getAllCustomers();
}
