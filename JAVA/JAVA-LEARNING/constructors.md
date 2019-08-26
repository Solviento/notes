**Module Objectives**

- Identify a constructor in a class
- Write constructors for your classes
- Identify when the default constructor is generated
- Create valid constructor call chains with this() and super() 
- Identify when a super() call is implicitly inserted

**What is a Constructor?**

- A constructor is a block of code that runs when an object is created.

- Used to initialize the object’s state and prepare it for use
- Runs before any methods are invoked or any fields are accessed
- The process is invoked with the keyword  new

**Syntax**

- Constructors resemble methods, but there are key differences:
  - No return type
  - Must have same name as class

- Just like methods, constructors can be overloaded as needed
  - Different versions with different parameters

``` java
public class User {
	public User() {
		/* This is a constructor */
	}	
	public User(String data) {
		/*  This is a constructor with an argument */
	}
    public void User() {
		/* This is a poorly named, yet legal method */
	}
}
```

**The Default Constructor**

- All classes must have a constructor.
- If we do not write any, the compiler inserts a default constructor into our class:
  - No arguments
  - Empty body

- If any user-defined constructors are present in the class, the default constructor is not generated!

``` java
public class User {
	
	public String firstName;	
	public String lastName;

	public void validateDetails() {
		...
	}
}
```

``` java
public static void main(String[] args) {
	User user = new User();  // default constructor in action!
}
```

**this() and super()**

- Every constructor must begin with a call to this() or super()
  - These calls can have arguments
  - If neither is called, the compiler inserts a call to super()

- super() calls a constructor in the parent class
- this() calls another constructor in the same class
  - Often done to avoid duplicating code
  - Not to be confused with this  (Java keyword)

**this()**

``` java
public class User {
	
	public String firstName;	
	public String lastName;
	public User() {

		this( “default_first”, “default_last” );
	}	

	public User(String firstName, String lastName) {
		
		this.firstName = firstName;
		this.lastName = lastName;
	}	
}
```

``` java
// Will the code below compile?

public class MyClass {
	
	String firstName;	
	String lastName;
	public MyClass() {

		this( “Fake”, “Name” );
	}	

	public MyClass( String firstName, String lastName ) {
		
		this();
	}	
}

// Yes, since there are two corresponding constructors to the two different this(,,) calls
```

**Constructors and Inheritance**

- Constructors are not inherited!

- Constructor chaining – the sequence of constructors invoked through calls to this() and super()
  - Chain must be able to complete in order to compile

**Constructor Chaining**

``` java
public class User {
	
	public String name;	

	public User(String name) {
		this.name = name; 	
	}	
}

public class Admin extends User {

	public Admin(){
		this(“default_name”);
	}

	public Admin(String name){
		super(name);
	}
}
```

``` java
// Will the code below compile?

public class MyClass {
	
	String firstName;	
	String lastName;
	public MyClass() {

		this( “Fake”, “Name” );
	}	

	public MyClass( String firstName, String lastName ) {
		
		this();
	}	
}
```

**Module Review**

- What is a constructor?
- How does a constructor differ from a method?
- What is the default constructor?
- What must the first statement of a constructor be?
- What does constructor chaining refer to?