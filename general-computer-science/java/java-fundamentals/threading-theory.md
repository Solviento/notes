**Concurrency**

- Concurrency is when a program can perform more than one thing at once.
  - E.g. Listening to music while writing a paper, playing a video game with an integrated messaging system.

- Note: Unless we have multiple processor cores, the program is not actually performing multiple things at the same time.

**How Concurrency Works**

- Concurrency generally works through context switching:
  - 2GHz CPU performs 2000000000 operations per second
  - The time is split up between multiple programs or tasks
  - This is managed by something called the scheduler

<img src="../PHOTOS/threading-theory-01.png" width=600px>

- Each program gets a chance on the CPU for a short amount of time and then another program gets a chance (multitasking)
  - When programs are switched the following occurs:
  - Save old registers and load new registers
  - Save old memory maps and load new memory maps
  - Save old tables and load new tables
  - Save old lists and load new lists
  - ...
- Context switching is costly!

**Multithreading Overview**

- The code we write is executed sequentially:
  - One line executes, then the next executes
  - If a line of code calls a method, then the method executes line by line
  - This can be thought of as a single thread

- A thread refers to a sequence of instructions executed line by line.
- Multithreading is when a single program has more than one thread:
  Several trails of execution in progress at the same time

**Process vs. Thread**

- Both are units of concurrent execution:
  - Process 
    - Usually refers to one program (but multi-process programs exist)
    - Gets its own allocated memory and resources
  - Thread
    - Exist within a process – every process has at least one thread
    - Also called “lightweight processes”
    - Threads share memory and resources with other threads!

**Review – Memory Handling**

- There is one stack per thread, but only one heap per JVM.

<img src="../PHOTOS/threading-theory-02.png" width=600px>

**Benefits of Multithreading**

- Utilize multiple cores
- Perform background tasks
- Deal with several tasks at once
- Improve responsiveness

**How Threading Works**

- In Java, every application has at least one thread – the main thread:
  - Its set of instructions are the code in the main() method
  - Starts running when main() is invoked
- We can create other threads
  - These will run concurrently with the main thread
  - Background system threads also compete with our threads for CPU time
  - The JVM’s thread scheduler decides which one runs when

**Thread States**

- A thread can be in one of the following states:

<img src="../PHOTOS/threading-theory-03.png" width=600px>

**Hazards**

- When implementing multithreading, there are several common issues to be aware of:
  - Race Condition
  - Deadlock
  - Livelock
  - Starvation

**Race Condition**

- The order in which threads execute affects the outcome:

- Race Condition: Two or more threads have access to a single object’s data. In other words, methods executing on two different stacks are both calling, say, getters or setters on a single object on the heap. 
- It’s a whole ‘left-hand-doesn’t-know-what-the-right-hand-is-doing’ thing. 

<img src="../PHOTOS/threading-theory-04.png" width=700px>

**Deadlock**

- Thread A and B are tasked with using the Pen to write on the Board:

- A deadlock happens when you have two threads, both of which are holding a key the other thread wants. 
- There’s no way out of this scenario, so the two threads will simply sit and wait. And wait. And wait…
- One of the most common tips is to pay attention to the order in which your threads are started.

<img src="../PHOTOS/threading-theory-05.png" width=600px>

**Livelock**

- Thread A and B react to each others actions in a continuous loop and are unable to progress:
- A livelock is similar to a deadlock, except that the state of the two processes involved in the livelock constantly changes with regards to the other process.
- http://codingarchitect.wordpress.com/2006/01/18/multi-threading-basics-deadlocks-livelocks-and-starvation/

<img src="../PHOTOS/threading-theory-06.png" width=600px>

**Starvation**

- One thread is unable to get enough time on the CPU due to lack of resources and ends up blocked:
- Starvation is a problem, where a process is perpetually denied necessary resources. Without those resources, the program can never finish its task. Starvation is related to deadlock.
- http://codingarchitect.wordpress.com/2006/01/18/multi-threading-basics-deadlocks-livelocks-and-starvation/

<img src="../PHOTOS/threading-theory-07.png" width=600px>

**Solutions**

- The following techniques are often used to prevent these issues: 
  - Re-entrant methods
  - Synchronization
  - Wait/notify mechanism
  - Locks
  - Atomic operations

**Re-entrant Methods**

- A method is re-entrant if:
  - It does not depend on the state of the containing class
  - It does not depend on any non-final static data 
  - It does not depend on any data that might be changed by other parts of the program

- In other words…
  - All dependencies are passed into the method or created inside
  - Be careful with Single Responsibility and Decoupling here!

**Synchronization**

- Synchronization is a mechanism by which we can control access to data shared by threads:
  - A thread can obtain a lock on an object
  - This prevents other threads from using that object
  - Only one thread can hold the lock at a time

**Wait/Notify Mechanism**

- Provides a flagging system that allows threads to coordinate their activities:
  - Threads can register that they are waiting for a resource
  - Threads can notify other threads when a resource is available

**Locks**

- A system to indicate whether an object is in use by a thread:
  - If a thread has a lock on an object, that means the object is in use by that thread
  - Any other thread looking to acquire the lock will not be able to, unless multiple locks are available

**Atomic Operations**

- Atomic means cannot be broken down.
- Atomic operations are operations which either happen completely or do not happen at all.
- They cannot be broken up into smaller operations.

**Module Review**

- What is multithreading?
- What are the benefits of multithreading?
- What are some of the issues that can happen with multithreading?
- What are some of the solutions to these issues?
- What states can a thread exist in?
- What is context switching?