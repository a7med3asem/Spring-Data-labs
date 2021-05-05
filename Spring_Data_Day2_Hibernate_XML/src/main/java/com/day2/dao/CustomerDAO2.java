package com.day2.dao;

import com.day2.entity.Customer;

public interface CustomerDAO2 {
    Customer saveWithTransaction(Customer customer);

    void update(Customer customer);

    void delete(Customer customer);
}
