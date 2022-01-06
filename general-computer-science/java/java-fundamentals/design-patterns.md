# Design Patterns

**Overview**

- Design patterns describe recurring problems and core solutions to these problems
- Use carefully
- First formulated by the Gang Of Four
- www.oodesign.com
- www.dofactory.com/Patterns/Patterns.aspx

**Categories**

- There are 3 categories of design patterns
  - Creational
  - Behavioral
  - Structural

**Design Patterns**

- Singleton
- Factory Method
- Adapter
- Observer
- Command
- Object Pool

**Singleton**

- Allows us to maintain one and only one instance of a class in memory for the duration of the program

**Singleton - Implementation**

- A Singleton is defined by
  - A private constructor
  - private static variable of own type
  - public static getInstance() method

<img src="../PHOTOS/design-patt-01.png" height=150px>

**Singleton - Example**

- If you put some paperwork into a filing cabinet, you cannot expect to retrieve it from a different filing cabinet. It has to be the same one.
- Singleton means that within our system we can only ever have a single filing cabinet. It is impossible to build more than one.
- What are the advantages of this?

**Factory Method**

- The Factory method is a class with the Single Responsibility of creating objects
- Can allow us to create objects without specifying the exact class of the object to be created

**Factory Method- UML**

<img src="../PHOTOS/design-patt-02.png" width=650px>

**Adapter**

- The adapter pattern is used to adapt from one API to another.
- We use it when we have two interacting systems or classes that are incompatible with each other.

```
Scenario
We have two systems.
One is a Reader object, which reads from a database and stores the results in an array.
The other, designed to display data can only work with XML files.
How can we get these two systems to work together?
```

<img src="../PHOTOS/design-patt-03.png" width=650px>

<img src="../PHOTOS/design-patt-04.png" width=650px>

**Observer**

- The Observer pattern allows one object to observe the state of another object, allowing it to act when that state is changed
- There are two key constructs
  - **Observable**
  - **Observer**

- The Observable holds a list of Observers
- Observers can be added or removed from an Observable by calling attach and detach
- When the state of the Observable changes, it will call its notify method. This calls the update method of each Observer

<img src="../PHOTOS/design-patt-05.png" width=650px>

**Command**

- The Command pattern allows us to completely encapsulate method calls
- This allows us to decouple an object that requests an action from the object that performs it

- We are Abstracting away the behavior
- There are 4 main parts to the Command Pattern
  - Client
  - Command Object
  - Invoker
  - Receiver

<img src="../PHOTOS/design-patt-06.png" width=650px>

<img src="../PHOTOS/design-patt-07.png" width=650px>

**Object Pool**

- An Object Pool is a class which holds a ‘pool’ of objects of the same type.

- Its primary purpose is to allow reuse of objects, rather than creating a new one each time one is needed.

- It can also be used to place a limit on the number of objects in circulation.

<img src="../PHOTOS/design-patt-08.png" width=650px>

**Review**

- What is a design pattern?
- Give 6 design patterns
- What is the purpose of each of the design patterns?
- What problems do each of the design patterns solve?
