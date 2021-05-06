package com.day4.dao;

import com.day4.entity.Customer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
//@NoRepositoryBean
 * .NoSuchBeanDefinitionException: No bean named 'customerDAO' available
 * */
public interface CustomerDAO extends CrudRepository<Customer, Integer> {
    int countByNameAndAgeAfter(String name, int age);

    int countByName(String firstName);

    List<Customer> findCustomersByIdGreaterThan(int id);

    @Query(value = "FROM Customer c where name = ?1")
    List<Customer> findByName(String name);

    @Transactional
    /*
    * .TransactionRequiredException: Executing an update/delete query
    * */
    @Modifying
    /*
    * Not supported for DML operations [UPDATE com.day4.entity.Customer c SET age = ?1, name = ?2 WHERE id = ?3]
    * */
    @Query("UPDATE Customer c SET age = ?1, name = ?2 WHERE id = ?3")
    void updateCustomer(int age, String name, int id);
}
