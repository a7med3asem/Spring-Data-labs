package com.day1.dao;

import com.day1.model.Customer;

import java.util.List;

public interface CustomerDAO {

    public Customer getCustomerById(int id);

    public List<Customer> getAllCustomers();

    public int insertCustomer(Customer customer);

    public int updateCustomer(int id, Customer customer);

    public int deleteCustomer(int id);

    public int getCustomerCount();

    void createTable();
}
