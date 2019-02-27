# System Design

- Ask good questions
    - What features to work on
        - What features do they care about, what do they not care about
    - Define the minimal viable product to work on
    - How much should the system scale?
        - Latencies in the system
        - How much data needs to be stored, how many requests per second
- Don't use buzzwords
    - Don't throw words like MD5 hashing unless you know for sure what they are
    - Make sure you know the technologies that you discuss well
- Clear and organized thinking
    - Define all APIs
    - Draw out all actors of the system
- Drive discussions
    - You should be talking 80% of the time
    - Lead the discussion, anticipate the problems in your solution and fix them preemtively

A detailed list

- Features
- Define APIs
- Availability
    - How available are these services?
    - If data center went down, how available will it be?
- Latency Performance
    - If speed is an issue, a cache might be necessary
- Scalability
    - 100 vs 1 million users
    - Will it scale as we add users and more requests
- Durability
    - Data is not compromised
    - Make sure that system is durable
- Class Diagram
    - OO principles
- Security and Privacy
    - Important to designing security service
- Cost effective
 
