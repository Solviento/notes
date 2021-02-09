**AOP Introduction**

- Programming paradigm
  - Aspect Oriented Programming
  - Can be used to complement OOP
  - Helps achieve strong decoupling
- Designed to address cross cutting concerns

- Key phrase: **Cross cutting concerns**
  - Concerns that cut across the entire application
    - Security
    - Authentication
    - Validation
    - Persistence

- How do we usually handle such concerns in our application? 
- Are there any problems with this approach?

- AOP decouples code that handles cross cutting issues from code that handles business logic.

- Analogous to the Observer pattern
  - Observer and observed do not know they are being used in the observer pattern
- Non-invasive way to add functionality throughout your application
- Enables better adherence to Single Responsibility

**AOP Benefits**

- Simplicity
- Abstraction
- Single Responsibility
- Decoupling
- Separation of Concerns

**Types of AOP**

- AOP works by “weaving” in code that deals with cross cutting issues
- Three main types:
  - Compile time
    - The compiler rearranges source code
  - Class load time
    - Bytecode is modified when the class is loaded
  - Runtime – Proxy based
    - (Spring AOP)
    - An object is created to link together pieces of functionality at runtime

**Proxy-based AOP**

- Spring works by building proxy objects around our objects
- The proxy looks and acts like our object (the target), while invoking other functionality in the background
- The proxy knows about:
  - Our target object (what needs to be “Observed”)
  - Code to invoke in response (the “Observer”)

- We interact with the proxy exactly as we would with the target, and it weaves in “observer” code behind the scenes.

- The proxy is what is performing the actual observing.

Simple method invocation:

- Gets a direct reference to the object: 
  - Object obj = new Object(); OR
  - Object obj = ctx.getBean(“myObject”);
- Invokes method() on the object directly:
  - obj.method();
- method() executes

<img src="../PHOTOS/spring-aop-01.png" width=200px>

Proxy-based method invocation:

- Gets an indirect reference to the object:
  - Object obj = ctx.getBean(“myObject”);
- Invokes method() on what is actually a Spring-provided proxy:
  - obj.method();
- Proxy object calls relevant “Observer” code (e.g. Logging, security checks) 
- method() executes

<img src="../PHOTOS/spring-aop-02.png" width=200px>

- Note! Only external calls (through the proxy object) will activate AOP functionality

``` java
Object obj = ctx.getBean(“myObject”);
obj.someMethod();
```

- Calls that do not go through the proxy are not being watched by Spring
  - e.g. Calls on a direct reference, or if a method calls another method in the same object

``` java
Object obj = new Object();
obj.someMethod();
```

**Key Terminology**

- Advice
  - The code to handle a cross-cutting concern
  - Will be invoked at specified points
    - e.g. Logging code, security authentication code, etc.
- Join point
  - A place in the code where advice can be placed
  - Any method can be a join point
- Pointcut
  - A specific pattern that will match a subset of join points
  - The “observed” code that advice methods can be applied to
    - e.g. “The execution of any method named go() that returns a String”

- Aspect
  - The combination of pointcuts and advice
  - How a single cross cutting concern is handled in the code
- Weaving
  - Applying an aspect to a target object
    - e.g. Adding logging code to a simple User bean to get a User object that logs.
- Introduction
  - Dynamically adding a new method or attribute to an existing class

**Maven Dependencies**

- Dependencies required for Spring AOP:
  - spring-beans
  - spring-context
  - spring-core
  - spring-aop 
  - aspectjweaver 
  - aspectjrt
  - cglib (if required)

**Key Tags**

```
<aop:config>
```

- Identifies AOP configuration in the XML file
- Configuration for aspects, advice, and pointcuts goes inside this tag

``` 
<aop:pointcut>
```

- Defines a pattern for a pointcut
- Identifies a specific set of method executions
- (Inside ```<aop:config>```)

``` java
<aop:aspect>
```

- Defines advice/pointcut combinations to handle a single aspect
- Use as many of these as there are aspects
- (Inside ```<aop:config>```)

**Advice Tags**

Inside ```<aop:aspect>``` tags:

- <aop:before>
  - Identifies advice to run before the specified join point runs
- <aop:after>
  - Identifies advice to run after the specified join point runs
- <aop:after-throwing> 
  - Identifies advice to run after the specified join point throws an exception
- <aop:after-returning>
  - Identifies advice to run after the specified join point returns successfully
- <aop:around>
  - Identifies advice to run before and after the specified join point 

**Pointcut** **Tag Example**

Syntax:

``` java
<aop:pointcut id=“<some id>"
	expression="execution(<return type> 	<method call>)" 	/>
```

Example:

``` java
<aop:pointcut id="readInput"
	expression="execution(java.lang.String 	com.fdm.bankingSystem.ui.CommandLineUserInputReader.
		readUserInput(..)) " />
```

**Advice Tag Example**

Syntax:

``` java
<aop:after-returning pointcut-ref=“<pointcut id>” 
		returning=“<return parameter>" 
		method=“<advice method name>" />
```

Example:

``` java
<aop:after-returning pointcut-ref="readInput” 
		returning="input" 
		method="clean" />
```

- <return parameter> is the name of the method parameter of the advice method where Spring will pass in the return value of the join point that ran.

**AOP Annotations**

- Key annotations:
- @Aspect
  - Annotates a class containing configuration for advice and pointcuts
  - Equivalent to <aop:config>

- @Pointcut
  - Annotates an empty method to use as the identifier of a pointcut
  - Equivalent to <aop:pointcut>

**Advice Annotations**

- The following annotations are placed directly above methods containing advice code:

| **Annotation**  | **Equivalent** **to…** |
| --------------- | ---------------------- |
| @Before         | <aop:before>           |
| @After          | <aop:after>            |
| @AfterReturning | <aop:after-returning>  |
| @AfterThrowing  | <aop:after-throwing>   |
| @Around         | <aop:around>           |

**Combining Advice and** **Pointcuts**

- We can specify the pointcut as an attribute to an advice annotation:

``` java
@Before(“execution(* com.fdmgroup.MyClass.*(..))”)
public void someAdvice(){ … }
```

- Alternatively, we can use an empty method as a pointcut ID:

``` java
@Pointcut (“execution(* com.fdmgroup.MyClass.*(..))”) 
public void anEmptyMethod(){   }
```

- Now we can identify this pointcut with “anEmptyMethod()”:

``` java
@Before(“anEmptyMethod()”)
public void someAdvice(){ … }
```

**Module Review**

- What is AOP?
- What are the benefits to using AOP?
- How is proxy-based AOP implemented?
- What are some key XML tags to use for AOP?
- What are some key annotations to use for AOP?



