package com.day3;

import com.day3.dao.CustomerDAO1;
import com.day3.dao.CustomerDAO2;
import com.day3.entity.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        CustomerDAO1 customerDAO1 = context.getBean("customerDAO1", CustomerDAO1.class);

        Customer c1 = new Customer("mohamed", 29);
        System.out.println("insertion done? : " + customerDAO1.save(c1));

        System.out.println("number of customers : " + customerDAO1.countCustomers());

        System.out.println("Customer with id 1 : " + customerDAO1.findOne(1));

        System.out.println("countByAgeGreaterThan 26 : " + customerDAO1.countByAgeGreaterThan(26));

        System.out.println("All customers :");

        customerDAO1.findAll().forEach(System.out::println);

        System.out.println("update done? : ");
        Customer c3 = new Customer(30, "Mostafa", 30);
        customerDAO1.update(c3);

        System.out.println("delete done? : ");
        customerDAO1.deleteById(25);


        System.out.println("-------------------   @Transactional  -----------------");

        CustomerDAO2 customerDAO2 = context.getBean("customerDAO2", CustomerDAO2.class);

        Customer customer = new Customer("Hamada", 79);
        customerDAO2.save(customer);

        c3.setAge(100);
        customerDAO2.update(c3);

        customerDAO2.deleteById(20);

        context.close();
    }
}
