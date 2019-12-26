**Maven Overview** 

- Maven is a build tool.
  - Encourages convention over configuration
  - Driven by a central repository
  - Installed as a command line tool

- M2e is the plugin that allows Maven to run in Eclipse.
  - Make sure the following button appears on the About Eclipse screen:

**Key Plugins**

- Maven plugins provide additional functionality and configuration.
  - Not necessarily dependencies
- You will need to use the maven-compiler-plugin.
  - Sets the target and source Java version
  - Will compile against Java 3 by default

**Configuring m2e**

- Go to Window -> Preferences screen and find Maven on list of topics on the left side of the window.
- Under Maven -> Installations, add the current version of Maven to the list
  - Click the Add button
  - Find directory installation is in
  - Press OK and make sure installation is checked
- Under Maven -> User Settings, set User Settings field to reference conf\settings.xml from the installation’s folder

**Converting Existing Projects**

- A project that already exists can be converted to a Maven project:
  - Right click on the project
  - Go to Configure  Convert to Maven Project
  - Enter Group ID (name of company/domain that will own the product)
  - Enter Artifact ID (name of the resulting product)
  - Click Finish

- This will add pom.xml to the root of your project.

**Disabling Maven Nature of Projects**

- The “Maven Nature” of a project is the way it utilizes Maven’s abilities. 
- To disable a project’s Maven nature:
  - Right click on project name
  - Go to Maven -> Disable Maven Nature
- This will remove the Maven libraries/dependencies from your project.
- If those libraries are necessary, they (and their dependencies) will have to be added manually.
- pom.xml will stay in the project, but will no longer be used by Eclipse.

**Creating a New Maven Project**

- To create a project with a Maven Nature from scratch:
  - From New submenu, select Project…
  - Under Maven, select Maven Project and press Next
  - Check the boxes for Create a simple project and Use default Workspace location and press Next
  - Enter Group ID (company name or domain name) and an Artifact ID (the name of the project to be created) and Press Finish.
- The resulting project will have:
  - main/java and main/resources source folders
  - test/java and test/resources source folders
  - The pom.xml file

**Basic Folder Structure**

<img src="../PHOTOS/maven-01.png" width=600px>

**The POM**

- Project Object Model – pom.xml
- An XML file containing all configuration for a project:
  - Contains full description of the project and dependencies used
  - Identifies the project uniquely
    - groupId – The domain of the company
      - com.fdm
      - org.apache
    - artifactId – The name of the project
      - mockito-core-all
      - junit

- Different types of packaging:
  - war
  - ear
  - jar
  - pom
  - ejb
  - Rar
- Other meta information:
  - Project website
  - Project name

**Dependency Management**

- Maven manages dependencies automatically:
- Downloads required jars from a central repository
- Adds required jars to project
- Allows the specification of different scopes of dependencies

- There are various scopes that we use to specify when to include each dependency:
  - Compiled
    - Will be included during compile and runtime
  - Provided
    - Will be included during compile time, but will not be packaged with the artifact
  - Runtime
    - Will not be included during compile time but will be included at runtime
  - Test
    - Will be included only during the test phase of the build cycle

**Managing the Project Lifecycle**

mvn install

- Will do the following key steps:
  - Compile the source code and place it in a directory called *target.*
  - Compile the tests.
  - Run the tests.
  - Package the project into a jar, as specified in the POM, and place it inside *target.*
  - Install the resulting jar into the local Maven repository as an artifact like any other.

mvn clean

- removing any output generated previously

**Creating Maven Projects**



**Module Review**

- What is Maven?
- What are some common Maven Goals?
- What is the default archetype?
- What does Maven do with dependencies?
- What are the dependency scopes?
- What is the basic folder structure?
- What is the POM?
- Which plugin must you always remember?