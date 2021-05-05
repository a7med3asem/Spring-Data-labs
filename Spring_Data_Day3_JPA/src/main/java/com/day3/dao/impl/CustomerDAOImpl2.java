package com.day3.dao.impl;

import com.day3.dao.CustomerDAO2;
import com.day3.entity.Customer;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class CustomerDAOImpl2 implements CustomerDAO2 {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public long countCustomers() {
        String queryString = "select count(c) from Customer c";

        Query query = entityManager.createQuery(queryString);

        return (long) query.getSingleResult();
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        entityManager.persist(customer);

        return customer;
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        entityManager.merge(customer);
    }

    @Transactional
    @Override
    public void deleteById(Integer customerId) {
        Customer customer = findOne(customerId);

        delete(customer);
    }

    @Transactional
    @Override
    public void delete(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    public Customer findOne(Integer customerId) {
        return entityManager.find(Customer.class, customerId);
    }

}
