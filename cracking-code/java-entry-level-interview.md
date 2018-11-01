Object Oriented Programming Concepts



What Is a Class?

A class is a blueprint or prototype from which objects are created.  



What Is Inheritance?

Inheritance provides a powerful and natural mechanism for organizing and structuring your software. Objects can relate to each other with either a “has a”, “uses a” or an “is a” relationship.  “Is a” is the inheritance way of object relationship.   



Rather than duplicate functionality, inheritance allows you to inherit functionality from another class, called a superclass or base class.



What Is an Interface?

An interface is a contract between a class and the outside world. When a class implements an interface, it promises to provide the behavior published by that interface.  



What Is a Package?

A package is a namespace for organizing classes and interfaces in a logical manner. Placing your code into packages makes large software projects easier to manage.  



Encapsulation

Encapsulation is the hiding of data implementation by restricting access to accessors and mutators.  



Accessor

An accessor is a method that is used to ask an object about itself. In OOP, these are usually in the form of properties, which have, under normal conditions, a get method, which is an accessor method. However, accessor methods are not restricted to properties and can be any public method that gives information about the state of the object.



Mutator  

Mutators are public methods that are used to modify the state of an object, while hiding the implementation of exactly how the data gets modified. Mutators are commonly another portion of the property discussed above, except this time it's the set method that lets the caller modify the member data behind the scenes.



So, the use of mutators and accessors provides many advantages. By hiding the implementation of our Person class, we can make changes to the Person class without the worry that we are going to break other code that is using and calling the Person class for information.  



If we wanted, we could change the fullName from a String to an array of single characters (FYI, this is what a string object actually is behind the scenes) but they callers would never have to know because we would still return them a single FullName string, but behind the scenes we are dealing with a character array instead of a string object. Its transparent to the rest of the program.  



This type of data protection and implementation protection is called Encapsulation. Think of accessors and mutators as the pieces that surround the data that forms the class.



Abstraction

Data abstraction and encapsulation are closely tied together, because a simple definition of data abstraction is the development of classes, objects, types in terms of their interfaces and functionality, instead of their implementation details. Abstraction is essential in the construction of programs. It places the emphasis on what an object is or does rather than how it is represented or how it works. Thus, it is the primary means of managing complexity in large programs.



Abstraction is used to manage complexity. Software developers use abstraction to decompose complex systems into smaller components. As development progresses, programmers know the functionality they can expect from as yet undeveloped subsystems. Thus, programmers are not burdened by considering the ways in which the implementation of later subsystems will affect the design of earlier development.



Polymorphism

Polymorphism means one name, many forms. Polymorphism manifests itself by having multiple methods all with the same name, but slightly different functionality.  



There are 2 basic types of polymorphism. Overriding, also called run-time polymorphism, and overloading, which is referred to as compile-time polymorphism. The difference is, for method overloading, the compiler determines which method will be executed, and this decision is made when the code gets compiled. Which method will be used for method overriding is determined at runtime based on the dynamic type of an object.



Collections in java is a framework that provides an architecture to store and manipulate the group of objects. All the operations that you perform on a data such as searching, sorting, insertion, manipulation, deletion etc. can be performed by Java Collections. Java Collection simply means a single unit of objects. Java Collection framework provides many interfaces (Set, List, Queue, Deque etc.) and classes (ArrayList, Vector, LinkedList, PriorityQueue, HashSet, LinkedHashSet, TreeSet etc).

hierarchy of collection framework






Questions



Where do you see yourself in 5 years?

I see myself working as a software developer for a large tech company. It's my hope to find projects that I can make a lasting impact on.



Why do you want to work for Revature?

Revature offers a no-risk pathway to a high in demand job in the tech industry. With Revature, I can gain the hands-on experience I need to become a successful software engineer.  



Can you relocate?

Yes, I can relocate on the East Coast.



What are the four pillars of OOP and how they are used in Java?

Abstraction, Encapsulation, Polymorphism, Inheritance. Abstraction refers to showing only the essential features of the application and hiding the details, they provide methods to the outside world to access and use the data variables but they themselves are hidden from direct access. Inheritance is away to reuse code - the class which is inherited from is called the base class, the class that inherits the code from a base class is called a derived class. Encapsulation is about binding data variables and functions together in a class. Polymorphism is feature that lets us create functions with the same name but different arguments.



What is OOP?

Object Oriented Programming is programming based on the concept of objects (which may contain data) in the form of fields, also known as attributes.



What is JVM, JRE?

Java Virtual Machine, Java Runtime Environment.



Do you know SQL?

Very basic SQL.



How do you select all tables from a database?

SELECT * FROM DATABASE.INFORMATIONSCHEMA.TABLES WHERE TABLE_TYPE='BASENAME'



What is inner join?

Inner join selects all rows from both participating tables as long as there is a match between the columns.  



What does it mean to have a "Normalized" database?

Basically limits a table to one purpose which reduces the number of duplicate data stored within the database.  



Difference between union and unionall?

UNION is used to select related information from two tables, all selected columns NEED to be of the same type - with UNION only distinct values are selected. UNION ALL is like UNION but that it selects all values (will not eliminate duplicate values).



What are SQL primary vs foreign keys?

Primary key cannot have a null value, tables can only have one primary key. Foreign keys is a field in the table that is a primary key in another table, they can also accept multiple null values, more than one foreign key can exist in a table.



What is the difference between WHERE and HAVING in SQL?  

The main difference between WHERE and HAVING is that WHERE is used while fetching data from a table while HAVING is used to filter summarized data or grouped data.



How do you select all the data in one table using SQL?  

SELECT * FROM table_name



How do you select all rows from a table?



Give a simple CRUD query

Select name

Insert name

Update name

Delete name



Describe how to write a query to find all students in a database where their average final score was more than 70 points

SELECT student_id FROM data_table WHERE final_score > 70



What is a Wrapper Class?

A wrapper class is a class whose object wraps or contains a primitive data type.  

Primitive

Wrapper

char

Character  

byte

Byte

short

Short

float

Float



What are the differences between an Interface and Abstract Class?

Interface contains only signatures of methods, the interface itself cannot do anything. An abstract class looks like an interface but you can define a behavior for them.



What are the different access modifiers?

Default, private, protected, public.

access-modifiers-in-java


What does the static keyword do?

Static members belong to the class instead of a specific instance. It means that only one instance of a static field exists. It will be shared by all instances of the class.  



What are the uses of the final keyword?

final keyword in java


What is the difference between Set and Map?   

A set list does not allow duplicates, while a map doesn't allow duplicate keys but can have duplicate values.



What is difference between array and Array List?

Arrays are simple fixed sized arrays while ArrayLists are dynamic sized arrays in Java.



Asked to implement some simple methods in a language of your choices. Generating prime numbers, generating an alternating sequence of numbers, reverse a number.

// basic prime checker

for (int I = 2; I < n/2; i++){

    if (n % i == 0) {

        return false;

    }

}

return true;



// alternate sequence of numbers

int number = 1;

for (int i = 0; i < some_num; i++){

    number = number + i;

    System.out.print(number);

}  



// reversing anumber

while (num!=0){

    // retrieve last number using % and ensure while loop will terminate using *

    rev = rev*10 + num%10;  

    // remove last number using /

    num /= 10;

}

return num;



Write a program which answers whether or not the input string is a palindrome?



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



}



What is an exception, and how do you use them?

Exceptions are events that occur during the execution of programs that disrupt the normal flow of instructions. In Java, exceptions are objects that wrap around an error event that includes information of the error and state of the program. Examples include FileNotFoundException, IOException.  



try {

// code block

}

catch (ExceptionType1 e1){

// handle exceptiontype1 exceptions

}

catch …



finally {  

// code executed after the try and catch blocks

}



private int divideInts(int i1, int i2){

try{

return i1/i2;

}

catch(ArithmeticException e){

return 0;

}

}



Type an Object declaration?

NewExample obj = new NewExample();



What is Method Overloading?

Overriding is when two or more methods in one class have the same method name but different parameters.

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



What is Method Overriding?

Means having two methods with the same method name and parameters. One method is in the parent class while the other is in the child class.

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



Why does Java not allow multiple inheritance?   

// Error : Test is inheriting from multiple


// classes


class Test extends Parent1, Parent2

{

   public static void main(String args[])

   {

       Test t = new Test();

       t.fun();

   }

}

Multiple inheritance is not supported by Java using classes, due to that multiple inheritance is very complex. It creates problems during various operations like casting, constructor chaining, few instances where we actually need it, so better to omit it for keeping the things simple and straightforward.



Provide a real-world example of when it might be necessary to use the modulus operator.   

Checking whether a number is even, or do something every nth time (using for loop but using every cycle)



Do you know what multi-threading is? How do you do it (how would you start/stop a thread)?

Multithreading in java is a process of executing multiple threads simultaneously. Thread is basically a lightweight sub-process, a smallest unit of processing. Multithreading is used to achieve multitasking.
