# Quick Sheet for MySQL

To connect using username and password
``` SQL
mysql -u solviento -p
```

To turn off Strict Mode via SQL, in command prompt
``` bash
mysql -u solviento -p -e "SET GLOBAL sql_mode='NO_ENGINE_SUBSTITUTION';"
```
Verify by using sql
``` SQL
SHOW VARIABLES LIKE 'sql_mode';
```

To show schemas within a database
``` SQL
show schemas;
```

To show tables inside a schema labeled 'schema_a'
``` SQL
show tables in schema_a;
```

To create demo table in schema
``` SQL
CREATE TABLE `schema_a`.`demo_table` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `a` VARCHAR(45) NULL,
  `b` VARCHAR(45) NULL,
  PRIMARY KEY (`id`));
```