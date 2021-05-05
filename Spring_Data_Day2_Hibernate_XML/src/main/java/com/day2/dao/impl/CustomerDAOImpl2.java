package com.day2.dao.impl;

import com.day2.dao.CustomerDAO2;
import com.day2.entity.Customer;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class CustomerDAOImpl2 implements CustomerDAO2 {

    private HibernateTemplate hibernateTemplate;
    private TransactionTemplate transactionTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    @Override
    public Customer saveWithTransaction(Customer customer) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                hibernateTemplate.save(customer);

                return status;
            }
        });

        return customer;
    }

    @Override
    public void update(Customer customer) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                hibernateTemplate.update(customer);

                return status;
            }
        });
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
