package com.anz.codingchallenge.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.anz.codingchallenge.domain.Customer;

@RepositoryRestResource(exported = false)
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
