# Comprehensive Guide of Pointers

- Envisage a pointer as containing a numeric memory address

- Full visual of a pointers and pointees (initialized data in either stack/heap)
```
const char *p = "abc";
```
Boils down to:

p = 'a', 'b', 'c', '\0'

In the actual memory:
```
Memory Address (hex)	                        Variable name	Contents

1000							'a' == 97 (ASCII)
1001							'b' == 98
1002							'c' == 99
1003							'0' == Null
...
2000-20003						1000 (hex)
```

- What it means to 'dereference'
    - To refer to the character p points to, we dereference p using any of the below:
    - *p		// *p == 'a'	The first character that p points to is 'a'
    - p[1]	// p[1] == 'b'	p[1] dereferences a pointer and then moves past one
							whole data type
	  -	*(p+1)	// *(p+1) == 'b' Another notation for p[1]
  - We can move pointers through the pointee data
  - ++p;	// Will now point to address 1001 which holds 'b'

- Pointers in functions
- When a function is written as:
```
void reverseList(struct List *list){}
```

The following are acceptable:
```
struct List *ltr;

reverseList(ltr);		// No dereference needed, ltr is a pointer

struct List rank;

reverseList(&rank);		// Reference needed, 'rank' is located on the stack so
                      // it requires a & for it to work
```
You may also need to convert struct variables like so:

struct sockaddr_in serverAddr;	// Non-pointer variable stored on stack

connect(sock, (struct sockaddr *)&serverAddr,..);

Since connect requires a sockaddr * type in its second parameter, we first
reference 'serverAddr' (this essentially allows us to use it as a pointer arg)
and then cast convert it to a sockaddr * type for us to use in connect()

- Pointers in Conditional Statements

Example:

struct Node *cur = list->head;

while(cur){ .. }		// Taken from a linked list program. Will keep the while
					// loop going until 'cur' node reaches a NULL value
	
- Converting void * in C

When you have a function as:

static void printDouble(void *p) { ..}

And you would like to perform some operation on it which requires a double
or float, you would need to convert using the following:

*(double *) digit = *(double *)p;	// Cast as double *, then dereference to
								// access the data held pointed to by p

- Arrays vs Pointers

Arrays can be used interchangeably with pointers
HOWEVER, do not confuse this with pointers being used
interchangeably with arrays

## Data Types and their Sizes
```
char	1 byte
un. char	1 byte
sig. char	1 byte
int		4 bytes
un. int	4 bytes
short	2 bytes
un. short 2 bytes
long		4/8 bytes
float	4 bytes
double	8 bytes
```
Binary (Bit-wise) Operators
```
&		AND (copies a bit to the result if it exists in both operands)
|		OR	(copies a bit if it exists in either operand)
^		XOR (copies the bit if it is set in one operand but not both)
~		NOT	(flips all the bits)
<< 		Left shift (The left operands value is moved left by the # of bits specified by the right operand)
>>		Right shift (Same as above but with operands switched)
```
Hexadecimal Conversions

- Normally you would multiply 16^0, 16^1, 16^2... for each bit (from LSB to MSB)

Popular Hexadecimals (AP)

0xFF	-	255

Storage Classes

static - Retained when out of scope, static global variables are confined to the
		 scope of the compiled object file they were declared in

extern - Used when the variable is declared by another file

Recursion

In AP, we use structs with a recursive structure pointer inside. This is most 
useful when we implement them in linked lists

Accessing Structs

strName.x	- Accesses member 'x' of a struct declared in the stack

ptrName->x	- Accesses member 'x' of a struct declared in the heap (usually a ptr)

Pointers (major topic in AP)

int/char/struct *x	- All pointers have a data type like normal variables

void *v		- Pointers can also have an incomplete type

int x[*] 	- Arrays can also be used as pointers to the first array element (always used in trick questions for AP)

x			
- By itself, a pointer variable name will contain a memory address that points to another memory address that stores a value (the 'pointed' to memory address and its stored value will be used, not the pointer itself)

*x			
- Dereference. When you want to access the data/value in the memory that the pointer points to, then you DEREFERENCE the pointer

&x
- Reference. Will return and give you access to the memory address of the variable x. REFERENCE can be imagined as an actual memory address reference in an address book

**Return Values**

return &x	- Returning a variable by pointer

Looping

while		- Loop skipped if test condition initially false

do while	- Always runs through loop at least once

File IO

"r"/"rb"	- Read text/binary file

"w"/"wb"	- Write new or over existing text/binary file

"a"/"ab"	- Write new or append to existing text/binary file

"r+"		- Read and write existing file

"w+"		- Read and write new/existing

**Smart Pointers**

Why use smart pointers?
- They make pointers more manageable + better code readability and clean up

**Using Smart Pointers**

``` c
#include <string>
#include <memory>

using namespace std;

class Dog
{
	string name_;

	public:
		Dog(string name)
		{
			cout << "The dog: " << name << " was created." << endl;
			name_ = name;
		}
		Dog()
		{
			cout << "Nameless dog created." << end;
			name_ = "nameless";
		}
		void bark()
		{
			cout << name_ << " rules!" << endl
		}
		// Destructor ~() not needed!
};

void foo()
{
	shared_ptr<Dog> doggy(new Dog("Gunner")); // Count = 1
	// Two steps: 1. "Gunner" is created. 2. doggy is created

	// Smart pointer ('shared_ptr') can be declared and initialized in
	// the parenthesis field using 'new' + Object-Name + any-fields

	{
		shared_ptr<Dog> p2 = p; // Count = 2
		p2 -> bark();
	}
	// Count = 1

	p->bark(); 
	// No cleanup!
	// Count = 0 - The Dog 'Gunner' is deconstructed
}

int main()
{
	foo();

	// Pitfalls:

	Dog *d = new Dog("Tank");
	shared_ptr<Dog> p(d);	// p count = 1
	shared_ptr<Dog> p2(d);	// p2 count = 1

	// This is a bad use of the smart pointer, an object should be assigned
	// to a smart pointer as soon as it is created. 
	// Instead, below is exception safe:

	shared_ptr<Dog> p = make_shared<Dog>("Tank"); // Faster + safer
	
	(*p).bark(); // Can be dereferenced 
}

```