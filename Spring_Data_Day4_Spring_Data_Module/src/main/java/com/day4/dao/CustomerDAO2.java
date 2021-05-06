package com.day4.dao;

import com.day4.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerDAO2 extends PagingAndSortingRepository<Customer, Integer> {
}
