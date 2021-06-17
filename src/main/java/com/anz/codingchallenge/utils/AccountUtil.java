package com.anz.codingchallenge.utils;

public final class AccountUtil {

	/**
	 * The varifyAccuntNumber method validates the account number request parameter.
	 *
	 * @param accountNumber account number
	 * @return boolean true if account number is valid.
	 */
	public static boolean varifyAccuntNumber(String accountNumber) {

		if (!accountNumber.isEmpty()) {
			if (accountNumber.matches("[0-9]+") && accountNumber.length() == 9) {
				return true;
			}
		}
		return false;
	}

}
