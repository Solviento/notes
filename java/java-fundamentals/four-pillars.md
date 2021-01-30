**OOP - Four Pillars**

- Encapsulation
- Abstraction
- Inheritance
- Polymorphism

**Encapsulation – One Connotation**

- The act of grouping together data and behaviour into logical components.

- One connotation of “to encapsulate” means “to group together”
- Attributes and/or behaviour can be encapsulated by a class

**Encapsulation Goal - High Cohesion**

- Good design aims to achieve high cohesion.

- Cohesion is the degree to which components of a class belong together
- We want to group data and / or behaviours into logical components

**Encapsulation – Another Connotation**

- To encapsulate also means “to surround and cover so that nothing else can touch it”
  - Access to attributes and/or behaviours can be restricted
  - Information or details of implementation can be hidden

**Encapsulation Goal - Low Coupling**

- Coupling is the degree to which one class has knowledge of the internal workings of another class.  

- Good design aims to achieve low coupling.

``` java
class A {
	public int index;
}

class B {
	public void method() {
	A a = new A();
	a.index = 0;	  // tight coupling
	}
}
```

- The class should use a setter, rather than accessing the internal components of the object directly.

**Encapsulation**

<img src="../PHOTOS/fp-01.png">

- What is this Shareholder class encapsulating?

**Abstraction**

- Extracting relevant and essential information and behaviour.

- Largely relates to Data Abstraction
- Often refers to hiding the complexity of a system

- What information is it possible to know about people?
  - age
  - address
  - height
  - star sign
  - mobile number

- What information is relevant for a bank to know about?

- Think about what information is relevant to a car driver.

- Think about what information is relevant to a car mechanic.

- How does this relate to Abstraction?

**Abstraction Principle**

- Benjamin C. Pierce: Abstraction Principle
  - “Each significant piece of functionality in a program should be implemented in just one place in the source code. Where similar functions are carried out by distinct pieces of code, it is generally beneficial to combine them into one by abstracting out the varying parts.”

- Martin Fowler:  Rule of Three
  - “If a piece of code is copied more than once, i.e. it would end up having three or more copies, then it needs to be abstracted out.”
- The parts that are the same are abstracted out.  
- The parts that vary need to be encapsulated.

**Inheritance**

- The act of one class receiving the behaviours and attributes of another class or interface.

- IS-A relationship
  - Performed through the use of Realisation and Generalisation

**Inheritance – IS-A**

- What attributes and methods do Broker and Shareholder have?

<img src="../PHOTOS/fp-02.png">

**Polymorphism**

- Having multiple forms or versions of the same type of thing and the same types of behaviour.

- Two main types
  - Early Binding (Compile time)
    - Occurs at compile time
  - Late Binding (Runtime)
    - Occurs at runtime

**Polymorphism - Binding**

- Binding 
  - Joining attributes and method calls to actual objects and behaviours in memory
  - How the program decides on the next action or piece of functionality which needs executing

- Early Binding
  - Attributes and method calls are bound when compiling source code
- Late Binding
  - Attributes and method calls are bound when the program is running

**Polymorphism – Early Binding/Overloading**

<img src="../PHOTOS/fp-03.png">

- Two methods in the same class have the same name but different parameters.
- Distinguished by the number and type of arguments passed in.

- Can you think of another example?

**Polymorphism – Late Binding/Overriding**

- Only applies to inheritance
- Two methods in different classes (parent and child) have the same name and the same parameters, but different implementations

<img src="../PHOTOS/fp-04.png">

**Module Review**

- What is Object Oriented Programming?
- What are the Four Pillars?
- Describe each pillar succinctly.
- What are some key best practices to follow?



