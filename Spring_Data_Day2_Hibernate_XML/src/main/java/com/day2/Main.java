package com.day2;

import com.day2.dao.CustomerDAO1;
import com.day2.dao.CustomerDAO2;
import com.day2.dao.CustomerDAO3;
import com.day2.entity.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        CustomerDAO1 customerDAO1 = context.getBean("customerDAO1", CustomerDAO1.class);
        CustomerDAO2 customerDAO2 = context.getBean("customerDAO2", CustomerDAO2.class);
        CustomerDAO3 customerDAO3 = context.getBean("customerDAO3", CustomerDAO3.class);

        Customer c1 = new Customer("mohamed", 29);
        System.out.println("insertion done? : " + customerDAO1.save(c1));

        Customer c2 = new Customer("Ahmed", 25);
        System.out.println("insertion done? : " + customerDAO2.saveWithTransaction(c2));

        System.out.println("number of customers : " + customerDAO1.countCustomers());

        System.out.println("Customer with id 1 : " + customerDAO1.findOne(1));

        System.out.println("countByAgeGreaterThan 26 : " + customerDAO1.countByAgeGreaterThan(26));

        System.out.println("All customers :");
        customerDAO1.findAll().stream().forEach(customer -> {
            System.out.println("Customer with id " + customer.getId() + " : " + customer);
        });

        customerDAO1.findAll().forEach(customer -> {
            System.out.println(customer);
        });

        System.out.println("update done? : ");
        Customer c3 = new Customer(5, "ALi", 30);
        customerDAO2.update(c3);

        System.out.println("delete done? : ");
        customerDAO1.deleteById(10);


        System.out.println("-------------------   @Transactional  -----------------");
        Customer customer = new Customer("Adel", 40);
        System.out.println("Save : " + customerDAO3.save(customer));

        System.out.println(" findOne : " + customerDAO3.findOne(10));

        System.out.println("update done? : ");
        Customer customer1 = new Customer(6, "Adel40", 60);
        customerDAO2.update(customer1);

        context.close();
    }
}
