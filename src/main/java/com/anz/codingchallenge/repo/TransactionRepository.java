package com.anz.codingchallenge.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.anz.codingchallenge.domain.Transaction;

@RepositoryRestResource(exported = false)
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByAccountAccountNumber(long id);

}
