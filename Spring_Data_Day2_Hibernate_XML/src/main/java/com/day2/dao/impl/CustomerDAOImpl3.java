package com.day2.dao.impl;

import com.day2.dao.CustomerDAO3;
import com.day2.entity.Customer;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomerDAOImpl3 implements CustomerDAO3 {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public long countCustomers() {
        String query = "select count(c) from Customer c";

        List result = hibernateTemplate.find(query);
        return (long) result.get(0);
    }

    @Transactional
    @Override
    public Customer save(Customer customer) {
        hibernateTemplate.save(customer);

        return customer;
    }

    @Transactional
    @Override
    public void update(Customer customer) {
        hibernateTemplate.update(customer);
    }

    @Transactional
    @Override
    public void delete(Customer customer) {
        hibernateTemplate.delete(customer);
    }

    @Transactional
    @Override
    public void deleteById(Integer customerId) {
        Customer customer = findOne(customerId);

        delete(customer);
    }

    @Override
    public Customer findOne(Integer customerId) {
        return hibernateTemplate.get(Customer.class, customerId);
    }
}
