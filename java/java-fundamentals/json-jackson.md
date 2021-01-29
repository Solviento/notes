**Module Objectives**

- Describe the JSON format
- Recognize valid JSON, including objects and arrays
- Describe the purpose of the Jackson API
- Utilize Jackson to serialize/deserialize Java objects
- Customize Jackson behavior using annotations

How can a Java application communicate its state with other applications?

**Cross-platform communication**

- Serialization
  - Converts Java object state to byte arrays
  - Resulting data can be stored or transmitted
  - Can be deserialized by other Java applications
    - And only Java applications!
- JSON
  - JavaScript Object Notation
  - Lightweight human- and computer-parsable file format
  - Based on JavaScript syntax, but language-independent
    - Can be used to communicate between applications regardless of what they are written in

**JSON**

- JSON syntax is based on two constructs: objects and arrays
  - Objects: sets of name / value pairs
  - Arrays: ordered lists of values

- JSON value types:
  - Strings, e.g. "string"
  - Numbers, e.g. 4
  - true / false
  - null
  - Objects
  - Arrays

**JSON Objects**

- Objects in JSON consist of name / value pairs surrounded by curly brackets ("{ … }") and separated by commas
- Names in the name / value pairs are always strings, e.g. “name”
- Values can be any of the value types previously defined

``` json
{
    "name":"value",
    "anotherObject":
        {"name":"value"}
}
```

**JSON Object Example**

``` json
{
    "name":"John Doe",
    "stream":"ITSM",
    "academy":
        {
            "location":"New York",
            "address":"14 Wall Street",
            "popUp":false
		    },
    "signedOff":false
}
```

**JSON Arrays**

- Arrays in JSON consist of values written in square brackets (“[ … ]”) separated by commas
- JSON Arrays can consist of values of a single data type or multiple data types, e.g. a single array could include both strings and objects

``` json
[
    "string1",
    "string2",
    542,
    {"name":"value"}
]
```

**JSON Array Example**

``` json
{
    "class":"R-37-JAV-92",
    "stream":"Java",
    "trainees":
        [	
            "Jane Doe",
            "John Doe",
            "Jason Doe"
        ]
}
```

**Jackson**

- The Jackson Project
  - Previously “the standard JSON library for Java”
  - Includes libraries to read and write JSON (as well as other data formats)
    - Includes data-binding and streaming functionality

- Data-binding:
  - Defines how Java objects will be represented in JSON

- Streaming:
  - The reading/writing of JSON from/to various locations

``` java
public class Trainee {
	private int id;
	private String name;

	// constructors &
	// methods
}
```

- <- Deserialization
- Serialization ->

``` json
{
    "id":14,
    "name":"John Doe"
}
```

**ObjectMapper**

- The ObjectMapper class in JSON provides a customizable entry point for reading / writing JSON from a Java application
  - readValue( … ) can be used to deserialize JSON
  - writeValue( … ) can be used to serialize Java objects
- JSON can be written from / read to several locations via overloaded readValue( … ) and writeValue( … ) 
  - methods
  - Files
  - Network locations
  - Strings
  - Etc.

- Serialization
  - Java object → some data  format

- Deserialization
  - Data format → Java object

**Jackson Serialization Example**

``` java
ObjectMapper mapper = new ObjectMapper();

File destination = new File("toFile.json");
Trainee trainee = new Trainee(1, "John");

// serialize the Trainee to "toFile.json"
mapper.writeValue(destination, trainee);
```

**JavaBean Convention**

- By default, Jackson will assume your Java objects follow the JavaBean convention
  - Deserialized objects will be instantiated via a no-arg constructor
  - JSON property names will be based on getter / setter method names
  - Jackson does not require Java objects to implement Serializable

- JavaBean convention:
  - Public, no-arg constructor
  - Properties with getters and setters
  - Implements Serializable

``` java
public class Trainee {
	private int theId;
	private String theName;

	// getId() & setId( … )
	// getName() & setName( … )
}
```

``` java
{
    "id":14,
    "name":"John Doe"
}
```

* Note that, by default, the names of JSON properties are based on getter / setter names, not instance variable names, as shown in this example. 

**Jackson Annotations**

- Jackson de/serialization behavior can be customized using annotations
  - Can be necessary if your Java classes do not follow JavaBean conventions
    - For example, no public, no-arg constructor
  - Can be applied to classes, methods, constructors, fields, or parameters

``` java
public class Trainee {
	@JsonIgnore
	private int id;
	private String name
	// …
}
```

- @JsonProperty("property")
  - Method / field / parameter level
  - Associates the field, getter, setter, or parameter with a specific JSON property name
- @JsonCreator
  - Method / constructor level
  - Marks a constructor or factory method to use instead of the no-arg constructor during deserialization
  - @JsonProperty("property") marks which parameter of the contructor / method corresponds to each JSON property
- @JsonInclude
  - Class / method / constructor / parameter level
  - Customizes inclusion/exclusion of properties during serialization in cases where field is null, default, or "empty"
  - All properties are included by default

- @JsonIgnoreProperties({"property1", "property2"})
  - Class level
  - Instructs Jackson to ignore the listed properties
- @JsonIgnore
  - Field / method level
  - Instructs Jackson to ignore the property associated with the annotated field / getter / setter
- @JsonSerialize / @JsonDeserialize
  - Field / method level
  - Used to further augment de/serialization, including custom de/serializers
  - Created by extending StdSerializer / StdDeserializer

**Review**

- What is JSON?
- How do you write an object in JSON?
- What is the purpose of the Jackson API?
- What is data-binding?
- How does Jackson determine the default names of JSON properties?
- What are 5 Jackson annotations?