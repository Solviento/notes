Comprehensive Guide of Pointers

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
interchangeably with arrays. i.e:

char array[] = "Bell";

char *p = array;	// Okay 

char *p = "Shots";	// Okay

char *p = array[2];	// Also okay. Why? array[2] is basically &array[2]

array = p; 			// Bad! Arrays are CONST like pointers

In general, arrays are like pointer constants in that they CANNOT be used
as lvalues (i.e. lefthand values, constants also CANNOT be modified)

-- in a main() function far far away --

main(int argc, char **argv)

char **ptr = &argv[0];	// &argv[0] is EQUIVALENT to char **argv

// Likewise

char *p = "Bell";

char *copy = &p[0]; // &p[0] is EQUIVALENT to char *p

char *dup  = p;		// p by itself is the pointer, no need for * or &

- char ** and char * and char

char c				// Basically a single literal

char *ptr			// Basically a string (an array of string literals)

char **list			// Basically a a list of strings (like a sentence!)
				// Also interpreted as a pointer to an array of pointers

It gets trickier though, say we have:

void duplicateArgs(char **copy){    // Command line args are usually in the form
								// of char **. Ex: ./fun abc 123
char **p = copy;	// Makes another copy of the double pointer

while(*p){	

// Why *p? 

// p points to the overall array of pointers

// *p points to the individual pointer in the overall array

By REFERENCING we go down deeper one level to access the real data that is
pointed to by the pointer

// **p points to the individual data member hidden underneath
// In this case it points to the char

By referencing again we access the actual data pointed to by the double pointer

free(*p++);
}
free(copy);
}

Visual:

**copy (what we start with)					Handled as: copy

[simple] [message] [stored]

*copy (can also be iterated as seen above) 	Handled as: *copy

[simple]

copy (also iterable)						Handled as: **copy

[s]

Confusing right? I know. 

- Pointers get tricky in the way they're implemented such as:

float a = 5.2; 		// Recall that float ia FOUR byte data type

char *ptr;

ptr = (char *) &a;	// Reference then Dereference with char *
				// This is okay, since we go down one level (called
				// the memory address) and then back up (where pointer a is)

printf("%d ", *ptr++);	// Why *ptr? Since we are doing pointer arithmetic
					// we must dereference (REMEMBER: the act of
					// dereferencing a pointer means to refer to data
					// that ptr points to, in this case, the float data
printf("%d ", *ptr++);

Pointer arithmetic and cast conversion is confusing, but it may be understood as:
float a (four bytes - 8 bits per byte)

0000 0000 0000 .... .... 1010 (32 bit binary)

When (char *)& is cast to a, it boils down to the following:
(Remember: char is 1 byte)

(0000 0000) (0000 0000) ..... (0000 1010) (Four groups of 8 bits)

So when *ptr is iterated by (++), it will point to the values of the
individual byte groups as shown above. So the first printf will be the
value of the first 1 byte binary representation and second will the second
1 byte binary group, etc.

- Smart pointers

In general, use smart pointers like shared_ptr or unique_ptr

However, weak_ptr is recommended for doubly linked lists since you will
run into memory leaks if using shared_ptr.

Valgrind

- Valgrind is initated as:

valgrind ./program file.txt . .

- Leaks and Errors

Leaks occur when you ALLOCATE memory that you DID NOT free. In general, leaks
will cause a program to stack overflow

Errors occur when your attempt to read or write INVALID memory blocks, i.e. 
memory that you DO NOT have access to

- Valgrind Results

Conditional jump OR move depends on unitialized value(s)

- This means that your program has a value that is not initialized in all
cases before it used. For ex:
	
	char *array;	// Not initialized, bad :(

- All variables must be initialized in every case

Invalid free()

- This means that you are freeing a memory TWICE or freeing a pointer that
points to the stack

Invalid read of size X

- This means that you are trying to read memory that you do not have
access to such as:

	Reading after the end of an array

	Reading memory already freed

	Reading a string that does not end with a null '\0'

- Resolving Leaks

Common causes:

	failing to free() memory

	failing to fclose() a file pointer

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

Return Values

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



Does the following program crash? What's the output?

MyString var = "hello";
MyString &refVar = var;			// Stores var's data into the reference of refVar (any changes made to var
									// will also make changes to refVar
var = "hi";						// Changes contents of cvar

MyString *pointer1 = &refVar	// Store's refVar's memory address reference into pointer
MyString *pointer2 = &var;		// Stores var's reference (memory address) into pointer

assert(pointer1 == pointer2);	// Equality test
cout << refVar << endl;			// Will output "hi"

---

$ ls
Makefile mystring.cpp mystring.h main.cpp

$ git status
# Working directory clean
$ make
$ git add mystring.ccpp
$ git add x

Git status of each file?

---

Does this program print 10?

int get_num_elements(int a[]){
	return sizeof(a) / sizeof(int);
}
int main(){
	int a[10];
	cout << get_num_elements(a) << endl;	// When a[10] gets passed as an arg, it is casted to a pointer
											// meaning its size will take 8 bytes
	return 0;
}

If the first line were changed to
int get_num_elements(int *a){

Then the result would still be the same and the output would be (8 / 4 = 2)

Questions related to lab 7:

Entering the web root into the browser client will request for THREE different things:
index.html
ships.jpg (The browser requests this since the machine does not have this stored locally)
crew.jpg (Same as above)

Therfore, there are three HTTP requests when accessing columbia.edu:8888/cd3157/tng/

For lab 7, we never make ANY connections to mdb-lookup-server ONLY http-server. 

http-server calls connect to mdb-lookup-server when a search query is made

mdb-lookup-server never calls connect since it only receives and send search query data

Remember to look at the Sockets API summary pdf

This shows that http-server and mdb-lookup-server calls listen() only once in the overall process

---

What happens when?

MyString * f(){
	MyString s("123");
	return &s;
}
int main(){
	cout << *(f()) << endl
	return 0;
}

Will return 123, the memory is preserved since it is returned as a pointer

What happens when?

MyString f(){
	MyString s("123");
	return s;
}
int main(){
	cout << *(f()) << endl
	return 0;
}

Bad! You can't dereference the return type of MyString f(). 

Why does this print 1?

const char *f(){
	return "123";
}
int main(){
	cout << *(f()) << endl;
	return 0;
}

It prints '1' since the dereference (*) will point to a single char as prescribed by its
original return data type. "123" is a char array pointed to by a pointer. If we did not have
the * before f() in the cout call, then it would print "123" since the pointer is not 
dereferenced during the call.

What happens when?

MyString *f(){
	MyString *s = new MyString("123");
	return s;
}
int main(){
	cout << *(f()) << endl;
	return 0;
}

Memory leak! It will "123" since the object type is MyString. However, since the *s pointer
was never deleted, this will cause a memory leak during runtime. For cout, MyString's internals
include char *data and int len. 
How many bytes do you lose?
We lose in total:
MyString {
	int len;
	char *data = "123\0"
}

4 bytes (from int len)
8 bytes (from char *)
4 bytes (from "123")
16 bytes

What happens when?

SmartPtr<MyString> f(){
	SmartPtr<MyString> s(new MyString("123"));
	return s;
}
int main(){
	cout << *(f()) << endl;
	return 0;
}

No memory leaks. Will print "123"

What happens when:

vector<MyString> vtr;
vector<MyString>::iterator f(){
	vtr.push_back("123");
	return vtr.begin();
}
int main(){
	cout << *(f()) << endl;
	return 0;
}

*(f()) will dereference the iterator pointer and give you the contents of MyString which is
"123". No memory leaks since vector's destructor will clear all resources used.

What happens when:

SmartPtr<MyString> f(){
	MyString t = MyString("1") + "2" + MyString('3'); // Error
	// "1" gets promoted to char *, which is handled by MyString's constructor
	// "2" gets promoted to MyString
	// '3' is a char, which is not handled by MyString so it will return an error
	MyString *s = new MyString();

	*s += t;
	return SmartPtr<MyString>(s);
}

Smart Pointers

Why use smart pointers?
- They make pointers more manageable + better code readability and clean up

Using Smart Pointers
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