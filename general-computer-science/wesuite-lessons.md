# Quick WeSuite Lessons

  Stored Procedures

  - Basically a SQL script you can run

  Syntax
  ```sql
  create procedure procedure_name
  as
  sql_statement(s)
  
  go
  ```

  To execute
  ```sql
  exec procedure_name
  ```

  Stored Procedure Example
  - Will create stored procedure that selects all records from a table
  ```sql
  create procedure SelectAllCustomers
  as
  select * from Customers
  go
  ```

    - Programs
    - Microsoft Office (outlook, calendar)
    - Slack (instant messaging)
    - Tettra (intra-document sharing)
    - SnagIt (take window snapshots, make annotations)
    - Dropbox (specs and documentation readings)
    - Jump Desktop (connect
    - Microsoft remote desktop (connect to demo app and debug)
    - Jamf (install programs)
    - Airtame (screen share with tv)
    - Kayako (customer service cases)
    - Keeper (security login information)

  - Backend Workflow
    - Database language is MySQL
    - Code base is worked on Visual Studio

  Useful SQL Codes

  dbo.labordata duplicate key resolution
  ```sql
  update tmpfolders
  set prt =  b.pos from tmpfolders
  inner join
  (select pos,idnum from tmpfolders where lvl1prt=0 and lvl2prt=0 ) b
  on b.idnum=tmpfolders.lvl1prt or b.idnum=tmpfolders.lvl2prt
  
  
  update tmpfolders
  set prt=pos
  where lvl1prt=0 and lvl2prt=0
  ```

  Document parameters retrieval for doc editor
  ``` sql
  select priceinfo.priceinfoID, templateID from document
  inner join priceinfo on document.sanum = priceinfo.sanum and document.jobnum=priceinfo.jobnum and document.altnum=priceinfo.altnum
  inner join FolderSummary on priceinfo.priceinfoID=FolderSummary.priceinfoid
  ```

  Look through all tables for a column that matches to search term
  ``` sql
  SELECT      c.name  AS 'ColumnName'
              ,t.name AS 'TableName'
  FROM        sys.columns c
  JOIN        sys.tables  t   ON c.object_id = t.object_id
  WHERE       c.name LIKE '%SEARCHTERM%'
  ORDER BY    TableName
              ,ColumnName;
  ```