<h1> Java Documentation </h1>

**JavaDoc Overview**

- JavaDoc is a form of commenting that can be used to produce HTML documentation
- Generates API-like documentation
  - Understanding of variables, methods, classes
- The official Java API is constructed using JavaDoc:
  - http://docs.oracle.com/javase/6/docs/api/

- Java has three forms of commenting:

``` java
// to end of line

/* multi-line
	comment
*/

/** javadoc
	comment
*/
```

- Good comments do not state the obvious
- Good comments describe why the code was written in a certain way
- Good comments relate to business logic and need

**Benefits**

- No need to look at the code when using a library
- Only need to look at code when fixing bugs
- Detailed overviews of what a package or project does
- Detailed information for other developers without code analysis

**Implementation**

``` java
/**
 * Presents the login menu 
 * and retrieves user input 
 * @author John Smith
 */
public class LoginView {
	...
}
```

- Key JavaDoc attributes include:

- @author 			– Required in both classes and interfaces
- @version 			– Required in both classes and interfaces
- @param 			– Methods and constructors only
- @return 			– Methods only
- @throws 			– Methods that throw exceptions only
- @see or {@link} 		– Anywhere required
- @since 			– Anywhere required
- @deprecated 		– Anywhere required

**Review Questions**

- What is JavaDoc?
- How do you use JavaDoc?
- Which attributes must every class contain?
- Why should you use JavaDoc?



























