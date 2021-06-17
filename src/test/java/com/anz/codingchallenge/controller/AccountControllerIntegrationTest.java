package com.anz.codingchallenge.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;


import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Transaction;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AccountControllerIntegrationTest {

	@Autowired
	private AccountController accounttController;

	@Test
	public void testGetAccountByAccountNumberValid() {
		// GET an account to the controller; check the outcome
		String accountNumberStr = "585309209";
		long accountNumber = Long.valueOf(accountNumberStr);
		Account outcome = accounttController.getAccount(Long.valueOf(accountNumber));

		// Assert THAT the outcome is as expected
		assertThat(outcome.getAccountName(), is(equalTo("SGSavings726")));
	}

	@Test
	public void testGetAllAccountsByCustomerId() {
		// GET an account to the controller; check the outcome
		int customerId = 101;
		List<Account> accounts = accounttController.getAllAccountsByCustomerId(customerId);
		// Assert THAT the outcome is as expected
		assertThat(accounts.size(), is(equalTo(2)));
	}

	@Test
	public void testGetAllTransactionsByAccountNumber() {
		// GET an account to the controller; check the outcome
		String accountNumberStr = "585309209";
		long accountNumber = Long.valueOf(accountNumberStr);
		List<Transaction> transactions = accounttController
				.getAllTransactionsByAccountNumber(Long.valueOf(accountNumber));
		// Assert THAT the outcome is as expected
		assertThat(transactions.size(), is(equalTo(1)));
	}

}
