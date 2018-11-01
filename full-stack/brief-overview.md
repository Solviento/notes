# Overview of Full Stack Methodology

CSS Basic Box Model

https://developer.mozilla.org/en-US/docs/Web/CSS/CSS_Box_Model/Introduction_to_the_CSS_box_model

Basically the model for all HTML elements rendered by the browser

Think Margin, Border, Padding, and Content Properties



CSS Preprocessors

https://htmlmag.com/article/an-introduction-to-css-preprocessors-sass-less-stylus

Preprocessors essentially helps developers write reusable, maintainable code in CSS

Meant to increase productivity, reduce amount of code and redundancy



https://medium.com/coderbyte/a-guide-to-becoming-a-full-stack-developer-in-2017-5c3c08a1600c

How the internet works:

Computer requests to another computer (using packets) for information about a website page

Before user sees anything, a computer is "asking" for the contents of the page

Getting the request to the right place

Once request is approved by server, the requested page is "pulverised" into tiny packets and then scrambled to get to their destination (not necessarily through a single pipeline as thought previously)



Requests and responses

A query is submitted ("www.udemy.com") to an ISP (internet service provider)

After which the website URL name is set to a DNS to turn it into an IP address (23.235.47.175)

NOT a direct journey, this request is bounced around from server to server until it arrives to its destination

Server then returns the right content using HTML, CSS, and javascript



Front end vs Back end

Front end (what you see and interact)

HTML, CSS, Javascript

Back end (many choices)

Database, web server, php, etc.

Static vs dynamic

Static requires only frontend

Dynamic requires both



jQuery

Download jQuery and link locally

<script type="text/javascript" src="jquery.js"></script>

Link to a CDN

<script type="text/javascript" src="htttps://jquery.com"></script>

Selecting with jQuery

$("selector goes here")

Very similar to querySelectorAll in javascript as it returns all matching elements

$("img")

Select all img tags

$(".sale")

Select all elements with sale class

Note: when calling $ operator, the console will return a list. Even if you are only calling one object



Manipulating Style

.css() method is jQuery's interface to styling

$(selector).css(property, value)

$("#special").css("border", "2px solid red");

You can also pass in an object with styles

var styles = { backgroundColor: "pink", fontWeight: "bold" };

$("#special").css(styles);

Makes it easier when dealing with multiple elements since JavaScript requires you to loop through the list to change an element styling
