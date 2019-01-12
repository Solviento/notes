# CRUD

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
