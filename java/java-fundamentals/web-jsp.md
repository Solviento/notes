# Java EE Web - JSP

**Agenda**

- Attributes
- Java ServerPages (JSP)
- JSP Scripting
- Expression Language
- Custom Tags and JSTL

**Attributes**

```
Attributes are String-to-Object mappings used to 
share data between web app components.
```

- Some objects within the web app have attribute maps

- We can add attributes to the HttpServletRequest object in a Servlet:

``` java
request.setAttribute(“currentGuest”, UserManager.getUser(username));
```

- …and retrieve them anywhere that can access that HttpServletRequest

``` java
User myUser = request.getAttribute(“currentGuest”);
```

- Caution: Do not confuse these with request parameters, which hold data sent by the client.

**JavaServer Pages (JSP)**

- A JSP is another type of web app component
- Written as an HTML-based page with various types of embedded instructions for dynamic functionality:
- Plain Java code
- XML-style tags
- Expression Language (EL)
- In a standard Java EE MVC application, Servlets are typically used as the Controller, and JSPs are used as the View.

**JavaServer Pages (JSP)**

``` html
<html><body>
<% if( request.getAttribute(“loggedInUser”) != null ) { %>
	Welcome <%= request.getParameter(“username”) %>!
<% } %>
</body></html>
```

**JSP Translation**

- JSPs are actually Servlets with a different code representation!

- A JSP is translated into a Java servlet at runtime:
- A _jspService() method is generated, analogous to a servlet’s service()
- The dynamic parts of a JSP become Java code in _jspService() 
- The HTML and plain text parts are printed to the response stream directly (response.getWriter()) in the body of _jspService() 

``` html
<html><body>
	<% if( request.getAttribute(“loggedIn”) ) { %>
		Welcome <%= request.getParameter(“username”) %>!
	<% } %>
</body></html>
```

*Translation*

``` java
public void _jspService (HttpServletRequest req, HttpServletResponse resp){
	
	PrintWriter out = resp.getWriter();	
	out.print( “<html><body>” );
	if( req.getAttribute(“loggedIn”) )
		out.print( “Welcome ” + req.getParameter(“username”) + ”!” ); 	
	out.print( “</body></html>” );
}
```

**JSP Lifecycle**

- JSPs have a similar lifecycle to servlets:

1. A client requests a JSP page (www.example.com/index.jsp)
2. The JSP engine translates the page into a Java servlet (index_jsp.java)
3. The Java servlet file is compiled (index_jsp.class)
4. The web container loads the servlet, instantiates it and calls the jspInit() method
5. _jspService() can now be called to service this request and all subsequent requests to this JSP
6. When _jspService() is invoked, an output HTML file is generated and sent back to the client’s browser

**JSP Elements**

- A JSP allows use of the following languages and constructs:
  - HTML / CSS / Javascript
  - JSP Scripting – Java embedded in the JSP
  - JSP Standard Actions
  - Expression Language (EL)
  - JSP Standard Tag Library (JSTL)
  - Custom tag libraries

- Each is processed in its own way during the translation phase

**MVC**

- Why do we use JSPs for generating views and Servlets for talking to the Model?

|                        | **Servlet**                                                  | **JSP**                                                      |
| ---------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| **Talking to Model**   | Simple.       Java code calling other Java code.             | Complicated.       Hard to read, Java code is embedded in special tags and mixed with HTML. |
| **Constructing Views** | Complicated.         Hard to read, must print HTML as Strings to response stream. | Simple.         The JSP is primarily written in HTML.        |

**JSP Scripting**

- JSP scripting tags are embedded within the HTML of JSP pages. 

- There are four types:
  - Scriptlets: 		<% code %>
  - Expression: 	<%= code %>
  - Declarative: 	<%! code %> 
  - Directive: 		<%@ code %>

**JSP Tags –** **Scriptlets**

A scriptlet tag allows Java code to be incorporated into the JSP. This code will be in the body of _jspService()

``` html
<body> 
  <% 
     int randomValue = new Random().nextInt(10);
     if(randomValue == 7) { 
	 	out.println(randomValue + “ is 7! You win!”); 
     } else { 
	 	out.println(randomValue + “ is not 7. You lose.”); 
     } 
  %>
</body>
```

- Scriptlets – are used to write code in any language.  But that language is mentioned in the page language attribute.
  - E.g. <%@ page language="java“ %>

**JSP Tags – Expressions**

- An expression tag obtains the value of either a variable or a return value of a method:
  - Value is converted to a String
  - Automatically printed to the HTML response

```html
<!-- Expression -->

<p>The maximum of 2 and 3 is: <%= Math.max(2,3) %> </p>
```

- No semicolon is needed!

- HTML response:  “The maximum of 2 and 3 is 3”

**Expression**

- When the JSP is converted to a servlet, the code here will be evaluated and inserted into an out.println() method. So if you write <%= user.getPassword()%>, it will be converted to out.println(user.getPassword());  This is why we don’t put any semi-colon’s in the expression tag. 

**JSP Tags – Declarations**

- A declaration tag declares variables/methods outside the _jspService() method. They can be static if required.

``` jsp
<!-- Variable declaration (will go outside _jspService()) -->
<%! int randomVal = 0;	%>
<!-- Method declaration (will go outside _jspService()) -->
<%!	public int randomValue() {
    return new Random().nextInt(10) + randomVal; 
    	}
%>
<!-- Invoking the method in an expression (will go inside _jspService()) -->
<%= 	randomValue() 	%>
```

**JSP Tags – Directives**

- Directive tags control how a JSP is processed.
- Common directives are:
  - Import: 	Imports classes to be used by Scriptlet or Declarative tags.

`<%@ page import=“java.util.Random,com.fdmgroup.ServletExample” %>`

- Include: Includes the content of another file, which is parsed as a JSP.

`<%@ include file=“pathname”%>`

- ErrorPage: If an error occurs during JSP parsing, the user will automatically be directed towards the error page. 

`<%@ page errorPage=“pathname” %>`

**Demo 3 – JSP Scripting**

Step 1 – JSP
Use a JSP Expression tag to print out the value of a form parameter.
Use a page directive to import java.util.List.
Use a scriptlet to iterate over the List request attribute and output each element.

Goals: Add dynamic functionality to JSPs with JSP scripting.

**JSP Tags – EL**

- The JSP Expression Language (EL) provides a way to retrieve data without the use of expression tags:

`**${ code }**`

- EL allows retrieval of:
  - Values stored in maps (attributes, parameters)
  - Values stored in lists or arrays
  - Properties of objects following the JavaBean convention

**JavaBean Example**

class Share implements Serializable {	private String _symbol;
	private int _price;

	class Share implements Serializable {
		private String _symbol;
		private int _price;
	
		public Share() { }
		
		public String getStockSymbol() { return _symbol; }
		
		public void setStockSymbol( String stockSymbol ) {
			_symbol = stockSymbol; 
		}
		public int getPrice() { return _price; }
		public void setPrice( int price ) {  
			 _price = price; 
		}
	}
- A Share bean has two **properties** called **stockSymbol** and **price**.

**JSP Tags – EL**

- A set of implicit objects are provided as a starting point:
- Four attribute maps: 
  - applicationScope
  - sessionScope
  - requestScope
  - pageScope
- pageContext provides access to session, context
- Request parameter, header, and cookie information
- Other data

- Can use associative array notation or dot notation

- Suppose an object of type Share is a request attribute stored with the key “currentShare”. We can fetch it with the following:

```
${ requestScope[“currentShare”] }

${ requestScope.currentShare }
```

- We can go further in and get the value of the share price:

```
${ requestScope[“currentShare”].price }

${ requestScope.currentShare.price }
```

**Demo 4 – Expression Language**

- Step 1 – Servlet
  - Create a User object with a long chain of dependencies.
  - ex. A User HAS-A Portfolio, a Portfolio HAS-A Broker and a List of Stocks, a Broker HAS-A String name.
    - Set this User object as a request attribute forwarded to the JSP
- Step 2 – JSP
  - Access various User data using EL (associative array, dot notation, and various combinations of the two)
  - Replace all Expression tags on the JSP with EL.
- Goals: Use Expression Language to access all data in a JSP.

**Custom Tag Libraries**

- A tag library is a collection of customised tags that represent calls to Java methods. 
- To make use of a tag in a tag library, the taglib directive is needed:

``` jsp
<%@ taglib uri=“/mytaglib.tld” prefix=”mtl” %>
```

- uri denotes an identifier for the TLD (tag library descriptor)
- prefix denotes the prefix you will use for tags from this tag library

- A tag handler is a Java class that is responsible for handling calls.

```
<!-- Sample tag without body -->
<mtl:tagname attribute1=“value”/>
```

- When the JSP is translated, the presence of the above tag will invoke a method of the tag handler
  Tag attribute and body data will be passed to the method 

```
<!-- Sample tag with body -->
<mtl:tagname attribute1=“value” attribute2=“value”>
	body content
</mtl:tagname>
```

**JSTL**

- JSTL is a standard tag library included with Java EE
- It allows us to represent flow control mechanisms with XML-style tags instead of JSP Scripting, and eliminates the need for Java code in JSPs
- Why might we want to remove Java from our JSPs?

- Why?
  - Improves readability (for both humans and computers)
  - No messy scriptlet tags mixed with HTML content
  - Front-end developers can code JSPs without needing to know Java

- JSTL is declared with a “c” prefix.
- The following taglib directive is needed to use JSTL on a JSP:

```
<%@ taglib uri=“http://java.sun.com/jsp/jstl/core” prefix=“c” %>
```

**Scripting**

``` jsp
<%= “hello world” %> 
```

```jsp
<% if (2 == 2) { %>
          The result is true
<% } %> 
```

``` jsp
<% if (Integer.valueOf                (request.getParameter("value")) < 10 ) { %>
        Your input is less than 10
<% } else { %>
        Your input is greater than 10
<% } %> 
```

**JSTL**

``` jstl
<c:out value =“hello world”/>
```

```
<c:if test='${“2” == “2”}'>
         The result is true
</c:if>
```

```
<c:choose>
    <c:when test='${param.value<10}'>
          Your input is less than 10
    </c:when>
    <c:otherwise>
          Your input is greater than 10
    </c:otherwise>
</c:choose>
```

**Review Questions**

What is a JSP?
What are the lifecycle steps of a JSP?
List 3 JSP tags
List 3 types of scopes
How do you set values of variables with the request scope?

