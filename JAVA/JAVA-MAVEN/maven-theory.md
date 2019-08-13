# Maven Introduction

This document will introduce you to the Apache Maven build management tool.

## What is Maven?

- Maven is a build tool. It helps us manage a project’s lifecycle and dependencies. 
- We interact with Maven by executing tasks related to the project lifecycle, such as compiling source code, compiling test code, running test code, packaging the project into a JAR file, and so on. 
- Another aspect of Maven is that it follows the “convention over configuration” philosophy. If the application follows Maven conventions, the tool does not require a lot of configuration to work as expected. 

## History

- 1970’s – make and makefiles allow the developer to customize and automatically build a project.
- 2000 – Apache Ant emerges as a build tool for Java, using XML files and scripts.
- 2005 – Apache Maven is developed. Unlike Ant, Maven focuses on fully managing a project, not just the build process alone. 

## How It Works

- Every project is required to have a Project Object Model (POM) – an xml file that dictates what the project needs in order to be built and deployed, as well as how it should be deployed. 
- Another feature of Maven is that it treats all Java projects as nodes and leaves in an endless graph of *dependencies*.  This means that each project depends on zero or more other “projects”, such as libraries and frameworks like JUnit.  A project’s dependencies are listed in its POM file.  
- One of the primary reasons Maven is so widely used is because it will download and include all of the project’s dependencies with minimal effort on the developer’s part. 

## Key Terminology    

- **artifact** – any software project or module that can be deployed. Each artifact is uniquely identified. 
- **dependency** – an artifact needed by your project in order to compile, build, test, and run. These can be libraries and classes we import in our code from packages outside our project (JUnit, log4j, Mockito, and so on.)
- **pom.xml** – The Project Object Model. An XML file containing information about the project and configuration details needed to build it, including its dependencies.
- **repository** – a location where artifacts are physically stored, typically in the form of JARs. There are two types – local and remote.
- The **local repository** is a local cache of previous remote downloads, and also holds temporary, not yet released artifacts. If multiple projects depend on the same artifact, only one copy needs to be stored in the local repository.
- The **remote repository** can be the Maven central repository, or a mirror. We will be using a remote repository on FDM’s own servers to download dependencies from.

## Identifying Artifacts

- Each artifact is identified in a standardized way with the following data:
- **groupId**:         The group of projects that this project belongs to. (e.g. org.mockito)
- **artifactId**:        An identifier for this particular project, unique within the group.
- **version**:          The project version. A “Snapshot” version means that the artifact is still in development.

- There are other identifiers for more advanced functionality.

## Dependencies

- The POM contains a list of dependencies, each identified with the data above.

- Maven will read a project’s POM and attempt to load all of its dependencies from the local repository. If not found, Maven downloads them from the remote repository and saves them to the local repository. The dependencies are then added to the project’s build path.

## pom.xml Layout

- The root element is named `<project>`. All other tags will be nested inside it. Key POM tags are:

- `<groupId>, <artifactId>, <version>`, etc. – information about the project
- `<build>` – information about building the project
- `<dependencies>` – a list of dependencies, each identified in a `<dependency>` tag. 

- For example, here is a pom.xml file for a project that depends on Apache log4j:

``` xml
<project xmlns=”http://maven.apache.org/POM/4.0.0” xmlns:xsi=”http://www.w3.org/2001/XMLSchema-instance” xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  	<!-- Identifies this project. -->  

	<modelVersion>4.0.0</modelVersion>
	<groupId>com.fdmgroup</groupId>
	<artifactId>MavenExample</artifactId>
	<version>0.0.1-SNAPSHOT</version>

	<build>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.0</version>
				<configuration>
					<source>1.6</source>
					<target>1.6</target>
				</configuration>
			</plugin>
		</plugins>
    </build>
    <!--This plugin will compile our code. Note that we are setting source and target to 1.6 (Java 6) -->

	<dependencies>
		<dependency>
			<groupId>log4j</groupId>
			<artifactId>log4j</artifactId>
			<version>1.2.17</version>
		</dependency>
	</dependencies>
    <!-- List of dependencies. -->

</project>
```

## Dependency Scopes

- A `<scope>` tag can be nested in a `<dependency>` tag. It identifies which project lifecycle phases this dependency will be needed for. There are five scopes:

- compile
  - (Default)
  - The dependency will be needed to build, test, and run project

- provided
  - Needed only to build and test
  - Still needed to run, but will be provided by another party.
  - (E.g. The Java Servlet APIs are provided by Apache Tomcat.)
- runtime
  - Not needed to build
- Needed to test and run 
  - (E.g. Reflection allows us to use classes at runtime that the compiler is not aware of.)
- test
  - Needed to build and run only the unit tests
  - Not needed to build and run the project
- system
  - Needed to build, test, and run
  - Retrieved from location on disk, instead of repository
  - Must specify location if using system scope

## How Maven Manages a Project

- Maven organizes its functionality with **goals** and **plugins**: 

- **Goals** are specific tasks that have to do with building or managing a project. They can be executed directly, on their own.

- **Plugins** are collections of these goals (also called “plugin goals”). 

- For example, the **maven-compiler-plugin** is composed of two goals
  - **compiler:compile**                  – compiles the source files
  - **compiler:testCompile**           – compiles the test cases

- Maven is sometimes called a “plugin execution framework”. Its functionality comes from executing goals within plugins.

- Maven also relies on the idea of a build lifecycle.

- A **lifecycle** defines the overall process for building and delivering a project. 

- There are three built-in lifecycles:
  - default – describes the deployment of the project
  - clean   – describes the cleaning of the project
  - site       – describes the process for generating documentation

- Each lifecycle is composed of lifecycle **phases**. These represent stages of a lifecycle. Each phase has goals (tasks) bound to it. 



## Executing Commands

- We can use Maven to manage our project by executing lifecycle phases.
- Phases are executed sequentially; invoking one phase will also execute all that lead up to it, in sequence.  All goals bound to those phases will be executed.
- Goals can be invoked individually as well, but this is rarely done.

- A common invocation is **mvn clean install** on the command line. This invokes the clean phase of the clean lifecycle (will clean up previously generated output), and the install phase of the default lifecycle (will compile, test, package it, and install it into the local repository).

<img src="../PHOTOS/maven-theory-01.png">