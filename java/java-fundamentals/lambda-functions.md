**Module Objectives**

- Understand what a functional interface is
- Convert an anonymous inner class into a lambda expression
- Replace a functional interface with a lambda expression
- Explain why lambda functions are used 
- Explain what added functionality lambda’s provide 

**Functional Interfaces**

- What is a Functional Interface?

- A functional interface is a regular interface in Java with some constraints.

- It can have only one abstract method inside of it. 

- It should be annotated using @FunctionalInterface above the class declaration.

``` java
@FunctionalInterface
public interface Predicate<T> {

    boolean test(T t);

}
```

- Key points. 
  - Can have many default methods
  - Can have only one abstract method
  - Does not need to be annotated with @FunctionalInterface 
  - @FunctionalInterface only adds compile time check to ensure only on abstract method exists

- Why are they used?

- There are many places where functional interfaces are used within Java. 

- This enables flexibility with in the system through the dependency inversion principle.

- Wherever a functional interface is being used as a data type we can replace it using a lambda expression.

- A lambda expression often represent small amounts of functionality that does not constitute the addition of a full class into the code base. 