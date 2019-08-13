# Setting Up and Working with a Maven Application in Eclipse m2e

On its own, Maven is a command line tool. m2e is an eclipse plugin that provides easy Maven integration. It comes included in recent releases of eclipse.

## Before Beginning

To start, please make sure that Eclipse and m2e are configured as described below.

### Eclipse 

Check that the workspace is set to **C:\Users\firstname.lastname\JavaWorkspace**.

\1.    File à Switch Workspace à Other

\2.    Browse to **C:\Users\firstname.lastname\JavaWorkspace** à Ok

Eclipse will restart, opening the workspace above.

Note: As this workspace is located on the C: drive, please ensure that you use your source control repository to store all code.

### M2e Plugin

Check that the m2e Maven integration plugin is configured properly for the Eclipse workspace.

\1.    Window à Preferences

\2.    Select ‘Maven’

\3.    Make sure ‘Download repository index updates on startup’ is checked. This will allow you to look up dependencies through the GUI editor because they will be indexed.

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image002.png)

\4.    Expand ‘Maven’

a.    Select ‘Installations’

​                                          i.    The installation selected should point to **C:\apache-maven-3.0.3**. If it does not, click Add and browse to the correct location. This is the location of the Maven installation on the computer.

​                                         ii.    On the same screen, make sure ‘Global settings from installation directory’ points to **C:\apache-maven-3.0.3\conf\settings.xml**

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image004.png)

b.    Select ‘User Settings’

​                                          i.    Make sure that ‘User Settings’ also points to **C:\apache-maven-3.0.3\conf\settings.xml**

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image006.png)

 

  

|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ![Rounded Rectangle: The global settings file contains the following information:  •	Location of the local Maven repository •	Location of the FDM remote repository mirror •	Firewall proxy settings ](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image007.png) |

\5.    Click ‘Apply’

\6.    Click ‘Ok’

# Working with Maven Projects

## Maven Console    

To open a console showing Maven log statements in Eclipse:

\1.    Open the console view by selecting **Window** à **Show View** à **Console**

\2.    Find the **Open Console** icon on the view, and click the arrow next to it:  ![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image008.png)

\3.    Select **Maven Console** to switch to the Maven console view.

This console will display Maven output that would normally appear on the command line. 

## Form-Based POM Editor

 Even though the POM is an XML file, we can edit it using a form-based interface provided by m2e.

Double-clicking on the POM in the project hierarchy will bring up the form-based editor. However, the rightmost tab, **pom.xml**, allows us to edit the XML of the POM manually.

## Creating a New Maven Project

\1.    Open **File** à **New** à **Project…**

\2.    Expand or search for **Maven** and select **Maven Project**

\3.    Click Next.

\4.    Check the box “Create a simple project (skip archetype selection)

\5.    Click Next
 ![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image010.png)

\6.    Fill in the **groupId** and **artifactId**. 

a.    The **groupId** identifies the company and/or group of projects

b.    The **artifactId** will be the name of your eclipse project 

\7.    Click Finish.

\8.    Four source folders and the pom.xml file will be generated.

a.    **src/main/java             –**          Java classes

b.    **src/main/resources   –**          Classpath resources, such as properties files

c.    **src/test/java               –**          Test cases

d.    **src/test/resources     –**          Classpath resources for test cases

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image011.png)  

\9.    Click on the pom.xml file and open the tab called **pom.xml**. You will most likely see a handful of identifying tags (groupId, artifactId, etc.) and not much else. 

a.    Add the following inside the <project> tag to ensure compilation with Java 6:

```xml
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
```

\10.  **Save** the POM.
 \11.  **Right-Click** project name à **Maven** à **Update Project**…

\12.  The project is ready to run.

## Converting an Existing Project to Maven

\1.    Right-click on the project name.

\2.    Select **Configure** à **Convert to Maven Project**  

\3.    If necessary, make changes to the artifactId and groupId.

\4.    Click Finish.

\5.    The folder structure will not be altered, but a POM will be generated.

a.    Specify the correct compiler plugin configuration (Java 1.6) as we did in Step 9 of **Creating a New Maven Project** (see above)

## Converting a Project Back to a Regular Project    

\1.    Right-click on the project name

\2.    Select **Maven**

\3.    Click **Disable Maven Nature**  

\4.    Delete the pom.xml

## Specifying Dependencies

### Adding a Dependency

\1.    Double-click on the project’s **pom.xml**  

\2.    Click the **Dependencies** tab

\3.    Click **Add…**  

\4.    In the text box marked “Enter groupId, artifactId, or…” begin typing the name of the dependency you would like to add. 

\5.    After a few letters have been typed, a list of matching dependencies will display.

a.    Each can be expanded to show available versions.

\6.    Double-click the one you need, or the dependency name itself to use the latest version.

\7.    **Save**

a.    This step will prompt Maven to build the project. 

b.    Check for errors on the Maven console or at the top of the form-based POM screen.

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image013.png)

Alternatively, the following can be added to the <dependencies> tag in the pom.xml:

```xml
<dependency>

<groupId> **desired groupId** </groupId>

<artifactId> **desired artifactId** </artifactId>

<version> **desired version** </version>

<dependency></dependency>
```

### Removing a Dependency

\1.    Double-click on the project’s **pom.xml**  

\2.    Click the **Dependencies** tab

\3.    Select the dependency to remove

\4.   Click **Remove**  

Alternatively, remove the dependency tag from the pom.xml.

# Troubleshooting

·        **My project’s JRE system library is showing up as version 1.5.** 

If not specified, Maven will default to 1.5. Include the following inside the project tag to instruct the compiler plugin to use Java 6:

```xml
<build>

​           <plugins>

​                <plugin>

​                     <groupId>org.apache.maven.plugins</groupId>

​                     <artifactId>maven-compiler-plugin</artifactId>

​                     <version>3.0</version>

​                     <configuration>

​                           <source>1.6</source>

​                           <target>1.6</target>

​                     </configuration>

​                </plugin>

​           </plugins>

​     </build>
```

·        **Changes to dependencies / settings are not reflected.**

**Right-Click** project name à **Maven** à **Update Project**…

·        **The dependency I would like to add is not showing up in the list.**

Re-index the remote repository.

\1.    Open **Window** à **Show View** à **Other…**  

\2.    Expand (or search for) **Maven** and select **Maven Repositories**  

\3.    Click OK.

\4.    Re-Index the local repository if necessary:

a.    Expand **Local Repositories** 

b.    Right-click the C: drive **Local Repository**

c.    Click on **Update Index** or **Rebuild Index**  

\5.    Re-Index the remote repository if necessary:

a.    Expand **Global Repositories** 

b.    Right-click the **internal-repository**

c.    Click on **Update Index** or **Rebuild Index**

·        **Error Message “Missing artifact log4j:log4j:bundle:1.2.17”**

Open the XML version of the pom.xml, and find the log4j dependency. Remove the <type>bundle</type> tag from the dependency. The bundle version does not exist on the repository, so the correct JAR version should be retrieved.

·        **There is a dependency listed on my form-based POM screen with a question mark (“?”) instead of an artifact name. It generates a “Project Build Error” and clicking Remove does not affect it.**

This is an empty phantom dependency that can be removed manually from the XML. Delete the empty tag:

``` xml
<dependency>

    <artifactId></artifactId>

</dependency>
```



 