package com.day1.dao.impl;

import com.day1.dao.CustomerDAO;
import com.day1.mapper.CustomerRowMapper;
import com.day1.model.Customer;
import lombok.Setter;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

@Setter
public class CustomerDAOImpl implements CustomerDAO {

    private JdbcTemplate jdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createTable() {
        String sql = "create table xxx ( id INT, xxxcol VARCHAR(45), PRIMARY KEY (id))";
        jdbcTemplate.execute(sql);
    }

    @Override
    public Customer getCustomerById(int id) {
        String selectQuery = "select * from customer where id =?";
//        Object[] args = new Object[]{id};
//        Customer customer = jdbcTemplate.queryForObject(selectQuery,args,
//                new BeanPropertyRowMapper<>(Customer.class));
        Customer customer = (Customer) jdbcTemplate.queryForObject(selectQuery,
                new Object[]{id}, new CustomerRowMapper());
        return customer;

    }

    @Override
    public List<Customer> getAllCustomers() {
        String getAllQuery = "select * from customer";
        List<Customer> customers = jdbcTemplate.query(getAllQuery, new CustomerRowMapper());
        return customers;
    }

    @Override
    public int insertCustomer(Customer customer) {
        String insertQuery = "Insert into customer values(?,?,?)";
        Object[] args = new Object[]{customer.getId(), customer.getName(), customer.getAge()};
        return jdbcTemplate.update(insertQuery, args);

    }

    @Override
    public int updateCustomer(int id, Customer customer) {
        String updateQuery = "update customer set id=?, name=?, age=? where id=? ";
        Object[] args = new Object[]{customer.getId(), customer.getName(), customer.getAge(), id};
        return jdbcTemplate.update(updateQuery, args);
    }

    @Override
    public int deleteCustomer(int id) {
        String deleteQuery = "delete from customer where id =?";
        Object[] args = new Object[]{id};
        return jdbcTemplate.update(deleteQuery, args);
    }

    @Override
    public int getCustomerCount() {
        String SQL_COUNT = "select count(*) from Customer";
        int rowCount = jdbcTemplate.queryForObject(SQL_COUNT, Integer.class);
        return rowCount;
    }
}
