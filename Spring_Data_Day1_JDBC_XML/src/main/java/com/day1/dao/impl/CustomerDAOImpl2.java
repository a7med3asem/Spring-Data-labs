package com.day1.dao.impl;

import com.day1.dao.CustomerDAO;
import com.day1.model.Customer;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerDAOImpl2 implements CustomerDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCustomer;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        insertCustomer = new SimpleJdbcInsert(dataSource)
                .withTableName("customer")
                .usingColumns("name", "age")
                .usingGeneratedKeyColumns("id");

    }

    @Override
    public Customer getCustomerById(int id) {
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public int insertCustomer(Customer customer) {
        String insertQuery = "insert into customer (id, name, age) values(:id,:name,:age)";
        Map<String, Object> args = new HashMap<>();
        args.put("id", customer.getId());
        args.put("name", customer.getName());
        args.put("age", customer.getAge());

        return jdbcTemplate.update(insertQuery, args);
//        return insertCustomer.execute(param);
    }

    @Override
    public int updateCustomer(int id, Customer customer) {
        return 0;
    }

    @Override
    public int deleteCustomer(int id) {
        return 0;
    }

    @Override
    public int getCustomerCount() {
        String SQL_COUNT = "select count(*) from Customer";
        Map<String, Object> args = new HashMap<>();
        int rowCount = jdbcTemplate.queryForObject(SQL_COUNT, args, Integer.class);
        return rowCount;
    }

    @Override
    public void createTable() {

    }
}
