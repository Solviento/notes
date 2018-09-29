- Return employee record with highest salary
``` SQL
select *
from employee
where salary = (select Max(salary) from employee)
```
- Note that we are using a sub query (a query within a query)
- The sub query will extract the max salary number from the employee table which the main query will use to extract the employee with the max salary

- Return the highest salary in employee table
``` SQL
select Max(salary)
from employee
```

- Return the 2nd highest salary from employee table
``` SQL
select Max(salary)
from employee
where salary Not In (select Max(salary) from employee)
```
- first line, selects the max salary
- where clause will filter to find the employee that is not the maximum salary earning employee
- in essence, if it is not equal to the maximum salary, it will equal to the second maximum salary

- Select range of employees based on id
``` SQL
select *
from employee
where employee_id between 2003 and 2008
```
- When working with range, you will need to use 'between'

- Return an employee with the highest salary and the employee's department name
``` SQL
select e.firstname, e.lastname, e.salary, d.departmentname
from employee e inner join department d on (e.departmentid = d.departmentid)
where salary in (select Max(salary) from employee)
```
- When dealing with foreign keys, use an inner join and then a sub query to extract an extra layer of search condition

- Return highest salary, employee name, department name for each department
``` SQL
select e.firstname, e.lastname, e.salary, d.departmentname
from Employee e inner join department d on (e.departmentid = d.departmentid)
where salary in (select Max(salary) from employee group by departmentid)
```
