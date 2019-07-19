**In the olden days…**

- Arrays
  - Inherently type safe, to an extent
  - Fixed size
  - Not much functionality

- Collections (Java 1.2)
  - Solved problems with arrays
  - Worked with Objects, no type safety

- Generics (Java 5)
  - Added compile-time type safety to Collections

- The introduction of the Collections framework, solved many problems of arrays.  However, we lost the type safety.
- In Java 5 Generics was introduced to ensure compile-time type safety. 

**Purpose**

```
Generics allow you  to specify the type of object that another object should deal with in a dynamic way.
```

- E.g. List<String> list = new ArrayList<>();
  - This ArrayList can only hold String objects
- Adds compile-time type safety to: 
  - Collection classes
  - Other classes (including your own)
  - Single methods
- Helps detect bugs at compile-time, instead of run-time.

- Type erasure means…
  - At runtime, type parameters in generic types will be replaced with the types they are bound to.
    For example, an ArrayList<Cat> object would have the method add(Cat cat) at runtime.
  - No new classes are created to accommodate parameterized types. No runtime overhead.
  - Casts are inserted where needed.

**Type Erasure**

- Generic type information does not exist at runtime.
- This is called type erasure:
  - Type parameters will be replaced with simple types.
  - Casts and type checks will be inserted where necessary.

**Uses**

- Typically used for Collections or storage mechanisms.
- Possible scenarios include:
  - Command Pattern
    - Each Command could work with a specific type of object
  - Storage for Databases
    - Each Storage class is designed to store a specific type of object
  - New Collection functionality
    - Each Collection holds the same type of object

- Methods and classes can be declared using generic type parameters.

- Just like Collections do with arrays, we can create a class that uses a collection internally, but provides even more functionality.

**Generics Syntax**

``` java
public class GenericClass<T> { ...	}
```

- Within the brackets, a letter represents a specific type of object.
  - Any letter is allowed, but use meaningful letters 
  - T – Type, E – Element/Extension
  - Called the type parameter
- An actual type will be specified when you instantiate the class.

``` java
public class GenericClass<T> {

	public void add(T type) {
	}
    
	public T get(String id) {
		//return a value 
	}
}
```

``` java
GenericClass<String> gc = new GenericClass<>();
```

- Note that every time you use the GenericClass name, you must specify a type parameter. In this case, it is String.

``` java
GenericClass gc = new GenericClass(); 			// error
```

``` java
GenericClass<int> gc = new GenericClass<>(); 	// bad
```

``` java
GenericClass<int[]> gc = new GenericClass<>(); 	// good
```

- Not specifying a type will compile with a warning.
  - Do not do this!   Java allows it solely for compatibility reasons.
- Only reference types can be used as the type argument.
  - Any Objects, including Arrays
  - NOT primitives! (Wrapper classes can help…)

**Generics Syntax**

- We can use more than one type parameter in a Generic class:

``` java
public class GenericClass<T,X> {
	...
}
```

**Generic Methods**

``` java
public <T> void createList(T t)
{	List<T> list = new ArrayList<>();
	list.add(t);
}

createList(new Integer(7)); 

```

- A method can use Generics, even when the class does not.
  - Type T is determined based on what is passed in to the method, when it is called.
  - Type variable is declared before the return type.
- The call to createList will see “Integer” and replace occurrences of “T” with “Integer”

**Generics and Polymorphism**

- Polymorphism does not apply to type parameters!

``` java
GenericClass<Vehicle> gc = new GenericClass<Car>();	// bad
```

``` java
public void method(GenericClass<Vehicle> gc) {
	...
}

method( new GenericClass<Vehicle>() );				// okay

method( new GenericClass<Car>() );					// bad
```

**Bounded Type Parameters**

- We can restrict what kind of types our generic code can work with:

``` java
public class GenericClass<T extends Storable> {
	...
}
```

- With generics, the keyword ‘extends’ also applies to classes that implement an interface.  `<T implements Storable>` would not compile!

**Wildcards**

- Wildcards give us more flexibility when creating references to generic types:

``` java
GenClass<? extends Vehicle> gc = new GenClass<Car>();		// okay
```

``` java
public void method(GenClass<? extends Vehicle> gc) {...}
	
method( new GenClass<Vehicle>() );							// okay
method( new GenClass<Car>() );								// okay
method( new GenClass<SUV>() );								// okay
```

- The super wildcard allows us to use a super type:

``` java
GenClass<? super Car> gc = new GenClass<Vehicle>();			// okay
```

``` java
public void method(GenClass<? super Car> gc) {...}

method( new GenClass<Object>() );							// okay
method( new GenClass<Vehicle>() );							// okay
method( new GenClass<Car>() );								// okay
```

- Wildcards are only used when declaring references to objects.

``` java
GenClass<Dog> gc = new GenClass<? extends Dog>();			// bad
```

- When using “new”, we are creating an object, so we must specify a real type in the brackets.

- Wildcards do not work for generic class and method declarations.

``` java
public class MyClass<? extends Animal> {					// bad
	public <?> void myMethod(){...}							// bad
}
```

**Module Review**

- What is a Generic class?
- What is a type parameter?
- Where might Generics be useful?
- What syntax do you need to create Generics?
- How are bounds used with generics?
- What is a wildcard?

