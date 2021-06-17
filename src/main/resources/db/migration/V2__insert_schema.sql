INSERT INTO CUSTOMER (customer_id, first_name, last_name, email) VALUES
  ('100', 'Daniel', 'Mondate', 'daniel.mondate@myemial.com.au'),
  ('101', 'Krishna', 'Kumar', 'krishna.kumar@myemail.com.au'),
  ('102', 'Mary', 'Abbot', 'mary.abbot@myemail.com.au');
  
INSERT INTO ACCOUNT (account_number, customer_id, account_name, account_type, balance_date, currency, opening_balance) VALUES
  ('585309209', '101', 'SGSavings726', 'Savings', TO_DATE('17-Jan-2018'), 'SGD', '84327.51'),
  ('791066619', '102', 'AUSavings933', 'Savings', TO_DATE('17-Jan-2018'), 'AUD', '88005.93'),
  ('321143048', '101', 'AUCurrent433', 'Current', TO_DATE('17-Jan-2018'), 'AUD', '38010.62');
  	
INSERT INTO TRANSACTION (account_number, value_date, debit_amount, credit_amount, transaction_type, transaction_narrative) VALUES
  ('585309209', TO_DATE('17-Jan-2018'), '56788.00', '1000.00', 'CR', 'transactionNarrative1'),
  ('321143048', TO_DATE('17-Jan-2018'), '33456.99', '1000.00', 'DB', 'transactionNarrative2'),
  ('321143048', TO_DATE('17-Jan-2018'), '88456.75', '7000.00', 'CR', 'transactionNarrative3'),
  ('321143048', TO_DATE('17-Jan-2018'), '88456.75', '7000.00', 'CR', 'transactionNarrative3'),
  ('321143048', TO_DATE('17-Jan-2018'), '88456.75', '7000.00', 'CR', 'transactionNarrative3'),
  ('321143048', TO_DATE('17-Jan-2018'), '88456.75', '7000.00', 'CR', 'transactionNarrative3');