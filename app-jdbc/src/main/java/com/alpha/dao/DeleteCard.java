package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class DeleteCard extends SqlUpdate {

	private static final String SQL_DELETE_CARD = "delete from card where card_id = :card_id";
	
	public DeleteCard(DataSource dataSource) {
		super(dataSource, SQL_DELETE_CARD);
		super.declareParameter(new SqlParameter("card_id", Types.INTEGER));
	}
}
