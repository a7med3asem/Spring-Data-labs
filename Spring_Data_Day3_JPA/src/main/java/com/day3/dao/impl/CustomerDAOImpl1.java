package com.day3.dao.impl;

import com.day3.dao.CustomerDAO1;
import com.day3.entity.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class CustomerDAOImpl1 implements CustomerDAO1 {

    EntityManager entityManager;

    public void setEntityManager(EntityManagerFactory entityManagerFactory) {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public long countCustomers() {
        String queryString = "select count(c) from Customer c";

        Query query = entityManager.createQuery(queryString);

        return (long) query.getSingleResult();
    }

    @Override
    public long countByAgeGreaterThan(int age) {
        String queryString = "SELECT COUNT(c) FROM Customer c WHERE c.age >= :age";

        Query query = entityManager.createQuery(queryString)
                .setParameter("age", age);

        return (long) query.getSingleResult();
    }

    @Override
    public Customer findOne(Integer customerId) {
        return entityManager.find(Customer.class, customerId);
    }

    @Override
    public List<Customer> findAll() {
        String queryString = "FROM Customer c";

        Query query = entityManager.createQuery(queryString);

        return query.getResultList();
    }

    @Override
    public Customer save(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        return customer;
    }

    @Override
    public void update(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteById(Integer customerId) {
        Customer customer = findOne(customerId);

        delete(customer);
    }

    @Override
    public void delete(Customer customer) {
        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();
    }
}
