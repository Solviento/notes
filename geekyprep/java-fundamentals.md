
Which of the following class contains a set of assert methods?
- Assert class contains a set of assert methods

Output of below:
``` java
class Test{
  public static void main(String... args){
    if (0){
      System.out.println("False");
    }
    else
      System.out.println("True");
  }
}
```
- Output is compiler error, 0 IS NOT TREATED AS FALSE IN JAVA, if() ALWAYS require a boolean

Output of below:
``` java
if("Welcome ".trim().intern() == "Welcome".trim().intern()){
  System.out.println("Equal");
}
else
  System.out.println("Not equal");
```
- trim() will remove leading and trailing whitespace, intern() will force the string "Welcome" to be stored only once in memory
- Output is Equal

Output of below:
``` java
public class EqualTest { 
  public static void main(String[] args) { 
    String s1="YES"; 
    String s2="YES"; 
    if(s1==s2) 
      System.out.println("equal"); 

    String s3= new String ("YES"); 
    String s4= new String("YES"); 
    if(s3==s4) 
      System.out.println("s3 eq s4"); 
    } 
} 
```
- String strLiteral = "String";
- String strObject = new String("String");
  - Creating String objects with new will always create a new object in heap memory
  - Creating String literals will return an existing object from String pool if it already exists
  
The command javac is used to
- compile a java program

What is the package that contains the java library Calendar class?
- java.util

Which are of type Strings?
- "0" and "hello"+"world"
- "geek"-"points" is NOT VALID

Which keywords makes a member accessible only from within its own class?
- private
  - keyword makes a member accessible only from within its own class
- public
  - keyword means that all other classes regardless of the package they belong to, can access the member
- final
  - makes it impossible to extenda class, when applied to a method it prevents it from being overridden in a subclass, when applied to a variable it makes it impossible to reinitialize once it has been initialized
- protected
  - makes member accessible only to classes in the same package or subclass of the class

Which of the following is a keyword in java?
- assert

Which is a valid declaration of a string?
- String s = null; is valid
- String s = 'null'; is not valid, cannot use single quotes

Output of below:
``` java
System.out.println("Hi");;
```
- Prints Hi, the second ; is considered a blank line of code by java compiler

Which programming language is JVM written in?
- C/C++

What will be the output of this code?		
``` java
public class test {

	public static void main(String[] args) {
		String str = "hi there you";
		str = str.replace("him", "her").replace("you", "him");
		System.out.println(str);
	}
}
```
- Order of execution goes from left to right, will print hi there him

What will be the output? (Assume all imports included)
``` java
Class Test
{
   public static void main(String[] args)
   {
      if(!true)
      System.out.println("False");
      else
      System.out.println("True");
   }
}
```
- Prints out false, !true evaulates to false which does not trigger if statement

What is a full form of WAR which is a type of file deployed on a server?
- Web archive
- Web Archive contains servlet class files, jsp files, gid and html files as JAR 

How to explicitly drop an object reference by setting the value of a variable to
- null
- Assigning the reference to null will make the object point to nothing

What is the output?
``` java
class Ideone 
{ 
  public static void method(){
    System.out.print("hi");
    method();
  }
  public static void main(String[] args){
    Ideone.method();
  }
}
```
- Would generate infinite hi's and cause stack overflow since method is a static method and can be called using the class name


Which of the following loops will execute the body of loop even when condition controlling the loop is initially false?
- do while loop
  - do while will execute the body of loop even when condition is initially false since it checks after the execution of the loop once

What is the output of the given program?(Assume all imports included)
``` java
public class Test {

    static {
        System.out.print("Static Block ");
    }
    public static void main(String[] args) {
        
        System.out.print("Main Starts ");
        Test t = new Test();
        System.out.print("Main Ends ");

    }
}
```
- static block main starts main ends
- in built static methods called first

Output?
``` java
Class Test{
   public static void main(String []args){   
    int sum=0;
    int a[]={1,2,3,5,8,13,21,34,55};
    for(int i=0;i<5;i++){
      sum+=a[a[i]];
    }
    System.out.println(sum);
  }
}
```
- a[1] + a[2] + a[3] + a[5] + a[8]
- 2 + 3 + 5 + 13 + 55 = 78

Which is not a Java reserved keyword?
- args

Output?
``` java
class AA
{
  public static void main(String args[]){
    String st="geekyprep";
    String x=st.substring(6);
    String y=st.substring(0,3);
    System.out.print(x + " ");
    System.out.print(y);
  }
}
```
- x = rep
- y = gee

What is the error here?
byte b = 50;
b = b*50;
- * operator converts expression contain int, bytes, shorts, into int which is then returned as type int, needs to be explicitly cast as byte

What is the default value of boolean in java?
- false

Which data type is not considered as signed data types?
- everything but booleans and chars are considered signed data types

Which is a valid keyword in java?
- interface
  - String, Integer are a class type in Java not a keyword

What is the output of following Java code?(Assume all imports included)
``` java
class Test
{
    public static void main(String[ ] args)
     {
         while(5)
         {
           System.out.println("Geekyprep");
         }
     }
}
```
- Compile time error, in place of boolean you cannot have int
- int cannot be converted to boolean in java

Why are chars the size of 2 bytes in java?
- Characters are UNICODE encoded

Which data type value is returned by all transcendental and trig math functions?
- Double type is returned since it has the highest range and precision

Which is the method to know the class associated with an object?
- getClass()

The correct order of the declarations in a java program is
- package declaration, import statement, class declaration

Output?
``` java
class ide{
  public void method(){
    System.out.println("hi");
    method();
  }
  public static void main(String... args){
    ide id = new ide();
    id.method();
  }
}
```
- infinite "hi"s and cause stack overflow

Which one will declare an array and initialize it with five numbers?
- int[] a = [21, 22, 32, 44, 35];

Which of the following is a keyword in java?
- short, long

Which annotation along with expected attribute can be used to test the code whether it throws desired exception or not?
- @Test
- this annotation is used to test the code

Which package contains java library class date?
- java.util

Which is a namespace for organizing classes and interfaces in a logical manner?
- Package

