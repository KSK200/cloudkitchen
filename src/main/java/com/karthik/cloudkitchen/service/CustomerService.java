package com.karthik.cloudkitchen.service;

import com.karthik.cloudkitchen.model.Customer;

public interface CustomerService {
    Customer registerCustomer(Customer customer);
    Customer login(String email, String password);
    Customer findByEmail(String email);
}

