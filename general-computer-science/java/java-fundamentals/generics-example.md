# Example

We will start off by considering a scenario related to our Book Shop application.

## Application Requirements

**The book store application will consist of several catalogues of items stored in a database. Each catalogue will be identical in functionality, but will deal with a different type of Item.**

**There should be a Catalogue for the following types of Item: Book, Magazine, DVD and CD.**

**It should be possible to query a catalogue to obtain a list of all the items it contains of that type. Further, an administrative user should be able to add new item objects of the correct type to a catalogue.**

**An item should consist of, at least, a unique identifier and name.**

**It should be possible to remove items from a catalogue via their unique id.**

**An item’s price is subject to change, so functionality to update the price of an item within a given catalogue should be included.**

<img src="../PHOTOS/generic-01.png">

## Requirement Analysis

Building on your solution to the previous exercise allows us to consider the *Book* catalogue already completed. It will probably look something akin to the following:

``` java
**public** **class** BookCatalogue {

​      

​      **private** List<Book> allBooks =  **new** ArrayList<Book>();

 

​      **public** List<Book> getAllBooks(){

​            **return** allBooks;

​      }

 

​      **public** **void** addItem(Book book){

​            allBooks.add(book);

​      }

**public** Book getItem(String isbn){

​            **for**(Book temp: allBooks){

​                  **if**(temp.getIsbn().equals(isbn)){

​                        **return** temp;

​                  }

​            }

​            **return** **null**;

​      }

​      **public** **void** removeItem(String isbn){

​            **for**(**int** i=0; i<allBooks.size();i++){

​                  **if**(allBooks.get(i).getUniqueId().equals(id)){

​                        allBooks.remove(i);

​                  }

​            }

​      }

}
```



On the face of it the next steps involve creating 3 new catalogues, one to deal with *Magazines*, one to deal with *DVDs* and one to deal with *CDs*.

**Question 1: What is the problem with this approach?**

**Answer 1: The Catalogue objects will be identical, apart from the type of Object they are using. We will therefore have a lot of repeated code.**

**Question 2: What is the problem with code repetition?**

**Answer 2:**

**a)**      **If we want to add a new type of Item to our system, we will have to code yet another Catalogue.**

**b)**      **If the requirements for our Catalogues change we will have to change all of them. In our case, that means updating at minimum 4 different classes.**

**This is clearly bad design.**

## Solution 1: Dependency Inversion

One potential solution to this issue of code repetition is to utilise Dependency Inversion to create a Catalogue that is capable of dealing with the general *Item* abstract class and will therefore be able to deal with any sub-class of Item.

*That* class may look something like this:

``` java
**public** **class** ItemCatalogue {

​      

​      **private** List<Item> allItems =  **new** ArrayList<Item>();

 

​      **public** List<Item> getAllItems(){

​            **return** allItems;

​      }

 

​      **public** **void** addItem(Item item){

​            allItems.add(item);

​      }

**public** Item getItem(String id){

​            **for**(Item temp: allItems){

​                  **if**(temp.getUniqueId().equals(id)){

​                        **return** temp;

​                  }

​            }

​            **return** **null**;

​      }

​      **public** **void** removeItem(String uniqueId){

​            **for**(**int** i=0; i<allItems.size();i++){

​                  **if**(allItems.get(i).getUniqueId().equals(uniqueId)){

​                        allItems.remove(i);

​                  }

​            }

​      }

}
```

We will of course need to add a String uniqueId attribute and associated getter and setter to our Item abstract class.

The above class will make our code more reusable, we will no longer require 4 separate Catalogue objects and if we add a new sub-class to *Item*, we won’t have to write another class; our solution is Open/Close.

However, this implementation of Catalogue does not actually meet our defined requirements. Our requirements stated **“There should be a Catalogue for the following types of Item: Book, Magazine, DVD and CD.”** *A separate catalogue*. The above solution creates *one* general Catalogue that accepts *any* type of Item.

**Question: Imagine that we have added some Books to the catalogue, followed by some DVDs and CDs and finally several Magazines. When we request to get something back out of the catalogue, what is the only thing we can say about it for certain?**

**Answer: It will be an Item.**

## Type Safety

Our revised Item Catalgoue is not *type safe*. It will allow an Item of any type to be added to the catalogue, when in fact, our requirements dictate that they should be kept separate.

So, what’s the solution? **Generics**.

Generics will allow us to write *just one* Catalogue class that can be changed at run-time to work for the type of Item we need.

### Recap: Generics in Collections

We have actually already used generics when we used collections.

Let’s think about how we use the *List* class:

List<Book> booklist = new ArrayList<Book>();

List<DVD> dvdlist = new ArrayList<DVD>();

There is only one *List* class, but in one case we are making an object List of Books and in the other an object List of DVDs. We are taking the class, which has been defined with *placeholders* and we are setting those placeholders to be equal to Book, DVD, or some other type entirely.

## Placeholders

So, Generics are about defining placeholders? Yes.

A generic acts as a placeholder for a type, which we can then set later to be something specific.

### Placeholder Example

**private** T item;

In the above line of code we have defined a variable called *item*, but we have not yet said what *type* it will be. We have instead used a placeholder, **T**, which we will set to something more specific later (perhaps a Book, a DVD, or some other type like String or even a List).

## Solution 2: Generics

First, let’s think about how we’d like to use our item catalogue.

### Aspirational Code

In our client, we would like to use our catalogues like below. We wish to use one catalogue class, but tell it to be a catalogue of Books in one case and a catalogue of DVDs in another.

``` java
Book book = **new** Book();

​            bookItem.setUniqueId("9780575094185");

​            bookItem.setName("Do Androids Dream of Electric Sheep?");

​            Catalogue<Book> bookCatalogue = **new** Catalogue<Book>();

​            bookCatalogue.addItem(book);

​            Book returnedBook = bookCatalogue.getItem("9780575094185");

​                        

​            DVD dvd = **new** DVD();

​            dvd.setUniqueId("B000G7P5NO");

​            dvd.setName("Blade Runner");

​            Catalogue<DVD> dvdCatalogue = **new** Catalogue<DVD>();

​            dvdCatalogue.addItem(dvd);

​            DVD returnedDVD = dvdCatalogue.getItem("B000G7P5NO");
```

As you can see, we are using the *Catalogue* object to make both bookCatalogue and dvdCatalogue. It is the same class, but we are telling it to handle different objects each time. We could easily continue this and create a catalogue of Magazine objects and CD objects.

Our class will need to  be generic and use placeholders instead of actual objects:

``` java
**public** **class** Catalogue<T> {

​      

**private** List<T> allItems =  **new** ArrayList<T>();

 

**public** List<T> getAllItems(){

​      **return** allItems;

}

**public** **void** addItem(T item){

​      allItems.add(item);

}

**public** T getItem(String id){

​      **for**(T temp: allItems){

​            **if**(temp.getUniqueId().equals(id)){

​                  **return** temp;

​            }

​      }

​      **return** **null**;

}

**public** **void** removeItem(String uniqueId){

​      **for**(**int** i=0; i<allItems.size();i++){

​            **if**(allItems.get(i).getUniqueId().equals(uniqueId)){

​                        allItems.remove(i);

​            }

​      }

}

}
```

The structure is the same as our class that utilises dependendy iversion, but this time, instead of using the *Item* type to determine what arguments each method takes and what they return, we are instead using a placeholder, **T**.

Notice the error underlining both uses of getUniqueId() we will address this shortly.

**Placeholder Conventions**

**We can actually use anything as a placeholder for our Generics, however a single capital letter is the convention. ‘T’ is a popular choice to stand in for ‘Type’ but another common one (especially in collections) is ‘E’ for ‘Element’.**

The first thing we need to do is define the *class* as Generic. This is done by adding the Generic placeholder in less-than and greater-than (<>) brackets after the class name. This syntax marks the entire class as generic and will give other developers a warning if they try to instantiate the class without telling it which type should be used to fill in for the placeholder. The process of telling a generic class which type it should be using is known as **Parameterizing a Generic**.

Above shows the warning message produced if we forget to parameterize a generic class.

Once we have defined the class as generic, we can then use our placeholder instead of a specific type.

Some uses of note, we have said that our catalogue will contain a List of T objects. The addItem method accepts objects of type T and the getItem method returns an object of type T. These will then transform to be one type or another depending on which object is used for parameterization as below, where the same method in the same class accepts a *Book* in the first case and a *DVD* in the second.

## Limiting Options

As it stands, our current placeholder, **T** can actually be parameterized to *any* object in the Java language. As such, the only thing we can say for certain about it is that it will be some kind of *Object*. 

This means that is we want to run a method of an object defined as type **T** the only ones we can guarantee it has are those 11 defined in the *Object* class, this is why we cannot call getUniqueId(), because currently, **T** could still be parameterized to a type that doesn’t have that method.

Additionally, our catalogues should hold a type of *Item*. Therefore we want to limit **T** to being an Item of some kind. We do that by adding the following code to **T**’s declaration:

**public** **class** Catalogue<T **extends** Item> {

​      //Class body

}           

**Now, T can only be parameterized to Item or a subclass of Item**

This means that we can use getUniqueId() as long as it is defined in *Item* because we can now guarantee that *every* possible valid declaration for T will contain it.

 

**Note**

**We always use the** **extends** **keyword here, even if Item were an interface, it’s just how the Java language is defined.**

 

We have now defined a Generic class that is *Type Safe* and allows us to reuse code.

It is highly extendable. If we add another sub-class of *Item*, then we will have a catalogue that will be able to hold it, we just need to parameterize one to the new type.