package com.luv2code.springdemo.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.dao.ICustomerDAO;
import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements ICustomerDAO {

	// need to inject session factory
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Customer> getCustomers() {

		// get the current session
		Session session = sessionFactory.getCurrentSession();

		// create query
		Query<Customer> query = session.createQuery("from Customer order by firstName,lastName,id",
				Customer.class);

		// execute query
		List<Customer> customers = query.getResultList();

		// return the list of customers
		return customers;
	}

	@Override
	public void saveCustomer(Customer customer) {

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		// save the customer in db
		session.saveOrUpdate(customer);
	}

	@Override
	public Customer getCustomer(int id) {

		Session session = sessionFactory.getCurrentSession();

		Customer customer = session.get(Customer.class, id);
		return customer;
	}

	@Override
	public void deleteCustomer(int id) {

		Session session = sessionFactory.getCurrentSession();

		session.delete(getCustomer(id));

	}

}
