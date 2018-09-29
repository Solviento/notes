# Intro SQL

## Quick Intro
- Structured Query Language, created by IBM in 1970's
- Used in database systems for the CIA and US Navy
- SQL is based on relational calculus and algebra

## Basic SQL Keywords
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

## Sample SQL expressions

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
