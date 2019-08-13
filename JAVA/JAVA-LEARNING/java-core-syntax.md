Object Oriented Development
Core Java Syntax Walkthrough 

What does this walkthrough cover?
This walkthrough will introduce you to the Java language and cover the basics of Java syntax.
How long will the walkthrough take to complete?
3-4 hours
What should you have already completed?
All foundation training on the technical stream
Javascript Codecademy course and associated exercise
What do you need?
In order to complete this tutorial exercise you will need:
•	Java Development Kit 1.6 or above
•	Notepad++
•	Eclipse IDE Kepler or above
•	Subversion
What does this walkthrough cover?
•	Introduction to language features such as variable typing rules, code compilation and execution
•	Basic “Hello World” example using packages to demonstrate the structure of a class file. 
•	Introduction to Eclipse
•	Example covering the basics of data types, objects and methods.
 
Introduction to Java Language Features
Language Typing

Java is a Strongly Statically Manifestly Typed language.

•	Strongly typed- Once a variable has been declared as a specific data type, it cannot change to another without explicit conversion.
E.g. A string cannot be treated like an array or an integer.
o	Opposite- A weakly typed language like javascript, which allows a string to be directly treated like an integer and vice versa

•	Statically Manifestly typed- Variables must be declared along with their type.
E.g. int i=10 or String name = “John”
o	Opposite- A dynamically typed language like javascript, where the type of the variable is worked out based on its context.
E.g. var i = 10 or var name = “John”

In Java therefore, when we declare a variable, we must define the type it will be and it cannot then be treated as another type without explicitly running it through a conversion method of some kind.

Compiled vs. Interpreted

Typically programming languages fall into one of two categories: Compiled or Interpreted

A compiled language is converted from its source code into machine (binary) code by a compiler, this binary code is then executed by the operating system.

E.g. C, C++, COBOL, Fortran, Haskell

An interpreted language is run from the source code directly and is translated on the fly by an interpreter.

E.g. HTML, Bash scripting, Javascript, MATLAB

|                 | **Advantages**                                               | **Disadvantages**                                            |
| --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Compiled**    | ·           Very quick to execute when ran   ·           Do not have to send source code to clients | ·           Must compile once for each target platform       |
| **Interpreted** | ·           Code can be ran on any platform with an   interpreter | ·           Slower to run as it must be interpreted   ·           Requires sending source code to clients |



Question: 	Is Java a compiled or interpreted language?
Answer: 	Java is both compiled and interpreted

Java is first compiled to an intermediate language known as Java Byte Code (JBC). This JBC is then sent out to the client machine and is executed by an interpreter known as the Java Virtual Machine (JVM).

This model give Java the advantages of both the Compiled and Interpreted approaches, it only has to be written and compiled once, and can be ran on any machine that has the JVM installed.
 
Basic “Hello World!” Example
Setup

If you have not done so already, it is worthwhile getting windows to display file extensions. This lets us easily see what type a file is and allows us to change the type if appropriate.

Start  Type “File Extensions”  Show or Hide file extensions  Untick the box for “Hide extensions for known file types”

Java Files

Java source code is written inside .java files.

We will need a place to store them, called our Workspace, which we will need to create:

C:\Users\firstname.lastname\My Documents\JavaWorkspace

Question: Why are we saving our work inside the C: drive. Why not use H:?

Answer: The H: drive is a network drive, which means every time we make changes to our code, or compile it (which will be often) we need to send data over the FDM network. Using the C: drive is significantly faster. As our work will be stored on our local machine though, we will need to use Subversion if we move machines (which we will talk more about later).

Inside the JavaWorkspace directory you should have just created:

Right-Click   New  Text Document  Call it “Hello.java” (it must have a capital “H”)  Delete the .txt extensions  Widows will ask you to confirm  Click “Yes”

Right-click “Hello.java”  Edit with Notepad++

You should now be looking at an empty document. We will write our source code in here.
 
Writing Source Code

Add the following code (notice the syntax highlighting as you type):

public class Hello {	
	public static void main(String[] args){
		System.out.println("Hello World!");
	}
}

Let’s look at each line in turn:

1.	The first line declares that this is a class which is like a blueprint for an object. It should be accessible from any part of our program, so it is also public. Finally its name is Hello. This name must exactly match the name of the file (including capitalisations, but excluding the extension). By convention, all java class files should start with upper case letters.

2.	The next line is declaring a method called main. It is also public, and so can be accessed from anywhere. It is defined as static which is unusual, but means we don’t need to make an object out of its class to run it (we’ll see why shortly). The void keyword says that it doesn’t return any value. Finally, the (String[] args) part means that it takes an array of strings as its input argument, which can be referred to by the name args.


3.	System.out.println(“Hello World!”); simply prints Hello World! to the command line (think of it like Java’s version of echo or console.log()


Key Concept

Main is a special method in Java- it acts as an entry point for the JVM. When we tell the Java Virtual Machine to execute our code, it will always start with main.

It is static so that the JVM can call it without needing to make an object out of its class first- main belongs to the class, not any instance.

The string array it takes is like $* in Unix systems. The array represents any command line arguments passed into our programme.

The java main method is: always public, always static, always void, always called main and always takes an array of strings. 


Compiling our Code

Before we can run our code, we need to compile it using the Java Compiler to convert it into Java Byte Code. Later, we will use an Integrated Development Environment (IDE) to do this for us. For now we will do it manually using the command line.

Click on an empty space within your JavaWorkspace folder  Hold shift and right-click  Select “Open Command Line Here” 

A command line window will pop up. The current directory should be set to our workspace. Type:

dir

dir is the Windows equivalent of Unix’s ls. 

It should show one file Hello.java

Let’s compile out source code using the Java compiler command, javac:
javac Hello.java

Type dir again, or take a look at the folder. You should see that another file has appeared called Hello.class.
Running our Code

Hello.class is our compiled Java Byte Code, which we can ask the JVM to interpret. We do this using the command Java, followed by the name of our class (name only, no extension of any kind):

java Hello
It should print Hello World! to the command line.


Packages

Great! We’re writing code. For large projects however, we will have many, many source code files to deal with (hundreds or thousands are not uncommon). As such we need to organise them.

Typically, we would organise files on an operating system into files or directories. Java is no different, but instead of calling them folders, we use the term package because they do more than just hold files.




Package conventions:

•	Packages should all have lower case letters
•	Packages should include the reverse web domain name of your employer
o	e.g. com.fdmgroup
•	Packages should contain the name of the project
•	Packages can be further sub-divided into logical grouping such as usermanagement or webcontrollers.
Create the following folder structure, starting in your JavaWorkspace folder:


com
fdmgroup
exampleProject

Now, copy your Hello.java source code file into the exampleProject folder/package. This may see like a lot of folders, but they will help us greatly as our programs get more complicated. We will also shortly be looking at a tool that helps make these folders for us.


Re-open your Hello.java file and add the following line to the top:

package com.fdmgroup.exampleProject;

This line tells our class which package it is in. This is important as it needs to know where it is compared to the other classes in the project.



Compiling and Running with Packages

Compiling and running when using packages is basically the same, but there is one difference. When we run the code from the command line, we need to be in the same folder as the root package. Our root package is com in the JavaWorkspace folder, so that’s where we need to be. 

javac com\fdmgroup\exampleProject\Hello.java
java com.fdmgroup.exampleProject.Hello

We should get the same result as previously, but now we’re thinking with packages.
 
Eclipse

We will now take the basics we have learned and apply them within an Integrated Development Environment (IDE). We will be using Eclipse, a free, very popular Java IDE that supports a range of plugins.

Open Eclipse, if it asks you to give it a workspace, direct it to the following. If it does not ask, select File  Switch Workspace  Other and direct it to the following folder:

C:\Users\firstname.lastname\My Documents\JavaWorkspace


Book Shop

Create a package called com.fdmgroup.bookShop if it does not already exist.
Book

Right-click on the package  New   Class  Call it “Book”

Add the following attributes:
•	String title
•	String author
•	String ISBC
•	double price
•	int numberOfPages
Make these attributes private, which will mean another class cannot access them directly.


Question: If another class cannot access them, then are they useful to us?
Answer: Yes, we will access them via getters and setters later on


package com.fdmgroup.bookShop;

public class Book {
	private String title;
	private String author;
	private String iSBN;
	private double price;
	private int numberOfPages;
}


Each of these attributes define some aspect of a Book that we’d like to model within our system. We now have a Book class that we can use as a blueprint to make Book objects.
 
Client

Create a class called Client in the same way we made Book.

We won’t be covering User Interfaces yet, so our client will stand in for our user for now, performing tasks within the rest of our system.

Client should have the main method:

public static void main(String[] args){
	//code
}


Recap

The main method will be how the Java Virtual Machine runs our code. It is the VMs ‘in’ or access point to our system and will always be the first method within our application to be ran.

It is static as it needs to be ran without having to instantiate its surrounding class (more on instantiation shortly). The String array it accepts is similar to $* in Unix, in that, if we were running it from the command line, we could pass in arguments and then reference them using this array.

It is void as it will never return anything.

Variable Declaration

Inside your main method, create a primitive variable of type int, named pages, with the value 100:

int pages = 100;

All variable declaration in Java follows this same basic structure of:

dataType variableName = variableValue;

Have a look at the other primitives available in Java and consider their precisions (how large a number they can store):

https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html




Primitives

Primitive data types are raw data. They have no behaviour.

Note that if you do not assign a value to a primitive, it will default to 0 (zero). This is true for all primitives apart from boolean, which defaults to false.

Primitive types all start with lower case letters.

Objects

The other data type that Java contains are Objects. Anything that is not a primitive is an object (including String).

Objects contain both attributes and behaviour, otherwise known as variables and methods.

Variables within an object can be primitive types, or other object types. Methods are like functions that you will have seen in other languages.

Objects default to null. Null in Java means empty: there is no object in the reserved memory.

All object in Java exist within a hierarchy (more on this later). At the top of the hierarchy is the Object object which every other object inherits from either directly or indirectly via another parent:

http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html


Instantiation

Let’s create an object variable.

Book book1 = new Book();
        
Just like before, we have data type and variable name on the left hand side of the equals sign and the value on the right. The difference here is that the value is an object, which is more complicated than a primitive. As such Java needs to build an object for us. It does this using the object’s constructor.










Constructors

Constructors are like special methods with some specific rules governing them:

•	They must have the same name as the class they are declared in
•	They will never declare a return type (they implicitly return a new object  of that class)
•	They can take parameters just like methods
•	If we do not define a constructor, Java will add one for us when it compiles our code, known as the default constructor

Let’s add a default constructor inside Book:

public Book(){
		
	}

This constructor is identical to the one Java adds for us, so our programme will work the same way whether it is there or not. Later we will look at using custom constructors in more useful ways.
 
Object Manipulation

Inside Client, we have an object called book, which is an instance of the Book class, but we are not doing anything with it.

Add the following code to your main method:

book.numberOfPages = 100;

Our code gives us a compilation error. Why?

Because numberOfPages is private, we cannot access it directly. This gives us control over how it is used (we could stop, for example, someone from setting a book with a negative number of pages).


Let’s add a setter to Book:


public void setNumberOfPages(int numberOfPages) {
		this.numberOfPages = numberOfPages;
}


Now, change the line in Client that does not compile to:


book.setNumberOfPages(100);



Questions: How could we stop someone giving our book a negative number of pages?
Questions: How can we get the number of pages a book has back again?
 
Methods

Getter and Setter methods are useful for controlling access to private variables, but they are not the only methods we will use.

Key things to remember about methods:

•	In Java, all methods should start with lower case letters (except constructors). Other languages have different best practises.
•	Camel case should be used in method names. e.g. getNumberOfPages()
•	All methods should define a return type
o	The return type can be any data type (primitive or object) OR void if the method does not return anything
•	All methods should define input parameters
o	Input parameters can be any number of any type in any order or simply left as empty brackets if the method does not take any input
•	The first line of a method that defines all this is known as a method signature and will define the methods access modifier, return type, name and input parameters in that order.
public int calculatePrice(Item item, int quantity){

•	Other modifiers may appear in a methods signature (such as static) we will cover these when they become relevant in the course.
In Java all code should appear inside a method apart from object wide attribute declarations such as title and author and numberOfPages in Book.
 
Checkout

Create another class called Checkout, as before.

Define a method called calculatePrice inside Checkout as so:

public double calculatePrice(Book[] books){
    		return 0.0;
    }

Our method will take an array of Book objects and work out their total price. We are returning 0.0 just to make it compile for now.

Question: What is an array?


Making the Books

Inside the main method of Client, create 3 Books with the following data:

| Object Name | Title                        | Author   | ISBN       | Price | NumberOfPages |
| ----------- | ---------------------------- | -------- | ---------- | ----- | ------------- |
| book1       | “Head First Java”            | O’Reilly | 0596009208 | 25.99 | 720           |
| book2       | “Java: How to Program”       | Deitel   | 0131364839 | 29.99 | 1560          |
| book3       | “Head First Design Patterns“ | O’Reilly | 0596007124 | 22.49 | 694           |

The first one will be defined as so:

Book book1 = new Book();
     book1.setTitle("Head First Java");
     book1.setAuthor("O'Reilly");
     book1.setiSBN("0596009208");
     book1.setPrice(25.99);
     book1.setNumberOfPages(720);

Note that only the Strings are surrounded by double quotes. The double and int are defined without them

Now define the other two books.


Next, we will create an array of Book objects and add your three books to it:

        Book[] books = {book1, book2, book3};

Note that there are other ways of instantiating and populating arrays. Do some research to find out how.  Also note that arrays in Java are zero-based. The first element is at position 0, the last element is at position (array length-1).



Create an instance of Checkout inside Client

Checkout myCheckout = new Checkout();



Now, we will make a call to the calculatePrice method of checkout from client, passing it the array we have just created:

double totalPrice = checkout.calculatePrice(books);


Notice how, as calculatePrice returns a value, we are capturing it and putting it into a variable called totalPrice.


Print out totalPrice inside Client:

System.out.println("Total price of books: " + totalPrice);

This should simply print out 0.0 for now.


Our main method should now look like so (including your definitions for book2 and book3):

public static void main(String[] args)
{
     Book book1 = new Book();
     book1.setTitle("Head First Java");
     book1.setAuthor("O'Reilly");
     book1.setiSBN("0596009208");
     book1.setPrice(25.99);
     book1.setNumberOfPages(720);
        

     Book[] books = {book1, book2, book3};
        
     Checkout myCheckout = new Checkout();
double totalPrice = checkout.calculatePrice(books);
System.out.println("Total price of books: "+ totalPrice);
}
 
Adding Logic with a Loop

We need to loop through our array of book objects inside calculatePrice to get each of their prices in turn and add them to a total.

As with other languages, we have a number of options: for-loop, for-each loop, while-loop

For-Loop

double priceTotal = 0.0;
for(int i  = 0; i< books.length; i++){
	priceTotal += books[i].getPrice();
}
return priceTotal;
For-Each Loop

double priceTotal = 0;
for(Book temp: books ){
	priceTotal += temp.getPrice();
}
return priceTotal;
While Loop

double priceTotal = 0;
int i = 0;
while(i<books.length){
	priceTotal += books[i].getPrice();
	i++;
}		
return priceTotal;

All of the above methods will work, giving us the combined price of every book in the array. Choose of one the above and add it to the calculatePrice method.

Run Your Code
Execute your code by pressing ctrl-F11. When ran, the above code should output a price of 78.47.
 
Saving our Work with Subversion

Now we have written our code, we need to save it. Currently the only place it is saved is as a local copy on the C: drive of your current computer.

We will be using a source control framework called Subversion which will allow us to back up our work to remote server. This has several advantages:

1.	Our work is accessible from any machine with a connection to the internet
2.	We have a backup of our work
3.	Many people can access our code, which would allow us to collaborate as part of a team of developers all working on one project

Question: Are there any other advantages to using Source Control. Do some research into the topic.

The copies of Eclipse on the Academy computers have a plugin installed called Subclipse which allows us to natively talk to SVN.

IF YOU ARE WORKING AT HOME ONLY

If you are working at home, you may need to install Subclipse from the Eclipse market place (it does not come pre-installed):

 Help  Eclipse Marketplace…  Search tab  Find  Subclipse  Enter  Subclipse should be the top result, click install now.


To save a project to the remote server there are two steps:

Right-click your project  Team  Share Project…  SVN  Create new repository location  svn://192.168.0.2:5500/firstname.lastname  Finish

Right-click your project again  Team  Commit Add a sensible message such as “Initial Project Commit”  Ok

You have now saved a copy of your project to a remote Unix server which you can access from any machine.
Retrieving a Project Saved to SVN

To get our work back again:

File  Import…  SVN  Checkout Projects from SVN  Next  Either select or add your desired repository  Find the project you wish to download and select it  Finish