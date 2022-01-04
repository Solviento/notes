**Module Objectives**

- Use and apply the correct structure and syntax of Java applications
- Create Java programs and components in packages
- Explain the role of access modifiers and apply them to code
- Use all the Java flow control loops and statements

**Classes**

- Syntax:

``` java
public class ClassName {
	// variables and methods
}
```

- Example:

``` java
public class User {
	String username = "myUsername";
	Password passwd = new Password();
}
```

**Methods**

- Syntax:

``` java
ReturnType methodName(Parameters parameters) {
		// logic
		return ReturnType;
}
```

- Example:

``` java
String getUsername() {
	return username;
}

void setUsername(String username) {
	this.username = username;
}
```

**Packages**

- A namespace is an area where each item must have a unique name.
- In Java a package is a namespace for Java files. They also appear on disk as folders.
- This prevents class/interface from having the same name in the same namespace. If they are located in a separate namespace, then they can have a duplicate name.

``` java
// valid
com.fdmgroup.mypackage.MyClass.java
com.fdmgroup.mypackage.MyOtherClass.java
com.fdmgroup.myOtherpackage.MyClass.java
```

``` java
// invalid
com.fdmgroup.mypackage.MyClass.java			// bad
com.fdmgroup.mypackage.MyOtherClass.java
Com.fdmgroup.mypackage.MyClass.java			// bad
```

**Packages and Import Statements**

``` java
package com.fdm.project;

import com.fdm.userSystem.*;

public class TradingPlatform {
	...
}
```

- The package declaration must be the first line of the file.
- Any import statements must go directly after it.

- Explain that each . Represents a new sub folder.

- Discuss what we mean by “fully-qualified class name”

**Access Modifiers**

- Visibility of a method/variable of Class A from other classes

<img src="../PHOTOS/syntax-1.png">

- **Public** members of class A are visible from all other classes

<img src="../PHOTOS/syntax-02.png">

- **Protected** members of class A can be seen from:
  - all classes in the same package as A
  - subclasses of A in other packages

<img src="../PHOTOS/syntax-03.png">

- Protected only works through inheritance.

- Ex.

``` java
package one;

public class A {
	protected int x;
}
// ---------------------
package two;

public class B extends A {

	void method(){

		this.x = 3; 	// OK, visible

		A a = new A(); 
		a.x = 3;   	// Will not compile, not visible.

	}	
}
```

- The **default**, package-private, or package access level:
  - Any member of class A without a modifier can be seen from all classes in the same package as A.

<img src="../PHOTOS/syntax-04.png">

- **Private** members of class A can not be seen from any other class

**Modifiers**

| **Field Modifiers** | **Method Modifiers** | **Class** **Modifiers** |
| ------------------- | -------------------- | ----------------------- |
| **static**          | **static**           | **static**              |
| **final**           | **final**            | **final**               |
|                     | **abstract**         | **abstract**            |
|                     | **synchronized**     |                         |
| **volatile**        |                      |                         |
| **transient**       |                      |                         |
|                     | **strictfp**         | **strictfp**            |
|                     | **native**           |                         |
|                     | **default**          |                         |

**Final**

- The final modifier prevents extension:
- Applied to variables
  - The variable may not be assigned a new value

- Applied to methods
  - The method may not be overridden

- Applied to classes
  - The class may not be extended

**Static**

- The static modifier denotes that a method or variable belongs to the class, rather than an instance of a class.

``` java
public class BritishTelephone {
	private String phoneNumber;

public BritishTelephone(String phoneNumber){
	this.phoneNumber = phoneNumber;
}
public static String getCountryCode() {
	return "+44";
}
public String getNumber() {
	return phoneNumber;
}
} 
```

``` java
BritishTelephone.getCountryCode();		// valid

BritishTelephone.getNumber();			// invalid

BritishTelephone myTelephone = 
    new BritishTelephone("021367896654");

myTelephone.getCountryCode();			// not advised

myTelephone.getNumber();				// valid
```

**Flow Control**

- If statement
- Switch statement
- Ternary operator
- While loop
- Do while loop
- For loop
- For each (enhanced for)

**If Statement**

``` java
if( number%3 == 0 ) {
	System.out.println("Fizz");

} else if ( number%5 == 0) {
	System.out.println("Buzz");

} else {
	System.out.println(number);
}
```

**If and Switch Statements**

``` java
if(guess == 1 || guess == 2){
	System.out.println("too low");
}
else if(guess == 3) {
	System.out.println("correct");
}
else {
	System.out.println("too high");
}
```

``` java
switch(guess){
case 1: //fall-through cases
case 2:
	System.out.println("too low");
	break;
case 3: 
	System.out.println("correct");
	break;
default :
	System.out.println("too high");
}
```

**Ternary Operator** 

``` java
if(a > b) {
	result = a; 
} else {
	result = b;
}
```

``` java
result = (a > b) ? a : b;
```

**Loops: While and For**

``` java
int counter = 0;
while(counter <= 10) {
    System.out.println("Counter value is: " + counter);
    counter++;
}
```

``` java
for(int counter = 0 ; counter <= 10 ; counter++) {
    System.out.println("Counter value is: " + counter);
}
```

**Loops: Do While**

- Unlike the while loop, do while will execute the do block at least once.

``` java**Loops:
int counter = 0;
do {
    System.out.println("Counter value is: " + counter);
    counter++;
} while (counter <= 10);
```