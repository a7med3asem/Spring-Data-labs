package com.day2.dao.impl;

import com.day2.dao.CustomerDAO1;
import com.day2.entity.Customer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
//import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/*
 * You can extend HibernateDaoSupport and not inject HibernateTemplate yourself
 *
 * */
//public class CustomerDAOImpl1 extends HibernateDaoSupport implements CustomerDAO {

public class CustomerDAOImpl1 implements CustomerDAO1 {

    private HibernateTemplate hibernateTemplate;
    private TransactionTemplate transactionTemplate;


    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public long countCustomers() {
        String query = "select count(*) from Customer";

//        List result = getHibernateTemplate().find(query);
        List result = hibernateTemplate.find(query);
        return (long) result.get(0);
    }

    @Override
    public long countByAgeGreaterThan(int age) {
        String query = "SELECT COUNT(c) FROM Customer c WHERE c.age >= :age";

        List result = hibernateTemplate.findByNamedParam(query, "age", age);

        return (long) result.get(0);
    }

    @Override
    public Customer findOne(Integer customerId) {
        return hibernateTemplate.get(Customer.class, customerId);
    }

    @Override
    public List<Customer> findAll() {
        String query = "FROM Customer c";

        return (List<Customer>) hibernateTemplate.find(query);
    }

    //    @Override
//    public Customer save(Customer customer) {
////        hibernateTemplate.saveOrUpdate(customer);
//        hibernateTemplate.save(customer);
////        hibernateTemplate.save("customer", customer);
//
//        return customer;
//    }
    @Override
    public Customer save(Customer customer) {
        hibernateTemplate.execute(new HibernateCallback<Object>() {
            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                session.beginTransaction();
                session.save(customer);
                session.getTransaction().commit();

                return null;
            }
        });
        return customer;
    }

    @Override
    public void deleteById(Integer customerId) {
        Customer customer = findOne(customerId);

        delete(customer);
    }

    @Override
    public void delete(Customer customer) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                hibernateTemplate.delete(customer);

                return status;
            }
        });
    }
}
