# SVG (Simple Vector Graphic)

Basic simple SVG graphic (yellow circle with green outline)
``` HTML
<!DOCTYPE html>
<html> 
<body>
<h1>My first SVG</h1>
<svg width="100" height="100">
    <circle cx="50" cy="50" r="40" stroke="green" stroke-width="4" fill="yellow"> 
</svg>
</body>
</html>
```

SVG Shapes
- SVG has some predefined shape elements that can be used by developers:

Rectangle ```<rect>```
``` HTML
<svg width="400" height="110">
<rect width="300" height="100" style="fill:rgb(0,0,255);stroke-width:3;stroke:rgb(0,0,0)">
</svg>
```

- "style" attribute used to define CSS properties for the rectangle
- "fill" property defines fill color
- "stroke-width" defines border width
- "stroke" defines border color

``` HTML
<svg width="400" height="180">
<rect x="50" y="20" width="150" height="150"
  style="fill:blue;stroke:pink;stroke-width:5;fill-opacity:0.1;stroke-opacity:0.9">
</svg>
```

- "x" attribute defines left position of rectangle (offset from left margin)
- "y" delivers the same but from top margin
- "fill-opacity" defines opacity of fill color from 0-1
- "stroke-opacity" define opacity of stroke from 0-1
- style="fill:blue;stroke:pink;stroke-width:5;opacity:0.5"
- "opacity" defines opacity value for whole element


``` HTML
<svg width="400" height="180">
  <rect x="50" y="20" rx="20" ry="20" width="150" height="150"
  style="fill:red;stroke:black;stroke-width:5;opacity:0.5">
</svg>
```
- "rx" and "ry" attributes rounds the corners of the rectangle

Circle ```<circle>```
``` HTML
<svg height="100" width="100">
  <circle cx="50" cy="50" r="40" stroke="black" stroke-width="3" fill="red" />
</svg>
```
"cx" and "cy" define x and y coordinates of center of the circle, if omitted the circle center is set to 0, 0

Ellipse ```<ellipse>```
``` HTML
<svg height="140" width="500">
  <ellipse cx="200" cy="80" rx="100" ry="50"
  style="fill:yellow;stroke:purple;stroke-width:2" />
</svg>
```
- "rx" and "ry" define x, y coordinate of the center of the ellipse

Line ```<line>```
``` HTML
<svg height="210" width="500">
  <line x1="0" y1="0" x2="200" y2="200" style="stroke:rgb(255,0,0);stroke-width:2" />
</svg>
```

- "x1" and "y1" define start of the line on x and y axis
- "x2" and "y2" define end of line on x and y axis

Polyline ```<polyline>```
``` HTML
<svg height="210" width="500">
  <polygon points="200,10 250,190 160,210" style="fill:lime;stroke:purple;stroke-width:1" />
</svg>
```
- The points attribute defines the x and y coordinates for each corner of the polygon

``` HTML
<svg height="210" width="500">
  <polygon points="100,10 40,198 190,78 10,78 160,198"
  style="fill:lime;stroke:purple;stroke-width:5;fill-rule:nonzero;" />
</svg>
```

Creates a star
- Its also possible to create a star with empty fill center

Polygon ```<polygon>```
``` HTML
<svg height="200" width="500">
  <polyline points="20,20 40,25 60,40 80,120 120,140 200,180"
  style="fill:none;stroke:black;stroke-width:3" />
</svg>
```
- A shape consisting of straight lines
- Line begins at first point then connects sequentially to the rest

``` HTML
<svg height="180" width="500">
  <polyline points="0,40 40,40 40,80 80,80 80,120 120,120 120,160"
  style="fill:white;stroke:red;stroke-width:4" />
</svg>
```
- A shape of straight lines, resembles stairs

Path ```<path>```
- Commands available to path data"
- M = moveto
- L = lineto
- H = horizontal lineto
- V = vertical lineto
- C = curveto
- S = smooth curveto
- Q = quadratic Bézier curve
- T = smooth quadratic Bézier curveto
- A = elliptical Arc
- Z = closepath

``` HTML
<svg height="400" width="450">
  <path id="lineAB" d="M 100 350 l 150 -300" stroke="red"
  stroke-width="3" fill="none" />
  <path id="lineBC" d="M 250 50 l 150 300" stroke="red"
  stroke-width="3" fill="none" />
  <path d="M 175 200 l 150 0" stroke="green" stroke-width="3"
  fill="none" />
  <path d="M 100 350 q 150 -300 300 0" stroke="blue"
  stroke-width="5" fill="none" />
  <!-- Mark relevant points -->
  <g stroke="black" stroke-width="3" fill="black">
    <circle id="pointA" cx="100" cy="350" r="3" />
    <circle id="pointB" cx="250" cy="50" r="3" />
    <circle id="pointC" cx="400" cy="350" r="3" />
  </g>
  <!-- Label the points -->
  <g font-size="30" font-family="sans-serif" fill="black" stroke="none"
  text-anchor="middle">
    <text x="100" y="350" dx="-30">A</text>
    <text x="250" y="50" dy="-10">B</text>
    <text x="400" y="350" dx="30">C</text>
  </g>
</svg>
```

Builds a bezier curve quadratic
- Text, stroking, filters, etc. found on:
- https://www.w3schools.com/graphics/svg_text.asp
