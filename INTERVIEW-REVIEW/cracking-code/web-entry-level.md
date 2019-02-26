# HTML
Hyper Text Markup Language
``` HTML
<!DOCTYPE html>
  <head>
    <title>Fitness App 1.0</title>
  </head>

  <body>

  </body>

</html>
```

How do you insert an image in HTML?   

``` HTML
<img> tag and SRC for the source file  

<img src="http://i.imgur.com/qK42fUu.jpg">

<img src="/img/photo.img">
```

HTML Links

``` html
<a href="www.facebook.com">Click here</a>
```

HTML Lists

- Ordered List

  - ``` html
    <ol>
        <li>Coffee</li>
        <li>Tea</li>
    </ol>
    ```

- Unordered List

  - ``` html
     <ul>
         <li>Coffee</li>
    	<li>Tea</li>
    </ul>
    ```

How do you change the background color?   

- background: #ecf0f1 or blue;


What are the different HTML elements?
- An HTML element is defined by a starting tag
``` html
- <head>
- <body>
- <Title>
- <br>
- <p>
- <div>
- <h1>
```
HTML Tags
- not all of them come in pairs!
```html <br>```

What is the use of DOCTYPE in HTML?   
- The <!DOCTYPE> declaration is not an HTML tag; it is an instruction to the web browser about what version of HTML the page is written in

  ``` html <!DOCTYPE html> ```

How could you create a button with HTML?   

``` HTML
<button type=”button”> Click me</button>
```


How do you comment in HTML?
``` HTML
<!—comment-->   
```

## CSS

What is CSS and how is it used in web development?

- CSS stands for cascading style sheets
- It is used to describe how the HTML document looks

``` css
body{
    background-color: blue;
}
h1{
    color: white;
}
```

Styling HTML

- can be added inline, internal using ```<style>``` in the ```<head>``` section or external using a CSS file

``` html
inline
<p style="color:blue;">This a blue text</p>

internal css
<head>
    <style>
        body{
            background-color: blue;
        }
    </style>
</head>

external css
<head>
    <link rel="stylesheet" href="styles.css">
</head>
```

CSS can select elements using class, id's, element tags, element>element relationships, etc.

## Javascript

How do you create a variable in JavaScript?
- ‘var’ = whatever you want to variable to be assigned to

``` JavaScript
var name = ‘leet’
var x, y, z;
x = 5; y = 6;
z = x + y;
```

What do you use JavaScript for?
- In web pages, makes things responsive (hover, drag, clickable)

What is a stored procedure?
- Stored procedures are a batch of SQL statements that can be executed in a couple of ways. Most major DBMs support stored procedures
- It is a set of pre-compiled SQL statements, it is stored in the database

What is the difference between "==" and "===" in Javascript?
- === strictly equality comparison
  - No type conversion is done, datatypes and their respective values must be the same
- == abstract equality comparison
  - will compare for equality after doing any necessary type conversions

``` javascript
var a = 9;
var aS = "9";
a == aS; // evaluates to true since string "9" is converted into integer

var b = 10;
var bS = '10';
b === bS; // evaluates false since no type conversion takes place
```



What is the difference between == and .equals()?
- == is a reference comparison( based on memory addresses)
- .equals() compares the actual values in the objects

What are undeclared variable in JavaScript?
- Variables that have been declared without the keyword ‘var’

What does the "$" mean in JQuery?   
- It’s an alias to JQuery, the JQuery object
- $("#id") or jQuery("#id") is the same.
