# Debugging A Maven WebApp

 

\1.       Create a new WebApp Maven project.

a.       The procedure here is exactly the same as always. Select the *webapp* archetype and set up the project as normal. Remember to including all required web Dependencies as well as the maven-compiler-plugin and the tomcat-maven-plugin in the *pom.xml*.

\2.       No we need to convert it into an Eclipse compatible web project.

a.       Make a new *run-configuration*

​                                                               i.      It can be named whatever you wish

​                                                             ii.      The base directory should point at the project you wish to convert

​                                                            iii.      The goal is: 

**eclipse:eclipse –Dwtpversion=2.0**

b.      Run this configuration. The first time you do this it will take some time to download some files before converting the project.

c.       Once you receive the BUILD SUCCESS message, refresh your project explorer. There should be two new files within your project: *JAX-WS Web Services* and *Deployment Descriptor: MavenWebDebug*

\3.       Enable the *Servers* view within Eclipse (window->show view-> others -> servers).

a.       Right-click with the S*ervers* box, and select *new->server ->Apache -> Tomcat v7.0 Server*. Ensure the server’s name is *localhost*.

b.      Click *next->browse*

​                                                               i.      Navigate to c:\apache-tomcat7.x.xx. Select this folder and press *ok.*

c.       Click *next.*

d.      Select *YourProjectName* and move it to the right hand side of the window.

e.      *Finish*.

\4.       Double click the server you have just created, *Tomcat v7.0 Server at localhost,* which will open up a configuration window.

a.       Within this window, find the section *Server Locations* and select *Use Custom Location ->Server Path -> Browse.*

​                                                               i.      Navigate to an empty folder somewhere on your hard drive. This is a custom folder we will set up to hold our configuration information. If you do not have a folder already, make one. Select this custom, currently empty folder. *Ok.*

*b.*      Now, still within *Server Locations* select *Deploy Path -> Browse.*

​                                                               i.      Create a new folder within the one you created above and call it **webapps**. Navigate to this folder and select it. *Ok.*

c.       Copy the *manager* and *ROOT*  folders from the *webapps* folder in your machines existing Tomcat 7 installation (found from *Start-> All Programs-> Apache Tomcat-> Apache Tomcat 7.x.xx.*) into the *webapps* folder you have just created above.

d.      You have now set up a custom location to deploy your project to from within Eclipse.

\5.       Now we want to check the server is set up correctly.

a.       Right-click your server (*Tomcat v7.0 Server at localhost*) and click *start*.

b.      Open a web browser in Eclipse (the globe icon on the top toolbar) and navigate to [http://*localhost:8088*](http://localhost:8088) (note the http:// is required).

​                                                               i.      Navigate into the manager as normal (username: admin, password: *leave blank).*

​                                                             ii.      You should see your project, *manager* and */.*Your project has been deployed automatically.

\6.       Now it is time to write some web code.

a.       Create a Servlet within the root folder *src/main/*java adding any custom package structure you desire after right-clicking and selecting *new -> Servlet*. 

b.      Your web.xml will be automatically updated to map the new servlet to a URL corresponding to /*ServletName*. Obviously, you can change this manually if you wish.

c.       Create a new .jsp in WEB-INF called *Hello.jsp*.

d.      In your servlet add the following code to its *doGet* method:

**RequestDispatcher rd = request.getRequestDispatcher(“WEB-INF/Hello.jsp”);**

**rd.forward(request, response);**

e.      Add a break point to the first line, where the *getRequestDispatcher* method is called.

\7.       We can now debug our application.

a.       Restart the server in debug mode by right clicking on it and selecting *Restart in Debug*.

b.      Right-click on your project and select *Debug As-> Debug on Server.* Select your server (it should be the only one) and click *Finish.*

\8.       A web page should open in Eclipse. If it does not, open one and navigate to http://*localhost:8088*

a.       Select your project and navigate to [*http://localhost:8088/YourProjectName/YourServletName*](http://localhost:8088/YourProjectName/YourServletName)

\9.       You should now be shunted into debug mode as your application runs the servlet and hits the breakpoint. You can debug your project as normal.

 

**Note: For the time being this only works in non-Spring web projects. It should work with Spring, but there is some more configuration information that is needed and it didn’t work properly in the demonstration.**





2	Maven and Eclipse
2.1	Pre-requisites
1.	You have chosen and created a new workspace within Eclipse

In order to use Maven with your new workspace you need to configure your workspace.
2.2	Why
The reason for this is that there are certain configurations put into the build which enable Maven to run, without this configuration you will not be able to use Maven and Eclipse.
2.3	Steps
1.	Go to Window -> Preferences
2.	Within the window that comes up select Maven
3.	Expand Maven and select installations
4.	Click the Add button
5.	Navigate to C:/apache-maven-3.0.3/
6.	Click open
7.	Make sure that the external install is selected
8.	Click Apply
9.	Go to User Settings
10.	In the config file box (the first one) Navigate it to C:/apache-maven-3.0.3/conf/settings.xml
11.	Click Update Settings
12.	Click Apply
13.	Click OK
	2.4	Finish
You should now be able to use Maven with your workspace. You will need to repeat these steps for each workspace you create.
	3	Maven and Tomcat
	3.1	Pre-requisites
This sessions assumes the following:
1.	You have create a new maven project based upon the webapp archetype
	3.2	Why
Tomcat has had a configuration change regarding the default port used. This means that you need to configure maven to use this port rather than that standard port.
The new port is 8088, the old port was 8080
	3.3	Steps
1.	Open the pom.xml
2.	Within the build->plugins tag type the following:
<plugin>
		<groupId>org.codehaus.mojo</groupId>
		<artifactId>tomcat-maven-plugin</artifactId>
		<version>1.1</version>
		<configuration>
			<url>http://localhost:8088/manager/html</url>
		</configuration>
	</plugin>
3.	Save the file
4.	Deploy the application by creating a maven run configuration for that project using the goal tomcat:redeploy
	3.4	Finish
This should now work; you will need to do this for all web projects done this way.
	4	Tomcat and Eclipse
	4.1	Pre-requisites
This assumes that you are using Eclipse and that you are deploying to Tomcat.
This will look at the different ways to achieve this, i.e. internal and external installations
	4.2	Why
It is possible to do this in several ways so it should be clear which way is being used.
	4.3	Steps
	4.3.1	Internal
This is for Tomcat configured as an internal server within Eclipse.
NOTE: This method can NOT be used with Maven configured projects!

1.	Go to new -> other
2.	Search for server and select server
3.	click next
4.	Go to Apache ->Tomcat 7
5.	Click next
6.	For tomcat installation directory click browse and navigate to C:/apache-tomcat-7.0.22/
7.	Click open and click next and finish
8.	Go to new
9.	Select other
10.	Type web into the search
11.	Select dynamic web project
12.	click next and fill out the relevant information
13.	Create the relevant files that you want and then deploy by:
	a.	Right click the project
	b.	Run as...
	c.	Run on server
	d.	Select the tomcat 7 server
	e.	This will then throw up the internal browser
14.	Any dependencies that you use must also be manually copied into the /WEB-INF/lib/ folder, otherwise the application will not work. Alternatively use Maven and the preferred external method.
	4.3.2	External (Preferred)
This is for Tomcat running outside of Eclipse as a stand along server.

1.	Ensure that Tomcat is running externally
2.	Create a new Maven project from the webapp archetype
3.	Apply the configuration mentioned in section 3
4.	Create a new Maven run configuration and use goal tomcat:redeploy