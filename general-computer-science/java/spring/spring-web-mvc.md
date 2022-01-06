**MVC Recap**

- Model
  - Contains business logic and   custom objects
- View
  - Generates output representations for the user
- Controller
  - Sends commands to the model and the view and passes objects around where required

<img src="../PHOTOS/spring-web-01.png" width=400px>

**Spring MVC**

What is Spring MVC?

- Spring’s web framework 
- Implements the Front Controller pattern (Java EE pattern)
  - All requests are addressed to a central servlet
  - The servlet delegates requests to controller classes
  - Controllers do the request handling work
  - In Spring, this “central servlet” is known as the DispatcherServlet
- Facilitates working with Servlets and JSP
  - No need to create a Servlet for each command

- Encourages use of best practices and SOLID
- Separation of concerns
  - Each role (controller, validator, servlet, view, model objects, etc.) can be fulfilled by a specialized object.
- Simplification
  - Easy to configure.
  - Far fewer servlet mappings.
- Form binding
  - Allows us to bind an object to a Spring form directly.
  - Spring populates the object with form data automatically, instead of having to retrieve form parameters one by one.

**Spring MVC Architecture**

<img src="../PHOTOS/spring-web-02.png" width=700px>

- Front controller = DispatcherServlet – Map in web.xml
- Controller = Controller classes, defined either in the dispatcher-servlet.xml or by annotation in the classes
- View = JSTLView and Internal View resolver
- Model = normal POJO’s and standard logic

**Spring MVC**

DispatcherServlet

- DispatcherServlet is a Spring-provided class that extends HttpServlet. Like any servlet, it must be mapped in web.xml:

``` java
<servlet>
	<servlet-name>dispatcher</servlet-name>
	<servlet-class>
		org.springframework.web.servlet.DispatcherServlet
	</servlet-class>
</servlet>
<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>
```

- <servlet-name> is important! Spring will try to load the application context from a file named [servlet-name]-servlet.xml inside WEB-INF.

- The / in URL pattern tells the servlet to handle all requests. 

- This could be set to *.jsp to handle JSP requests only, or *.html for HTML requests only.

- /* would work for all pages but this would cause problems with requests for static content such as images.

dispatcher-servlet.xml

- First, specify the base package.

``` java
<context:component-scan base-package="com.fdm.controllers" />
```

- This will activate Spring Web MVC annotation scanning and will look for annotated classes in the given package.

In order to obtain the view to send to the client, Spring consults a view resolver.

It attaches a specified prefix and suffix to a logical view name.

<img src="../PHOTOS/spring-web-03.png" width=700px>

``` java
<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	<property name="viewClass"
    	<value="org.springframework.web.servlet.view.JstlView" />
    <property name="prefix" value="/WEB-INF/views/" />
    <property name="suffix" value=".jsp" />
</bean>
```

**Controllers**

- DispatcherServlet delegates request handling to regular Java classes:
  - @Controller marks these classes
  - @RequestMapping maps a URL to a method
    - value: 		specifies mapped URL 
    - method: 	specifies HTTP method
  - Methods return a String (logical name of the view to respond with)

``` java
@Controller
public class HomeController {
    @RequestMapping(value=“/register”, method=RequestMethod.POST)
	public String goToRegisterPage(){
    	return “registration”;
	}
}
```

- @RequestMapping(“/register”) can also be used, if we wish to specify only the URL.

- For example, clicking on this hyperlink:

``` java
<a href=“register”>Register</a>
```

- …executes the method below. Because it returns “registration”, you will be directed to:

``` java
/WEB-INF/views/registration.jsp
```

``` java
@Controller
public class HomeController {
    
	@RequestMapping(“/register”)
    public String goToRegisterPage(){
        return “registration”;
    }
}
```

- Methods annotated with @RequestMapping can take any number or type of parameters, as needed.  For example:
  - An HttpServletRequest to access request data
  - Strings representing specific request parameters
  - A model object such as a User
  - Various others					

``` java
@Controller
public class HomeController {
    
    @RequestMapping(“/register”)
    public String goToRegisterPage(HttpServletRequest req){
		String type = req.getParameter(“userType”);
		log(“Redirecting to registration page for “+type);
        return type+“registration”;
}
```

**@RequestParam**

- Usually we get form parameters from the HttpServletRequest object: 
  - req.getParameter(“paramName”);
- Spring provides an alternate way using @RequestParam:

``` java
@Controller
public class HomeController {
    
    @RequestMapping(“/register”)
    public String goToRegisterPage(@RequestParam String username){
        return “register/” + username;
    }
}
```

- Spring will get the form parameter “username”, and pass its value into the method parameter “username”.

- We can use @RequestParam, even on a method parameter with a different name, by using the value attribute:	

``` java
@Controllerpublic class HomeController {
    @RequestMapping(“/register”)
    public String goToRegisterPage(@RequestParam(value=“username”) 											String doesntMatch){
        return “register/” + doesntMatch;
    }
}
```

- Spring will get the form parameter “username”, and pass its value into the method parameter “doesntMatch”.

**@PathVariable**

- Binds a variable in the URL (a placeholder in the request mapping) to a method parameter

- User-submitted data (such as a search term) can be displayed on the URL

``` java
@Controller
public class LibraryController {
    
    @RequestMapping(“/find/{title}”)
    public String getBook(@PathVariable String title){
        ...
    }
}
```

**Attributes**

- Spring facilitates how we pass around application data with the Model object:

``` java
@Controller
public class LibraryController {

    @RequestMapping(“/register”)
    public String goToRegisterPage(Model model){
        model.addAttribute(“attributeName”, new User());
        return “register”;
    }
}
```

- This will become an attribute in the request scope, accessible by ${attributeName} or   ${requestScope.attributeName}

**Spring Forms**

- Spring Forms is a feature that allows us to bind an object directly to a form on a view.
- Normally, we would manually retrieve each piece of user data from a request:

``` java
<form action=“register” method=“POST”>
	Username: <input type=“text” name=“username” />
    Password: <input type=“password” name=“password” />
    <input type=“submit” value=“Click Here” />
</form>
```

``` java
req.getParameter(“username”);

req.getParameter(“password”);
```

Spring’s custom tags allow us to create a form bound to a particular object in the Model:

``` java
<sf:form action=“process” method=“POST” modelAttribute=“user”>
	<sf:label path=“username”> Username </sf:label>
	<sf:input type=“text” path=“username” />
    <sf:label path=“password”> Password </sf:label>
    <sf:input type=“password” path=“password” />
    <input type=“submit” value=“Click Here” />
</sf:form>
```

- sf is the tag library prefix, but we can use any prefix.
- modelAttribute specifies the name of the Model attribute that holds the object this form will be bound to.
- path specifies the property of the object to which a particular input is bound.

**Spring Form on a JSP**

- To use Spring Form custom tags on a JSP (e.g. registration.jsp), these taglib directives are required:

``` java
<%@ taglib prefix=“s”
	uri=“http://www.springframework.org/tags” %>
<%@ taglib prefix=“sf”
	uri=“http://www.springframework.org/tags/form” %>
```

- Additionally, the JSP must be passed the modelAttribute object (to which to bind the form) when it is first loaded!

``` java
@RequestMapping(“/register”)
public String goToRegisterPage(Model model){
    model.addAttribute(“user”, new User());
    return “registration”;
}
```

**Using the Populated Object**

To retrieve the data, simply add a parameter of the object’s type to the destination controller method:

``` java
@RequestMapping(value=“/process”, method=“RequestMethod.POST)
public String register(User user){
    log(user.getUsername()+” has registered!”);
    return “confirmation”;
}
```

- This User object is populated with the data from the form!

- Previously, we would have had to populate this object manually, with individual parameter values.

**Summary**

<img src="../PHOTOS/spring-web-04.png" width=900px>

**Review Questions**

- How does Spring retrieve the correct view to use?
- What processes user requests?
- Where do all requests initially go?
- How do you pass attributes back to the users browser?
- Describe the route taken between an initial request and the final response.