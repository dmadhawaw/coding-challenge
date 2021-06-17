package com.anz.codingchallenge.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Customer;
import com.anz.codingchallenge.domain.Transaction;
import com.anz.codingchallenge.service.AccountService;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

	@Mock
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
	private AccountController accountController;

	@BeforeEach
	void setMockOutput() {
		mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();

	}

	/*
	 * This Test method test 'getAccountById' JSON content contains the specific
	 * account details.
	 */
	@Test
	void whenGetAccountByAccountNumber_returnJsonContent() throws Exception {
		Account account = new Account();
		account.setAccountName("TestAccountName");

		account.setAccountNumber(Long.valueOf("999888777"));
		Customer customer = new Customer();
		customer.setCustomerId(1000);
		customer.setFirstName("TestCustomerFirstName");
		customer.setLastName("TestCustomerLastName");
		customer.setEmail("test.customer@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("100000.00"));

		when(accountService.getAccountById(Long.valueOf("999888777"))).thenReturn(account);

		this.mockMvc.perform(get("/accounts/999888777")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestAccountName"))).andDo(print());
	}

	/*
	 * This Test method test 'findByCustomerCustomerId' JSON content contains the specific
	 * account details.
	 */
	@Test
	void whenGetAccountsByCustomerNumber_returnJsonContent() throws Exception {
		List<Account> accounts = new ArrayList<>();
		Account account = new Account();
		account.setAccountName("TestCurrentAccount");

		account.setAccountNumber(Long.valueOf("999888777"));
		Customer customer = new Customer();
		customer.setCustomerId(1000);
		customer.setFirstName("TestCustomerFirstName");
		customer.setLastName("TestCustomerLastName");
		customer.setEmail("test.customer@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("100000.00"));

		Account accountTwo = new Account();
		accountTwo.setAccountName("TestSavingsAccount");

		accountTwo.setAccountNumber(Long.valueOf("555444333"));
		Customer customerTwo = new Customer();
		customerTwo.setCustomerId(1000);
		customerTwo.setFirstName("TestCustomerFirstName2");
		customerTwo.setLastName("TestCustomerLastName2");
		customerTwo.setEmail("test.customer2@myemail.com.au");

		accountTwo.setCustomer(customerTwo);
		accountTwo.setAccountType("Savings");
		accountTwo.setOpeningBalance(new BigDecimal("900000.00"));

		accounts.add(account);
		accounts.add(accountTwo);

		when(accountService.findByCustomerCustomerId(1000)).thenReturn(accounts);

		this.mockMvc.perform(get("/customers/1000/accounts")).andExpect(status().isOk())
				.andExpect(content().string(containsString("TestSavingsAccount"))).andDo(print());
	}

	/*
	 * This Test method test 'findByAccountAccountNumber' JSON content contains the specific
	 * account details.
	 */
	@Test
	void whenGetTransactionsByCustomerNumber_returnJsonContent() throws Exception {
		List<Transaction> transactions = new ArrayList<>();
		Transaction transactionOne = new Transaction();
		transactionOne.setTransactionId(Long.valueOf("100177"));
		transactionOne.setType("DB");

		Account account = new Account();
		account.setAccountName("TestCurrentAccount");

		account.setAccountNumber(Long.valueOf("999888777"));
		Customer customer = new Customer();
		customer.setCustomerId(1000);
		customer.setFirstName("TestCustomerFirstName");
		customer.setLastName("TestCustomerLastName");
		customer.setEmail("transactionstest.customer@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("100000.00"));
		transactionOne.setAccount(account);

		Transaction transactionTwo = new Transaction();
		transactionTwo.setTransactionId(Long.valueOf("100188"));
		transactionTwo.setType("CR");
		transactionTwo.setAccount(account);

		transactions.add(transactionOne);
		transactions.add(transactionTwo);

		when(accountService.findByAccountAccountNumber(Long.valueOf("999888777"))).thenReturn(transactions);

		this.mockMvc.perform(get("/accounts/999888777/transactions")).andExpect(status().isOk())
				.andExpect(content().string(containsString("100188"))).andDo(print());
	}

}
