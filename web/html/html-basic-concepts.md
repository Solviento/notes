# Learning html

HTML
- Stands for Hyper Text Markup Language

Building blocks of HTML pages
``` HTML
<!DOCTYPE html>  
```
- Defines this document to be HTML5
``` HTML
<html>
``` 
- This is the root element of an HTML page
``` HTML
<head>
```
- This element contains meta information about the document
- A container for all head elements like title, scripts, styles, and more
``` HTML
<title>
``` 
- Specifies a title for the document
``` HTML
<body>
```
- Contains visible page content
``` HTML
<h1>
```
- Defines a large heading
``` HTML
<p>  
```
- Defines a paragraph

HTML links
- HTML links are defined with the < a > tag
``` HTML
<a href="https://www.drive.com/"> This is a link </a>
```

HTML images
- HTML images are defined with the <img> tag
``` HTML
<img src="apple.png" alt="apple.com" width="200" height="140">
```
- The alt attribute specifies an alternative text to be used in the case where an image cannot be displayed

Style attribute
- The style attribute is used to specify the styling of an element, like font, color or size
``` HTML
<p style="color:red">I am a paragraph</p>
``` 

HTML fonts
- The font-family property  defines the font to be used for an HTML element
``` HTML
<p style="font-family:courier;">This is a paragraph.</p>
```
- Font size, text alignment, color can also be used on text

HTML formatting
- Bold, important, italic, emphasized, marked, small, deleted, inserted, subscript, superscript can all be used to display special types of text
``` HTML
<b>, <em>, <small>, <sup>, <sub>
```

Quotations
- Use q for short quotations
``` HTML
<q> building a future </q>
```

Blockquotes
- Blockquotes can also be used
``` HTML
<blockquote cite="worldwildlife.com/index.html" For 50 years, protection of nature".</blockquote>
```

Abbreviations
Use abbr for an abbreviation or acronym
``` HTML
<abbr title="World Health Org"</abbr>
```

Single vs double quotes
- In some situations it is necessary to alternate between single and double quotes
``` HTML
<p title='John'>
<p title="Bill">
```

Separate content
- To define a thematic break, we use the horizontal rule
``` HTML
<p>This is some text.</p>
<hr>  
``` 
To define a line break we use the break rule
``` HTML
<br>
```

HTML comments
``` HTML
<!-- comments here -->
```

HTML colors
- Background color, text color, border color, and more can be used to colorize HTML elements

HTML block and inline elements
- A block-level element always starts on a new line and takes up the full width available (stretches out to the left and right as far as it can)
- The ```<div>``` element is a block level element.
- Includes ```<address>, <figure>, <form>```, etc
- An inline element does not start on a new line and only takes up as much width as necessary
- ```<span>, <a>, <b>, <label>, <img>```, etc


```<div>``` element
- ```<div>``` is often used as a container for other HTML elements
- Used with CSS to style blocks on content

```<span>``` element
- Used as a container for some text
- Also used with CSS to style parts of the text

HTML class attribute
- The class attribute specifies one more classes for an HTML element
