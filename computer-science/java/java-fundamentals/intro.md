**Module Objectives**

- Explain some of the different programming paradigms
- Explain different type systems
- Explain the difference between a compiled and interpreted language
- Explain the purpose of the JVM
- Explain how Java is platform independent
- Illustrate the Java delivery process
- Compile and execute Java programs in the command line

**Programming Paradigms**

- Programming paradigms are fundamental styles of programming, or the way a program achieves its goal.
- Some key paradigms are:
  - Declarative
  - Functional
  - Imperative
  - Logic
  - Object-Oriented
  - Symbolic
- A language may be designed to support one paradigm, or multiple.

**Object Oriented Programming**

- “Sending messages between objects to simulate the evolution of a set of real world events.”

- A type of Structured Programming

- The code base is made up of ‘objects’
  - Attributes
  - Behaviours

- Modularity and the ability to reuse these objects is a fundamental tenet of OOP

**Language Type Systems**

- Statically Typed 
- Data types are checked at compile time

- Dynamically Typed
  - Types are checked at runtime
  - A target may hold a binding to any kind of object
- Manifestly Typed 
  - Variable types must be declared
- Type Inferred
  - Variable types are deduced by context

- Advantages of Statically typed languages:
  Stability - means many errors are caught at compile time instead of run time. 
  Readability – you are providing information regarding how the program is supposed to run.

- Leads to:
  Better Development Tools because the IDE can tell you what this variable is supposed to do.  

- Examples:
  - Bash is weakly typed.
  - Java is strongly/manifestly typed
  - PHP is weakly/dynamically typed
    - For example, in PHP you can write
      - $count = "5"; // defines a string variable   
      - $count = $count * 2; // this is legal

- Strongly Typed
  - Types are restricted within their own bounds
  - Once assigned a value of a particular kind, the object must obey rules about how it can interact with other objects
- Weakly Typed
  - A value of one type can be treated as another

- **Java is strongly manifestly typed.**

- Variables must be declared to be of a specific data type
- Variables cannot mutate between different data types
  - E.g. Cannot treat a String as an Array

- Some form of example about these, of what can and cannot be done because of these rules

**Compiled Languages**

- Source code is written in languages such as C and C++.

- Compilers convert source code to binary machine code.

- The machine code is platform dependent – it cannot be transferred from one platform to another.

- Source Code:

``` java
int main(){
	printf(“Hello”);
}
```

- Machine Code:

``` java
01010101001001010100101010101001010111101010010100101010010101001010101010101001010101010101001010101001010101010100101010101010100001011110101010010001
```

**Interpreted Languages**

- Source code is written in languages such as Bash and SQL.

- An interpreter will read this code and perform the instructions.

- These scripts can be run on any platform where the interpreter exists.

**Compiled and Interpreted**

- **Java is *both* compiled and interpreted!**

- Java source code is compiled to Java Byte Code.
- Java Byte Code is then interpreted by the Java Virtual Machine (JVM).

**JVM**

- The Java Virtual Machine (JVM)
  - An abstract machine that interprets and executes Java Byte Code
- There are many languages for the JVM, including:
  - Java
  - Scala
  - Clojure
  - Groovy
  - Jython
  - JRuby

- Java Byte Code -> Unix, Windows, Mobile Phone, etc.

**Delivery Process** 

- Developer writes source code
- Compiler converts source code to Java Byte Code
- Java Byte Code is distributed to different clients
- JVM interprets the Java Byte Code

**Compiling and Running**

- Command line tools are:
  - javac
  - java

**Module Review**

- What are some of the different types of languages?

- What is the difference between compiled and interpreted?

- What type of language is Java?

- What is the purpose of the JVM?

- What is the core Java delivery process?

- What commands would you need to take Java source code and execute it?