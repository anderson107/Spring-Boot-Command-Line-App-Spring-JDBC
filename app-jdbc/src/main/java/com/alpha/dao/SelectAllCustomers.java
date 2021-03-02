package com.alpha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

import com.alpha.entities.customer.Customer;

public class SelectAllCustomers extends MappingSqlQuery<Customer> {
	
	private static final String SQL_SELECT_ALL_SINGER = "select * from customer";
	
	public SelectAllCustomers(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_SINGER);
	}

	@Override
	protected Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customer = new Customer();
		customer.setId(rs.getInt("customer_id"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setAddress(rs.getString("address"));
		customer.setEmail(rs.getString("email"));
		
		return customer;
	}

}
