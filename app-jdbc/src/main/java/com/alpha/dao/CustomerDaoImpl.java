package com.alpha.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.alpha.entities.customer.Customer;

@Repository("customerDao")
public class CustomerDaoImpl implements CustomerDao {

	//private fields
	private static Logger logger = LoggerFactory.getLogger(CustomerDaoImpl.class);
	private DataSource dataSource;
	private SelectAllCustomers selectAllCustomers;
	private SelectCustomerById selectCustomerById;
	private InsertNewCustomer insertNewCustomer;
	
	
	// constructors
	public CustomerDaoImpl() {
		
	}
	
	@Resource(name="dataSource")
	@Override
	public void setDataSource(DataSource conn) {
		this.dataSource = conn;
		this.selectAllCustomers = new SelectAllCustomers(dataSource);
		this.selectCustomerById = new SelectCustomerById(dataSource);
		this.insertNewCustomer = new InsertNewCustomer(dataSource);
	}

	// CRUD methods
	// return all customers
	@Override
	public List<Customer> findAll() {
		return selectAllCustomers.execute();
	}

	// return customer by Id input
	@Override
	public Customer findCustomer(int customerId) {
		Object[]params = new Object[1];
		params[0] = customerId;
		List<Customer> customers = selectCustomerById.execute(params);
		if(customers.size()>0) {
			return customers.get(0);
		}else {
			return null;
		}
	}

	// insert customer object into DB
	@Override
	public void insert(Customer customer) {

		Map<String, Object> map = new HashMap<>();
		map.put("first_name", customer.getFirstName());
		map.put("last_name", customer.getLastName());
		map.put("address", customer.getAddress());
		map.put("email", customer.getEmail());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertNewCustomer.updateByNamedParam(map, keyHolder);
		customer.setId(keyHolder.getKey().intValue());
		logger.info("New customer added with id: " + customer.getId());
	}

	// it removes customer from DB by id input
	@Override
	public void delete(int id) {

		String query = "delete from customer where customer_id=" + id;
		String query2 = "select * from customer where customer_id =" + id;
	}

	// it updates customer data
	@Override
	public void update(Customer customer) {

		String query = "update customer set first_name = ?, last_name = ?, address = ?, email = ? where customer_id = ?";
		
	}

}
