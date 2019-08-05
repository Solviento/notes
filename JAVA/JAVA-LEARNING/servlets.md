**Agenda**

- Recap – web architecture
- Web containers
- Web servlets
- Deployment Descriptor
- Request Handling
  - Sending information to the server
  - Redirection
  - Forwarding

**Recap of Web Architecture**

- What is meant by the terms client and server?
- What is the World Wide Web?
- What is HTTP and how is it used?
  - What are the key parts of an HTTP request?
  - What are the key parts of an HTTP response?
- What is the difference between static web pages and dynamically-generated web pages?

**Introducing the Web Container**

- A simple web server application can only serve static resources
  - Served the same way every time (images, audio files, simple HTML…)
  - E.g. Apache HTTP Server

- To generate pages dynamically, the help of another application is needed
  - Web container (or servlet container)
  - E.g. Apache Tomcat
- The APIs for Server-side programming are part of Java EE

- Both Apache Tomcat and Apache HTTP Server are open source and very widely used.

**Web Container Responsibilities**

- The web container handles incoming HTTP requests by:
  - Running servlet code to generate responses
  - Security (restricting access to server resources)
  - Concurrency (each request is handled in a new thread)
  - Lifecycles of all components (Servlets, JSPs, Filters, Listeners, etc.)
  - Application configuration and deployment

**Introducing Servlets**

- Web Servlets are server-side Java components – simple Java classes!
- Servlets process HTTP requests and generate HTTP responses:
  - Requests are addressed to individual Servlets
  - Responses can differ depending on the request content

<img src="../PHOTOS/servlets-01.png">

- Servlets don’t have a main method so the web container is in charge of running these
  application. 

- The methods that handle HTTP requests within a Servlet look like this:

``` java
void doPost (HttpServletRequest req, HttpServletResponse resp) {		PrintWriter out = resp.getWriter();
	out.println(“<html>”);
	out.println(“<head><title>view Stock</title></head>”);
	out.println(“<body><p>” + currStock + ”</p></body>”);
	out.println(“</html>”);
}
```

- The resulting HTML response content can differ every time.

**Demo 1 – Generate HTML from Servlet**

- Step 1 - HTML file
  - Make index.html page with a link to an Eclipse-generated servlet. 

- Step 2 - Servlet
  - In the doGet() use a PrintWriter to output HTML.

- Step 3 - Server
  - Add project to server and run.
- Goals: Navigate from an HTML link to our servlet-generated HTML code.

**HttpServlet**

- Our servlets will extend abstract class HttpServlet (from the Java EE API).
  - HttpServlet extends GenericServlet

- The container calls its service() method to handle each request.
- service() calls the corresponding HttpServlet method:
  - doGet()
  - doPost()
  - etc.
- **Override these to give your servlets functionality**!
- Do not override service()!

**Servlet Lifecycle**

- For each servlet, the web container will do the following:
  1. Load its class
  2. Create an instance – only one for each servlet!
     1. Can be done at startup
     2. Can be done when it is requested for the first time
  3. Initialize by calling its init() method 
  4. Call its service() method in a new thread for each request addressed to the servlet
  5. Call its destroy() method (sometimes done to reclaim memory)

- A servlet object spends most of its life in the service() method.
- init() – you could put initialization code into your servlet’s init() method. E.g getting a database connection.

**The Deployment Descriptor**

- A configuration file called the deployment descriptor (web.xml) specifies how the application is to be deployed:
  - Servlet, Filter, and Listener declarations
  - Which URLs are mapped to which Servlets
  - Security constraints
  - Login configuration

- The container deploys the application according to these instructions.
- With Java EE 6, much of this information can be supplied via annotations.

**Mapping a Servlet to a URL**

``` xml
<servlet>
  <servlet-name>Servlet name</servlet-name>
  <servlet-class>com.fdmgroup.MyServlet</servlet-class>
</servlet>

<servlet-mapping>
  <servlet-name>Servlet name</servlet-name>
  <url-pattern>/location</url-pattern>
</servlet-mapping>
```

- <servlet-name> is the deployment name, known to the deployer.
- <servlet-class>  is the class name, known to the developer.
- <url-pattern> is the servlet’s URL, known to the public.

- Servlet name – known to deployer
- com.fdmgroup.MyServlet – known to developer
- /location– public URL pattern, known to all clients

- Multiple URLs can be mapped onto a Servlet.

- Servlet mapping information: “defines mappings between URL paths and the servlets that handle requests with those paths. The web server uses this configuration to identify the servlet to handle a given request and call the class method that corresponds to the request method (e.g. the doGet() method for HTTP GET requests).” https://developers.google.com/appengine/docs/java/config/webxml

**Demo 2 – Map a Servlet to a URL**

- Step 1 – Servlet mapping
  - Add servlet mapping to the deployment descriptor.

- Step 2 – URL
  - Change the URL in the HTML page to match the URL pattern in the deployment descriptor. 

- Step 3 – Server
  - After modifying the deployment descriptor, you must restart the server.
- Goals: Navigate from an HTML link to a servlet, without hardcoding the servlet name in the HTML. 

**Sending Information to the Server…**

- HTML’s  <form> tag lets us send a GET or POST HTTP request:

``` html
<form method=“POST” action=“location”>
<input type=“text” name=“parameterName” />
	<input type=“submit” value=“Submit” />
</form>
```

- Hitting the ‘Submit’ button sends an HTTP request to the server
  - The container forwards it to a Servlet mapped to /location

- The <input> elements become the request parameters
  - parameterName is the key
  - What the user types into the text box is the value

**Retrieving Client Information Server-side…**

- Inside a Servlet, user input can be retrieved using parameterName as a lookup key:

``` java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	PrintWriter out = response.getWriter();
	out.print(“<html><body>”);
	out.print(“You entered:” + req.getParameter("parameterName"));
	out.print(" </body></head>");

}
```

**HttpServletRequest** **&** **HttpServletResponse**

- A pair of objects is created to service a single request:
  - The HttpServletRequest object contains all data sent by the client 
  - The HttpServletResponse object can be used to construct the response

- … and passed to every component that processes the request:

``` java
protected void doGet (HttpServletRequest req, 
						HttpServletResponse res) {
	// Request processing
}
```

- Both are request scoped and therefore thread-safe 

**Demo 4 – Retrieve Input from a Form Server-side**

- Step 1 – Form 
  - Form input parameters have meaningful attribute ‘name’.
- Step 2 – Servlet
  - Use the HttpServletRequest object to access the form parameters from within the servlet.
    Ensure you use the parameter names as they are written in the HTML form.

- Goals: Access HTML form data from within the servlet, using the request object.

**Request Redirection**

- So far, you have seen how a Servlet can construct an HTML response.
- Another thing a Servlet can do is send back a redirect:
- A redirect tells the client’s browser to send a second request to the specified URL, possibly to a different server.

``` java
void doPost (HttpServletRequest req, HttpServletResponse resp) {
	resp.sendRedirect(“http://www.website.com”);
}
```

**Redirection**

<img src="../PHOTOS/servlets-02.png">

**Demo 5 – Send a Redirect**

- Step 1 – Response object
  - Use the HttpServletResponse to redirect the user to http://www.google.com.
- Step 2 – Browser
  - Notice that when the response is loaded, the browser address bar shows the new URL.

- Goals: Redirect to a different URL.

**Request Forwarding**

- A Servlet can also forward the request to another Servlet within the same application for further processing.

- Note: If the first Servlet has already written something to the PrintWriter, it will be ignored once you call forward()

``` java
void doPost (HttpServletRequest req, HttpServletResponse resp) {
	RequestDispatcher requestDispatcher = 
					request.getRequestDispatcher(“location”);
	
	requestDispatcher.forward(request, response);
}
```

- location should be the be the mapping associated with the second Servlet.
- If we want to print to the response from this Servlet and another component, we can use the include() method instead. However, this is not very useful if the response is an HTML file.

<img src="../PHOTOS/servlets-03.png">

**Demo 6 – Request Forwarding**

- Step 1 – Request object
  - Obtain a RequestDispatcher from the HttpServletRequest.
  - Forward the request and response objects using the RequestDispatcher.

- Step 2 – Second Servlet
  - Notice that we still use the HTTP method of the original request.

- Goals: Forward a request from one servlet to another.

**Review**

- What is a web container?
- What is a Servlet?
- What is the web container responsible for?
- What are the lifecycle steps of a Servlet?
- What is the deployment descriptor?
- List some tags used in the deployment descriptor.