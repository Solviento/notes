<h1>Collections</h1>
**Introduction**

What is a collection?

- A collection is a way of grouping objects of commonality together
- Java provides a Collections Framework as part of its API, which includes
  - Interfaces (List, Map)
  - Concrete implementations (ArrayList, Stack)
  - Algorithms (Sort)
- Builds on standard arrays to provide further functionality such as a mutable collection size

- Each entry inside a collection is referred to as an element
- Different collection types have different rules upon them:
  - Ordering
  - Sorting
  - Retrieving/inserting elements

**Ordered Collections**

- A collection may be ordered or unordered 
- In an ordered collection, we can iterate over elements in a specific order
- In an unordered collection, the elements have no relation to each other

Sorted Collections

- A sorted collection is ordered according to some rule imposed by the element content
  - It maintains its sorted state as elements are inserted and removed
- An unsorted ordered collection is ordered in a way that has no relation to the element constant

**The Collection Interface**

- The Collection interface is the root of the collection hierarchy
- It defines basic operations like add() and remove()
- Three key interfaces extend it: List, Queue, Set

<img src="../PHOTOS/collections-diagram-01.png" width=800px>

**The List Interface**

| List (Interface)            |
| :-------------------------- |
| boolean add(int index, E e) |
| E get(int index)            |
| int indexOf(Object o)       |
| E remove(int index)         |
| E set(int index, E e)       |

- An ordered collection, also known as sequence
- Defines methods to manipulate elements by their position
  - Index driven, starts from 0
- Allows duplicates

**List Implementations**

- ArrayList
  - Fast
- LinkedList
  - Appends
- Vector
  - Slow, thread safe

<img src="../PHOTOS/collections-diagram-02.png" width=800px>

**The Queue Interface**

| Queue (Interface)                             |
| --------------------------------------------- |
| boolean add(E e) throws IllegalStateException |
| E remove() throws NoSuchElementException      |
| E element() throws NoSuchElementException     |
| boolean offer(E e)                            |
| E poll()                                      |
| E peek()                                      |

- Allows duplicates

<img src="../PHOTOS/collections-diagram-03.png" width=800px>

**The Set Interface**

- Models a mathematical Set
- Does not define any new methods, but adds restrictions to inherited methods
- Does not allow duplicates
- HashSet 
  - stores elements in a Hashtable.
- TreeSet 
  - stores elements in a tree, natural-order for elements, by ascending value, so it is slower than HashSet.
- LinkedHashSet 
  - implemented as a hash set with a linked list running through it, insertion-order.
- With HashSet and LinkedHashSet the objects you add to them must override hashCode() otherwise the default Object hashCode will allow multiple objects that you might consider “meaningfully equivalent” into your “no duplicates allowed” set.

**Set Implementations**

<img src="../PHOTOS/collections-diagram-04.png" width=800px>

**The Map Interface**

| Map (Interface)                     |
| ----------------------------------- |
| V put(K key, V value)               |
| boolean containsKey(Object key)     |
| boolean containsValue(Object value) |
| V get(Object key)                   |
| V remove(Object key)                |

- Defines an object that maps keys to values
  - Each key has one value
  - No duplicate keys
  - Duplicate values are okay
- Part of the Collections Framework, does not extend Collection

**Collection Views**

- The collection view methods allow us to view a map in three ways:
  - keySet – the set of keys in the map
  - values – collection of values in the map
  - entrySet – set of key-value pairs contained in the Map

- To iterate over a map you HAVE TO use the collection views. E.g. 

``` java
for (KeyType key : m.keySet())
   System.out.println(key);

for (Map.Entry<String, Integer> element : m.entrySet())
{
   	System.out.println(element.key);
 	System.out.println(element.value);
}
```

**Map Implementations**

- HashMap 
  - the location of the key on the map will depend upon it’s hash code. So the more efficient your hashcode, the better access performance you’ll get. HashMap can have one null key and multiple null values in a collection. 
- Hashtable 
  - a synchronized version of HashMap. 
- Linked HashMap 
  - maintains insertion order. Slower than HashMap for inserting and removing elements, but faster iterations. 
- TreeMap 
  - a map sorted by the natural order. 

<img src="../PHOTOS/collections-diagram-05.png" width=800px>

**Ordered and Sorted**

<img src="../PHOTOS/collections-diagram-06.png" width=800px>

**public boolean equals(Object obj)**

- Used to determine whether an object is equal to another
- Defined in the Object class, inherited by all classes
- Can be overridden in custom data types
- Can take any field or combination of fields into account, as desired
- equals() is invoked by collections when finding elements

``` java
Object o1 = new Object();
Object o2 = new Object();
o1.equals(o2);
```

**hashCode()**

- Returns an int hash code value for any given object
  - Can be overridden
  - Based on the same things being compared in equals
- Buckets Objects together
  - Acts as a coarse grained equals
- Used as a way of narrowing down a search
- If two object have the same hash code, they do not have to be equal
- If two objects are equal, they have the same hash code

**Hashed Collections**

- Some collections use hash codes to organize their elements
- This reduces number of elements to check when searching

**Comparisons**

- Collections which are sorted (PriorityQueue, TreeMap) requires a means to compare storable objects

- Java provides two interfaces to do this

  - **Comparable**

    - Comparable is defined in java.lang package.

    - Comparable is an interface with a single method
  
      - ``` java
      public int compareTo(Type t)
        ```

    - An object that implements this can be compared to other objects
  
      - Return 0 if they are the same
    - Return 1 if it is greater than object t
      
    - Return -1 if it is less than object t
      
    - ``` java
      public class User implements Comparable<User>
      {
      	private String username;
      	
      	public int compareTo(User u){
      		// Code to compare a User object to other User objects
      	}
    }
      ```
  
    - ``` java
      User admin = new User();
      User shareholder = new User();
    admin.compareTo(shareholder);
    ```
  
- **Comparator**
  
    - Comparator is defined in java.util package. 
  
    - Limited, as you need to make an instance of class Comparator in order to test to of your objects. Which means that the Comparator instance cannot ‘see’ any of your objects private fields. 
    
  - Comparator is an interface with two methods
    
  - ``` java
        public int compare(Type t1, Type t2);
      public boolean equals(Object o);
    ```
  
    - An object that implements this is designed to compare two other objects to each other
  
      - Returns 0 if they are the same
      - Returns -1 if t1 is less than t2
      - Returns 1 if t1 is greater than t2
    
    - ``` java
      public class UserComparator implements Comparator<User>
    {
          public int compare(User u1, User u2){
        	// Code to compare User objects goes here
          }
      }
      ```
    
    - ``` java
      User shareholder = new User();
      User admin = new User();
      UserComparator comparator = new UserComparator();
      comparator.compare(shareholder, admin);
      ```

**Collections Class**

- Collections is a class that provides functionality for
  - Sorting and ordering
  - Searching
  - Converting
  - Min and Max entries
  - Singleton collections
  - Empty collections
  - Extracting elements within a collection
  - Many algorithms needed to deal with Collection objects
- All methods are static!

- **Collections.sort(List<T> list)**
  - Sorts elements in a list where every element implements the Comparable interface.
    The objects will be sorted based on their natural order defined by the CompareTo method.
- **Collections.sort(List<T> list, Comparator<? Super T> c)**
  - This method must take a Comparator

























































































