package com.anz.codingchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anz.codingchallenge.data.repository.CustomerRepository;
import com.anz.codingchallenge.domain.Customer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CustomerContoller")
@RestController
public class CustomerContoller {

	private CustomerRepository customerRepository;

	@Autowired
	public CustomerContoller(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	/**
	 * Lookup for all customers.
	 * 
	 * @return List of all customers
	 */
	@GetMapping(value = "/customers")
	@ApiOperation(value = "Return all the customers details", notes = "Returns HTTP 404 if customers are not found")
	public List<Customer> all() {
		return (List<Customer>) customerRepository.findAll();

	}

	/**
	 * Lookup a Customer by customer id .
	 *
	 * @param id Customer identifier
	 * @return Requested Customer for given customer id
	 */
	@GetMapping(value = "/customers/{customerId}")
	@ApiOperation(value = "Return a customers details for given customer Id.", notes = "Returns HTTP 404 if the customer is not found")
	public Customer findByCustomerId(@PathVariable(value = "customerId") Integer customerId) {
		return customerRepository.findById(customerId).orElseThrow(
				() -> new ResourceNotFoundException("Customer [customerId=" + customerId + "] can't be found"));
	}

}
