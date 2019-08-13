**What is Serialization?**

- Serialization is a Java mechanism for converting an object’s state into a stream of bytes.

- The stream can have a variety of destinations:
  - A file
  - Byte array
  - Another location on the network
- De-serialization is the reverse – restoring an object from a byte stream

**Why Serialize?**

- Common uses:
  - Saving / restoring a game’s state
  - Transferring an object across a server or over a network
  - Load balancing mechanisms in clustered architectures

**The Serializable Interface**

- To be serialized, an object must implement the java.io.Serializable interface 

- It does not define any methods! 
  This is called a “marker” or “tag” interface

- If we attempt to serialize an object that is not Serializable, a NotSerializableException will be thrown

**How It Works**

- The mechanism writes out all data associated with the object:
  - Class hierarchy information
  - All instance members (including private data) 
- Any object references (i.e. reference type fields) will be recursively serialized:
  - They must also be Serializable to avoid NotSerializableException!

**Object to be Serialized**

``` java
package com.fdm;

import java.io.Serializable;

class User implements Serializable {
	
	private String username;
	private String password;
	private Department department;	

   	// methods
}
```

**The Streams**

- Serializing is done using specialized I/O streams:

- ObjectOutputStream

```java
public final void writeObject(Object object) throws IOException
```

- ObjectInputStream

```java
public final Object readObject() throws IOException, ClassNotFoundException
```

**Serializing**

``` java
ObjectOutputStream oos = null;

try {
   	FileOutputStream fos = new FileOutputStream(“users.ser");
	oos = new ObjectOutputStream(fos);
	oos.writeObject( currentUser );

} catch (IOException e) {
	log.error(“Serialization failed”, e);
} finally{
	oos.close();
}
```

**De-serializing**

```java
ObjectInputStream ois = null;

try {
	FileInputStream fis = new FileInputStream(“users.ser");
	ois = new ObjectInputStream(fis);
	User currentUser = (User)ois.readObject();

} catch (IOException e) {
	log.error(“De-serialization failed”, e);
} catch (ClassNotFoundException e){
	log.error(“De-serialization failed”, e);
} finally{
	ois.close();
}
```

**How Serialization Works**

- Not serialized:
  - Static members
  - Variables marked transient 

- We can mark non-Serializable instance fields of a class as transient
  - Allows serialization to succeed
  - Data inside will be ignored and not saved! Keep this in mind when de-serializing.

**Serialization and Inheritance**

<img src="../PHOTOS/serialization-01.png">

**Non-Serializable Data**

- Can we do anything to preserve transient data when serializing? 
- How about data inherited from a non-Serializable superclass?

**Customizing Serialization**

- We can add the following methods to our class to customize its serialization:

| **private   void** **writeObject****(****ObjectOutputStream** **out)**    **throws**   **IOException** |
| ------------------------------------------------------------ |
| **private   void** **readObject****(****ObjectInputStream** **in)**    **throws**   **IOException****,** **ClassNotFoundException** |

- They will be called automatically when an object of the class is serialized.

- readObject and WriteObject must be private in order to be called automatically. This is important, if they are not private, they do not get called.

- Inside these methods, we can manually write out / read in data normally skipped by serialization:
  - transient data
  - Data inherited from non-Serializable superclasses
- We can also invoke default serialization functionality by calling:
  - out.defaultWriteObject()
  - in.defaultReadObject()

- readObject and WriteObject must be private in order to be called automatically. This is important, if they are not private, they do not get called.

**Versioning**

- When de-serializing, it is important to make sure that the class definition has not changed.
  - If it cannot be found, a ClassNotFoundException is thrown
  - If it has a different version ID, an InvalidClassException is thrown

``` 
Exception in thread "main" java.io.InvalidClassException: com.fdm.User; local class incompatible: stream classdesc serialVersionUID = 189611857327415406, local class serialVersionUID = -7967074341750652642
```

**Defining the Serial Version UID**

Within the Serializable class, include the following:

``` java
 private static final long serialVersionUID = 1;
```

- Java will check whether the object we are de-serializing has the same class version number as the current class in the system

- serialver – a Java command used to generate an SUID compatible with old versions of the class that do not have an SUID 
- “serialver –show” will bring up a GUI

Review Questions

What is required in order to serialize and deserialize objects?
What data type are objects stored as when serialized?
What is serialization used for?
What is a serial version id used for?
What interface needs to be implemented in order to serialize an object?







