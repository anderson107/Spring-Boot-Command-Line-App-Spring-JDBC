package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateCustomer extends SqlUpdate {

	private static final String SQL_UPDATE_CUSTOMER = "update customer set first_name=:first_name, "
			+ "last_name = :last_name, address= :address, email= :email where customer_id = :customer_id";

	public UpdateCustomer(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_CUSTOMER);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("address", Types.VARCHAR));
		super.declareParameter(new SqlParameter("email", Types.VARCHAR));
		super.declareParameter(new SqlParameter("customer_id", Types.INTEGER));
	}
}
