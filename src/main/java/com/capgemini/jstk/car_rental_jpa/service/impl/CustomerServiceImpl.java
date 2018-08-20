package com.capgemini.jstk.car_rental_jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.jstk.car_rental_jpa.dao.CustomerDao;
import com.capgemini.jstk.car_rental_jpa.domain.CustomerEntity;
import com.capgemini.jstk.car_rental_jpa.mappers.CustomerMapper;
import com.capgemini.jstk.car_rental_jpa.service.CustomerService;
import com.capgemini.jstk.car_rental_jpa.types.CustomerTO;

@Service
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerDao customerRepository;
	
	@Transactional(readOnly = false)
	@Override
	public CustomerTO saveCustomer(CustomerTO customer) {
		CustomerEntity customerEntity = customerRepository.save(CustomerMapper.toCustomerEntity(customer));
		return CustomerMapper.toCustomerTO(customerEntity);
	}
}