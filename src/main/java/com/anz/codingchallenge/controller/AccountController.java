package com.anz.codingchallenge.controller;

import java.util.List;

import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.anz.codingchallenge.domain.Account;
import com.anz.codingchallenge.domain.Transaction;
import com.anz.codingchallenge.service.AccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Account Controller
 *
 * Created by Dinesh Wijekoon
 */
@Api(value = "AccountController")
@RestController
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	/**
	 * Lookup a Account by account number .
	 *
	 * @param id Account identifier
	 * @return Requested Account for given account identifier
	 */
	@GetMapping(value = "/accounts/{accountNumber}")
	@ApiOperation(value = "Return an account for given account number.", notes = "Returns HTTP 404 if the account is not found")
	public Account getAccount(
			@PathVariable(value = "accountNumber") @Pattern(regexp = "\\d{9}", message = "Invalid accountNumber") long accountNumber) {
		Account account = accountService.getAccountById(accountNumber);
		return account;
	}

	/**
	 * Lookup a page of Accounts for a user.
	 *
	 * @param customerId User Identifier
	 * @param pageable   paging details
	 * @return Requested page of Account
	 */
	@GetMapping(value = "/customers/{customerId}/accounts")
	@ApiOperation(value = "Return all accounts for given  customer Id", notes = "Returns HTTP 404 if accounts are not found")
	public List<Account> getAllAccountsByCustomerId(@PathVariable(value = "customerId") Integer customerId) {
		return accountService.findByCustomerCustomerId(customerId);
	}

	/**
	 * Lookup a page of Transactions for a user account.
	 *
	 * @param id       Account Identifier
	 * @param pageable paging details
	 * @return Requested page of Transactions
	 */
	@GetMapping(value = "/accounts/{accountNumber}/transactions")
	@ApiOperation(value = "Return all transactions for account number", notes = "Returns HTTP 404 if transactions are not found")
	public List<Transaction> getAllTransactionsByAccountNumber(
			@PathVariable(value = "accountNumber") @Pattern(regexp = "^[0-9]{9}$", message = "Invalid accountNumber") long accountNumber) {
//		if (!AccountUtil.varifyAccuntNumber(String.valueOf(accountNumber)))
//			throw new CustomException("Please provide valid account number.", ErrorCodes.BAD_REQUEST);
		return accountService.findByAccountAccountNumber(accountNumber);
	}

}
