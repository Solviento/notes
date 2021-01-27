







#  Complete SQL Overview

**SQL Basic Concepts**

## **What is a database? (DB)**

- Any collection of related information
- Phone book
- Shopping list
- Todo list
- Databases can be stored in different ways
- On paper
- In your mind
- On computer/powerpoint

## **Database Management Systems (DBMS)**

- A special software program that helps users create and maintain a database
- Makes it easy to manage large amounts of information
- Handles security
- Backups
- Import/export data
- Concurrency

## CRUD

- Create, Read, Update, Delete

**Two types of databases**

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

**Relational Database Management Systems (RDBMS)**

- mySQL, Oracle, postgreSQL, mariaDB, etc.

**Structured Query Language (SQL)**

- Standardized language for interacting with RDBMS
- SQL code not always portable to another RDBMS without modification

**Non relational databases (noSQL)**

- Key value hash
- mongoDB, dynamoDB, cassandra, firebase
- Implementation specific
- Not as standardized as SQL
- No set language standard, each NRDBMS will implement their own language for performing CRUD and administrative operations on the databases

**Database Queries**

- Queries are requests made to the database management system for specific information
- As database become more complex, it becomes difficult to get specific pieces of information

**Basic SQL Keywords**

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

**Stored Procedure**

- A stored procedure is a prepared SQL code that can be saved so the code can be resused  

**Data Types used in SQL**

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

**Triggers**

- Triggers are basically stored procedures that get fired whenever
  - BEFORE/AFTER INSERT
  - BEFORE/AFTER UPDATE
  - BEFORE/AFTER DELETE
- Triggers are automatic (also considered to be under Transact SQL)
- Note that malicious code can run under triggers with escalated privileges

## SQL Code Snippets

Sample SQL expressions

- "SELECT, ORDER BY"

``` SQL
SELECT ID, firstname, lastname, SSN, payrate
FROM Employees
ORDER BY lastname, firstname, SSN
```

- Will extract basic employee information and order using last name and firstname, SSN (default ordering using ascending)

- "WHERE, LIKE"

``` SQL
SELECT ID, firstname, lastname, SSN, payrate
FROM Employees
WHERE lastname LIKE '%germany%'
ORDER BY SSN DESC
```

- Will extract employee information and order using SSN descending

- "AND"

``` SQL
SELECT ID, firstname, lastname, SSN
FROM Employees
WHERE firstname LIKE '%charles%' AND lastname LIKE '%germany%'
ORDER BY SSN DESC
```

- Will extract employee information using two search parameters and the and operator

- "OR"

``` SQL
SELECT ID, firstname, lastname, Rupees
FROM HyRule
WHERE firstname LIKE '%link%' OR firstname LIKE '%zelda%'
ORDER BY Rupees DESC
```

- Will extract employee information using two search parameters and the or operator

- Multiple tables

``` SQL
SELECT firstname, lastname, killratio
FROM Players, CPUS
WHERE Players.lastname = CPUS.lastname
ORDER BY killratio ASC
```

- Remember to separate the two tables using a comma

- Inserting values into fields

``` SQL
INSERT INTO Players(firstname, lastname)
VALUES(Charles, Germany)
```

- Inserting values into fields that already exist (firstname, lastname)

- Updating information in fields

``` SQL
UPDATE Employees
SET SSN = 24698749
WHERE lastname = 'Germany' AND firstname = 'Charles'
```

- Creating a table

``` SQL
CREATE TABLE
Ponies(ID Integer, PonyName Text(32))
```

- Deleting a table

``` SQL
DROP TABLE Ponies
```

- Inserting information into fields

``` SQL
INSERT INTO Ponies
VALUES(1, 'Starlite')
```

- Similar to other insertion, without the need to write the field names

# CRUD Samples

Creating Schema, Database, Tables, and Views

``` SQL
-- set up schemas, then switch to primary
CREATE SCHEMA IF NOT EXISTS 'appdb';
CREATE SCHEMA IF NOT EXISTS 'testdb';

USE 'appdb';

-- create 'users' table inside the appdb schema
CREATE TABLE IF NOT EXISTS 'appdb'.'users'(
  `usersID` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(45) NOT NULL,
  `lname` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`usersID`)
);

-- switch to testdb and create the 'table1' table (should rename)
USE 'testdb';

CREATE TABLE IF NOT EXISTS `testdb`.`table1` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `release_date` DATE NOT NULL,
  `title` VARCHAR(45) NULL,
  PRIMARY KEY (`movie_id`)
);

-- create view for testdb and then drop it
USE 'testdb';
CREATE TABLE IF NOT EXISTS 'testdb'.'custom_view'('id' INT);

DROP TABLE IF EXISTS 'testdb'.'custom_view';

```

Renaming a table

``` SQL
ALTER TABLE 'testdb'.'table1'
RENAME TO 'testdb'.'test_table';
```

Adding a column to an existing table

``` SQL
ALTER TABLE 'appdb'.'users'
ADD COLUMN 'dacreated' DATE NOT NULL AFTER 'phone';
```

Changing a column attribute

``` SQL
ALTER TABLE 'appdb'.'users'
CHANGE COLUMN 'dacreated' 'dacreated' DATE NULL;
```

Renaming a column is also similar

``` SQL
ALTER TABLE `appdb`.`users`
CHANGE COLUMN `usersID` `userID` INT(11) NOT NULL AUTO_INCREMENT ;
```

Simple CRUD Query

``` SQL
SELECT * FROM users;

INSERT INTO users (fname, lname, username, password, email, phone)
VALUES("Jason", "Perez", "jperez", "54321", "perej8169@gmail.com", "6463715410");

UPDATE users
SET dacreated = '2018-11-07'
WHERE userID = 1;

DELETE FROM users
WHERE userID = x;
```

## Interview Questions and Answers

Return employee record with highest salary

``` SQL
select *
from employee
where salary = (select Max(salary) from employee)
```

- Note that we are using a sub query (a query within a query)
- The sub query will extract the max salary number from the employee table which the main query will use to extract the employee with the max salary

Return the highest salary in employee table

``` SQL
select Max(salary)
from employee
```

Return the 2nd highest salary from employee table

``` SQL
select Max(salary)
from employee
where salary Not In (select Max(salary) from employee)
```

- first line, selects the max salary
- where clause will filter to find the employee that is not the maximum salary earning employee
- in essence, if it is not equal to the maximum salary, it will equal to the second maximum salary

Select range of employees based on id

``` SQL
select *
from employee
where employee_id between 2003 and 2008
```

- When working with range, you will need to use 'between'

Return an employee with the highest salary and the employee's department name

``` SQL
select e.firstname, e.lastname, e.salary, d.departmentname
from employee e inner join department d on (e.departmentid = d.departmentid)
where salary in (select Max(salary) from employee)
```

- When dealing with foreign keys, use an inner join and then a sub query to extract an extra layer of search condition

Return highest salary, employee name, department name for each department

``` SQL
select e.firstname, e.lastname, e.salary, d.departmentname
from Employee e inner join department d on (e.departmentid = d.departmentid)
where salary in (select Max(salary) from employee group by departmentid)
```

*Sample tables*

courses

| course_id | course_name | teacher_id |
| --------- | ----------- | ---------- |
| 1         | math_101    | 1          |
| 2         | math_202    | 1          |
| 3         | sci_101     | 2          |
| 4         | sci_201     | 2          |
| 5         | sci_301     | 3          |
| 6         | eng_101     | 4          |

student_courses

| course_id | student_id |
| --------- | ---------- |
| 1         | 1          |
| 1         | 2          |
| 1         | 3          |
| 2         | 3          |
| 2         | 4          |
| 3         | 1          |
| 3         | 2          |
| 4         | 3          |

students

| student_id | student_name |
| ---------- | ------------ |
| 1          | Amy          |
| 2          | John         |
| 3          | Marco        |
| 4          | Amber        |
| 5          | George       |

teachers

| teacher_id | teacher_name |
| ---------- | ------------ |
| 1          | Smith        |
| 2          | Mary Sue     |
| 3          | Bob Oden     |
| 4          | Derbin       |

Create query to get list of all students and how many courses each student is enrolled in

- Remember that this must include students who take no courses as well

``` sql
select student_name, students.student_id, count(student_courses.course_id) as '#_Courses'
from students left join student_courses
on students.student_id = student_courses.student_id
group by students.student_id;
```

- Further explained:
  - left join is used to include students who take 0 courses, using inner join would only count students who are taking 1+ courses
  - count() and group by are often used in conjunction, without the other this query would fail
  - Once count() is performed, group by will clean up the results and show only students and their courses count
  - Remember that keyword 'on' is used for joins, not 'where'

Implement a query to get a list of all teachers and how many students they each teach

- If a teacher teaches the same student in two courses, you should double count the student
- Sort the list in descending order of the number of students a teacher teaches
- First find list of teacher_id's and how many students are associated with each teacher_id (subquery to larger query)

``` sql
select teacher_id, count(student_courses.student_id) as 'num_students'
from courses inner join student_courses
on courses.course_id = student_courses.course_id
group by courses.teacher_id
```

- Once tested, you'll notice that teachers who are not teaching will not be returned, this will be taken care of in next step
- Result is:

| teacher_id | num_students |
| ---------- | ------------ |
| 1          | 5            |
| 2          | 4            |

``` sql
select teacher_name, isnull(student_numbers.num_students)
from teachers 
left join (
  select teacher_id, count(student_courses.student_id) as 'num_students'
  from courses inner join student_courses
  on courses.course_id = student_courses.course_id
  group by courses.teacher_id
) student_numbers
on teachers.teacher_id = student_numbers.teacher_id
order by student_numbers.num_students desc;
```

# Useful SQL Methods

- Count()
  - returns number of rows that match a specified criteria
- Avg() 
  - returns the average value of a numeric column
- Sum()
  - returns total sum of a numeric column
- Min()
  - returns smallest value of the selected column
- Max()
  - returns largest value of the selected column
- Isnull(expression)
  - returns 1 or 0 depending if expression is null