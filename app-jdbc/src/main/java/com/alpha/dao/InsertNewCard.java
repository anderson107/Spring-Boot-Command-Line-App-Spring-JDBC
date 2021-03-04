package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertNewCard extends SqlUpdate{

	private static final String SQL_INSERT_CARD = "insert into card(customer_id, card_number, card_code, expire_date) "
			+ "values (:customer_id, :card_number, :card_code, :expire_date)";
	
	public InsertNewCard(DataSource dataSource) {
		super(dataSource, SQL_INSERT_CARD);
		super.declareParameter(new SqlParameter("customer_id", Types.INTEGER));
		super.declareParameter(new SqlParameter("card_number", Types.VARCHAR));
		super.declareParameter(new SqlParameter("card_code", Types.VARCHAR));
		super.declareParameter(new SqlParameter("expire_date", Types.DATE));
		super.setGeneratedKeysColumnNames(new String("card_id"));
		super.setReturnGeneratedKeys(true);
	}
}
