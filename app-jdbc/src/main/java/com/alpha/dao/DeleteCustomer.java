package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteCustomer extends SqlUpdate{
	
	private static final String DELETE_CUSTOMER = "delete from customer where customer_id= :customer_id";

	public DeleteCustomer(DataSource dataSource) {
		super(dataSource, DELETE_CUSTOMER);
		super.setParameters(new SqlParameter("customer_id", Types.INTEGER));
	}
}
