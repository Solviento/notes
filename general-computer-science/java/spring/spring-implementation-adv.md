**Configuration**

- Some objects require certain things to be consistently different each time they are created
- E.g. A unique identifier
- How might you achieve this normally?
  - Spring incorporates a factory-method attribute which allows you to specify the factory responsible for creating an object
- This can be internal or external to the object being created

``` java
<bean id=“bean” class=“com.fdm.MyFactory” factory-method=“createBean” 
				scope=“prototype” />
```

- Each time that bean is referenced it will actually call the factory method
- If the factory method takes arguments, this can be specified by using the constructor-arg attribute
- Spring includes a feature called autowiring
- Spring will guess which dependencies to inject where
- This means you do not need to specify this manually
- There are 4 types
  - byName
  - byType
  - constructor
  - autodetect

- byName
  - Autowires by variable name and bean name
- byType
  - Autowires by variable type and bean type
- constructor
  - Autowires byType for constructors
- autodetect
  - Autowires by constructor first and then byType

``` java
<bean id=“bean” class=“com.fdm.MyBean autowire=“byType” />
```

- Class User might have two fields which must always be populated, but could have several field remaining unpopulated
- There needs to be a way to guarantee that the required fields are populated when Spring instantiates the beans
- Two solutions
  - dependency-check attribute (deprecated)
  - @Required

- @Required annotation allows fine control over which properties should be set and which should not. This annotation is applied to the setter method only.
- If a property is listed as required but it is not set then Spring will throw an Exception (covered later)

``` java
public class User {
  private String username;

  @Required
  public void setUsername(String username){
    this.username = username;
  }
}
```

**Annotations**

- Annotations reduce the amount of work needed to be done in the xml
- In order to use annotations you must have the following xml configuration set

``` java
<context:annotation-config />
```

- Four key annotations to use in code
  - @Required (covered previously)
  - @Autowired
  - @Qualifier
  - @Resource
- Annotations are applied directly to class fields

``` java
public class User{
  @Autowired
  private String username;
}
```

- @Autowired
  - Also acts as @Required
  - Indicates that Spring should automatically inject a valid dependency into this field 
  - Works byType
- @Qualifier(“beanName”)
  - Provides an addon to @Autowire
  - Specifies the name of the bean to use
    - Replicating byName

- What issues are there with the current identified annotations?
  - They make you completely dependent upon Spring!

- @Resource
  - This is a JSR-250 alternative to the spring annotations
  - Does not cause the program to couple to Spring
  - Acts as @Required, @Autowired and @Qualifier

``` java
public class User{
  @Resource(name=“name”)
  public String username
}
```

**Collections**

- Spring provides functionality for wiring collections

``` java
<map></map>
<list></list>
<array></array>
<props></props>
<set></set>
```

- Makes injecting collections very straight forwards

- Within the tags you should reference the bean or create the bean anonymously

- This configuration works for list, set and array

``` java
<bean name=“myBean” class=“com.fdm.MyBean”>
  <property name=“myCollection”>
    <list>
      <ref bean=“myOtherBean” />
      ...
    </list>
  </property>
</bean>
```

- Map and Props work on key-value pairs
- Identify for maps as follows

``` java
<bean name=“myBean” class=“com.fdm.MyBean”>
  <property name=“myCollection”>
    <map>
      <entry key="Key 1" value="1" />
      <entry key="Key 2" value-ref="PersonBean" />
      <entry key="Key 3">
        <bean class="com.Person">
          <property name="name" value=“PersonX" />
        </bean>
      </entry>
    </map>
  </property>
</bean>
```

- Props only works with Strings

- Identify for props as follows

``` java
<bean name=“myBean” class=“com.fdm.MyBean”>
  <property name=“myCollection”>
    <props>
      <prop key=“MyValue”>TheStringValue</prop>
    </props>
  </property>
</bean>
```

- Spring provides an additional namespace called util which can be used for generating collections of a specific type

``` java
<beans …
 xmlns:util=“http://www.springframework.org/schema/util”
  xsi:schemaLocation=“…
    http://www.springframework.org/schema/util
    http://www.springframework.org/schema/util/spring-util-3.1.xsd”>
  <bean name=“myBean” class=“com.fdm.MyBean”>
    <property name=“myCollection”>
      <util:set set-class=“java.util.TreeSet”>
        ...
      </util:set>
    </property>
  </bean>
</beans>
```

**Exceptions**

- Spring creates a well defined Exception called BeanCreationException

- This Exception will be thrown if there are missing dependencies and various other issues which might occur

- This Exception introduces an extra layer of protection by causing the application to fail early rather than late

<img src="../PHOTOS/spring-adv-01.png" width=800px>



















