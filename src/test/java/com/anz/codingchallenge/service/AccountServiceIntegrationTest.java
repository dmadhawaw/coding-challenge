package com.anz.codingchallenge.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Transaction;

@SpringBootTest
public class AccountServiceIntegrationTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void testGetAccountById() {
		String accountNumberStr = "321143048";
		long accountNumber = Long.valueOf(accountNumberStr);

		Account accountResponse = accountService.getAccountById(accountNumber);
		// Verify the get account by account number.
		assertThat(accountResponse.getAccountName(), is(equalTo("AUCurrent433")));
		assertThat(accountResponse.getAccountType(), is(equalTo("Current")));
		assertThat(accountResponse.getCurrency(), is(equalTo("AUD")));
	}

	@Test
	public void testFindByCustomerCustomerId() {
		int customerId = 101;
		List<Account> accounts = accountService.findByCustomerCustomerId(customerId);
		// Verify the get accounts by customer Id.
		assertThat(accounts, hasSize(2));

	}

	@Test
	public void testGetAllTransactionsByAccountNumber() {
		String accountNumberStr = "321143048";
		long accountNumber = Long.valueOf(accountNumberStr);

		List<Transaction> transResponse = accountService.findByAccountAccountNumber(accountNumber);
		// Verify the get transactions by account number.
		assertThat(transResponse, hasSize(5));
	}

}
