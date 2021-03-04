package com.alpha.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

import com.alpha.entities.card.Card;
import com.alpha.entities.card.CardFactory;
import com.alpha.entities.customer.Customer;

public class SelectCardById extends MappingSqlQuery<Card> {

	private static final String SQL_SELECT_CARD_BY_ID = "select c.customer_id, c.first_name, c.last_name, c.email,"
			+ " c.address, s.card_id, s.customer_id, s.card_number, s.card_code,"
			+ " s.expire_date from customer c left join card s on c.customer_id = s.customer_id where s.card_id = ?";
	
	public SelectCardById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_CARD_BY_ID);
		super.setParameters(new SqlParameter("card_id", Types.INTEGER));
	}
	
	@Override
	protected Card mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Customer customer = new Customer();
		customer.setId(rs.getInt("customer_id"));
		customer.setFirstName(rs.getString("first_name"));
		customer.setLastName(rs.getString("last_name"));
		customer.setAddress(rs.getString("address"));
		customer.setEmail(rs.getString("email"));
		
		LocalDate date = rs.getDate("expire_date").toLocalDate();
		
		Card card = CardFactory.createCard(rs.getString("card_number"),
				customer,
				rs.getString("card_code"),
				date);
		card.setId(rs.getInt("card_id"));
		
		return card;
	}

}
