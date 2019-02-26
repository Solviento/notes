# Object Oriented Programming Concepts

What Is a Class?
- A class is a blueprint or prototype from which objects are created.  

What Is Inheritance?
- Inheritance provides a powerful and natural mechanism for organizing and structuring your software. Objects can relate to each other with either a “has a”, “uses a” or an “is a” relationship.  “Is a” is the inheritance way of object relationship.   
- Rather than duplicate functionality, inheritance allows you to inherit functionality from another class, called a superclass or base class.

**Interfaces**

What Is an **Interface**?
- An interface is a contract between a class and the outside world. When a class implements an interface, it promises to provide the behavior published by that interface.  
- An interface is also a reference type, similar to classes but has a collection of abstract methods
  - Interfaces may also contain constants, default methods, static methods and nested types
  - The class that implements the interface must define all methods in the interface UNLESS the class is abstract
- Interfaces can contain methods, are written with .java extension and also appear in packages
- Interfaces CANNOT be instantiated, DO NOT contain constructors, ALL methods of interface are abstract, fields in an interface MUST BE static and final, an interface is NOT extended by a class, interface CAN extend multiple interfaces

Declaring Interfaces

- **interface** keyword must be used

``` java 
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

``` java
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

``` java
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

Multiple Interface declaration

```java 
public interface hockey extends sports, event{...}
```

**Exceptions**

- Types of exceptions
  - Checked exceptions
    - Must be handled explicitly using **throw** 
  - Unchecked exceptions
    - Errors and **RuntimeExceptions** are unchecked, not always handled explicitly
    - Index out of bounds exception
  - Errors
    - Errors are problems that arise beyond control of user such as Stack Overflow

Throws/Throw

- **throws** keyword tells compiler that your function MIGHT throw an exception and this should be caught by the compiler
- **throw** keyword will throw an exception that is instantiated or one that is caught in the code

``` java
import java.io.*;

public class name{
    public void deposit(double amount) throws RemoteException{
        // method implementation
        throw new RemoteException();
    }
}
```

**Try/Catch**

``` java
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

``` java
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



What Is a Package?

- A package is a namespace for organizing classes and interfaces in a logical manner. Placing your code into packages makes large software projects easier to manage.  

Encapsulation
- Encapsulation is the hiding of data implementation by restricting access to accessors and mutators.  

Accessor
- An accessor is a method that is used to ask an object about itself. In OOP, these are usually in the form of properties, which have, under normal conditions, a get method, which is an accessor method. However, accessor methods are not restricted to properties and can be any public method that gives information about the state of the object.

Mutator  
- Mutators are public methods that are used to modify the state of an object, while hiding the implementation of exactly how the data gets modified. Mutators are commonly another portion of the property discussed above, except this time it's the set method that lets the caller modify the member data behind the scenes.
- So, the use of mutators and accessors provides many advantages. By hiding the implementation of our Person class, we can make changes to the Person class without the worry that we are going to break other code that is using and calling the Person class for information.  
- If we wanted, we could change the fullName from a String to an array of single characters (FYI, this is what a string object actually is behind the scenes) but they callers would never have to know because we would still return them a single FullName string, but behind the scenes we are dealing with a character array instead of a string object. Its transparent to the rest of the program.  
- This type of data protection and implementation protection is called Encapsulation. Think of accessors and mutators as the pieces that surround the data that forms the class.

Abstraction
- Data abstraction and encapsulation are closely tied together, because a simple definition of data abstraction is the development of classes, objects, types in terms of their interfaces and functionality, instead of their implementation details. Abstraction is essential in the construction of programs. It places the emphasis on what an object is or does rather than how it is represented or how it works. Thus, it is the primary means of managing complexity in large programs.
- Abstraction is used to manage complexity. Software developers use abstraction to decompose complex systems into smaller components. As development progresses, programmers know the functionality they can expect from as yet undeveloped subsystems. Thus, programmers are not burdened by considering the ways in which the implementation of later subsystems will affect the design of earlier development.

Polymorphism
- Polymorphism means one name, many forms. Polymorphism manifests itself by having multiple methods all with the same name, but slightly different functionality.  
- There are 2 basic types of polymorphism. Overriding, also called run-time polymorphism, and overloading, which is referred to as compile-time polymorphism. The difference is, for method overloading, the compiler determines which method will be executed, and this decision is made when the code gets compiled. Which method will be used for method overriding is determined at runtime based on the dynamic type of an object.

Collections
- Collections in java is a framework that provides an architecture to store and manipulate the group of objects. All the operations that you perform on a data such as searching, sorting, insertion, manipulation, deletion etc. can be performed by Java Collections. Java Collection simply means a single unit of objects. Java Collection framework provides many interfaces (Set, List, Queue, Deque etc.) and classes (ArrayList, Vector, LinkedList, PriorityQueue, HashSet, LinkedHashSet, TreeSet etc).

What are the four pillars of OOP and how they are used in Java?
- Abstraction, Encapsulation, Polymorphism, Inheritance. Abstraction refers to showing only the essential features of the application and hiding the details, they provide methods to the outside world to access and use the data variables but they themselves are hidden from direct access. Inheritance is away to reuse code - the class which is inherited from is called the base class, the class that inherits the code from a base class is called a derived class. Encapsulation is about binding data variables and functions together in a class. Polymorphism is feature that lets us create functions with the same name but different arguments.

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

What is difference between array and Array List?
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

