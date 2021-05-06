package com.day4;

import com.day4.dao.CustomerDAO;
import com.day4.dao.CustomerDAO2;
import com.day4.entity.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext context
                = new ClassPathXmlApplicationContext(
                "beans.xml");

        CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

        CustomerDAO2 customerDAO2 = (CustomerDAO2) context.getBean("customerDAO2");

        System.out.println("------- CRUD Repo   ----------------");

        Customer customer = new Customer("Ali", 30);

        Customer customer2 = new Customer("Ahmed", 50);
        customerDAO.save(customer2);

        customer = customerDAO.save(customer);

        customerDAO.findAll().forEach(customer1 -> System.out.println("Customer ID# " + customer1.getId() + " " + customer1));

        System.out.println("Find by Id ");
        System.out.println(customerDAO.findById(customer.getId()));

        System.out.println(customerDAO.existsById(customer.getId()));

//        customerDAO.deleteAll();

        System.out.println("----------   Pagination   ---------------");

        Pageable pageable = PageRequest.of(1, 3, Sort.by("name"));

        Page<Customer> result = customerDAO2.findAll(pageable);

        if (result.hasContent()) {
            System.out.println(result.getContent());
        }

        System.out.println("-------  Defining Query Methods (Method Name) ----------");

        System.out.println("countByName " + customerDAO.countByName("Ali"));

        System.out.println("findCustomersByIdGreaterThan " +
                customerDAO.findCustomersByIdGreaterThan(44));

        System.out.println("countByNameAndAgeAfter " +
                customerDAO.countByNameAndAgeAfter("Ahmed", 44));

        System.out.println("----   Defining Query Methods (Defined query -----");

        System.out.println("findByName");
        customerDAO.findByName("Ahmed").forEach(System.out::println);

        customerDAO.updateCustomer(25, "Mostafa", 40);

//        Iterable iterable = customerDAO.findAll();
//        iterable.forEach(o -> System.out.println("Customer ID# " + (Customer)o + o));
//        Iterator iterator = iterable.iterator();
//        while (iterator.hasNext()) {
//            Customer customer1 = (Customer) iterator.next();
//            System.out.println(customer1);
//        }
    }
}
