package com.alpha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.alpha.entities.customer.Customer;

public class SelectCustomerById extends MappingSqlQuery<Customer> {

	private static final String SQL_FIND_BY_ID = "select customer_id, first_name, last_name, address, email from customer where customer_id = ?";

	public SelectCustomerById(DataSource dataSource) {
		super(dataSource, SQL_FIND_BY_ID);
		super.declareParameter(new SqlParameter("customer_id", Types.INTEGER));
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
