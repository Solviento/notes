# SQL Basic Concepts

What is a database? (DB)
- Any collection of related information
- Phone book
- Shopping list
- Todo list
- Databases can be stored in different ways
- On paper
- In your mind
- On computer/powerpoint

Database Management Systems (DBMS)
- A special software program that helps users create and maintain a database
- Makes it easy to manage large amounts of information
- Handles security
- Backups
- Import/export data
- Concurrency

CRUD
- Create, Read, Update, Delete

Two types of databases
- Relational databases (SQL)
  - Organize data into one or more tables
  - Each table has columns and rows
  - A unique key identifies each row
  - Basically an excel sheet
- Non relational (noSQL)
  - Organize data is anything but a traditional table
  - Key value stores
  - Documents (JSON, XML, Etc)
  - Graphs
  - Flexible tables

Relational Database Management Systems (RDBMS)
- mySQL, Oracle, postgreSQL, mariaDB, etc.

Structured Query Language (SQL)
- Standardized language for interacting with RDBMS
- SQL code not always portable to another RDBMS without modification

Non relational databases (noSQL)
- Key value hash
- mongoDB, dynamoDB, cassandra, firebase
- Implementation specific
- Not as standardized as SQL
- No set language standard, each NRDBMS will implement their own language for performing CRUD and administrative operations on the databases

Database Queries
- Queries are requests made to the database management system for specific information
- As database become more complex, it becomes difficult to get specific pieces of information

Basic SQL Keywords
- SELECT
- FROM
- WHERE
- GROUP BY
- ASC
- DESC
- AND
- OR
- ORDER BY
- INSERT INTO
- UPDATE
- DELETE FROM

Stored Procedure
- A stored procedure is a prepared SQL code that can be saved so the code can be resused  

Data Types used in SQL
- Binary
- Bit (True/false)
- Char
- Datetime
- Decimal
- Image
- Int
- Money
- Numeric
- Real
- Text
- Timestamp
- Varbinary
- varchar

Triggers
- Triggers are basically stored procedures that get fired whenever
  - BEFORE/AFTER INSERT
  - BEFORE/AFTER UPDATE
  - BEFORE/AFTER DELETE
- Triggers are automatic (also considered to be under Transact SQL)
- Note that malicious code can run under triggers with escalated privileges
