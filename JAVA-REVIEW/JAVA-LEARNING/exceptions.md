**Exceptions**

```
What is an exception?
```

- An event that disrupts the normal flow of instructions during the execution of a program:
  - Files not available
  - Network connection lost
  - Database server restarted

<img src="../PHOTOS/exceptions-01.png" width=650px>

**Searching For An Exception Handler**

- The runtime system searches the call stack for a block of code that can handle the exception.

<img src="../PHOTOS/exceptions-02.png" height=300px>

- If the topmost method doesn’t ‘handle’ the exception, it is popped off the stack – even if the method has not finished executing! 

**Purpose**

```
Why are Exceptions useful?
```

- Can recover from issues without crashing the application
- Can recover from circumstances outside the code’s control 
- If every method is popped off the stack, the program crashes!
- Instead, we can specify which method will handle the problem. 

**Hierarchy**

<img src="../PHOTOS/exceptions-03.png" height=350px>

<img src="../PHOTOS/exceptions-04.png" height=300px>

**Errors**

- Errors indicate a serious problem:
  - External to the program
  - Cannot be recovered from
  - Should not be caught
- For example, if you are unable to read from a file due to a hardware malfunction, a java.io.OutOfMemoryError will be thrown. 

**Checked and Unchecked Exceptions**

Exceptions fall into two categories:
Checked  Exceptions
Caused by situations outside the code’s control
Unchecked  Exceptions (RuntimeExceptions)
Caused by bugs and errors in the code

**Checked Exceptions**

- Checked Exceptions indicate a problem external to the program:
  - Able to be recovered from
  - Should be anticipated and handled
- The Catch or Specify Requirement (‘Handle or Declare Rule’) states:
  - Code that might throw a Checked exception will not compile unless:
    - It is in a try-catch block, or
    - The method declares that it might throw the exception

- This code will not compile because the PrintWriter constructor declares that it might throw a FileNotFoundException:

``` java
void myMethod() {
	PrintWriter pw = new PrintWriter("NonExistant.File");
}
```

**Unchecked Exceptions**

- Unchecked Exceptions indicate a problem internal to the program:
  - Caused by lack of defensive coding
  - Caused by improper use of the API
  - Should not be caught
- This code compiles, but throws an ArrayIndexOutOfBoundsException at runtime:

``` java
void myMethod() {
	String[] stringArray = new String[5];
  	stringArray[5] = "oops";
}         			 
```

**Handling Unchecked Exceptions**

- Unchecked Exceptions are caused by errors and bugs in code
- Prevent them by implementing adequate if statements:
  - When casting check that the variable is the correct datatype
    - ClassCastException
  - When passed a reference variable as input check if it is null
    - NullPointerException
  - When doing division check that the divisor is not 0
    - ArithmeticException
  - When accessing arrays, check that the index is within the array bounds
    - IndexOutOfBoundsException
- This is called **defensive coding**.

**Handling Checked Exceptions 
Option 1 - Specify**

``` java
public void myMethod() throws FileNotFoundException {
  PrintWriter pw = new PrintWriter(“NonExistant.File”);
}
```

- We declare that myMethod() itself *might* throw this exception.

**Handling Checked Exceptions
Option 2 - Catch**

``` java
public void myMethod() {
     PrintWriter pw = null;
  try {
     pw = new PrintWriter(“NonExistant.File”);
  } catch (FileNotFoundException e) {
     log.warn(e); 		// Do something to recover
  } finally {
     pw.close();      // Code to clean up resources
  }
}
```

**Catch Blocks**

- A try must be immediately followed by a catch or finally block
- A try can have multiple catch blocks
  - Allows us to handle different exceptions in different ways
  - An exception will be caught by the first matching catch  block
  - Catch blocks must be ordered from most to least specific
  - Catching ‘Exception’ is considered bad practice as it will catch everything in the hierarchy. Do not do this!

**Finally Blocks**

- The finally will almost always execute when the try block exits, whether it exits normally or an exception is thrown
  - Used to clean up resources
- The finally will not execute if:
  - The JVM exits whilst in the try or catch
  - The thread is interrupted or killed

**Handling Checked Exceptions
Option 3 – Try-with-resources**

``` java

public void myMethod(){
 try (PrintWriter pw = new PrintWriter(“NonExistant.File”)) {
     pw.write(“Some text”);
  } catch (FileNotFoundException e) {
     log.warn(e); 		// Do something to recover
  }	
	// No need to close PrintWriter as the try with 	    	// resources block with automically call close()
}
```

**Try-With-Resources**

- Updates try-catch handling to automatically close resources
- Objects that implement AutoCloseable interface can be passed almost as argument to the try block
- Eliminates the need for many finally blocks and exception handling within finally blocks
- Objects remain scoped to the try.
- Can set up multiple resources, semi-colon separated

**Catch Multiple Exceptions**

- One catch block can handle multiple exceptions
- Can still have separate catch blocks, each with multiple exceptions
- Simplify exception handling
- Use pipe (|) similar to logical ‘OR’
- The Exception types being handled cannot subclass one another

``` java
public abstract int divide(String a, String b) throws 		NumberFormatException, ArithmeticException;

public void DoDiv() {
	String a = "1";
	String b = "0";
	try {
		divide(a, b);
	} catch (NumberFormatException | ArithmeticException ex) {
		System.out.println("Exception on the numeric operations in divide()");
	} catch (IllegalArgumentException ex) { //Bad practice
		System.out.println("Something else went wrong");
	}
}
```

**Custom Exceptions**

- To create your own exception, create a class that:
  - Extends Exception for a checked exception
  - Extends RuntimeException for an unchecked exception
  - Typically includes constructors that take in a message and/or a cause.

``` java
public class MyException extends Exception {
    public MyException(String message) {
        super(message);
    }
    public MyException(String message,
                       Throwable cause) {
        super(message, cause);
    }
}
```

**Throwing Exceptions**

- Any code can throw an exception – your code, code written by someone else, or the environment. 
- This is done with the **throw** keyword, when something goes wrong.

``` java
public void myMethod() throws MyException {
    if(some condition) {
        throw new MyException("message");
    }
}
```

- Do not throw an exception without a conditional!

**Do Not Do This!**

- Do not throw and catch the same exception in the same method!

``` java
 public void myMethod(){

    try {
       throw new MyException("message");
    }
    catch (MyException e) {
       // Do something to recover
       log.warn(e);
    }

 }
```

**Module Review**

- What is the purpose of exceptions?
- What are the two main types of Exception?
- What are Errors?
- Which type of Exception should you not handle?
- How do you handle exceptions?
- How can you create your own Exception?