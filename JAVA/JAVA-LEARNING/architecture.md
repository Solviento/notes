**MVC**

- MVC is one of the most common application architecture design patterns.
  - Model – the business logic
  - View – the user interface
  - Controller – manages Model and View interaction

**MVP  Model - View - Presenter**

- Has come about through with M$ technologies such as Silverlight, Windows Presentation Foundation...
- View-Model is responsible for exposing the data objects from the Model in such a way that those objects are easily managed and consumed. It’s more Model than View...
- “MVVM was designed to make use of specific functions in WPF to better facilitate the separation of View layer development from the rest of the pattern by removing virtually all “code-behind” from the View layer.[4] Instead of requiring Interactive Designers to write View code, they can use the native WPF markup language XAML and create bindings to the ViewModel, which is written and maintained by application developers. This separation of roles allows Interactive Designers to focus on UX needs rather than programming or business logic, allowing for the layers of an application to be developed in multiple work streams”

- Handy read: http://en.wikipedia.org/wiki/Model_View_ViewModel

<img src="../PHOTOS/architecture-01.png" height=350px>

**Tiers vs. Layers**

- Layer
  - Logical separation
  - How the code is organized
- Tier 
  - Physical separation
  - System infrastructure
- A 3 Layer architecture can be on the same machine, or across multiple tiers.
- A 3 Tier architecture involves multiple machines.

**N Layer Architecture**

- Large applications are often separated into 3 layers:
  - Presentation
    - The View and Controller of MVC.
  - Domain logic
    - The Model of MVC. Also known as the domain model, the business model, or the business logic.
  - Data storage
    - How application interacts with data storage system. (Database, flat file, any other type of storage.)
- Layers are decoupled from one another.

**3-Layer with MVC**

<img src="../PHOTOS/architecture-02.png" width=700px>

**2 Tier Architecture: Client / Server**

- The original model for distributed computing:

<img src="../PHOTOS/architecture-03.png" width=450px>

- The “original” distributed computing model.
- “Fat” clients contain all the presentation logic but also a vast amount of business logic.
- This creates a lot of issues for deploying changes to codebase.
  In addition the database may struggle due to the sheer volume of direct connection requests being made with no logic included anywhere to manage those requests.
- Reasons why commercial database solutions have limitations on the number of concurrent connections are varied but include financial constraints such as licensing costs (it’s a “bigger picture“ issue than just “can the kit handle what we want?)

**2.5 Tier Architecture: Client / Server**

<img src="../PHOTOS/architecture-04.png" width=450px>

- 2.5 Tier is a bit of a fudge in terminology (but commonly used)
- Essentially business logic is split out from the client and placed on the db server as a logic layer of stored procedures (hence the .5)
- Benefits: changes to business logic are deployed centrally on the server – reducing deployment issues.
  Downside: database server may still be struggling under the load, and in fact, we’re asking it to do more than classic 2 tier. In addition we may increased network traffic.

**3 Tier Architecture**

<img src="../PHOTOS/architecture-05.png" width=450px>

- Classic 3 tier...
- Take the business logic onto a 3rd server. Reduces db load and we can add logic to manage connectivity better.
- Changes to business logic are again simpler to deploy as it’s on a server.
- Downside: yet more network traffic

**N Tier Architecture: Client / Server**

<img src="../PHOTOS/architecture-06.png" width=450px>

- More tiers if required = N Tier. Specific tiers may have specific roles
- There may be benefits in deploying multiple layers of business logic to refine database requests. Different layers do different jobs.
- Client -> web server (interprets client requests and serves cages)
- Web server -> 2nd middleware server (takes requests formulated by web server, interprets them and makes appropriate db calls)

**N Tier Architecture: Increasing Load?**

<img src="../PHOTOS/architecture-07.png" width=700px>

- As the number of clients increases – server resources may become stretched...
- If time, solicit possible solutions from the class:
- Add a layer to “throttle” requests, perhaps using a message queuing approach?
  Downside: The volume of requests needs to be met quickly, slowing down the handling of requests may not be appropriate as a result
- Rewrite the code on the middleware to be “more efficient”
  Downside: it takes a long time and once you’ve done everything you can it may still not be enough.
- Any other thoughts?

**N Tier Architecture: Managing Load**

<img src="../PHOTOS/architecture-08.png" width=700px>

- Resolve the problem of the increasing load by putting in more middleware servers doing the same job.
- If time, solicit possible discussion from the class:
- How do we decide which clients talk to which server? 
  Add logic in the clients? (not a great idea but it could work)
- Would we end up worrying about “Server Affinity”? i.e. Client must talk to a particular middleware server continuously...
- So what can we do now?

**N Tier Architecture: Balancing Load**

<img src="../PHOTOS/architecture-09.png" width=700px>

- Add a load balancer – which all clients know about. This removes the need for the clients to understand there are multiple middleware servers.
  This is essentially how “server farms” work, multiple servers all doing the same job and the complexity is shielded from clients.
- The load balancer decides which server to fire the client requests at and routes the responses back to the clients
- If people are interested (and you have time) you can have a discussion about “Server Affinity”.
  “Server Affinity” – say the Middleware maintains a session state regarding a specific client. The Load Balancer must take that into account when routing repeat requests and ensure the traffic goes to the correct server.
- Also: we’ve been talking abut ONE db server throughout, could we share load on that tier? Of course we could...
  DB server clustering solutions etc..

**Hexagonal Architecture**

- Also known as "Ports and Adapters Pattern“, similar to onion architecture or clean architecture
- Problem: How do we make our applications work with different automated test suites, UIs and databases without making frequent changes?
- Solution: 2 hexagons, 1 inside the other. The inside hexagon represents the business logic and application logic. The layer between hexagons represents the adapter layer (GoF design pattern). Each side of the hexagons represents the ports, however there can be fewer or more than six ports

**Hexagonal Architecture**

- Traditional Layered Architecture

<img src="../PHOTOS/architecture-10.png" width=700px>

<img src="../PHOTOS/architecture-11.png" width=700px>

- Core
  - This is where the business/domain logic exists
  - The business logic does not have knowledge of the outside world 
    - It knows it can store data but not how
    - It receives requests to perform actions but doesn’t know from where
- Ports
  - Function like APIs
    - Describe how non core logic interacts with the business logic
    - Creates contracts around how questions can be asked to the business logic and the form the response will take.
    - Usually take the form of an interface in Java
- Adapters
  - Concretions 
    - Contain the concrete implementations such as DAOs

**Microservices Architecture**

- A variant of SoA (Service-orientated Architecture)
- No official definition, but some common descriptions:
  - Small applications or processes that communicate over a network to fulfill a larger goal (often HTTP/REST)
  - Each service is independently deployable
  - Each service focuses on a fine-grained business capability
  - A group of services need not be written in the same language
  - Should be able to be re-written in a short amount of time
- Leads to a more modular structure
- Helps with continuous delivery as only one or small amount of services need redeploying/rebuilt

**Microservices Architecture**

- With N-Tier we have:

<img src="../PHOTOS/architecture-12.png" width=700px>

- Moving to a microservices architecture creates more layers and tiers

<img src="../PHOTOS/architecture-13.png" width=700px>

- MVC design pattern defines an architectural approach
  - Variations: MVP, MVVM
- N-Layer Architecture
  - Logical organization of code
- N-Tier Architecture
  - Performance considerations
  - Resilience
  - Ease of deploying change (code maintenance)
  - There is no “right way”
    - Often the options available are determined by external factors (e.g. cost)

- Review the content:
  - MVC is great way to think about designing a system that interacts with the user. And also updates some form of datastore.
  - However, it does not suit all cases, hence the variations MVP, MVVM which may facilitate specific technology approaches.
  - Refer the class to the wiki articles for further study/review on MVC and variations
  - Testing understanding of MVC is a popular “litmus question” at interview...
  - N- Tier architecture is extremely common in enterprise solutions.
    Having an appreciation of some of the approaches allows developers to better understand WHY they need to write code in certain ways...
  - Understanding how the systems you work on/with is set up as an architecture allows better understanding of why the code is written the way it is.
  - Distributed computing (i.e. across the network) often means “purist” OO considerations need to be left aside in the name of performance.
    Understand WHY code is not written the way you expected. Take the limitations imposed by distributed architecture into account when delivering your own solutions.
  - There is no “right answer”, only options available. Often the choices made are nothing to do with code and based on external factors.











