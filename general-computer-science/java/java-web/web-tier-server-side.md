**After completing this module you will be able to:**

- Describe the purpose of a web container and a web server.
- Explain what servlets are and identify the steps of their lifecycles.
- Describe what a deployment descriptor is and its purpose
- Describe what Listeners and Filter are and when and where they should be used.

**Web Container**

- Application server that executes Servlets and renders web pages that includes JSP code.
- Responsible for:
  - Security (Prevent access  to resources)
  - Concurrency (Create a new thread for every servlet request.)
  - Lifecycle management(Manages filters, servlet and JSP)
  - Application deployment

- Example: 
  - Tomcat

- Servlet don’t have a main method so the web container is in charge of running these application.

**Web Server**

- Provides services to access the Internet.
- Responsible for:
  - Receiving and interpreting HTTP requests.
  - Processing and sending back HTTP responses.

- A web container relies on a web server to provide HTTP message handling.
  - Example: 
    - Apache HTTP Server.

**Servlets**

- Java classes that dynamically process requests and constructs responses.
- Controlled by the web container.
- Servlets can define content
  - Return a different response depending on the request 
  - Response can dynamically generate HTML.

``` java
void doPost (HttpServletRequest req, HttpServletResponse resp) {
	PrintWriter out = resp.getWriter();
	out.println(“<html>”);
	out.println(“<head><title>view Stock</title></head>”);
	out.println(“<body><p>”+somevariable+”</p></body>”);
	out.println(“</html>”);
}
```

- A web application runs in a web container on a web server.

<img src="../PHOTOS/server-side-01.png">

**Servlet lifecycle**

- The lifecycle of a servlet is managed by the web container.
- The web container:
  - Loads and instantiates the servlet class at container start-up.
  - Calls the init() method.
  - Calls the service() methods. This is where the servlet will spend most of its life.
  - Calls the destroy() method. Container shutdown.

- The init() and destroy() methods are called only once in the servlet lifecycle.

**Servlet Hierarchy**

- GenericServlet abstract class implements Servlet and Servletconfig interfaces.
- To create a servlet class, user should extend from HttpServlet abstract class, which extends from GenericServlet.

<img src="../PHOTOS/server-side-02.png">

**HttpServlet**

- The HttpServlet abstract class is an extension of GenericServlet. It is a specific implementation for the HTTP.
- The service methods of a HttpServlet are split into:
  - doDelete() – deletes the resource/file at the requested URL.
  - doGet() – asks to get the resource/file through the URL.
  - doHead() – similar to doGet but the response is without body.
  - doOptions() - asks for a list of HTTP methods and use one to respond.
  - doPost() - similar to a doGet with extra information sent through the body.
  - doPut() – put enclosed information at the requested URL.
    doTrace() - ask for a loopback if the request message.

**Deployment Descriptor**

- Configuration file that describes how an application should be deployed.
  - Web.xml
- Written in XML tags and contains:
  - Servlet mapping information
  - Initial parameters
  - Security constraints
  - Login configuration

- Servlet mapping information: “defines mappings between URL paths and the servlets that handle requests with those paths. The web server uses this configuration to identify the servlet to handle a given request and call the class method that corresponds to the request method (e.g. the doGet() method for HTTP GET requests).” https://developers.google.com/appengine/docs/java/config/webxml

**Deployment Descriptor – Servlet mapping**

Mapping a servlet onto a URL Servlet mapping

``` xml
<servlet>
	<servlet-name>Register</servlet-name>
	<servlet-class>com.fdmgroup.RegistrationServlet</servlet-class>
</servlet>

<servlet-mapping>
	<servlet-name>Register</servlet-name>
	<url-pattern>/registration</url-pattern>
</servlet-mapping>
```

- May map multiple URLs onto a servlet

- `<url-pattern>:` “Every character in a pattern must match the corresponding character in the URL path exactly, with two exceptions. At the end of a pattern, /* matches any sequence of characters from that point forward. The pattern*.extension matches any file name ending with extension. No other wildcards are supported, and an asterisk at any other position in the pattern is not a wildcard.” http://www.roguewave.com/portals/0/products/hydraexpress/docs/3.5.0/html/rwsfservletug/4-3.html

**Listeners**

- Listen for an event to occur in the web container.
  - Uses the observer pattern.
- The methods that are called vary depending on the listener that is implemented.
- For example, ServletContextListener listens for state changes in ServletContext object.
  - Listeners are pre-defined and handled by the web container.

- They are interfaces receiving notification events in the web container, including state changes in the servletContext, HTTPsession and ServletRequest objects. By implementing predefined interfaces such as servletContextListener, etc, the web container will notify you of certain events that are happening in your application.

- They are very powerful and have a lot of potential uses, such as intercepting request to perform logging and tracking HTTP session.

**Filters**

- A filter is a wrapper around a URI resource. 
- There are two kinds of filters:
  - Request Filter
    - Inspect the URI, the request parameter and the header and based on this decides whether the request should be blocked or not.
  - Response Filter
    - Process response after the servlet has completed its execution.
- Methods
  - Init(FilterConfig)
  - doFilter(ServletRequest, ServletResponse, FilterChain)
  - Destroy() 

- The filterChain decides what filter(or servlet) to call next.
- Request Filter
  - Performs actions before filterChain.doFilter() is called.
  - May modify the request object or prevent access to further filters/servlets.

- Response Filter
  - Performs actions after filterChain.doFilter() is called.
  - The servlet will send the response object directly towards the container. The container will receive the response before the filter receives it.

**Module Review**

- What is the web container responsible for?
- What is a servlet?
- What is the name of the class that you need to extends from in order to create a servlet?
- What are the lifecycle steps of a servlet?
- What is the purpose of the Deployment descriptor?
- List 4 tags used in the Deployment Descriptor.
- What  is the purpose of listeners?
- What is a filter?







