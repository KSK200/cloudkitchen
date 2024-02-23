package com.karthik.cloudkitchen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.karthik.cloudkitchen.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}
