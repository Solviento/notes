# Maps

You have already encountered a number of Collections within Java- List, Set and Queue. Here we will be looking at another type of collection, the Map.

**Key Distinction**

**The Map object in Java is a collection in the everyday English sense of the word in that it collects objects together, however it is not actually part of the Collection (big ‘C’) hierarchy as it does not inherit from the Collection interface.**

**Note that Map is an interface, just like the others.**

<img src="../PHOTOS/maps-01.png">

Implementing Classes- https://docs.oracle.com/javase/7/docs/api/java/util/Map.html

Map has a number of implementing classes, as can be seen from its API.

Commonly used implementations include: **HashMap, HashTable** and **TreeMap**

## Why is Map Different?

Map works slightly differently to the other collections in Java because of the way it stores data. Rather than storing a number of Element objects like List, Set and Queue do, Map actually stores *two objects per element*.

## Key-Value Pairs

Map stores elements much like the other collections (an element can be any kind of object, be that a String, User, Book or anything else).

In Maps, this element is called a **Value**.

In addition the Map also stores a unique **Key** corresponding to each Value.

This means that every ‘thing’ stored in a Map is in fact two ‘things’: a Key, to uniquely identify the thing and a Value, which is typically the thing itself.

Because of this Key-Value relationship, we do not refer to elements in a Map via their position (as we might in a List, for example), but instead via their Key.

## Example

Let us consider a Map that will store *Book* objects. The **Value** will of course be the book itself, whilst the **Key** should be something unique to each book. We will use the ISBN number, which is best represented as a String.

First, we need to create the Map. Like the other collections, we need to tell it what kind of objects it will be storing/dealing with, using the generic (<>) syntax. However, as Map holds *two types of objects*, we need to tell it the *two types* that it should deal with.

### Instantiating a Map

Map<String, Book> bookMap = **new** HashMap<String, Book>(); 

We will tell it the type of Key to expect first, and the type of the Value, or element.

### Adding Objects to a Map

Book book1 = **new** Book();

​            book1.setIsbn("1234567ABC");

​            

​            bookMap.put("1234567ABC", book1);

Here we have instantiated a *Book* object and added it to the Map using the put method. The put method takes two arguments, first the key (unique identifier) and then the value (the object we wish to store).

Note that the following syntax is more elegant, but does the same as above:

bookMap.put(book1.getIsbn(), book1);

### Retrieving Objects from a Map

Book returnedBook = bookMap.get("1234567ABC");

This syntax lets us get a Book back again by asking for the Value that corresponds to a specific Key (which is defined to be unique).

# Comparing Elements

All collections are made up of elements, but some collections are defined as being **Sorted**, that is the elements appear within the collection in an order defined by some feature of the elements themselves.

Some examples may be a Set of books sorted by price, or a Queue of employees sorted by alphabetical order of their last names.

There are a number of ways to compare objects in Java.

## Equals

The simplest way to compare objects in java is to use the equals(Object obj) method as defined in the *Object* class. Every object inherits from the Object class, so every object has an implementation of equals.

http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#equals(java.lang.Object)

By default the equals method will compare the references of objects, rather than their attributes.

Observe the following code:

Book book1 = **new** Book();

book1.setIsbn("0-575-01587-X");

​            book1.setName("Rendezvous with Rama");

​            

​            Book book2 = **new** Book();

book2.setIsbn("0-575-01587-X");

​            book2.setName("Rendezvous with Rama");

​            

​            **boolean** equals = book1.equals(book2);

System.*out*.println(equals); //Will print out false

Here, we have two book objects, both with the same ISBN and title. However, they are distinct *objects*, so when we run the equals method and compare them, it returns **false**.

This makes sense from a memory handling perspective, but from the point of our application they could be considered the same. As such, we may wish to *override* the equals method and cause it to look at an objects attributes, rather than just its memory references.

Overriding an equals method must be done in a very specific way, and ideally our overridden implementation should meet the 5 rules defined in the API of the equals method in the Object class.

Luckily, we have a tool that can perform this process for us: Eclipse. We can get it to generate an equals method that is both efficient and meets all of the conditions required. To do this, open up your book object and, in eclipses menu bar select:

Source à Generate hashCode() and equals()… à Select the attributes you wish to compare on (all of them will be selected by default à Ok

If the book objects has two attributes, ISBN and title, the following equals method will be generated:

​      @Override

​      **public** **boolean** equals(Object obj) {

​            **if** (**this** == obj)

​                  **return** **true**;

​            **if** (obj == **null**)

​                  **return** **false**;

​            **if** (getClass() != obj.getClass())

​                  **return** **false**;

​            Book other = (Book) obj;

​            **if** (isbn == **null**) {

​                  **if** (other.isbn != **null**)

​                        **return** **false**;

​            } **else** **if** (!isbn.equals(other.isbn))

​                  **return** **false**;

​            **if** (title == **null**) {

​                  **if** (other.title != **null**)

​                        **return** **false**;

​            } **else** **if** (!title.equals(other.title))

​                  **return** **false**;

​            **return** **true**;

​      }

This method will safely and comprehensively compare the Book object to any other object, Book or otherwise and return true if they are ‘equal’. Equal in this case means they are of the same *type* (Book) and have the same ISBN and title.

## HashCode

You’ll notice that Eclipse also generated an overridden implementation of hashCode() (another method inherited from Object).

http://docs.oracle.com/javase/7/docs/api/java/lang/Object.html#hashCode()

The hashCode method exists to allow the collections that store elements in groups to be more efficient. A prominent example is HashTable. Collections like this group objects into ‘buckets’ to make searching faster and take less resources, as the hashcodes can aid in narrowing down a search before using the equals method to find the exact object we are looking for.

Like equals, there is a set of rules that must be followed when overriding hashcode:

·         Equal objects *must* have the same hascode

·         Objects with the same hashcode are not necessarily equal

Eclipse will have taken care of this for us. The key point to remember is that if equals is overridden, it’s usually required that we override hashcode as well.

## Sorting Elements in a Collection

A common thing that we may want to do within a collection is to sort its elements. Indeed, there are some collections, such as the TreeSet, that will automatically sort itself every time a new element is added.

Sorting a collection requires knowledge of how the elements relate to one another though. Let’s consider a book:

If we have a collection of 5 book objects, how are we going to sort them?

·         By Title?

·         By ISBN?

·         By Price?

·         By Publication Date?

All of the above are valid answers, depending on the situation and need.

There are two ways that we can define how we can compare objects in Java, Comparable and Comparator. Both are interfaces that define a method that can be used by collections to perform sort operations.

Within these methods we can define custom implementations that dictate whether one object should come before or after another using a simple rule.

·         If object A should come *before* object B, a negative int should be returned

·         If object B should come before object A, a positive int should be returned

·         If they are equals, the int 0 should be returned

### Comparable

https://docs.oracle.com/javase/7/docs/api/java/lang/Comparable.html

**A comparable object can compare itself to other objects of the same type.**

The comparable interface is designed to be implemented by the object type of the elements to be sorted, as below.

**public** **class** Book **implements** Comparable<Book>{

​      

@Override

​      **public** **int** compareTo(Book otherBook) {

​            **if**(**this**.price < otherBook.getPrice()){

​                  **return** -1;

​            }

​            **else** **if**(**this**.price > otherBook.price){

​                  **return** 1;

​            }

​            **else**{

​                  **return** 0;

​            }

​      }

Here, the attribute that we are comparing the book objects on is **price**. Therefore, they will be sorted by price, lowest to highest.

#### Drawbacks of Comparable

Here, our object can compare itself to other objects of the same type and work out whether it should come before or after the other object in a sorted collection. There are however two drawbacks to this approach:

\1.       We can only compare on one attribute or set of attirbutes.

·         If we wished to sort books by price in one location and by title in another, the *Comparable* interface is not the right choice

\2.       Book is no longer fulfilling the Single Responsibility Principle

·         Book now does two things, represent a book and compare books to one another for ordering- it now has more than on reason to change. This is bad design.

### Comparator

https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html

**A comparator is an object who’s sole responsibility is to compare other objects.**

The comparator interface is implemented by an object separate to the object to be sort.

**public** **class** BookPriceComparator **implements** Comparator<Book>{

​      @Override

​      **public** **int** compare(Book book1, Book book2) {

​            **if**(book1.getPrice()< book2.getPrice()){

​                  **return** -1;

​            }

​            **else** **if**(book1.getPrice()>book2.getPrice()){

​                  **return** 1;

​            }

​            **else**{

​                  **return** 0;

​            }           

​      }

}

The above object compares two book objects based on their price.

If, in another part of our application, we wished to compare objects based on their publication date, we would simply create another comparator that compared those dates instead.

**public** **class** BookPublicationDateComparator **implements** Comparator<Book>{

@Override

**public** **int** compare(Book book1, Book book2) {

​      **if**(book1.getPublicationYear() < book2.getPublicationYear()){

​            **return** -1;

​      }

**else** **if**(book1.getPublicationYear()>book2.getPublicationYear()){

​                  **return** 1;

​      }

​      **else**{

​            **return** 0;

​      }

}

}

Therefore, using Comparator solves both of the two issues encountered when using Comparable.

### Using Comparables and Comparators

If a Collection is explicitly sorted by default, like a TreeSet, then we will need to either, only add elements to it that implement the Comparable interface, *or* give it a Comparator when we create it.

Set<Book> sortedBookSet = **new** TreeSet<Book>(**new** BookPriceComparator());

If we want to sort a normally unsorted collection, like an ArrayList, then we will need to use the *Collections* helper class. Specifically it’s sort methods. http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html#sort(java.util.List)

If the elements in the collection implement the Comparable interface, we can simply give the sort method the collection:

Collections.*sort*(bookList);

If the elements in the collection do *not* implement the Comparable interface, we will instead need to give the sort method the Comparator that it should use to sort the elements. It is of course possible to sort on a different attribute depending on the Comparator we use.

We could sort on price:

Collections.*sort*(bookList, **new** BookPriceComparator());

or we could sort on publication dates:

Collections.*sort*(bookList, **new** BookPublicationDateComparator());

Or, indeed, we could sort on any attribute for which we have defined a Comparator.