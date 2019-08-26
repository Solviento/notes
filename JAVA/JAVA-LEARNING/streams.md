**Module Objectives**

- Understand what a stream is
- Identify key methods used within Streams 
- Identify an intermediate method 
- Identify a terminal method
- Use the Streams to filter, map and collect a stream
- Identify what objects can be used with a stream
- Discuss the purpose behind parallel streams

**What is a Stream?**

- A Stream is an interface in Java which enables us to act on a stream of data such as a collection or an IO Stream.

- It provides methods to assist in filtering, mapping and collecting a stream of data into another data type.

- It was introduced to the Java programming language in Java 8 and has many dependencies on Functional Interfaces to support the use of lambda functions. 

**Why is it used?**

- The Stream API provides simple to use functions, which require less code to achieve commonly occurring tasks with streams of data in Java. 

- Like lambda functions they streamline our existing code by reducing lines required to do certain tasks.

- Developers can however write code that does the same thing without the use of streams. 

- Enable the use of parallel stream manipulation to improve efficiency.

Simple interface to carry out everyday code. 
Parallel streams – not covered yet enable us to utilise all cores of the CPU when processing data.

**What can be treated as a Stream?**

- In Java all classes that implement Collection can be treated as a stream of objects.

- In addition to reference Streams there are streams tailored for primitive types such IntStream. Others can be found at in the java.util.stream packages. 

- Arrays in java can also be used as the data source to streams using Arrays.stream(Object[] arr) utility to handle the conversion. 

- Finally some classes within the IO packages offer methods to convert the input into a stream such as BufferedReader. 

- Collections have a stream() to access the stream. 
BufferedReader has a lines() to access all lines read from a file as a stream
Get the class to look at the java.util.stream package.