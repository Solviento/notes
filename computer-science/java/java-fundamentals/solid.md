**OO Design**

- Good design centers around the following goals:
  - Code reuse 
    - Can be reused for different data or applications
  - Scalability
    - Can handle increase in work or number of users
  - Extensibility
    - Easily extended or modified
  - Maintainability
    - Holds up to changing user needs

- Bad design is…
  - Rigid – Difficult to change
  - Fragile – Easy to break
  - Immobile – Hard to extract reusable components
  - Repetitive – Contains repeated code
  - Needlessly Complicated – “Overdesigned” 

``` java
SOLID is a set of principles that provide a means to deal with the problem of bad design.
```

**SOLID Principles**

- Single Responsibility Principle
- Open Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

**Single Responsibility Principle (SRP)**

``` 
A class should have only one reason to change.
```

- Each class or entity should be responsible for one thing.
- Each should perform a set of closely related tasks.
- Associated concepts:
  - Encapsulation
  - Cohesion

- How many reasons to change does the following class have?

``` java
public class Employee {
	
	private String id;
	private String name;
	private Role title;	

	public double calculateSalary() { … }		// reason 1: calculate salary
 	public void storeToDatabase() { … }			// reason 2: storing to db
	...
}
```

**Open Closed Principle (OCP)**

```
Classes and functions should be open for extension but closed for modification.
```

- Open to extension:
  - Can extend modules with new behavior
- Closed to modification:
  - Behavior can be added without changing the source code of module

- Associated concepts:
  - Abstraction
  - Polymorphism

- How easily can we add a new type of  Shape to the system?

``` java
class Circle { … }
class Square { … }
class Rectangle { … }

public class ShapeGraphics{ 
	
	public void drawCircle (Circle circle) { … }			// rigid - hardcoded
	public void drawSquare (Square square) { … }
	public void drawRectangle (Rectangle rectangle) { … }
    
    public void drawShape(Shape shape) {...}				// flexible - dynamic 
}
```

**Liskov Substitution Principle (LSP)**

```
Subclasses should be substitutable for their base classes.
```

- Classes should correctly fulfill any expected behaviors inherited from superclasses.
- Related concepts:
  - Inheritance
  - Polymorphism

``` java
class Rectangle {

	private int x;
	private int y;

	public void setX (int x){ 
		this.x = x;
	}

	public void setY (int y){ 
		this.y = y;
	}

	public int getArea(){
		return x*y;
	}
}
```

``` java
class Square extends Rectangle {
	
	@Override	
	public void setX (int x){ 
		this.x = x; 
		this.y = x;
	}

	@Override
	public void setY (int y){ 
		this.y = y; 
		this.x = y;
	}
}
```

**Interface Segregation Principle (ISP)**

``` 
Interfaces should be small, each dealing with one aspect of a problem.
```

- “Fat”, general-purpose interfaces are not cohesive.
- An interface should allow only relevant behavior to be seen.
- A class should not be forced to implement irrelevant behavior.
- Related concepts:
  - Cohesion
  - Inheritance
  - Abstraction

What is the problem with the following setup?

``` java
public interface Timeable {
	boolean isTimeUp(); 
	void setTime(int time);
}
```

``` java
public interface Door extends Timeable {
	void open(); 
	void close();
}
```

``` java
public class TimedDoor implements Door { … }
```

**Dependency Inversion Principle (DIP)**

``` 
Classes should depend upon abstract concepts, rather than concrete implementations.
```

- All modules should depend on high-level modules.
- Abstractions should not depend on details.
- Details should depend on abstractions.
- Related concepts:
  - Abstraction

- What is the problem with this setup?

``` java
public class Worker {
	void doWork(){ ... }
}
```

``` java
public class RobotWorker {
	void doWork(){ ... }
}
```

``` java
public class Manager {
    
	private Worker worker;
    
	public void setWorker(Worker worker){
		this.worker = worker;
	}
    
    public Worker getWorker(){ 
		return worker; 
	}	

	public void manage(){ 
		worker.doWork(); 
	}
}
```

- Solution

``` java
public interface IWorker {
	void doWork();
}
```

``` java
public class Worker implements IWorker {
	void doWork(){ ... }
}
```

``` java
public class RobotWorker implements IWorker {
	void doWork(){ ... }
}
```

``` java
public class Manager {
    
	private IWorker worker;
	
    public void setWorker(IWorker worker){
		this.worker = worker;
	}
    
    public IWorker getWorker(){ 
		return worker; 
	}	

	public void manage(){ 
		worker.doWork(); 
	}
}
```













