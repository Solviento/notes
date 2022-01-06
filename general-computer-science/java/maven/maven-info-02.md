# Maven Web Application Set Up Guide

This document will guide you through creating and deploying a Java web application using Eclipse, Maven (through the m2e Eclipse plugin), and Tomcat.

## Before Beginning

To start, please make sure that Eclipse and m2e are configured as described below.

### Eclipse

Check that the workspace is set to **C:\Users\firstname.lastname\JavaWorkspace**.

1. File à Switch Workspace à Other
2. Browse to **C:\Users\firstname.lastname\JavaWorkspace**      à OK

Eclipse will restart, opening the workspace above.

Note: As this workspace is located on the C: drive, please ensure that you use your source control repository to store all code.

### M2e Plugin

Check that the m2e Maven integration plugin is configured properly for the Eclipse workspace.

1. Window à Preferences
2. Select ‘Maven’
3. Make sure ‘Download repository index updates on      startup’ is checked. This will allow you to look up dependencies through      the GUI editor because they will be indexed.

1. Make sure ‘Do not automatically update      dependencies from remote repositories’ is unchecked.

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image002.png)

1. Expand ‘Maven’

2. 1. Select ‘Installations’

The installation selected should point to **C:\apache-maven-3.2.5** If it does not, click Add and browse to the correct location. This is the location of the Maven installation on the computer. Select Finish.

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image006.png)

1. 1. Select ‘User Settings’
       Make sure that both ‘Global Settings’ and ‘User Settings’ also points to **C:\apache-maven-3.2.5\conf\settings.xml**

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image008.png)

|      |                                                              |
| ---- | ------------------------------------------------------------ |
|      | ![Rounded Rectangle: The global settings file contains the following information: •	Location of the local Maven repository •	Location of the FDM remote repository mirror •	Firewall proxy settings ](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image009.png) |

1. e Settings’, ‘Reindex’, and ‘Apply’
2. Click ‘OK’

## Creating a New Maven Web Application

1. File à New à Maven Project

2. 1. If Maven Project is not an option, select       ‘Other…’ and choose Maven Project.

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image011.jpg)

1. Select ‘Next’, making sure that ‘Create a simple      project (skip archetype selection)’ is **unchecked**. 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image013.png)

1. On this screen, we will choose an archetype in      order to generate a pre-set Maven web application. However, there is no      archetype that meets our needs perfectly, so we will use the 

closest suitable archetype with some customization.   Search for **maven-archetype-webapp**.

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image015.jpg)

 

1. Select it and click ‘Next’

1. Fill out the **groupId**,      **artifactId**, **version**, and **package**. Click ‘Finish’.

2. 1. **groupId** – the group of projects this project belongs to
   2. **artifactId** – the unique identifier of this project
   3. **version** – the version identifier
   4. **package** – the base package where all of your code will       be created (be sure that your package names are all lowercase – this will       not be the default)
             
             

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image017.png)

Now the project exists, but some customization is required to fit the expected structure of a webapp. There are also errors that will be removed in the following steps.

1. Right-click on the project à Build      Path à Configure Build Path
2. Go to the ‘Source’ tab

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image019.jpg)

1. Select all folders marked ‘missing’ and click      ‘Remove’
2. Click ‘OK’ 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image021.jpg)

1. Now we need to manually add the required source      folders:

2. 1. Right-click on project name à New à Source Folder
   2. Under Folder Name, type in **src/main/java**  à Click ‘OK’

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image023.jpg)

1. 1. Repeat the process for **src/test/java**, **src/test/resources**,       and **src/main/webapp**.
       **src/main/webapp** will be used to store web content such as web pages. It already exists, but we will add it for easier access within Eclipse.

1. Now we need to make some changes to the POM. Open      **pom.xml** and click the ‘pom.xml’      tab on the bottom.
    ![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image025.jpg)

1. The <dependencies> tag is nested inside the      <project> tag and should look like this:

```xml
<dependencies>
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>servlet-api</artifactId>
			<version>2.5</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jsp-api</artifactId>
			<version>2.0</version>
			<scope>provided</scope>
		</dependency>
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
			<version>1.2</version>
		</dependency>
	</dependencies>	
```

1. The <build> tag is also nested inside the      <project> tag and should look like this:

 ```xml
<build>
<finalName>SampleMavenWebApp</finalName>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.6.1</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>				
        </plugin>
        <plugin>
            <groupId>org.apache.tomcat.maven</groupId>
            <artifactId>tomcat7-maven-plugin</artifactId>
            <version>2.2</version>
            <configuration>
                <url>http://localhost:8088/manager/text</url>
                <username>admin</username>
                <password></password>
            </configuration>			
        </plugin>
    </plugins>
</build>
 ```

1. Save your pom.xml file.

1. Next, expand the **src/main/webapp** source folder, expand **WEB-INF**, and open **web.xml**.
2. We need to change the content of this file stub      with on that links to the correct schema. This one is created for Servlet      API version 2.3, but we will either use 2.5 (for training) or 3.0 (for      future projects, if desired). 
         Replace the content of this file with one of the versions below:

Servlet API 2.5 Stub:

<?xml version=*"1.0"* encoding=*"UTF-8"*?>

<web-app xmlns:xsi=*"http://www.w3.org/2001/XMLSchema-instance"* xmlns=*"http://java.sun.com/xml/ns/javaee"* xsi:schemaLocation=*"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"* id=*"WebApp_ID"* version=*"2.5"*>

  <display-name>Sample Maven Web App</display-name>

  <welcome-file-list>

​    <welcome-file>index.jsp</welcome-file>

  </welcome-file-list>

</web-app>

Servlet API 3.0 Stub:

<?xml version=*"1.0"* encoding=*"UTF-8"*?>

<web-app xmlns:xsi=*"http://www.w3.org/2001/XMLSchema-instance"* xmlns=*"http://java.sun.com/xml/ns/javaee"* xsi:schemaLocation=*"http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"* version=*"3.0"*>

  <display-name>Sample Maven Web App</display-name>

  <welcome-file-list>

​    <welcome-file>index.jsp</welcome-file>

  </welcome-file-list>

</web-app>

1. Save web.xml.

1. Right-click on the project à Maven à Update      Project… à OK

1. At this point, you may have errors with Eclipse’s      project facets. If you do, do the following:

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image031.png)

 

a.    Right-click on the project à Properties à Project Facets

b.    Change the version of Java to 1.8 by selecting the arrow next to 1.5

c.    Uncheck Dynamic Web Module à OK

d.    Open up the Project Facets again: Right-click the project à Properties à Project Facets

e.    Recheck Dynamic Web Module and change the version to 2.5 and. When you recheck this, you should see the information box “Further configuration available”.

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image033.jpg)

 

f.     Select “Further configuration available”. The “content directory” box reads WebContent, leading to errors. Replace that box with “src/main/webapp”.

g.    Click OK. Then, right-click on the project à Maven à Update Project… à OK

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image034.png)


 

 

1. You may now have a web.xml missing error. If you      do, follow these steps:
         
         

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image035.png)
 
 

1. 1. Right-click on the project à Properties à Deployment Assembly

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image037.jpg)

 

1. 1. Select /src/main/webapp and click ‘Remove’
   2. Right-click on the       project à Maven à Update Project… à O

The application should now be correctly set up for use with Maven.

## Deploying a Maven Web Application to Tomcat

1. Start Tomcat using one of the following      methods:

2. 1. Run the script at **C:\apache-tomcat-7.0.64\bin\startup.bat**              
   2. Open the Start menu and search for “Tomcat”. A       shortcut should come up called **Tomcat       7 – startup**. Select the shortcut and click ‘Run’

3. In Eclipse, Right-click on the project à Run As à Maven      build…

4. Under Base Directory, make sure it specifies the      location of our project inside the workspace. 
         For example: **C:/Users/firstname.lastname/JavaEclipseWorkspace/SampleMavenWebApp**            

5. Under Goals, enter **-e clean tomcat7:redeploy**            

6. Maven Runtime should point to the External      installation of Maven, at **C:\apache-maven-3.2.5**

 

![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image039.png)

 

1. Click ‘Run’

1. After some time, the console output in Eclipse      should contain **[INFO] BUILD SUCCESS**      at the end. 

1. Open up a web browser and navigate to **http://localhost:8088/YourProjectName***.* You should see the welcome page      of your web application.

### Tomcat Manager App    

While the application is deployed:

1. Navigate to **http://localhost:8088/***.* You should see links to Tomcat      documentation and other resources.
2. Click on the        ![img](file:///C:\Users\Jason\AppData\Local\Temp\msohtmlclip1\01\clip_image040.png)   button. 
3. You will be prompted for a username and password.      Fill in **admin** as the username,      and do not fill in anything for the password. Click ‘Log In’.
4. Now you will see a list of all applications that      are currently deployed. Your web app should be one of them. 
5. Sometimes you will also see old applications that      were deployed to this Tomcat installation in the past. To clear the list      and remove old deployed applications, open the Start menu and search for      “Reset Tomcat configuration”. Click on the shortcut that comes up and a      script will run to reset the installation.

### Shutdown

When done, don’t forget to shutdown Tomcat using one of the following methods:

1. Run the script at **C:\apache-tomcat-7.0.64\bin\shutdown.bat**            
2. Open the Start menu and search for “Tomcat”. A      shortcut should come up called **Tomcat      7 – shutdown**. Select the shortcut and click ‘Run’

 

 