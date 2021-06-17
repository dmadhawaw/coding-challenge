package com.anz.codingchallenge.service;

//import static org.junit.Assert.assertEquals;

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
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class AccountServiceIntegrationTest {

	@Autowired
	private AccountService accountService;

	@Test
	public void testGetAccountById() {
		String accountNumberStr = "321143048";
		long accountNumber = Long.valueOf(accountNumberStr);

		Account accountResponse = accountService.getAccountById(accountNumber);
		// Verify the get account by account number.
		assertEquals("AUCurrent433", accountResponse.getAccountName());
	}

	@Test
	public void testFindByCustomerCustomerId() {
		int customerId = 101;
		List<Account> accounts = accountService.findByCustomerCustomerId(customerId);
		// Verify the get accounts by customer Id.
		assertEquals(2, accounts.size());

	}

	@Test
	public void testGetAllTransactionsByAccountNumber() {
		String accountNumberStr = "321143048";
		long accountNumber = Long.valueOf(accountNumberStr);

		List<Transaction> transResponse = accountService.findByAccountAccountNumber(accountNumber);
		// Verify the get transactions by account number.
		assertEquals(5, transResponse.size());
	}

}
