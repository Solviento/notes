Output?
``` python
import re
match = re.search(r'\d\s+\d\s+\d','1  2    3')
print(match.group())
```
- 1 2  3
- \s matches the 1 or more number of spaces
- \d matches decimal

Output?
``` python
import re
match = re.search(r'(\w[\w.]+)@([\w.]+)','called p@gmail.com better: xyzg')
print(match.group(2))
```
- Error, i believe \w[\w.] should not retrieve anything

Which will be raised by the python interpreter?
``` python
x = int('abc')
print(x)
```
- ValueError, raised when value of argument passed to a function is not what was expected

Output?
``` python
print(3*'un'+'ium')
```
- 'unununium'

Output?
``` python
print("Geek"+3)
```
- Error, print() cannot convert int to string impliciitly

What will be the output of the following code?
``` python
tuple_x = '1', '2'
tuple_y = ('3', '4')

print (tuple_x + tuple_y)
```
- ('1', '2', '3', '4')
- Both tuples get added and then printed

