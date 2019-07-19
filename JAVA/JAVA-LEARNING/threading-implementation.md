**Implementation**

- There are two ways to create your own thread:
  - Extend the Thread class, overriding run()
  - Implement the Runnable interface, implementing run()
- The run() method contains the code for your thread to execute.

**Extending Thread**

``` java
public class MyThread extends Thread {
	@Override
	public void run(){
		// The code to execute
	}
}
```

``` java
MyThread myThread = new MyThread();
myThread.start();
```

- Inheritance (IS-A)
  - Less code needs to be  to written in order to start executing the thread
  - Can lead to system design issues
    - If our class extends Thread, it is unable to extend any other class

**Implementing Runnable**

``` java
public class MyRunnable implements Runnable {
	@Override
	public void run(){
		// The code to execute
	}
}
```

``` java
MyRunnable myRunnable = new MyRunnable();
Thread thread = new Thread(myRunnable);
thread.start();
```

- Composition (HAS-A)
  - Preferred method – no issues with inheritance
  - Runnable implementation can be used to create multiple threads
    - Thread pool?

**Key Thread Methods**

- start()
  - Launches thread execution – the run() method is called by the JVM.
- sleep(long millis)
  - Static. Causes currently executing thread to sleep for specified duration.

- yield()
  - Static. Indicates that the currently executing thread is willing to stop running on the CPU to allow others to execute.
- join()
  - Currently executing thread stops running until the specified thread dies.

**Synchronized Keyword**

``` java
public void doSomething(Object anObject){
	// anObject is being used as the lock
    synchronized(anObject){
		// Do something with anObject
        
		// Only one thread at a time can execute 			
        // inside this code block
 	}
}
```

**wait() and notify()/notifyAll()**

``` java
public void fill(Container container) {
	synchronized(container) {
        while(container.isFull())
			container.wait(); 
		container.addItem();
		container.notifyAll(); 	
	}
}
```

``` java
public void empty(Container container) {
	synchronized(container) {
        while(container.isEmpty())
			container.wait(); 
		container.removeItem();
		container.notifyAll(); 	
	}
}
```

**Concurrency Package**

- The java.util.concurrent.atomic package:
  - Provides solutions for performing atomic operations
  - Deals with atomic primitive arithmetic
    - Caution: Atomic and non-atomic objects are not interchangeable
  - Contains advanced concurrency functionality beyond the scope of this course

**Module Review**

- What are the two ways to create your own thread in Java?
- Which is preferred and why?
- Name some key Thread methods. What do they do?
- What is the syntax for implementing synchronization?
- What is the purpose behind the use of wait() and notify()/notifyAll()?













