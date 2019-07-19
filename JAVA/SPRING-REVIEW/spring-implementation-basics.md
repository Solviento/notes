**Configuration**

- Configuration is declared in context.xml
  - Beans
  - Injections
  - Advanced configuration
- Must remember the namespaces
  - Getting these wrong will cause issues
- Key dependencies
  - Spring Core
  - Spring Beans
  - Spring Context

- Location of context.xml file
  - src/main/resources/context.xml

- Key way to declare a bean

``` java
<bean id=“beanName” class=“com.fdm.project.BeanName” />
```

**ApplicationContext**

- Used to instantiate the core container and load up the context.xml
- Several types
- ClassPathXmlApplicationContext (main one to use)
- AnnotationConfigApplicationContext
- FileSystemXmlApplicationContext
- XmlWebApplicationContext
- AnnotationConfigWebApplicationContext

**ApplicationContext**

- Overall hierarchy
- Everything is an Interface

<img src="../PHOTOS/spring-basics-01.png" width=800px>

| **Feature**                                     | **BeanFactory** | **ApplicationContext** |
| ----------------------------------------------- | --------------- | ---------------------- |
| Bean instantiation and wiring                   | Yes             | Yes                    |
| Automatic BeanPostProcessor   registration      | No              | Yes                    |
| Automatic BeanFactoryPostProcessor registration | No              | Yes                    |
| Convenient MessageSource   access (for iln8)    | No              | Yes                    |
| ApplicationEvent   publication                  | No              | Yes                    |

Key way to initiate the ApplicationContext

``` java
ApplicationContext context = new ClassPathXmlApplicationContext(“context.xml”);
```

**Beans and Dependencies**

- Remember, Beans are just POJO’s
  - i.e. Plain Old Java Objects
  - There is no special code required for them

- There are two main types of xml based injection
  - Setter injection
  - This uses the standard setAttribute(Attribute attribute) methods
- Constructor injection
  - This uses the constructor public MyClass(Attribute attribute)

- This injects into the constructor during instantiation

``` java
<bean id=“helloMessage” class=“com.fdm.Message”> 
	<constructor-arg value=“Hello” />
</bean> 
```

- This injects into the setter method after instantiation

``` java
<bean id=“helloMessage” class=“com.fdm.Message”>
  <property name=“message” value=“Hello” />
</bean> 
```

- It is possible to pass beans in as dependencies using the ref attribute

``` java
<bean id=“password” class=“com.fdm.Password”>
  <constructor-arg value=“myPassword” />
</bean>

<bean id=“user” class=“com.fdm.User”>
  <property name=“password” ref=“password” />
</bean>
```

- It is possible to declare a bean as you need it rather than before

``` java
<bean id=“user” class=“com.fdm.User”>
  <property name=“password”>
    <bean class=“com.fdm.Password”>
      <constructor-arg value=“myPassword” />
    </bean>
  </property>
</bean>
```

- Bean scopes – Dictate how beans are created
  - Singleton – (Default)
    - Maintains a single instance per Spring Container
- Prototype
  - Produces a new instance each time it is referenced
- Singleton scope removes the issues with Gang of Four Singleton –  Why?

``` java
<bean id=“user” class=“com.fdm.User” scope=“prototype”>
  <property name=“password” ref=“password” />
</bean>
```

- Some beans require additional work to set them up which can only be done by Java

- The init-method attribute allows you to specify any method to run during instantiation

**Ambiguities and Issues**

- Several issues can occur
  - Constructor ambiguity
  - Dependency problem (Spring 3.1 and earlier)
- Constructor ambiguity
  - Overloaded constructors with different types of arguments but the same number of arguments
- Dependency problem (Spring 3.1 and earlier)
  - One class may depend on another class being fully configured
  - Spring can inject classes before they are fully initialised

Constructor ambiguity solution

- index – specify the index of the argument
- type – specify the data type of the argument
- When combined this allows you to resolve all constructor ambiguities

``` java
<bean id=“user” class=“com.fdm.User” scope=“prototype”>
  <constructor-arg value=“Username” index=“0” type=“java.lang.String” />
  <constructor-arg value=“16” index=“1” type=“int” />
</bean>
```

- The dependency problem does not exist after Spring 3.2

- Prior to that the solution involves using the depends-on attribute

``` java
<bean id=“password” class=“com.fdm.Password”>
  <constructor-arg value=“myPassword” />
</bean>

<bean id=“user” class=“com.fdm.User” depends-on=“password”>
  <property name=“password” ref=“password” />
</bean>
```























