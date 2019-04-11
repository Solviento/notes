# Object Oriented Programming Concepts

**Packages & Classes**

What Is a Package?

- A package is a namespace for organizing classes and interfaces in a logical manner. Placing your code into packages makes large software projects easier to manage. 

``` java
// importing a package called packagename
import packagename.*;
// importing a single class
import packagename.ClassName;
// subpackage example
import packagename.subpackagename.ClassName;
import java.util.ArrayList;
```

- Note that package name is all lower case letters
- The package java.lang is automatically provided to all Java classes
- *public, static, and void* are reserved keywords

Javadoc Comments

- Javadoc comments @param and @return are used to produce Javadoc output

``` java
/* Returns area of rectangle
* Precondition: length and width are both positive
* @param length length of rectangle
* @param width width of rectangle
* @return the area of rectangle
* @throws IllegalArgumentException if length of width are not positive
*/
```

**Types and Identifiers**

Identifiers

- Identifier is a name for a variable, parameter, constant, user defined method or user defined class
- Can be any sequence of alphanumeric letters, must not begin with a digit
- By convention, Java variables are lowercase: findSurfaceArea

Final

- a final variable is a quantity whose value will not change
- Often used as an array bound

**Operators**

Bit Operators

- & - and
- | - or
- ^ - xor

**Input/Output**

- User Scanner class for terminal based input
- System.out.print for terminal based output

- \n - newline
  - "Welcome to\n a new line"
- \\\ - backslash
  - "He is known\\"Hothead Harry\\".")
- \\" - double quote
  - "The file path is d:\\\myFiles\\\\.."

**Control Structures**

- if, if/else, for, while, do while

``` java
int x = 10;
do{
    System.out.println(x);
    x++;
}while(x<20);
```



**Exceptions**

- An exception is an error condition that occurs during execution of a Java program

- Types of exceptions
  - Checked exceptions
    - Is one where you provide code to handle the exception
    - Must be handled explicitly using **throw** **new .. Exception** or a try/catch/finally statement
    - Not all exceptions stem from code, a file could be corrupted due to a broken network connection
  - Unchecked exceptions
    - Is one that is automatically handled by Java's standard exception handling methods which terminates
    - Index out of bounds exception
  - Errors
    - Errors are problems that arise beyond control of user such as Stack Overflow

**Throws/Throw**

- **throws** keyword tells compiler that your function MIGHT throw an exception and this should be caught by the compiler
- **throw** keyword will throw an exception that is instantiated or one that is caught in the code

```java
import java.io.*;

public class name{
    public void deposit(double amount) throws RemoteException{
        // method implementation
        throw new RemoteException();
    }
}
```

**Try/Catch**

```java
try {
   // Protected code
} catch (ExceptionType1 e1) {
   // Catch block
} catch (ExceptionType2 e2) {
   // Catch block
} catch (ExceptionType3 e3) {
   // Catch block
}finally {
   // The finally block always executes.
}
```

Example of try/catch

```java
public class ExcepTest {
   public static void main(String args[]) {
      int a[] = new int[2];
      try {
         System.out.println("Access element three :" + a[3]); // Accessing 3rd element
      } catch (ArrayIndexOutOfBoundsException e) {
         System.out.println("Exception thrown  :" + e);
      }finally { // Always executed no matter what!
         a[0] = 6;
         System.out.println("First element value: " + a[0]);
      }
   }
}
Output: 
Exception thrown  :java.lang.ArrayIndexOutOfBoundsException: 3
First element value: 6
```



## Classes and Objects

**Classes (Public, Private, Static)**

What Is a Class?

- A class is a software blueprint for implementing objects a given type
- In OOP, an object is a single instance of the aforementioned class
  - The current state of a given object is maintained in its data fields or instance variables provided by the class
  - Class methods are provide behaviors and operations that manipulate the object

Public

- Using the keyword public preceding the class declaration signals that the class is usable by all client programs, outside the class

Private

- Private methods and variables in a class can be accessed only by methods of that class

- Restriction of access is known as information hiding

Static

- A static variable contains a value that is shared by all instances of the class
- Static means that memory allocation happens once
  - Useful for keeping track of statistics for objects of the class, provide a new identity number for each new object, accumulate a total
- Static final variables in a class cannot be changed

**Encapsulation**

- Encapsulation is the hiding of data implementation by restricting access to accessors and mutators.  

**Methods**

``` java
public			 void			withdraw	 (String password, double amount)
access specifier return type	method name	 parameter list
```

Constructor Methods

- A constructor creates an object of the class
- A default constructor has no arguments

Accessor

- An accessor is a method that is used to ask an object about itself. In OOP, these are usually in the form of properties, which have, under normal conditions, a get method, which is an accessor method. However, accessor methods are not restricted to properties and can be any public method that gives information about the state of the object.

Mutator  

- Mutators are public methods that are used to modify the state of an object, while hiding the implementation of exactly how the data gets modified. Mutators are commonly another portion of the property discussed above, except this time it's the set method that lets the caller modify the data member behind the scenes.
- So, the use of mutators and accessors provides many advantages. By hiding the implementation of our Person class, we can make changes to the Person class without the worry that we are going to break other code that is using and calling the Person class for information.  
- If we wanted, we could change the fullName from a String to an array of single characters (FYI, this is what a string object actually is behind the scenes) but they callers would never have to know because we would still return them a single FullName string, but behind the scenes we are dealing with a character array instead of a string object. Its transparent to the rest of the program.  
- This type of data protection and implementation protection is called Encapsulation. Think of accessors and mutators as the pieces that surround the data that forms the class.

Static Methods

- Instance methods are constructors, accessors, mutators
- A method that performs an operation for the entire class, not individual objects, is called a static method

Method Overloading

- Overloaded methods are two or more methods in the same class that have the same name but different parameter lists

``` java
class DoOperations{
    public int product(int x){...}
    public double product(double x){...}
}
```

**Scope**

- Scope of a variable or method is the region in which that variable or method is visible and can be accessed

**References**

Primitive Data Types

- Simple built in data types like double and int, char, boolean are primitive data types

Reference data types

- All objects and arrays are reference data types (String, Random, int[], String, Cat)
- The difference between primitive and reference data types are the way they are stored
  - int num1 = 3;
    - The variable num1 can be thought of as a memory slot
  - Date d  = new Date(2, 17, 2049);
    - This declaration creates a reference variable d that refers to a Date object

Tips with Objects and Primitive Types as Parameters

``` java 
void foo(int x){
    x = 3;
}
static void main(String[] args){
    int a = 5;
    foo(a);
    // a remains 5
}
```

 ``` java
void chooseBest(Account better, Account b1, Account b2){
    if(b1.getBalance()>b2.getBalance()){
        better = b1;
    }
    else{
        better = b2;
    }
}
void main(String[] Args){
    Account brian = new BankAccount(...);
    Account paul = new Account(...);
    Account betterFund = null;
    
    chooseBest(betterFund, brian, paul);
    // betterFund remains null
}
 ```

- In above, chooseBest() will create a new object instance called better which then gets set to the better Account object while betterFund Account object will remain unchanged from the calling method (out of scope)

## Inheritance and Polymorphism

**Inheritance**

- Inheritance defines a relationship between objects that share characteristics 

What Is Inheritance?

- Inheritance provides a powerful and natural mechanism for organizing and structuring your software. Objects can relate to each other with either “has a”, “uses a” or an “is a” relationship.  “Is a” is the inheritance way of object relationship.   
- Rather than duplicate functionality, inheritance allows you to inherit functionality from another class, called a superclass or base class.
- A sub class always has more data and methods than a parent class
- Use an inheritance hierarchy chart when designing objects

``` java
class Student{
    private String name;
    private String grade;
    public Student(){
        name = "";
        grade = "";
    }
    public Student(String studName, String studGrade){
        name = studName;
        grade = studGrade;
    }
    // accessors, mutators
    void computeGrade(){
        if(name.equals("")){
            grade = "no grade";
        }
        // and so on
    }
}
class UnderGrad extends Student{
    private int ID;
    public UnderGrad(){
        super();
    }
    public UnderGrad(String sName, String sGrade, int id){
        super(sName, sGrade);
        ID = id;
    }
    // accessors, mutators
}
```

- super() can also be used to call methods in subclasses

``` java
class GradStudent extends Student{
    // constructors, methods
    void computeGrade(){
        super.computeGrade();
        // additional code for grade string
    }
}
```

- Note that private methods CANNOT be overriden
- Constructors are never inherited!
  - Be sure to provide at least one constructor for a subclass
- If super() is used in a constructor, IT MUST be the first line of that constructor
- A subclass cannot directly access the private members of its superclass without the help of an accessor/mutator

**Polymorphism**

- a method that has been overridden in at least one subclass is said to be polymorphic
- In Java, method calls are always determined by the type of the actual object, NOT the type of the object reference

Polymorphism

- Polymorphism means one name, many forms. Polymorphism manifests itself by having multiple methods all with the same name, but slightly different functionality.  
- There are 2 basic types of polymorphism. Overriding, also called run-time polymorphism, and overloading, which is referred to as compile-time polymorphism. The difference is, for method overloading, the compiler determines which method will be executed, and this decision is made when the code gets compiled. Which method will be used for method overriding is determined at runtime based on the dynamic type of an object.

**Type Compatibility**

Downcasting

- Casting a GradStudent as a Student object would be considered for downcasting
- In order to call certain GradStudent methods from this Student object, you would need to cast it "down"
- ClassCastException occurs when you attempt to cast an object to a completely different class

**Abstract Classes**

Abstraction

- An abstract class is a superclass that represents an abstract concept and therefore should no be instantiated
  - Example: a maze program can have different components (paths, walls, exits). These components can be declared as subclasses of the abstract class MazeComponent (path objects, wall objects, etc. can be created but MazeComponent will not be instantiated)
  - Abstract classes may contain abstract methods, if they do then every subclass must override this method
  - A class that contains abstract methods must be declared as an abstract class

- Data abstraction and encapsulation are closely tied together, because a simple definition of data abstraction is the development of classes, objects, types in terms of their interfaces and functionality, instead of their implementation details. Abstraction is essential in the construction of programs. It places the emphasis on what an object is or does rather than how it is represented or how it works. Thus, it is the primary means of managing complexity in large programs.
- Abstraction is used to manage complexity. Software developers use abstraction to decompose complex systems into smaller components. As development progresses, programmers know the functionality they can expect from as yet undeveloped subsystems. Thus, programmers are not burdened by considering the ways in which the implementation of later subsystems will affect the design of earlier development.

``` java
public abstract class AbstractClass{
    private String name;
    public AbstractClass(String shape){
        name = shape;
    }
    public abstract double area();
}

public class SubClass extends AbstractClass{
    private double side;
    public SubClass(double square, String name){
        super(name);
        side = square;
    }
    public double area(){
        return side*side;
    }
}
```

**Interfaces**

What Is an **Interface**?

- An interface is a contract between a class and the outside world. When a class implements an interface, it promises to provide the behavior published by that interface.  
- An interface is also a reference type, similar to classes but has a collection of abstract methods
  - Interfaces may also contain constants, default methods, static methods and nested types
  - The class that implements the interface must define all methods in the interface UNLESS the class is abstract
- Interfaces can contain methods, are written with .java extension and also appear in packages
- Interfaces CANNOT be instantiated, DO NOT contain constructors, ALL methods of interface are abstract, fields in an interface MUST BE static and final, an interface is NOT extended by a class, interface CAN extend multiple interfaces

Declaring Interfaces

- **interface** and implement keyword must be used

```java 
// NameOfInterface.java
import java.lang.*;
// import statements
public interface NameOfInterface{
    // final, static fields
    // abstract method declarations
    public void methodA();
}
```

- The **abstract** keyword is already implicit, there is no need to declare it
- Interface methods are implicitly public

Implementing Interfaces

- A class uses the **implements** keyword to implement an interface

```java
// mammal.java
public class mammal implements animal{
    public void eat(){
        return null;
    }
    public void travel(){
        return null;
    }
    public static void main(String... args){
        mammal m = new mammal();
        m.eat();
        m.travel();
    }
}
```

- always remember that classes can implement more than one interface BUT classes can only extend one class

```java
// Filename: sports.java
public interface sports {
   public void setHomeTeam(String name);
   public void setVisitingTeam(String name);
}

// Filename: football.java
public interface football extends sports {
   public void homeTeamScored(int points);
   public void visitingTeamScored(int points);
   public void endOfQuarter(int quarter);
}

// Filename: Hockey.java
public interface hockey extends sports {
   public void homeGoalScored();
   public void visitingGoalScored();
   public void endOfPeriod(int period);
   public void overtimePeriod(int ot);
}
```

- A class that implements hockey MUST implement all six methods of sports and hockey, a class that implements sports will only implement two methods

Comparable Interface

- Any class that implements Comparable must provide a compareTo method for comparing objects
- The method compares the implicit object (this) with the parameter object (obj) and returns a negative int, zero or a positive int depending whether the implicit object is less, equal to, or greater than the parameter

``` java
public abstract class Shape implements Comparable{
    // constructor, setters, getters
    public int compareTo(Object obj){
        Shape rhs = (Shape) obj;
        if(rhs.area() < this.area()){
            return -1;	// obj area less than this object
        }
        if(rhs.area() > this.area()){
            return 1;	// obj area greater than this object
        }
        else{
            return 0;	// equal
        }
    }
}
```

Multiple Interface declaration

```java 
public interface hockey extends sports, event{...}
```

Interface vs. Abstract Class

- Use an abstract class for an object that is application specific but incomplete without its subclasses
- Consider using an interface when its methods are suitable for your program but could be applicable in a variety of programs
- An interface doesn't provide implementation, abstract classes can
- Interfaces cannot contain instance variables, abstract classes can
- No instantiation of abstract or interface objects

## Standard Classes

**Object Class**

- The universal superclass
- Every class automatically extends Object
- The methods of Object include: toString and equals
- All subclasses are expected to override these methods in some way

``` java
public String toString(){
    return "...";
}
public boolean equals(Object other){
    // boolean ifs
}
```

hashCode Method

- Every class inherits the hashCode method from Object
- the value returned by hashCode is an integer produced by some formula that maps your object to an address in a hash table
- A given object must always produce the same has code

**String Class**

- An object or String type is a sequence of characters
- All string literals, "sss!", are implemented as instances of this class
  - String literals can be zero or more characters
- Strings objects are immutable, which means there are no methods to change them after they've been constructed
- Use .equals(), .compareTo() when comparing strings

``` java
s1.equals(s2);
s1.compareTo(s2);
s1.length();
s1.substring(int index, int endIndex);
s1.indexOf(String str);
```

**Wrapper Class**

- A wraper class takes either an existing object or a value of primitive type, "wraps" or "boxes" it in an object, and provides a new set of methods for that type
- The point of a wrapper class is to provide extended capabilities for the boxed quantity
  - Integer Class
    - Will wrap values of type int
  - Double Class
    - Values of type double

**Math Class**

- Class that implements standard mathematical functions such as absolute value, square root, trig functions, etc.

``` java
int abs(int x);	// Math.abs(-3);
double abs(double x);
double pow(double base, double exp);
double sqrt(double x);
double random();
```



## Program Design and Analysis

**Software Development Life Cycle**

Waterfall Method

- Analysis of Specification -> Program Design -> Implementation -> Testing and Debugging -> Maintainence

**Object Oriented Program Design**

Steps in OO design

- Identify classes to be written
- Identify behaviors for each class
- Determine the relationships between classes
- Write the interface (public method headers)
- Implement the methods

**Program Analysis**

- A program must be tested for correctness

Assertions

- An assertion is a statement about a program at any given point
- The idea is that if an assertion is proved to be true, then the program is working correctly at that point

## Arrays and Array List

**Arrays**

- 1D arrays, 2D arrays

``` java
double[] data = new double[25];
int n = data.length;
for(double d: data){
    // do something
}
```

- Arrays are treated as objects so passing an array can also allow you to access and modify elements of the actual array

**ArrayLists**

- Can shrink or grow

``` java
import java.util.ArrayList;

ArrayList<Integer> arr = new ArrayList<>();
```

**List<E> Interface**

Methods of List<E>

``` java
boolean add(E obj);
int size();
E get(int index);
E set(int index, E element);
void add(int index, E element);
E remove(int index);
Iterator<E> iterator();
```

**Collections and Iterators**

Collections

- Collections in java is a framework that provides an architecture to store and manipulate the group of objects. All the operations that you perform on a data such as searching, sorting, insertion, manipulation, deletion etc. can be performed by Java Collections. Java Collection simply means a single unit of objects. Java Collection framework provides many interfaces (Set, List, Queue, Deque etc.) and classes (ArrayList, Vector, LinkedList, PriorityQueue, HashSet, LinkedHashSet, TreeSet etc).

Iterator<E> Interface

Methods of Iterator<E>

``` java
boolean hasNext();
E next();
void remove();
```





## Recursion

**Recursive Methods**



**General Recursion**



**Recursion Analysis and Sorting**



## Sorting and Searching

**Selection Sort**



**Insertion Sort**



**Merge Sort (Recursive)**



**Quicksort (Recursive)**



**Java Sorting Algorithms**



**Sequential Search**



**Binary Search**



------

***Sample Questions***

What are the four pillars of OOP and how they are used in Java?
- Abstraction, Encapsulation, Polymorphism, Inheritance. Abstraction refers to showing only the essential features of the application and hiding the details, they provide methods to the outside world to access and use the data variables but they themselves are hidden from direct access. Inheritance is a way to reuse code - the class which is inherited from is called the base class, the class that inherits the code from a base class is called a derived class. Encapsulation is about binding data variables and functions together in a class. Polymorphism is feature that lets us create functions with the same name but different arguments.

What is OOP?
- Object Oriented Programming is programming based on the concept of objects (which may contain data) in the form of fields, also known as attributes.

What is JVM, JRE?
- Java Virtual Machine, Java Runtime Environment.

What is a Wrapper Class?
- A wrapper class is a class whose object wraps or contains a primitive data type.  

| Primitive | Wrapper |
| -         | -       |
| char      | Character |
| byte      | Byte    |
| short     | Short   |
| float     | Float   |

What are the differences between an Interface and Abstract Class?
- Interface contains only signatures of methods, the interface itself cannot do anything. An abstract class looks like an interface but you can define a behavior for them.

What are the different access modifiers?
- Default, private, protected, public.
- access-modifiers-in-java

What does the static keyword do?
- Static members belong to the class instead of a specific instance. It means that only one instance of a static field exists. It will be shared by all instances of the class.  

What are the uses of the final keyword?
- final keyword in java

What is the difference between Set and Map?   
- A set list does not allow duplicates, while a map doesn't allow duplicate keys but can have duplicate values.

What is the difference between array and Array List?
- Arrays are simple fixed sized arrays while ArrayLists are dynamic sized arrays in Java.

Asked to implement some simple methods in a language of your choices. Generating prime numbers, generating an alternating sequence of numbers, reverse a number.

``` Java
// basic prime checker
for (int I = 2; I < n/2; i++){

    if (n % i == 0) {

        return false;

    }

}

return true;
```

``` Java
// alternate sequence of numbers

int number = 1;

for (int i = 0; i < some_num; i++){

    number = number + i;

    System.out.print(number);

}  
```

``` Java
// reversing anumber

while (num!=0){

    // retrieve last number using % and ensure while loop will terminate using *

    rev = rev*10 + num%10;  

    // remove last number using /

    num /= 10;

}

return num;
```


Write a program which answers whether or not the input string is a palindrome?

``` Java
// using library method
String reverse = new StringBuffer(s).reverse().toString();

if (s.equals(reverse)){
    System.out.println("yes");
}

else{
    print("not palindrome");
}

// using stack
String input = "Test";
Stack<Character> stack = new Stack<Character>();

for (int i = 0; i < input.length(); i++){
    stack.push(input.charAt(i));
}

String reverse = "";
while(!stack.isEmpty()){
  reverse += stack.pop();
}

if (input.equals(reverse){
  print("is palindrome");
})
```

What is an exception, and how do you use them?
- Exceptions are events that occur during the execution of programs that disrupt the normal flow of instructions. In Java, exceptions are objects that wrap around an error event that includes information of the error and state of the program. Examples include FileNotFoundException, IOException.  

``` Java
try {
// code block
}

catch (ExceptionType1 e1){
  // handle exceptiontype1 exceptions
}

finally {  
  // code executed after the try and catch blocks
}
```

``` Java
private int divideInts(int i1, int i2){

  try{
    return i1/i2;
  }
  catch(ArithmeticException e){
    return 0;
  }
}
```

Type an Object declaration?
- NewExample obj = new NewExample();

What is Method Overloading?
- Overriding is when two or more methods in one class have the same method name but different parameters.

``` Java
class Dog{

    public void bark(){
        System.out.println("woof ");
    }

    //overloading method
    public void bark(int num){
        for(int i=0; i<num; i++)
            System.out.println("woof ");
    }
}
```

What is Method Overriding?
- Means having two methods with the same method name and parameters. One method is in the parent class while the other is in the child class.

``` Java
class Dog{
    public void bark(){
        System.out.println("woof ");
    }
}

class Hound extends Dog{
    public void sniff(){
        System.out.println("sniff ");
    }

    public void bark(){
        System.out.println("bowl");
    }
}

Dog dog = new Hound();
dog.bark();
// will print "bowl"
```

Why does Java not allow multiple inheritance?   
- Multiple inheritance is not supported by Java using classes, due to that multiple inheritance is very complex. It creates problems during various operations like casting, constructor chaining, few instances where we actually need it, so better to omit it for keeping the things simple and straightforward.

``` Java
class Test extends Parent1, Parent2
{
   public static void main(String args[])
   {
       Test t = new Test();
       t.fun();
   }
}
```

Provide a real-world example of when it might be necessary to use the modulus operator.   
- Checking whether a number is even, or do something every nth time (using for loop but using every cycle)

Do you know what multi-threading is? How do you do it (how would you start/stop a thread)?
- Multithreading in java is a process of executing multiple threads simultaneously. Thread is basically a lightweight sub-process, a smallest unit of processing. Multithreading is used to achieve multitasking.

**Java Specific Data Structures**

- Arrays, queues, collections, list, arraylist, set, map

Arrays

- An array is a collection of similar type of elements that have a contiguous memory location
- A Java array is a fixed set

Queues

- FIFO, first in first out

Collections

- All collections frameworks include interfaces, data structures, algorithms

List

- Extends collections, allows elements to be inserted or accessed at will

ArrayList

- Dynamic array
- Slower than standard arrays
- Cannot be used for primitive types like char or int, needs a wrapper class

Set

- A set is a collection that cannot contain duplicate elements
- Duplicate elements are prohibited

Maps

- Maps unique keys to values
- NoSuchElementException, ClassCastException, NullPointerException, UnsupportedOperation may arise

List, Set, Map examples:

``` java
import java.util.*; // All the classes and interfaces are part of the util package
public class CollectionsDemo {

   public static void main(String[] args) {
      // ArrayList 
      List a1 = new ArrayList();
      a1.add("Zara");
      a1.add("Mahnaz");
      a1.add("Ayan");
      System.out.println(" ArrayList Elements");
      System.out.print("\t" + a1);

      // LinkedList
      List l1 = new LinkedList();
      l1.add("Zara");
      l1.add("Mahnaz");
      l1.add("Ayan");
      System.out.println();
      System.out.println(" LinkedList Elements");
      System.out.print("\t" + l1);

      // HashSet
      Set s1 = new HashSet(); 
      s1.add("Zara");
      s1.add("Mahnaz");
      s1.add("Ayan");
      System.out.println();
      System.out.println(" Set Elements");
      System.out.print("\t" + s1);

      // HashMap
      Map m1 = new HashMap(); 
      m1.put("Zara", "8");
      m1.put("Mahnaz", "31");
      m1.put("Ayan", "12");
      m1.put("Daisy", "14");
      System.out.println();
      System.out.println(" Map Elements");
      System.out.print("\t" + m1);
   }
}
```

