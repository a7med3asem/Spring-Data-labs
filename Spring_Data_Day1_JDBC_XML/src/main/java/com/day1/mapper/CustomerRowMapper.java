package com.day1.mapper;

import com.day1.model.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;

public class CustomerRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setAge(rs.getInt("age"));
        return customer;
    }
}
