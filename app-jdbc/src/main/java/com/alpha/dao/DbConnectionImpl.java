package com.alpha.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:db/jdbc.properties")
public class DbConnectionImpl implements DbConnection {

	private static Logger logger = LoggerFactory.getLogger(DbConnectionImpl.class);
	
	@Value("${driverClassName}")
	private String driverClassName;
	@Value("${url}")
	private String url;
	@Value("${user}")
	private String username;
	@Value("${password}")
	private String password;
	

	public DbConnectionImpl( ) {

	}
	
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Override
	public DataSource getConnection() {

		try {
		 BasicDataSource dataSource = new BasicDataSource();
		 dataSource.setDriverClassName(driverClassName);
		 dataSource.setUrl(url);
		 dataSource.setUsername(username);
		 dataSource.setPassword(password);
		 logger.info("Connected with JDBC successfully");
		 return dataSource;
		}catch(Exception e) {
			logger.error("Problem connecting with JDBC", e);
			return null;
		}
	}

}
