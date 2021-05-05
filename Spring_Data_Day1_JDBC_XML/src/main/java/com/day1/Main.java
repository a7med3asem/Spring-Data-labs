package com.day1;

import com.day1.dao.CustomerDAO;
import com.day1.model.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

        CustomerDAO customerDAO = context.getBean("customerDAO", CustomerDAO.class);

        System.out.println("number of customers : " + customerDAO.getCustomerCount());
        System.out.println("Customer with id 20 : " + customerDAO.getCustomerById(20));

        Customer c1 = new Customer("mohamed", 25);
        System.out.println("Insertion : " + customerDAO.insertCustomer(c1));

        Customer c2 = new Customer("Waleed", 50);
        System.out.println("Update : " + customerDAO.updateCustomer(4, c2));

        System.out.println("Delete : " + customerDAO.deleteCustomer(c1.getId()));

        customerDAO.getAllCustomers().stream().forEach(customer -> {
            System.out.println("Customer with id " + customer.getId() + " : " + customer);
        });
    }
}
