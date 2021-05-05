package com.day3.dao;

import com.day3.entity.Customer;
import org.springframework.transaction.annotation.Transactional;

public interface CustomerDAO2 {

    long countCustomers();

    @Transactional
    Customer save(Customer customer);

    @Transactional
    void update(Customer customer);

    void deleteById(Integer customerId);

    @Transactional
    void delete(Customer customer);

    Customer findOne(Integer customerId);
}
