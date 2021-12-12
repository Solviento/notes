# Microservices Fundamentals

### What are Microservices?

- Microservices is an architectural style where autonomous, independently deployable services collaborate together to form a broader software application or system
- They should be small but there is no official size limit

### Monolith Benefits

- Monoliths are not "wrong"
- They are suitable for small, single applications or for a single developer team
  - When starting out an application, it is appropriate to start with a monolith architecture as business capabilities are not always well defined at the start
- Simplicity
- One codebase
  - Easy to find things
- Deployment
  - One application to replace

### Monolith Problem of Scale

- Monoliths can work well for small applications
- After several years and many developers years later, the monolith approach becomes a problem
- More users, more data, more complexity means it is more difficult to maintain
  - Entangled modules
  - Difficult to deploy for just a small change
    - Requires downtime
  - Horizontal scaling often is not possible
  - Vertical scaling is possible but expensive
    - Must purchase additional processing power for the entire monolith versus simply providing more power to services that have a high use case
  - Wedded to legacy technology, difficult to upgrade
- Distributed monoliths
  - Slightly more modular than typical monoliths
  - Still shares a common database between the sub monoliths

### Benefits of Microservices

- Small Services
  - can be owned by a team
  - Easier to understand
  - Can be rewritten with other technologies
- Technology choice
  - Can adopt new technology
  - Uses the right tool
    - One might use a functional language, another an OOO language
    - One might use a relational db, another a non-relational db
  - Individual deployment
    - Lower risk of downtime
  - Scaling
    - Scale services individually
    - Cost effective
  - Agility
    - Adapt rapidly
    - Easier reuse
  - Microservices give you choice

### Downsides of Microservices

- New challenges are brought by microservices architecture
- Developer productivity
  - Each service needs to be cloned and setup in machine
  - Need to spend time to optimize developer efficiency on high number of services
- Complex interactions
  - Take care to avoid inefficient, chatty communications between microservices
- Deployment
  - You will need to automate the process of many microservice deployments
- Monitoring
  - Essential to have a great monitoring, centralized place to view logs

**eShop Microservices Demo**

![01](C:\Users\Jason\Documents\Git Projects\notes\microservices\photos\01.PNG)

### What if I already have an existing monolith application?

- Augment a monolith
  - Add new microservices
- Decompose a monolith
  - Extract microservices
- Starting with microservices can be hard as service boundaries are not always clear
- Need to define microservice responsibilities
  - Finding a public interface

### Microservices own their own data

- Microservice data ownership dictates that they avoid sharing data stores with each other
- Each microservice should have its own data store
  - If there is some need to access another microservice data store, then it should go through the microservice API
- Limitations
  - No more database joins across tables
  - Cannot update multiple tables at once
  - Eventual consistency is key
    - Will need to wait a while for data in tables to be consistent once a microservice has made a new change
    - New data changes need to be propagated to other microservices if said data is being shared







