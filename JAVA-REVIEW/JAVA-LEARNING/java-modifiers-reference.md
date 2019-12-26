**Modifier Hand Out**

| **Access Levels** |           |             |              |           |
| ----------------- | --------- | ----------- | ------------ | --------- |
| **Modifier**      | **Class** | **Package** | **Subclass** | **World** |
| public            | Y         | Y           | Y            | Y         |
| protected         | Y         | Y           | Y            | N         |
| *(default)*       | Y         | Y           | N            | N         |
| private           | Y         | N           | N            | N         |

 

**Java Modifier Summary**

| **Modifier**             | **Used on**                                             | **Meaning/Effect**                                           |
| ------------------------ | ------------------------------------------------------- | ------------------------------------------------------------ |
| abstract                 | class        interface        method                    | Contains   unimplemented methods and cannot be instantiated.        All interfaces are abstract. Optional in declarations        No body, only signature. The enclosing class is abstract |
| final                    | class        method        field        variable        | Cannot   be subclassed        Cannot be overridden and dynamically looked up        Cannot change its value. static final fields are compile-time constants.        Cannot change its value.   *A   final object variable/field means that the variable/field will always   reference the same object.  The data in   the object may be modified, however. |
| native                   | method                                                  | Platform-dependent.   No body, only signature                |
| none(package)            | class        interface        member                    | Accessible   only in its package        Accessible only in its package        Accessible only in its package |
| private                  | member                                                  | Accessible   only in its class(which defines it).            |
| protected                | member                                                  | Accessible   only within its package and its subclasses      |
| public                   | class        interface        member                    | Accessible   anywhere        Accessible anywhere        Accessible anywhere its class is. |
| strictfp   (rarely used) | class        method                                     | All   methods in the class are implicitly strictfp.        All floating-point computation done is strictly conforms to     the IEEE 754 standard. All values including intermediate results must be   expressed as IEEE float or double values. |
| static                   | class        method        field            initializer | Make an   inner class top-level class        A class method, invoked through the class name.        A class field, invoked through the class name     one instance, regardless of class instances created.        Run when the class is loaded, rather than when an instance is created. |
| synchronized             | method                                                  | For a   static method, a lock for the class is acquired before    executing the method. For a non-static method, a lock for the specific object   instance is acquired. |
| transient                | field                                                   | Not to be   serialized with the object, used with object serializations. |
| volatile                 | field                                                   | Accessible   by unsynchronized threads, very rarely used.    |

**All Possible Combinations of Features and Modifiers**

| **Modifier**                 | **Class** | **Variable** | **Method** | **Constructor** | **Free-Floating   Block** |
| ---------------------------- | --------- | ------------ | ---------- | --------------- | ------------------------- |
| public                       | yes       | yes          | yes        | yes             | no                        |
| protected                    | no        | yes          | yes        | yes             | no                        |
| none or   package or default | yes       | yes          | yes        | yes             | yes                       |
| private                      | no        | yes          | yes        | yes             | no                        |
| final                        | yes       | yes          | yes        | no              | no                        |
| abstract                     | yes       | no           | yes        | no              | no                        |
| static                       | no        | yes          | yes        | no              | yes                       |
| native                       | no        | no           | yes        | no              | no                        |
| transient                    | no        | yes          | no         | no              | no                        |
| volatile                     | no        | yes          | no         | no              | no                        |
| synchronized                 | no        | no           | yes        | no              | yes                       |
| strictfp                     | yes       | no           | yes        | yes             | no                        |

 