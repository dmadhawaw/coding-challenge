package com.anz.codingchallenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.anz.codingchallenge.domain.Account;

@RepositoryRestResource(exported = false)
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByCustomerCustomerId(Integer customerId);

}
