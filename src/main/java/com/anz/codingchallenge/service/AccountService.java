package com.anz.codingchallenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Transaction;
import com.anz.codingchallenge.exception.ErrorCodes;
import com.anz.codingchallenge.exception.ResourceNotFoundException;
import com.anz.codingchallenge.repo.AccountRepository;
import com.anz.codingchallenge.repo.TransactionRepository;

@Service
public class AccountService {

	private AccountRepository accountRepository;

	private TransactionRepository transactionRepository;

	@Autowired
	public AccountService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
	}

	public Account getAccountById(long id) {

		Optional<Account> accountResponse = accountRepository.findById(id);
		if (!accountResponse.isPresent())
			throw new ResourceNotFoundException("Account [Id=" + id + "] can't be found", ErrorCodes.NOT_FOUND);
		return accountResponse.get();
	}

	public List<Account> findByCustomerCustomerId(int customerId) {
		List<Account> accounts = new ArrayList<>();
		accounts = accountRepository.findByCustomerCustomerId(customerId);
		if (CollectionUtils.isEmpty(accounts))
			throw new ResourceNotFoundException("Customer [Id=" + customerId + "] doesn't have active accounts.",
					ErrorCodes.NOT_FOUND);
		return accounts;
	}

	public List<Transaction> findByAccountAccountNumber(long id) {
		List<Transaction> transactions = new ArrayList<>();
		transactions = transactionRepository.findByAccountAccountNumber(id);
		if (CollectionUtils.isEmpty(transactions))
			throw new ResourceNotFoundException("Account [Id=" + id + "] doesn't have transactions.",
					ErrorCodes.NOT_FOUND);
		return transactions;
	}

}
