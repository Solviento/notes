Module Objectives

- Explain and implement PreparedStatements 
- Describe the purpose of Batching
- Explain and implement CallableStatements

**SQL Injection**

- Consider the following code, in a one-column table, where ‘user’ is a string passed into the method.

``` java
String query = “SELECT * FROM Users WHERE username=‘”
					+ user + “’”;
Statement stmt = conn.createStatement();
stmt.executeUpdate(query);
```

- What if a user enters the following into a form, which is then passed into the method as the user string?

``` 
anything’ OR ‘x’=‘x
```

**PreparedStatement**

- PreparedStatement extends from Statement. 
- It is an alternative to using Statement and offers protection from SQL Injection attacks.
- Extra features
  - Parameters can be inserted into the SQL query
  - May improve performance of executed statements

``` java
public void deleteUser(int id, String name){
   String query = “DELETE FROM testTable WHERE tid = ? AND name=?”;
   PreparedStatement ps = conn.prepareStatement(query);
   ps.setInt(1, id);
   ps.setString(2,name);
   ps.executeUpdate();
}
```

**Batching**

- What is a batch?
  - It is a group of things

- Batching is a means of grouping SQL statements together.
  - Faster way of executing multiple statements.

- It decreases the number of calls to the database to 1 – instead of 1 for every statement.

- To use batching, auto commit should first be turned off.

``` java
conn.setAutoCommit(false);
```

- Set the necessary values in the PreparedStatement:

``` java
ps.setInt(1,id); 
ps.setString(2,name);
```

- Add the statement to the batch:

``` java
ps.addBatch();
```

- To execute the statement call:

``` java
ps.executeBatch();
```

- Call commit as we have turned off auto commit:

``` java
conn.commit();
```

- Auto commit is turned off in order to create a true transaction. This will guarantee that no statements will be sent to the database before we are done constructing our batch, and that the batch either succeeds or fails in its entirety.

**Batching Implementation**

``` java
conn.setAutoCommit(false);

String query = “INSERT INTO Users VALUES (?,?,?)”;
PreparedStatement ps = conn.prepareStatement(query);

ps.setInt(1, 1);
ps.setString(2, “Joe”);
ps.setString(3, “Bloggs”);
ps.addBatch(); 

ps.setInt(1, 2);
ps.setString(2, “John”);
ps.setString(3, “Jones”);
ps.addBatch();

ps.executeBatch();

conn.commit();
```

**CallableStatement**

- CallableStatement extends PreparedStatement
- It is used for calling stored procedures in the database.
- Stored procedures are created for intensive database processes.
- This reduces network bandwidth consumption by sending less data over the network.

**Stored Procedures**

``` sql
CREATE OR REPLACE PROCEDURE insertUser(
    p_userId IN User.user_id%TYPE,
    p_first_name IN User.first_name%TYPE,
    p_last_name IN User.last_name%TYPE)
IS
BEGIN
    INSERT INTO User
    VALUES (p_userId, p_first_name, p_last_name);
    COMMIT;
END;
/
```

**CallableStatement**

- The code to create a CallableStatement is:

``` java
CallableStatement cs = conn.prepareCall(“{call insertUser(?, ?, ?)}”);
```

- Setting parameters is done the same way as a PreparedStatement:

``` java
cs.setInt(1, 1);
cs.setString(2, “John”);
cs.setString(3, “Jones”);
```

- Once parameters are set and you want to execute it:

``` java
cs.executeUpdate();
```

**CallableStatement: OUT Parameters**

- A stored procedure may return OUT parameters – values returned instead of, or as well as, a ResultSet.
- After executing the CallableStatement, you can access them from the CallableStatement object:

``` java
CallableStatement cs = conn.prepareCall(“{call getUsers(?)}”);

cs.setString(1, “John”);

cs.registerOutParameter(1, java.sql.Types.VARCHAR);
cs.registerOutParameter(2, java.sql.Types.INTEGER);

ResultSet result = cs.executeQuery();
String out1 = cs.getString(1);
int    out2 = cs.getInt(2);
```

**Module Review**

- What is the difference between a statement and a prepared statement?
- What extra features prepared statements have?
- What are the benefits of using batching?
- What is the difference between a prepared and a callable statement?





