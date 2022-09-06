package com.learning.springSecurity.repository;

import com.learning.springSecurity.entity.JdbcCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Optional;

@Repository
public class JdbcCustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcCustomer findCustomerByEmail(String email){
        var sql = "SELECT customer_id, full_name, email, birth_date, gender FROM jdbc_customer "+ "WHERE email ="+ email + "' AND email is not null";

        return jdbcTemplate.queryForObject(sql,this::mapToCustomer);
    }

    private JdbcCustomer mapToCustomer(ResultSet rs,long rowNum){
        var customer = new JdbcCustomer();

        Optional.ofNullable(rs.getDate("birth_date")).ifPresent(b -> customer.setBirthDate(b.toLocalDate()));
        customer.setCustomerId(rs.getInt("customer_id"));
        customer.setEmail(rs.getInt("email"));
        customer.setFullName(rs.getString("full_name"));
        customer.setGender(rs.getString("gender"));

        return customer;
    }

}
