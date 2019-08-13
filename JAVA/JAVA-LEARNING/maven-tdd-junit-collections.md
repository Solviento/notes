A Further Look at Maven
So far, we have used Maven to generate and organise packages within our project, however, it is capable of much more.
Project Object Model- pom.xml
Let’s first take a look at the pom.xml file that should be at the bottom of your project. Double click it and select the pom.xml tab at the bottom of the view that has just opened.

This XML file is being used by Maven to configure our program.
Towards the top of the document, you can see where the groupId, artifactId and version that we entered when we created the project are stored.
Java Version Targeting
By default, Maven compiles and runs Java code for version 1.5 of Java, but Maven lets us target any version of the Java platform, if we have a higher version of the Java Development Kit installed.
For example: If we have Java 1.7 installed on our computer, we could target our code at any version of Java, from 1.7 all the way back to 1.0
Question: Why might we want to compile our code using an earlier version of Java?
Answer: Backwards compatibility for older systems.
Maven-Compiler-Plugin
We can change which version of Java is being used by using the maven-compiler-plugin, add this code to the pom.xml after the </dependencies> tag is close, but before </project> is (these tags have been included below to show you where the new elements need to go, do not repeat them).
</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.3</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>  
This code tells Maven to use the maven-compiler-plugin and configures it to treat the Java source code as being written in Java 1.6 and compile it to Java 1.6 Byte Code.
Dependency Management
You will no doubt have noticed the <dependencies> tag in the pom.xml that contains a reference to JUnit.
A dependency is another project that this one depends on. Often, it is easiest to think of dependencies as external libraries that we wish to use within our project. JUnit is one such library.
Without Maven, we must download and add these libraries to our project manually. Maven takes some of the hard work out of this process by allowing us to simply declare the libraries we want and it will go away and automatically download them for us, installing them into our project.
Throughout the course, we will be suing a number of external libraries, the only one we need right now is JUnit, which Maven adds automatically because Test Driven Development is such a popular process; it assumes you will be doing it.
JUnit Dependency
Version 3.8.1 is a really old version of Junit, we will be using one of the newer versions. Change the version to 4.10, as below.	
<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.10</version>
			<scope>test</scope>
		</dependency>
	</dependencies> 
As mentioned, this will cause Maven to download JUnit 4.10 and install it into our project for us, allowing us to reference it when needed.
The <scope>test</scope> part of the dependency says that we will only be using JUnit when we are running our unit tests. We will add different dependencies later on in the course that are in different scopes. 

Test Driven Development (TDD)
The idea behind test driven development is that before we write any code, we create a series of tests that will use our code. This lets us drive the development of our production code with tests to ensure that every line of code we write adds value to our solution.
The 5 Steps of TDD
1.	Write the test
2.	Make the test compile
3.	Watch the test fail
4.	Do just enough to get the test to pass
5.	Refactor and generalise
Following these steps allows us to work up to our final solution, ensuring that each piece we build does what we need it to do before we move on.
Qualities of a Good Test
A good test should be:
1.	Focused- It should only test one thing
2.	Easy to read- The test name should make it clear what the test is doing. It should be self-documenting
3.	Simple- Don’t overcomplicate your test with loops and decisions
4.	Independent- Individual tests should not affect each other in any way
5.	Flexible- A test and the code it is testing should be able to be re-used in different projects without having to change anything
Discussion Point: Why is each of the above qualities important? What advantages does following them bring? 
TDD Example
Create a new Maven project  with the artifactId of TDDBookShopWalkthrough and update the pom.xml to include both the newer version of JUnit and the maven-compiler-plugin.
Before we write any code, we want to make sure we are happy with what we want it to be able to do. For this we turn to the Use Cases or User Requirements, which will indicate the application’s desired functionality.
The book shop application will consist of a catalogue of books. It should be possible to query the catalogue to obtain a list of all books it contains. Further, an administrative user should be able to add new book objects to the catalogue.
A book object should consist of a title, an author, a unique ISBN number, a price and the number of pages the book contains.
The catalogue should be able to hold any number of books and it should be possible to remove books from the catalogue via their ISBN number.
A book’s price is subject to change, so functionality to update the price of a book in the catalogue should be included.

The key object referenced here is the Catalogue object, so we will focus on that first.
CatalogueTest
Right click on your package, com.fdmgroup.tddbookshopwalkthrough in your test source folder, src/test/java, and select:
New Other JUnit Test Case Name it CatalogueTest
This class will contain all of our tests for our Catalogue object. Note that we have not created the Catalogue class yet.
@Test
Eclipse has added a sample Test for us within the generated test class; it is marked with the @Test annotation. Annotations are typically used as flags in the Java language, this particular one is advertising to the JUnit framework that this method should be ran as a Unit Test.
Delete this first, example test now.
 
Test 1- Step 1: Write the Test
It can sometimes be difficult to make a start unit testing, so the first test is often the most difficult. Typically, you will want to start with the simplest possible behaviour. We will begin with requesting an empty list of books:
When queried for the books it contains, an empty catalogue should return a list of length zero. 
Test names should be highly descriptive and will consist of 3 sections:
1.	The method that is going to be tested
2.	The expected outcome
3.	The initial conditions
@Test
public void test_GetAllBooks_ReturnsAListOfLengthZero_WhenTheCatalogueHasHadNoBooksAdded(){
		//test code
}
Once we have defined our test name we will build our test.
Test should be built whilst considering the three A’s:
1.	Arrange
	•	Set up our test and any initial conditions
2.	Act
	•	Call the method being tested, passing any required arguments
3.	Assert
	•	Look at the result of running the method being tested and make sure it does what we want. JUnit has a large number of pre-defined methods to help us perform these checks. Consult the API for more information:
http://junit.sourceforge.net/javadoc/org/junit/Assert.html 
@Test
public void test_GetAllBooks_ReturnsAListOfLengthZero_WhenTheCatalogueHasHadNoBooksAdded(){
		//Arrange
		Catalogue catalogue = new Catalogue();
		//Act
		List<Book> booksInCatalogue = catalogue.getAllBooks();
		//Assert
		assertEquals(0, booksInCatalogue.size());

	} 
List
Within the test we are using an object of type List.
You can think of a List as an array that is able to both grow and shrink in size (something array cannot do) and which generally offers more options for data manipulation.
Here, the <Book> syntax simply means that this is a List of Book objects; we cannot put anything inside our List that is not a Book, or a subclass of Book. This gives us a degree of Type Safety.
The angled brackets denote a Generic. Generics will be covered in more detail later on in the course. 
Test 1- Stet 2: Make the Test Compile
We now need to write enough code to make our test compile. Remember we will write no logic at this stage, simply empty classes and methods.
	•	Create a Book object in your package in the src/main/java source folder. It should have no attirbutes at this stage.
public class Book {

}
	
•	Create a Catalogue object in the same package.
public class Catalogue {
	
		public List<Book> getAllBooks(){
			return null;
		}
}
Catalogue has one method, getAllBooks that takes no arguments and returns a List of Book objects. We just want our code to compile, so for now, return null (an empty piece of memory), we will return something more useful later.
Test 1- Step 3: Watch the Test Fail
Run the test. You can press ctrl-F11 with the test class open, or right click on the test class body or file name and select Run As  JUnit Test
Eclipse should display a red bar, indicating the test has failed.
Question: Why is it important to watch the test fail?
 
Test 1- Step 4: Do just enough to make the Test Pass
We will add the bare minimum code to force our test to pass. This is to ensure the logic is correct, we can come back and the code nicer later.
public class Catalogue {
	
		public List<Book> getAllBooks(){
			return new ArrayList<Book>();
		}
}
Note: List is an interface, so we cannot create and return one directly. Instead we will be using ArrayList, a concrete implementation.
Consult the Java API for other concrete implementations of List:
http://docs.oracle.com/javase/7/docs/api/java/util/List.html
Test 1- Step 5: Refactor and Generalise
We have so little code at this point that there is very little refactoring we can do to either our production code or our test class. As such, for now, we can move onto the next test.


Next Steps
•	Take a break to briefly reflect on what we have just covered
•	Test 2: After one book has been added, the List returned should be of length 1.
•	Test 3: After adding one book, the catalogue should contain that same one book
•	Test 4: After adding a Book we should be able to remove it using its ISBN number and get a List of length zero.


Collections
Within our TDD example, we used the List and ArrayList objects on a number of occasions. Let’s take a more detailed look at collections in Java.
Collection Definition
In everyday English, a collection is a group of things. The same is true in Java.
A thing inside a collection is referred to as an Element.
Collection Hierarchy
There is an interface in Java called Collection:
http://docs.oracle.com/javase/7/docs/api/java/util/Collection.html
Collection sits at the top of the Collection hierarchy (hence the name) and has several child interfaces below it (as can be seen in the API). There are 3 in particular though that we are interested in:
•	List
•	Set
•	Queue
Each of the above are interfaces and therefore have no implementations of their own. Instead we use implementing classes, some of which will be discussed below.
List
Of all the available collections, list is the closest to an array in implementation.
•	Can hold any number of elements
•	Allows duplicate elements
•	Is index driven
o	We refer to elements by their position within the List
•	Is zero-based
o	The first element is at position zero (like in arrays)
•	Is mutable
o	Can grow and shrink in size (unlike arrays)
Implementing Classes- https://docs.oracle.com/javase/7/docs/api/java/util/List.html
List has a number of implementing classes, as can be seen from its API.
They are all useful for different purposes, but ArrayList, LinkedList and Vector are 3 of the most commonly used. Look at the API for each of these and consider where you might use them.
 
Set
A Set is similar to a List, but with 2 key differences.
The first is that it is not index driven and does not have an easy way to extract a single element. The reason for this is that many Set implementations are unordered and so retrieving a specific element is not as simple as asking for the one at a specified place.
Sets cannot contain duplicates
Otherwise Sets:
•	Can hold any number of elements
•	Are mutable
Implementing Classes- http://docs.oracle.com/javase/7/docs/api/java/util/Set.html
Set has a number of implementing classes, as can be seen from its API.
Commonly used implementations include: HashSet, LinkedHashSet and TreeSet
Queue
Queues, as you would imagine from the name, allow you to store objects in a queue like manner. Queues are not designed to have elements removed from arbitrary positions within them and are often used as temporary storage before processing every element within the queue.
There are two fundamental forms of queue:
First-In-First-Out (FIFO)- Common across many implementations
Last-In-First-Out (LIFO)- Implemented by some concretions of Queue
All queues have a Head element, which is the ‘next’ element in the queue and a Tail element, which is the last element in the queue and is where new elements are added.
Queues:
•	Can store any number of elements
•	Are mutable
Implementing Classes- http://docs.oracle.com/javase/7/docs/api/java/util/Queue.html
Queue has a number of implementing classes, as can be seen from its API.
Commonly used implementations include: LinkedList and PriorityQueue
 
Collections
In addition to the Collection interface that sits at the top of the collection hierarchy, there is also a class called, potentially confusingly, Collections (note the ‘s’ at the end).
Its API can be found here:
http://docs.oracle.com/javase/7/docs/api/java/util/Collections.html
Collections consists of a number of helper methods that allow us to manipulate the various collections spoken about above, such as providing support for sorting, shuffling, reversing, copying, querying etc.
Take a look at the API and keep it in mind if you come across a situation where you need to manipulate a collection in some way. There may well be a built in way of achieving your goal.