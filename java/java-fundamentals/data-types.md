**Module Objectives**

- List all primitive types and their sizes
- Create custom classes and instantiate them
- Describe how Strings are handled in memory
- Describe the difference between StringBuilder and StringBuffer
- Use array and enum syntax effectively
- Use utility classes such as wrappers, BigDecimal, Math, and LocalDate

**Primitive Types**

- Primitive variables hold literal values
- Manipulated using Java operators (not methods) 
  - +, -, *, /, &&, ||, etc. 

``` java
int firstInt = 6078;
int secondInt = 5;
```

| **Type**    | **Size**  | **Default Value** | **Range**                                                    |
| ----------- | --------- | ----------------- | ------------------------------------------------------------ |
| **byte**    | 8 bit     | 0                 | -128 to 127                                                  |
| **short**   | 16 bit    | 0                 | -32,768 to 32,767                                            |
| **int**     | 32 bit    | 0                 | -2,147,483,648 to 2,147,483,647                              |
| **long**    | 64 bit    | 0L                | -9,223,372,036,854,775,808 to   9,223,372,036,854,775,807    |
| **float**   | 32 bit    | 0.0F              | (+-) 1.40129846432481707e-45 to    (+-) 3.40282346638528860e+38 |
| **double**  | 64 bit    | 0.0               | (+-) 4.94065645841246544e-324 to       (+-) 1.79769313486231570e+308 |
| **boolean** | undefined | false             | false to   true                                              |
| **char**    | 16 bit    | '\u0000'          | '\u0000' to '\uffff'                                         |

**Numeric Literals**

- Numeric types can be formatted with _ (underscore) to improve readability; 
  - Underscore is ignored
  - Cannot be used as first or last char of numeric literal, or either side of decimal point

``` java
long underscoredLiteral = 2_000_000_000_000;
double underscoredDouble = 1_234.567_8901;
```

**Reference Types - Classes**

- Contain both data (attributes) and behaviours (methods)
- Provide blueprints which can be instantiated later
- Allow you to create custom data types
- Many classes are built into the Java library

``` java
public class Book {	String title;
	double price;

	public String getDetails() { 
		return title + " " + price;
	}
}
```

**Reference Types - Objects**

- Objects are instances of classes.
  - Created using the ‘new’ keyword
  - Everything extends the Object class

``` java
Book myBook = new Book();
Object myObject = new Object();
 
myBook.getDetails();
```

**Strings**

- Strings are reference types, not primitives.
  - Content is compared using .equals() method, not ==
- Strings are immutable.
  - i.e. Do not change
- String literals are stored in their own area in memory – the String pool.
  - Will be re-used wherever possible
- Concatenation is expensive – a new String is always created.

``` java
String firstString = “Hello”;
String secondString = “Hello”;

firstString == secondString					// true
firstString.equals(secondString)			// true

String firstString = new String(“Hello”);
String secondString = new String(“Hello”);

firstString == secondString					// false
firstString.equals(secondString)			// true
```

- StringBuffer
  - Thread safe but slower
- StringBuilder
  - Not Thread safe but faster
- Both allocate an initial size or capacity.
  - Size changes as additional strings are appended

- String manipulation results in a lot of garbage collection.
  StringBuilder and StringBuffer manipulation does not.

**Arrays**

- Arrays are a way of containing multiple variables of the same type.
  - Arrays are reference types
  - Syntax uses square brackets
  - Indexed – indices start from zero
  - Immutable in size

``` java
int[] numbers = new int[3];
numbers[0] = 25;
numbers[1] = -1;

System.out.println(numbers[0]);
System.out.println(numbers[1]);
System.out.println(numbers[2]);
```

**Enums**

- Enum types are a way to represent a set of predefined values.
  - Essentially constants, represented in a readable and meaningful way
  - Act as a flag which can be read and utilised
  - More efficient than using Strings

``` java
public enum TrafficLight{
	RED, AMBER, GREEN
}
```

``` java
public enum EnglishDaysOfTheWeek{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY,
   FRIDAY, SATURDAY, SUNDAY
}
```

**Wrappers**

- **Primitive wrapper classes** 
  - Classes that wrap a primitive value and provide additional functionality.
  - Wrappers for numeric types extend the Number class. 

- They have:
  - Converter methods
  - Parser methods

| **Primitive** | **Wrapper**   |
| ------------- | ------------- |
| byte          | **Byte**      |
| short         | **Short**     |
| int           | **Integer**   |
| long          | **Long**      |
| float         | **Float**     |
| double        | **Double**    |
| boolean       | **Boolean**   |
| char          | **Character** |

**BigDecimal**

- BigDecimal fixes many issues with floats and doubles:
  - Has custom defined rounding modes to suit the situation
  - Has various operations which involve decimals:
    - Add
    - Subtract
    - Multiply
    - Divide
    - Power

**Math Class**

- The Math class contains several fields and methods for different mathematical operations:

- Example Field:
  - double PI

- Example Methods:
  - double sqrt(double value) – returns square root
  - double log(double value) – returns natural log
  - double pow(double a, double b)  - returns ab 

**DateTime**

- New API to fix issues with previous versions
- java.time package introduced with 3 main classes:
  - LocalDate
  - LocalTime
  - LocalDateTime
- Additional classes
  - Period
  - ZonedDateTime
  - Duration

- LocalDate, LocalTime, LocalDateTime
  - Can be created with current time with static .now() method

``` java
LocalDate nowDate = LocalDate.now();
LocalTime nowTime = LocalTime.now();
LocalDateTime nowDT = LocalDateTime.now();
```

- Can be created with specific date / time with static .of(…) method with applicable values

``` java
LocalDate nowDate = LocalDate.of(int year, int month, int day);
LocalTime nowTime = LocalTime.of(int hour, int minute, int sec);
LocalDateTime nowDT = LocalDateTime.of(LocalDate d, LocalTime t);
LocalDateTime nowDT = 
	LocalDateTime.of(int year, int month, int day,
					int hour, int minute, int sec);
```

- Have private constructors so cannot be directly instantiated!

- Period
  - Can be created with static method .of(…)

``` java
Period p = Period.of(int years, int months, int days)
```

- Can be created with static method .between(…)

``` java
Period p = Period.between(LocalDate start, LocalDate end);
```

**Module Review**

- What are primitives?
- What is the size of each primitive?
- What are classes?
- What are objects?
- What are Strings?
- How are Strings handled in memory?
- What alternatives are there to use in place of Strings?
- How are arrays indexed?
- What is the purpose of wrappers?
- What are some key utility classes and their benefits?