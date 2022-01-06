**After completing this module you will be able to:**

- Explain what JSPs are and identify the steps of their lifecycle.
- List the different JSP tags
- Identify the difference between the JSP tags
- List the different type of scopes a object can have in web application.
- Demonstrate how to use tag libraries.

**JavaServer Page (JSP)**

``` jsp
...<table>
	<tr><th>Email address</th></tr>
	<% 
	  Users list = (Users) request.getAttribute(“listOfEmail”);
	  Collection<User> emails = list.getCollections();

	  for(Iterator<User> it = emails.iterator(); it.hasNext();) {
		User e = (User) it.next();
	%>
	<tr><td>
		<% out.print(e.getEmailAddress());} %>
	</td></tr>
   </table>...
```

- Advantages of a JSP in comparison with a HttpServlet.
  - The mapping is defined by the JSP name and location on the server.
  - There is only one service method (calls doGet or doPost).
  - Promote MVC architecture.
  - Easier to code view components.
  - Front-end developers do not have to know how to code in Java.

**JSP lifecycle**

- JSPs have a similar lifecycle to servlets:
  - A client requests a JSP page (www.example/index.jsp)
  - The browser sends the request to the web server and consequently to the web container.
  - The JSP servlet engine converts the JSP page into a java servlet file (index_jsp.java).
  - The java servlet file is compiled (index_jsp.class)
  - The web container loads the servlet, instantiates it and calls the JSPInit() and JSPService() methods.
  - An output HTML file will be generated from this JSP and sent back to the browser. 

**JSP Tags**

- JSP tags are embedded within the HTML of JSP pages.
  - Expression: <%= code %>
  - Expression Language (EL): ${code}
  - Scriptlets: <% code %>
  - Directive: <%@ code %>
  - Declarative: <%! code %>

**JSP Tags – Expression and EL**

- An Expression tag obtains the value of either a variable or a return value of a method. This value is converted into an String and automatically printed out.
- An Expression Language works equivalently to Expression, the difference lies in the syntax.  

``` jsp
<!--Expression-->
<p>The maximum of 2 and 3 is: <%= Math.max(2,3) %></p>

<!--Expression Language-->
<p>The maximum of 2 and 3 is:  ${ Math.max(2,3) }</p>

Browser output: The maximum of 2 and 3 is 3
```

**JSP Tags – Scriptlets**

- A Scriptlet tag allows for Java code to be incorporated into the JSP. The java code is converted into HTML and returned to the browser’s client.  

``` jsp
...
<body> 
       <% int randomValue = new Random().nextInt(10);
             if(randomValue < 5){ %>
                 <% out.println(randomValue + “ is less than 5”); %>
         <% } else { %>
	  <% out.println(randomValue + “ is greater than or equal to 	        		5”); %>
           <% } %>
</body>
...
```

**JSP Tags – Directive**

- A Directive tag controls the processing of the JSP
- Common directive tags are:
- Import: imports classes to be used by Scriptlet or Declarative tags.

``` jsp
<%@ page import=“java.util.Random” %>
```

- Include: includes the content of another file. The included file is parsed as a JSP.

``` jsp
<%@ include file=“pathname” %>
```

- ErrorPage: if an error occurs within the JSP parsing, the user will be directed towards the error page. 

``` jsp
<%@ page errorPage=“pathname” %>
```

JSP Tags – Declaration

A Declaration tag declares variables or methods outside the service(or any other) method. Both variables and methods can be used as static if it required.

``` jsp
<%! Int randomValue = 0; %>    <!-- outside service method -->

<%public void randomValue() { <!-- inside service method -->
     randomValue = new Random().nextInt(10);
       if(randomValue < 5) { %>
      <% out.println(randomValue + “is less than 5”); %>
 <% }else { %>
    <% out.println(randomValue + “is greater than or equal to 5”); %>
  <% } %>
     } %>
```

**Scopes**

- Describe how widely an object is available and who can access it. There are four types of scopes.
- Application
  - Available to all JSP pages within the same web application.
- Session
  - Available within all JSP pages for the current session
- Request
  - Available within all JSP pages that are servicing the request.
- Page
  - Available only within the current JSP page

**Submitting information**

- To submit information towards servers, the information must be encoded within a HTTP request.
  HTML has a form tag that allows submission to servers using either the GET or the POST method.

``` jsp
<form method=“POST” action=“/location”>
        <input type=“text” name=“parameterName” />
        <input type=“submit” value=“submit” />
</form>
```

**Obtaining submitted information**

- When information is submitted towards the server, it is within the request scope.
- To obtain this information within a Servlet or Scriptlet:

``` jsp
request.getParameter(“parameterName”);
```

- To obtain this information via an EL:

``` jsp
${param.parameterName}
```

**Scope variables – setting values**

Application scope

``` jsp
this.getServletContext().setAttribute(“attributeName”, 13);
```

Session scope

``` java
request.getSession().setAttribute(“attributeName”, new Object());
```

Request scope

``` java
request.setAttribute(“attributeName”, “hello!”);
```

**Tag Libraries**

A tag library is formed of customised tags. They are a tag representation of calls to java methods.
To utilise a tag, the taglib directive is used:

``` java
<%@ taglib uri=“/mytaglib.tld” prefix=”mtl” %>
```

- URI denotes the location of the taglib TLD.
- PREFIX denotes the prefix used in tags for usage of the taglib.

- A tag handler is a java class that is responsible for handling calls.

``` jsp
<!--Tag lib form without body-->
<mtl:tagname attribute1=“value”/>
```

- This will call a method of the tag handler, that will accept attributes as input parameter.

``` jsp
<!--Tag lib form with body-->
<mtl:tagname attribute1=“value” attribute2=“value”>
	body
</mtl:tagname>
```

**Tag Libraries – JSTL Tags**

- JSTL is a tag library that is used to replace Scriptlets and Expressions.
- Why?
  - Improves readability (for both humans and computers)
  - Front-end developers do not need to understand java to code JSPs.
- JSTL is declared with a “c” prefix.

``` jsp
<%@ taglib uri=“http://java.sun.com/jstl/core” prefix=“c” %>
```











