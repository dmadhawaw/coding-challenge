CREATE TABLE CUSTOMER(
	CUSTOMER_ID BIGINT PRIMARY KEY,
	FIRST_NAME VARCHAR(100),
	LAST_NAME VARCHAR(100),
	EMAIL VARCHAR(250)
);

CREATE TABLE ACCOUNT(
	ACCOUNT_NUMBER BIGINT PRIMARY KEY,
	CUSTOMER_ID BIGINT NOT NULL,
	ACCOUNT_NAME VARCHAR(100),
	ACCOUNT_TYPE VARCHAR(100),
	BALANCE_DATE DATE,
	CURRENCY VARCHAR(10),
	OPENING_BALANCE DECIMAL(13, 2) DEFAULT NULL,
	CONSTRAINT FK_CustomerAccount FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(CUSTOMER_ID)
);

CREATE TABLE TRANSACTION(
	TRANSACTION_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
	ACCOUNT_NUMBER BIGINT NOT NULL,
	VALUE_DATE DATE,
	DEBIT_AMOUNT DECIMAL(13, 2) DEFAULT NULL,
	CREDIT_AMOUNT DECIMAL(13, 2) DEFAULT NULL,
	TRANSACTION_TYPE VARCHAR(10),
	TRANSACTION_NARRATIVE VARCHAR(250),
	CONSTRAINT FK_AccountTransaction FOREIGN KEY (ACCOUNT_NUMBER) REFERENCES ACCOUNT(ACCOUNT_NUMBER)
);