CREATE SCHEMA IF NOT EXISTS BANKDB AUTHORIZATION SA;

CREATE TABLE IF NOT EXISTS BANKDB.ACCOUNTS
(
	NAME VARCHAR2(255),
	ADDRESS VARCHAR2(2000),
	ACCOUNT_NUMBER INT,
	CREATED_TIME TIMESTAMP,
	BALANCE INT
);

CREATE TABLE IF NOT EXISTS BANKDB.BENEFICIARIES
(
	ID INT,
	ACCOUNT_NUMBER INT,
	BENEFICIARY_ACC_NUMBER INT,
	CREATED_TIME TIMESTAMP
);

CREATE TABLE IF NOT EXISTS BANKDB.TRANSACTIONS
(
	ID INT,
	FROM_ACCOUNT INT,
	TO_ACCOUNT INT,
	AMOUNT INT,
	IS_COMPLETE INT,
	CREATED_TM TIMESTAMP,
	COMPLETED_TM TIMESTAMP
);

CREATE SEQUENCE IF NOT EXISTS BANKDB.CUSTOMERS_SEQ INCREMENT BY 1 MINVALUE 100 MAXVALUE 9999999999999 CACHE 20;
CREATE SEQUENCE IF NOT EXISTS BANKDB.ACCOUNTS_SEQ  INCREMENT BY 1 MINVALUE 100 MAXVALUE 9999999999999 NOCYCLE CACHE 20;
CREATE SEQUENCE IF NOT EXISTS BANKDB.BENEFICIARIES_SEQ INCREMENT BY 1 MINVALUE 100 MAXVALUE 9999999999999 NOCYCLE CACHE 20;
CREATE SEQUENCE IF NOT EXISTS BANKDB.TRANSACTIONS_SEQ  INCREMENT BY 1 MINVALUE 100 MAXVALUE 9999999999999 NOCYCLE CACHE 20;

INSERT INTO BANKDB.ACCOUNTS(NAME,ADDRESS,ACCOUNT_NUMBER,CREATED_TIME,BALANCE) VALUES ('John', 'SF', 135, CURRENT_TIMESTAMP(), 800);
