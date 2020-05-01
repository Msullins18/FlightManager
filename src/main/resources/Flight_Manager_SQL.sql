DROP TABLE ADMIN CASCADE CONSTRAINTS;
DROP TABLE TRAVELLER CASCADE CONSTRAINTS;
DROP TABLE FLIGHT cascade constraints;
DROP TABLE AIRPORT cascade constraints;
DROP SEQUENCE flight_seq;
DROP SEQUENCE airport_seq;
CREATE SEQUENCE flight_seq START WITH 1000 INCREMENT BY 1;
CREATE SEQUENCE airport_seq START WITH 3000 INCREMENT BY 1;

CREATE TABLE ADMIN(
	EMAIL_ID VARCHAR(50),
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(70) NOT NULL,
	PHONE_NUMBER VARCHAR(10) NOT NULL,
	constraint ADMIN_EMAIL_ID_PK primary key ( EMAIL_ID )
);

CREATE TABLE TRAVELLER(
	EMAIL_ID VARCHAR(50),
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(70) NOT NULL,
	PHONE_NUMBER VARCHAR(10) NOT NULL,
	constraint TRAVELLER_EMAIL_ID_PK primary key ( EMAIL_ID )
);

SELECT * FROM TRAVELLER;
CREATE TABLE FLIGHT(
 	FLIGHT_ID NUMBER(10),
    FLIGHT_TYPE VARCHAR2 (50) NOT NULL,
    FLIGHT_SIZE NUMBER(10) NOT NULL,
    SEATS_AVAILABLE NUMBER(10) NOT NULL,
    DATE_OF_DEPARTURE DATE NOT NULL,
    DATE_OF_ARRIVAL DATE NOT NULL,
    DESTINATION VARCHAR2(50) NOT NULL,
	FLIGHT_FARE NUMBER(10) NOT NULL,
	FLIGHT_TAX NUMBER(10) NOT NULL,
    AIRPORT_ID NUMBER(10),
    constraint FLIGHT_FLIGHT_ID_PK primary key ( FLIGHT_ID )
 );

	
	
CREATE TABLE AIRPORT(
    AIRPORT_ID NUMBER(10),
    NAME VARCHAR(50) NOT NULL,
    CITY VARCHAR(50) NOT NULL,
    constraint AIRPORT_AIRPORT_ID_PK primary key ( AIRPORT_ID )
);
	
	
ALTER TABLE FLIGHT ADD CONSTRAINT AIRPORT_AIR_ID_FK FOREIGN KEY ( AIRPORT_ID ) REFERENCES AIRPORT( AIRPORT_ID );


INSERT INTO AIRPORT(AIRPORT_ID, NAME, CITY) VALUES(1000,'DFW','Dallas');
INSERT INTO AIRPORT(AIRPORT_ID, NAME, CITY) VALUES(1001,'SFO','San Francisco');

INSERT INTO FLIGHT(FLIGHT_ID, FLIGHT_TYPE, FLIGHT_SIZE, SEATS_AVAILABLE, DATE_OF_DEPARTURE, DATE_OF_ARRIVAL, DESTINATION, FLIGHT_FARE, FLIGHT_TAX, AIRPORT_ID) VALUES(2000, 'DOMESTIC', 999, 100, SYSDATE+1, SYSDATE+1,'New York', 250, 14, 1000);
INSERT INTO FLIGHT(FLIGHT_ID, FLIGHT_TYPE, FLIGHT_SIZE, SEATS_AVAILABLE, DATE_OF_DEPARTURE, DATE_OF_ARRIVAL, DESTINATION, FLIGHT_FARE, FLIGHT_TAX, AIRPORT_ID) VALUES(2001, 'DOMESTIC', 999, 100, SYSDATE+1, SYSDATE+1,'New York', 250, 14, 2000);
INSERT INTO FLIGHT(FLIGHT_ID, FLIGHT_TYPE, FLIGHT_SIZE, SEATS_AVAILABLE, DATE_OF_DEPARTURE, DATE_OF_ARRIVAL, DESTINATION, FLIGHT_FARE, FLIGHT_TAX, AIRPORT_ID) VALUES(2002, 'DOMESTIC', 999, 100, SYSDATE+1, SYSDATE+1,'New York', 250, 14, 3000);
select * from ADMIN
SELECT * from AIRPORT
SELECT * FROM FLIGHT
