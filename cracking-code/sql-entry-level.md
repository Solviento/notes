SQL



SQL Intro

All SQL languages must support SELECT, UPDATE, DELETE, INSERT, WHERE

To use SQL in a web site

Must have a RDBMS database program like MySQL

Use server side scripting language like PHP

Use SQL to get data you want



What is DBMS?

Database management system



What is RDBMS?

Relational database management system

Stores data into tables(columns and rows)

SQL Server is an example of RDBMS



What does SQL stand for?

Structured Query Language



Who determines the standards for SQL?

ANSI (American national Standards Institute)



What are the versions of SQL?

86th to 2016



Basic Structure and Commands:

A Database has tables (tables have multiple fields)

Field 1 - Integer

Field 2 - String

Field 3 - Float

Table: Employees

Employee_ID (Key field)

Name (Field)

Salary (Field)

Active (Field)

001

Fernando

1000

YES

002

Charly

1500

YES

003

Mike

1800

NO



Select

It gets data stored in a table of a database

SELECT * FROM Employees WHERE Salary = '1000;

Result:

Employee_ID 001

Insert  

INSERT INTO Employees (Name, Salary, Active VALUES ('Mark', '500', 'YES')

Result:

Employee_ID 004

Update

Update data already stored in database

UPDATE Employees  

SET Active='NO'  

WHERE Name='Mark'

Result:

Updated Employee_id 004

Delete

Remove data from database

DELETE FROM Employees WHERE Active='NO'

Result:

Removed 003 and 004



Important SQL commands

SELECT, extract data from a database

UPDATE, updates data in a database

DELETE, deletes data from a database

INSERT INTO, inserts new data into a database

CREATE DATABASE, creates a new database

ALTER DATABASE, modifies a database

CREATE TABLE, creates a new table

ALTER TABLE, modifies a table

DROP TABLE, deletes a table

CREATE INDEX, creates an index

DROP INDEX, deletes an index



SELECT syntax

SELECT column1, column2, … FROM table_name



SELECT DISTINCT

Used to return only distinct values since a column may contain many duplicates

SELECT DISTINCT column1 FROM table_name

To count the number of distinct values

SELECT COUNT(DISTINCT column1) FROM table_name



WHERE

The WHERE clause is used to filter records

SELECT * FROM Customers WHERE Country='Canada'

Some operators include

=, <>, <, >, >=, <=

BETWEEN

LIKE



AND/OR/NO

AND/OR operators are used to filter records based on more than one condition

SELECT column1 FROM table_name WHERE condition1 AND condition2

Ex: SELECT * FROM Customers WHERE City='Berlin' OR City='Munchen'



ORDER BY

ORDER BY keyword is used to sort the result-set in ascending or descending order

SELECT column1 FROM table_name ORDER BY column1 ASC (or DESC)

SELECT * FROM Customers ORDER BY Country, CustomerName

SELECT * FROM Customers ORDER BY Country ASC, CustomerName DESC



INSERT

Insert new records in a table

INSERT INTO table_name (column1, column2, etc) VALUES (value1, value2, etc)

INSERT INTO Customers (CustomerName, ContactName) VALUES ('Cardinal', 'Tom B')

If some information is missing, then null values will be placed accordingly



UPDATE

Used to modify existing records in a table

Jobs  



From <https://stackoverflow.com/jobs?med=site-ui&ref=jobs-tab>  



Update table_name SET column1 = value1 WHERE condition

UPDATE Customers SET ContactName = 'Alfred', City='Frankfurt' WHERE CustomerID=1

If WHERE is omitted, then all records will be updated!



DELETE

Delete existing records in a table

DELETE FROM table_name WHERE condition

DELETE FROM Customers WHERE CustomerName='Alfred'

DELETE * FROM table_name



SELECT INTO

SELECT INTO statement copies data from one table into a new table

SELECT * INTO newtable [IN externaldb] FROM oldtable WHERE condition

SELECT * INTO CustomersBackup FROM Customers

SELECT * INTO CustomersBackup IN 'Backup.mdb' FROM CUSTOMERS

SELECT * INTO CustomersGermany FROM Customers WHERE Country = 'Germany'

SELECT INTO can also be used to create a new, empty table using the schema of another

SELECT * INTO newtable FROM oldtable WHERE 1 = 0



CREATE DATABASE

Create a new SQL database

CREATE DATABASE dbname

CREATE DATABASE testdb



CREATE TABLE

Used to create a new table in a database

CREATE TABLE table_name( column1 datatype, column2 datatype, etc.)

CREATE TABLE Persons( PersonID int, LastName varchar(255))



CONSTRAINTS

Used to specify rules for data in a table

CREATE TABLE tablename( column1 datatype CONSTRAINT)

Includes

NOT NULL

UNIQUE

PRIMARY KEY

FOREIGN KEY

CHECK

INDEX



NOT NULL

By default, a column can hold NULL values

The NOT NULL constraint enforces a column to not accept NULL values

CREATE TABLE Person( ID int NOT NULL)



Unique

A constraint that ensures all values in a column are different

CREATE TABLE Persons( ID int NOT NULL UNIQUE)



Primary Key

A constraint that uniquely identifies each record in a database

Cannot contain null values

A table can only have one primary key which may consist of multiple fields

CREATE TABLE Persons( ID int NOT NULL PRIMARY KEY)



Foreign Key

A foreign key is used to link two tables together

A foreign key is a field in one table that refers to the primary key in another table

CREATE TABLE Orders(OrderID int NOT NULL PRIMARY KEY, PersonID int FOREIGN KEY REFERENCES Persons(PersonID)



JOINS

A JOIN clause is used to combine rows from two or more tables, based on a related column between them

Inner join

Returns records that have matching values in both table

SQL INNER JOIN
SELECT column_name(s) FROM table1

INNER JOIN table2 ON table1.column_name = table2.column_name

Left join

Return all records from the left table, and the matched records from the right table

SQL LEFT JOIN
Hm

SELECT column_name(s) FROM table1

LEFT JOIN table2 ON table1.column_name = table2.column_name

Right join

Return all records from the right table, and the matched records from the left table

SQL RIGHT JOIN
SELECT column_name(s) FROM table1

RIGHT JOIN table2 ON table1.column_name = table2.column_name

Full join

Return all records when there is a match in either left or right table

SQL FULL OUTER JOIN
SELECT column_name(s) FROM table1

FULL OUTER JOIN table2 ON table1.column_name = table2.column_name



Users vs Schemas?

USERS = SCHEMAS

A schema is a collection of database objects owned by a specific user



There are 4 main categories of SQL statements

DDL(Data definition language)

CREATE, ALTER, DROP



What is the difference between truncate and drop?

Truncate will keep the table structure and delete the rows and you can’t rollback

Empties the table

Drop will drop the entire table



What are set operators?

 They combine two component queries into one result

 Queries containing set operators are called Compound queries

Examples of set operators

 Union - All distinct rows selected by either query

Union all - All rows selected by either query, including all duplicates

Intersect - All distinct rows selected by both queries

Minus - All distinct rows selected by the first query but not the second



What is the UNION Operator?

It is used to combine the results of two SELECT statements

 They must have the same number of columns and data types AND be in the same order



What is the difference between ORDER BY and GROUP BY?

 Order by is only for sorting purposes

 Group by used with aggregate functions (COUNT, MAX, MIN, SUM, AVG) to group the result-set by one or more columns



What is the difference between the WHERE and HAVING clause?   

 Where is used to filter records from a result

 HAVING is used to filer values from a group

Used with group by



What is the purpose of JOIN in SQL?   

Combines one or more tables in a relational database



How do you select all rows and columns of a table in SQL?   

- SELECT * FROM…



When using NULL..

___ IS Null (Correct)

 ___ = NULL (incorrect)

because null is not equal to null or null is not not equal to null



What is a subquery?

Query with in a larger query



What are examples of oracle datatypes?

Char

Number

Float

Integer

Varchar2



What is normalization?

It is a process to remove redundancy across tables

Instead of having multiple of the same fields in different tables you can consolidate them into and ID so you are able to refer to one ID .



Difference between INNER JOIN and OUTER JOIN?





What is a foreign/primary key?   

A primary key uniquely identifies each row in a database

Can’t contain null values!!!

A foreign key is used to link data between two tables together



Give a simple CRUD query

Create



Read

Update

Delete 
