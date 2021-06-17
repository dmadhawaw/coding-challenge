package com.anz.codingchallenge.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.anz.codingchallenge.data.repository.AccountRepository;
import com.anz.codingchallenge.data.repository.TransactionRepository;
import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Customer;
import com.anz.codingchallenge.domain.Transaction;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@SpringBootTest
public class AccountServiceJunitTest {

	@Mock
	private AccountRepository accountRepository;

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private AccountService accountService;

	@BeforeEach
	void setMockOutput() {
		// Create a account
	}

	@Test
	public void testGetAccountById() {
		Account account = new Account();
		account.setAccountName("TestAccountName");
		String accountNumberStr = "999888777";
		long accountNumber = Long.valueOf(accountNumberStr);

		account.setAccountNumber(accountNumber);
		Customer customer = new Customer();
		customer.setCustomerId(1000);
		customer.setFirstName("TestCustomerFirstName");
		customer.setLastName("TestCustomerLastName");
		customer.setEmail("test.customer@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("100000.00"));
		Optional<Account> optAccount = Optional.ofNullable(account);

		when(accountRepository.findById(accountNumber)).thenReturn(optAccount);
		Account accountResponse = accountService.getAccountById(Long.valueOf(accountNumberStr));
		assertEquals("TestAccountName", accountResponse.getAccountName());
	}

	@Test
	public void testFindByCustomerCustomerId() {

		Account account = new Account();
		account.setAccountName("TestAccountName");
		String accountNumberStr = "999888777";
		long accountNumber = Long.valueOf(accountNumberStr);

		account.setAccountNumber(accountNumber);
		Customer customer = new Customer();
		customer.setCustomerId(1000);
		customer.setFirstName("TestCustomerFirstName");
		customer.setLastName("TestCustomerLastName");
		customer.setEmail("test.customer@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("100000.00"));

		List<Account> accounts = new ArrayList<>();
		accounts.add(account);
		when(accountRepository.findByCustomerCustomerId(1000)).thenReturn(accounts);
		List<Account> accountsResponse = accountService.findByCustomerCustomerId(1000);
		assertEquals(1, accountsResponse.size());
	}

	@Test
	public void testGetAllTransactionsByAccountNumber() {
		String accountNumberStr = "585309209";
		long accountNumber = Long.valueOf(accountNumberStr);
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction = new Transaction();
		Account account = new Account();
		account.setAccountName("AUCurrent433");

		account.setAccountNumber(accountNumber);
		Customer customer = new Customer();
		customer.setCustomerId(101);
		customer.setFirstName("Krishna");
		customer.setLastName("Kumar");
		customer.setEmail("krishna.kumar@myemail.com.au");

		account.setCustomer(customer);
		account.setAccountType("Current");
		account.setOpeningBalance(new BigDecimal("38010.62"));

		transaction.setAccount(account);
		transaction.setType("CR");
		transactions.add(transaction);
		when(transactionRepository.findByAccountAccountNumber(accountNumber)).thenReturn(transactions);
		List<Transaction> transResponse = accountService.findByAccountAccountNumber(accountNumber);// Verify the get
																									// account
																									// by Id.
		assertEquals(1, transResponse.size());
	}

}
