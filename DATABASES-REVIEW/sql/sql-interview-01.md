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
| course_id | course_name | teacher_id  |
|-----------|-            | -           |
|1          |math_101     | 1           |
|2          |math_202     | 1           |
|3          |sci_101      | 2           |
|4          |sci_201      | 2           |
|5          |sci_301      | 3           |
|6          |eng_101      | 4           |

student_courses
| course_id | student_id  |
|-----------|-------------|
|1          |1            |
|1          |2            | 
|1          |3            |
|2          |3            |
|2          |4            |
|3          |1            |
|3          |2            |
|4          |3            |

students
| student_id| student_name|
|-----------|-------------|
|1          |Amy          |
|2          |John         | 
|3          |Marco        |
|4          |Amber        |
|5          |George       |

teachers
|teacher_id |teacher_name |
|-----------|-------------|
|1          |Smith        |
|2          |Mary Sue     | 
|3          |Bob Oden     |
|4          |Derbin       |

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

|teacher_id|num_students|
|-|-|
|1|5|
|2|4|
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