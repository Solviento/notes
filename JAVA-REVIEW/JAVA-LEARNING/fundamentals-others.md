**Module Objectives**

- Convert between primitive types and object types through autoboxing/unboxing
- Understand how Java handles widening of variables
- Use varargs to accept an unknown sized parameter set
- Change reference types through casting

**Autoboxing**

- Prior to Java 1.5, the following would not compile:

``` java
Object o = 34;
```

- A barrier existed between objects and primitives:
  - Primitives could not be added to collections
  - Primitives could not be passed into methods that take an Object

- Since Java 1.5, this distinction has been blurred due to autoboxing.

- Autoboxing:
  - Implicitly wrapping a primitive type in its associated class wrapper type during assignment.

``` java
Object o = 1;
```

- The class of Object o is java.lang.Integer

- Unboxing:
  - Implicitly extracting a primitive type from a wrapper type

**Autoboxing/Unboxing**

- Watch out for unnecessary autoboxing and unboxing!
- Conversion is implicit, but still takes place
- Good practice is to use primitives whenever possible

``` java
Integer ix = 34;
ix *= 10;
System.out.println(ix);
```

- How many conversions take place?

**Widening**

- Widening is the way the compiler resolves overloading ambiguity.

- Method call: 

``` java
go(5);
```

- Which will compile?


``` java
void go(long x);	// ok
void go(short x);	// no
```

- The compiler picks the method with the smallest parameter that can hold the passed-in data type.

**Varargs**

- Consider the following:

``` java
int x = 12;
int y = 13;

System.out.format("%s %d %n %s %d",
"the variable x contains", x,
"the variable y contains ", y);
```

- Output: the variable x contains 12 the variable y contains 13 

- Notice how format() can take in any number of arguments. 
- How would you write a method like that?

- In Java, variable argument methods use the following syntax:

``` java
public void printManyArgs(String... str) {
    for(String s : str) {
    	System.out.print(s + " ");
	}
}
```

- Gets passed in as an array
- Can mix with other parameters, but only one var-arg is allowed
- Must always appear last in the argument list

**Casting Overview**

- Casting allows us to treat objects as different, but compatible types:

- Two types:
  - Upcasting (implicit casting)
  - Downcasting (explicit casting)

**Upcasting**

- Objects can be treated as instances of their super types:

``` java
Trade trade = new OptionTrade();
```

- OptionTrade extends Trade
- We can assign any OptionTrade object to a reference of type Trade
- Only methods and fields defined in the Trade data type will be visible!
  - Abstraction

- Upcasting is implicit, and always safe (compiler checked).

**Downcasting**

- Doing the reverse requires an explicit cast:

``` java
Trade t = new OptionTrade();
OptionTrade opt = (OptionTrade) t;
```

- This restores compile-time access to OptionTrade-specific methods and fields…
- …but t must reference an OptionTrade object.
  - Otherwise, a ClassCastException will be thrown
  - The compiler alone cannot verify this, but there is a way to perform this check at runtime...

**Instanceof Operator**

``` java
if( t instanceof OptionTrade ) {
	// Downcasting to OptionTrade can be done safely
}
```

- Checks whether the object referenced by t is an OptionTrade or subtype of OptionTrade 
  - Notice the lack of precision

- If t is null, instanceof returns false

**Primitive Casting**

- Casting references does not affect the original object and simply provides a different perspective on it.
- Casting a primitive changes the data:
  - Casting double 23.45 to an int will result in 23
  - Casting the same int back to a double will result in 23.00
    - Loss of Data!

**Module Review**

- Is upcasting safe?
- What about downcasting?
- Why have autoboxing?
- How can you pass an unknown number of arguments?