package com.alpha.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateCard extends SqlUpdate {

	private static final String SQL_UPDATE_CARD = "update card set card_code=:card_code, expire_date=:expire_date where card_id=:card_id";
	
	public UpdateCard(DataSource dataSource) {
		super(dataSource,SQL_UPDATE_CARD);
		super.declareParameter(new SqlParameter("card_id", Types.INTEGER));
		super.declareParameter(new SqlParameter("card_code", Types.VARCHAR));
		super.declareParameter(new SqlParameter("expire_date", Types.DATE));
	}
}
