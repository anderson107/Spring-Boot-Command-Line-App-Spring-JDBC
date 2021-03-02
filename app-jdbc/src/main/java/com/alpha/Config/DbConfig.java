package com.alpha.Config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.alpha.dao.*;

@Configuration
@ComponentScan(basePackages = "com.alpha.dao")
public class DbConfig {

	@Autowired
	private DbConnection dataSource;

	@Bean(destroyMethod="close")
	public DataSource dataSource() {
		return dataSource.getConnection();
	}

	@Lazy
	@Bean
	public CustomerDao customerDao() {
		CustomerDao customer = new CustomerDaoImpl();
		customer.setDataSource(dataSource());
		return customer;
	}

	@Lazy
	@Bean
	public CardDao cardDao() {
		CardDao card = new CardDaoImpl();
		card.setDataSource(dataSource());
		return card;
	}

}
