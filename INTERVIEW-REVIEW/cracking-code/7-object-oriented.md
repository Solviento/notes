# Ch. 7 Object-Oriented Design

Step 1: Handle Ambiguity
- OO questions are asked vague to test whether you'll make assumptions or will ask clarification
- Must ask who is going to use it and how they are going to use it, may need to use who, what, where, when, how, why
- Example case used is to create a object oriented design for a coffee maker

Step 2: Define the Core Objects
- Core objects in a system should be defined
- For a restaurant, the core objects are Table, Guest, Party, Order, Meal, Employee, Server, Host

Step 3: Analyze Relationships
- Once core objects have been decided, must analyze relationships between the objects
- Which objects are sub-members, is there inheritance, many-many or one-many relationships
- For the restaurant question, the following may apply
  - Party should have an array called Guests
  - Server and host inherit from Employee
  - Each Table has one Party but each Party may have multiple Tables
  - There is one Host for the Restaurant
- Talk to interviewer how general purpose the design should be

Step 4: Investigate Actions
- By now you should have a basic outline of the OO DESIGN
- Will need to consider key actions that objects will take and how they relate to each other
- Need to update design if necessary
- Example: Party walks into Restaurant, Guest requests Table from Host, Host looks up the Reservation, if it exists then assign Party to a Table. Otherwise, the Party is added to the end of a list. When Party leaves, the Table is freed and assigned to a new Party in the list

Design Patterns
- Singleton and Factory Method design patterns are widely used in interviews
- Need to look at other books to focus on design patterns

Singleton Class
- Singleton pattern ensues a class has only one instance and access to that instance through the app
- It's useful when you have a global object with just one instance such as a single Restaurant
- Remember this pattern interferes with unit testing
``` Java
public class Restaurant{
  private static Restaurant _instance = null;
  protected Restaurant(){ .. }
  public static Restaurant getInstance(){
    if (_instance == null){
      _instance = new Restaurant();
    }
  }
}
```

Factory Method
- Factory method offers an interface for creating an instance of a class with its subclasses deciding which class to instantiate
``` java
public class CardGame{
  public static CardGame createCardGame(GameType type){
    if (type == GameType.Poker){
      return new PokerGame();
    }
    else if (type == GameType.BlackJack){
      return new BlackJackGame();
    }
    return null;
  }
}
```