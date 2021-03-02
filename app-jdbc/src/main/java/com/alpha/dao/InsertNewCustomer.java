package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertNewCustomer extends SqlUpdate {

	private static final String SQL_INSERT_CUSTOMER = "insert into Customer(first_name, last_name, address, email) "
			+ "values (:first_name, :last_name, :address, :email)";

	public InsertNewCustomer(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CUSTOMER);
		super.declareParameter(new SqlParameter("first_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("last_name", Types.VARCHAR));
		super.declareParameter(new SqlParameter("address", Types.VARCHAR));
		super.declareParameter(new SqlParameter("email", Types.VARCHAR));
		super.setGeneratedKeysColumnNames(new String("id"));
		super.setReturnGeneratedKeys(true);
	}
}
