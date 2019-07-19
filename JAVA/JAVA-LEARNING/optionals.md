**NullPointerException**

- 70% of applications’ top 10 exceptions included NullPointerException
- "I call it my billion-dollar mistake. It was the invention of the null reference in 1965... This has led to innumerable errors, vulnerabilities, and system crashes, which have probably caused a billion dollars of pain and damage in the last forty years." – C.A.R. Hoare

- There are some interesting points of discussion, but this serves to show that null happens in production code far too often.
- Other notes:
  - NumberFormatException: Happens when parsing Strings into primitives or their wrappers. Trainees should always be aware of this unchecked exception.
  - IllegalArgumentException: This should be used to check for invalid data in public methods.
  - ClassCastException: This shows the need for instanceof as a defensive coding operation.

**Why is it so common?**

- null can be assigned to ANY reference type
- Methods might return null ambiguously
- Methods cannot communicate that they might return null except in documentation
  Requires you to read the JavaDocs: not sustainable!

``` java 
String s = null; int length = s.length();
// Throws NullPointerException

Patron patron = patronStorage.fetch(id); patron.pay(amt);
// Might throw NullPointerException
```

- Map.get(Object key) is notorious for miscommunication via null. get() returns null for one of two reasons:
  - if there is no mapping for the key
  - if the key was mapped to null.

- Only a map.containsKey(Object key) can be sure.
- This speaks to issues with arrays as well, which contain nulls in all indexes when they are first initialized.
- This also speaks to the problems of allows nulls in Collections, which is a separate issue. In general, nulls are dangerous to have floating ANYWHERE in code and should be avoided if possible!

**Current solutions to null**

- Catch NullPointerExceptions
  - Expensive way to achieve 
  - Runtime exceptions should be handled by defensive coding

- Check every return value from a method to see whether it is null
  - Unrealistic expectation
  - Not compiler-checker
  - Hard to remember

``` java
catch(NullPointerException e) { … }

Patron patron = patronStorage.fetch(id);     if(patron != null) { patron.pay(amt); }
```

- The first solution here is especially a non-solution, and should NEVER be used!
- Checking for null sometimes MUST be the solution, if the coder does not control the class. However, this can typically speak to a design flaw, which we will get to with "better ways" to code.

**Null Objects**

- Two types of Null Objects:
  - A representation of empty behavior
  - An optional absence of an object
- We will focus on the second
  - Could be due to an object changing state, such as a stream of data that has been closed
  - Fetching data from a data source: could be there, could be absent

- Null Object design pattern is a separate matter. This deals with having a no-op implementation of the interface, and is beyond the scope of this course. However, it should get trainees thinking about other solutions to problems they have encountered.

**Representing absence of objects**

- A BookStorage interface might declare methods for fetching individual information or fetching bulk information

- What values should these return if there is nothing found?

``` java
class BookRepository {
  //Exclude storage code
  
  List<Book> getAllBooks() {
    //Code for bulk fetch
  }
  Book get(String isbn) {
    //Code for single fetch
  }
}
```

**For arrays and collections**

- Returning an "empty" array or an "empty" collection
  - The results can still be put in a for-each, iterated through, etc.
  - Because there is no data, the for-each will never happen
- This promotes safety of using the returned collection, whether there is data or not	

``` java
List<Book> getAllBooks() {
  List<Book> books =
    new ArrayList<>();
    for(Book book :
        storage.getAll()) {
      books.add(book);
    }
    return books;
  }
```

- If checking the size beforehand is cheap, Collections.emptyXxx can be used as well for collections meant to be "read-only". As of Java 9, List.of() and other interface static factory methods can be used as well.

**For single objects**

- So far, null has been our empty object holder
- This is what introduces the issues with NullPointerException
- This can be done with another representation: Optional!

``` java
Book get(String isbn) {
  Book result =
    storage.get(isbn);
  if(result != null) {
    return result;
  } else {
    return ???;
  }
}
```

- There are some primitive specializations (OptionalInt, OptionalLong, and OptionalDouble) that may come up, but are beyond the scope of this presentation. Trainees working with these should be able to transfer their understanding of this object to those specializations, just as the Stream -> PrimitiveStream specializations.

**Optionals**

- Represent a value that might be present.
- Uses generics: Optional<T> can represent any optional type.
- An Optional communicates that the method might not return a value
  - Interactions with Optionals: Cannot just call methods of the internal object without extracting the object from the Optional.

- This is the key point of the presentation. Optionals represent what might be there, and communicate to the user of the object that they need to check the object. Any reference might be null, so no communication is there to let a user know that map.get() might return null. All classes that "might" return a single value should return Optionals. This should carry over to DAOs, and can be used in the Registration Controller exercise.

- For more on this, see the API note on Optionals in Java 9: https://docs.oracle.com/javase/9/docs/api/java/util/Optional.html

**Creating Optionals**

- Optionals are constructed with one of three static factory methods.
  - Optional.of(T value) – Contains the value given
    - Fails fast on null: throws NullPointerException if given null
  - Optional.empty() – Contains no value
  - Optional.ofNullable(T value) – Either contains the value given, or an empty optional if null is given

``` java
Optional<Book> get(String isbn) {
  Book result = storage.get(isbn);
  if(result != null) {
    return Optional.of(result);
  } else {
    return Optional.empty();
  }
}
```

``` java
Optional<Book> get(String isbn) {
  return Optional.ofNullable(
    storage.get(isbn));
}
```

- There are some theories on whether Optional.ofNullable(T value) should always be used, or of(T value) and empty. It is situational, but Optional.of() tends to read a bit better in code, and it communicates more clearly to those maintaining the codebase when a null/not null should be expected. As shown in the code example, null checks may be worth explicitly doing here. However, many methods are fine with failing fast on null (such as building an optional around a parameter that should never be null), so the check is not always strictly necessary.

**Using Optionals**

- Checking and using values:
  - isPresent() : boolean – Returns true if the optional has a value
  - get() : T – Returns the value if it is present
    - Note that this method throws a NoSuchElementException if there is no value in the optional. Always check with .isPresent() before calling get()
  - orElse(T other) : T – Returns default if no value is present
    - ifPresent(Consumer<? super T> consumer) : void - If a value is present, use it via a Consumer implementation

- There are plenty of other methods that are useful here. They are all listed here for supplemental purposes.

- Default actions other than orElse(T other) and ifPresent(Consumer<? super T> consumer)
  - orElseGet(Supplier<? extends T>other) : T – Returns the value if present, or else uses the supplier to fetch a result. This is very similar to orElse(T other), except uses a supplier, which looks like:
    - Book book = optionalBook.orElseGet(Book::new)
    - Or,                  optionalBook.orElseGet(() -> new Book("defaultIsbn"));
    - This method ensures the default is only created if there is no value present, unlike orElse(T other), which ALWAYS creates the other object. In general, this one is preferred, but is harder to grasp, and so is relegated to the notes section.
- <X extends Throwable> Optional.orElseThrow(Supplier<? extends X> exceptionSupplier) : T – Returns the value if one is present, or throws an exception provided by the supplier if there is no value present. This is commonly written with method references, like so:
  - Book book = optionalBook.orElseThrow(CustomException::new).
  - If arguments are required: .orElseThrow(() -> new CustomException("Custom message"))
- Inherited value-based object methods
  - equals(Object obj), hashCode(), toString() : good value-based implementations are provided for each of these.
- Methods useful for streams of Optionals or method chaining
  - filter(Predicate<? super T> predicate) : Optional<T> - filters to an empty optional either if the value does not meet the predicate or if there is no value.
    - optionalBook = optionalBook.filter(book -> book.getEdition().equals("First Edition"));
  - <U> map(Function<? super T, ? super U> mapper) : Optional<U> - Maps the Optional to a new type of optional if the value is present, or an empty Optional if it is not. Useful for post-processing on optional values returned as a terminal operator, but can be used midstream as well.
    Optional<String> title = optionalBook.map(Book::getTitle);
  - <U> flatMap(Function<? super T, Optional<U>> mapper) : Optional<U> - Similar to map, but maps to a different type of Optional explicitly. Then, flatMap returns that Optional rather than wrapping the value in an Optional. Useful to avoid Optionals of Optionals, which is not a useful construct.
    - An example right from Oracle’s article on this deals with getting the version string from the USB port of a soundcard on a computer. If the soundcard is optional, and the USB is optional, returning computer.map(Computer::getSoundCard) maps to an Optional<Soundcard>, and chaining that to .map(Soundcard::getUSB) would result in an Optional<Optional<USB>>. Flat map takes the “double” optional and flattens it to a single. Thus, code like this is easy to write:
    - String name =
           computer.flatMap(Computer::getSoundcard)
                           .flatMap(Soundcard::getUSB)
                           .map(USB::getVersion)
                           .orElse("UNKNOWN");

- In newer versions of Java, more methods: Java 9
  - ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) : void – Similar to ifPresent, but runs a default action with no parameters if there is no value. A "Runnable" in this context need not be run in its own thread, but represents a Consumer with no arguments to consume.
    - optionalBook.ifPresentOrElse(book -> patron.purchase(book), () -> patron.notify("That book is unavailable"));
  - or(Supplier<? extends Optional<? extends T>> supplier) : Optional<T> - Similar to orElseGet, but returns an Optional default instead. Can be useful with chaining multiple Optional methods together.
  - optionalBook.or(() -> Optional.of(defaultBook)).ifPresent(Book::displayDetails)
  - stream() : Stream<T> - This incredibly useful method can be found for transforming a stream of Optionals into a Stream of the contained values, which can be much safer than trying to extract the values from the Optionals without the use of default values. This is used in the Stream.flatMap method, as such:
    - stream.flatMap(Optional::stream).map(…)

- Java 10
  - orElseThrow() : T – Functions exactly as get(), but communicates that it might throw an exception. As of Java 10, this is the preferred method call to get() and should be used in all instances where get() would be called.

- Java 11
  - isEmpty() : boolean – Inverse of isPresent() for readability.

**When to Use Optionals**

- Optionals should only be used as a return value
- Optionals should be used when one piece of data "might" be returned, such as from a Data Access Object
  - Optional data from Collections should still return the Collection type and not populate it
- Optionals should not be used as parameters
- In particular, it is fine to have nullable data in objects as private state
  - This may or may not be represented by an Optional
  - Annotations are a cleaner option for fields to represent nullability

- There are strong opinions on whether fields should or should not be Optionals. The design of Optionals is intended to make APIs more communicative on whether they might return a value. However, using Optionals for nullable fields also communicates to the developer without requiring comments. Because fields are private, development teams can change between using Optionals or not at their own discretion without affecting the API.

- One of the larger arguments against using Optionals as fields is that Optionals are not currently Serializable. There are solutions to this is customized writeObject and readObject methods, or by using Serialization Proxy classes (see Effective Java for more on that), but it does create additional programming overhead. If your data is Serializable, it is recommended to not use Optional fields. This is especially true for DTOs and other value objects.
  Additionally, Optionals rely on generics. This means that, if an Optional stores another generic object, or a wildcard reference to Optionals comes along, confusing results can occur. Finally, generic types are erased at runtime, so Optionals can be dangerous for projects that rely on reflection.

- Annotations (such as @Nullable and @NonNull) can be used as well for fields, but these are not standardized. However, annotations will not prevent the object from being serialized, reduce the complexity of using the state of an object internally, and are in general a cleaner choice than Optionals as state.

- If an Optional is used for a field, it is still imperative that it is NEVER used as a parameter, especially for setters and constructors. As a parameter, this does not eliminate required null checks, and adds additional overhead for users of the class.

Module Review

- What are some issues that occur with using null?
  - NullPointerExceptions, lack of communication

- What should be returned if a collection of items is empty?
  - An empty collection (possibly from Collections.emptyXxx)

- What should be returned if an item might be present?
  - Optional<T>

- How do you construct an Optional instance?
  - Optional.of, Optional.empty, Optional.ofNullable

- What are some common methods of Optional?

  - isPresent, get, orElse, ifPresent. Many more.

- Where is the most appropriate place to use Optional?

  - As a return value

    











