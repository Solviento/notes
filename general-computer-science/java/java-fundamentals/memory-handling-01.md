**Module Objectives**

- List the differences between a reference and value type
- Describe what happens when we pass variables as method arguments
- Describe the JVM’s 2 memory types and their use
- Describe how the Garbage Collector works
- Describe the stack trace execution

**Value Types**

- Pieces of memory holding a raw value.
- In Java, primitive variables are value types.

``` java
int number = 6; 
```

- The variable number is of type int (32 bits)
- The bits hold the value 6

**Reference Types**

- Aliases to location in memory where the actual value is.
  - In Java, object variables are reference types
  - Object variables do not contain object data!
  - Object variables contain a value that represents the location of the actual object

``` java
User user = new User();
```

- The value of variable user is a memory address
- There is no way for us to see what the memory address is

**Pass by Value vs. Pass by Reference**

- Refers to how a programming language handles:
- Passing variables into methods:

``` java
int x = 1;
int y = 2;
add(x,y);
```

- Assigning variables to other variables:

``` java
int x = 1;
int y = x; 
```

- Java is strictly only Pass by Value!

- Pass by Value:
  - The variable’s value is evaluated
  - A copy is created
  - The copy is assigned to the new variable
- Pass by Reference:
  - New variable receives a reference to the original variable
  - Any change to new variable modifies original variable

**The Litmus Test**

- What is the value of arg 1 and arg 2 in a pass-by-reference language, and in a pass-by-value language?

``` java
int arg1 = ...;
int arg2 = ...;
swap(arg1, arg2);
```

``` java
swap(int var1, int var2){
	int temp = var1;
	var1 = var2;
	var2 = temp;
}
```

- Standard test to verify Pass by Reference behaviour:
- If after calling swap, arg1 and arg2 are different, then the language supports Pass by Reference
- In Java, calling swap() does not alter arg1 or arg2 – Pass by Value!

**Stack and Heap**

- Every action or variable requires RAM memory.

- Two areas:
  - Stack
  - Heap

- Each has different behaviour.

- As of JDK 7, memory is dynamically allocated. Prior to this the size was fixed at JVM startup. 

**Stack**

- Stores method calls and local variables:
  - Primitives – actual value is stored
  - Objects – reference to an object (its location on heap) is stored
- Last In First Out
- Memory is not reclaimed unless it goes out of scope
  - i.e. A method finishes executing and returns
- Be careful of stack overflow! 
  - Usually due to recursive calls
- One stack exists per thread

``` java
class Demo {
  int add(int a, int b) {
    int result = a + b;
    return result;
  }
}
```

<img src="../PHOTOS/mem-01.png">

**Stack Overflow**

``` java
class Demo {
  int count(int a) {
    return count(a + 1);
  }
}
```

- **This is infinite recursion!**

**Heap**

- Stores all objects and their data.
- References to objects could be stored on the stack, or inside other objects
- Maintained by the garbage collector
- String pool is contained here
- Only one heap per JVM

``` java
class Demo {
  public static void main(String[] args) {
    User usr = new User();
  }
}
```

<img src="../PHOTOS/mem-02.png">

**Stack and Heap**

- There is one stack per thread, but only one heap per JVM.

<img src="../PHOTOS/mem-03.png">

**Garbage Collector**

- Part of the JVM that reclaims and maintains memory on the Heap. 
  - Runs in its own Thread
  - Cannot be pre-empted
  - Stops the application completely while it runs
    Main reason why Java cannot be used in real time situations

- Alternative implementations of the JVM have different implementations of the Garbage Collector and specialise in different scenarios. Some of these work in real time and avoid Oracle’s “Stop The World” strategy. 

**Module Review**

- What is the Stack?
- What is the Heap?
- What is a Value type?
- What is a Reference type?
- What is Pass by Value?
- What is Pass by Reference?
- Which of the above is Java?
- What does the Garbage Collector do?







