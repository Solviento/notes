# Databases

Explicit Join
``` sql
select course_name, teacher_name
from courses inner join teachers
on course.teacher_id = teachers.teacher_id
```

Implicit Join
``` sql
select course_name, teacher_name
from courses, teachers
where courses.teacher_id = teachers.teacher_id
```

Denormalized/normalized databases
- Normalized databases are designed to minimize redundancy, while denormalized databases are designed to optimize read time
- Traditional normalized databases will sometimes contain common queries with expensive joins, such as storing a teacher's name only once in a separate table
- Denormalization is commonly used to create highly scalable systems due to redundancy, such as storing a teacher's name in multiple tables
