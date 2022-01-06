# React

What is React?
- React is a javascript library
- React uses JSX, a syntax entension to javascript

``` JavaScript
const element = <h1>Hello, world!</h1>
```

- Use JSX to describe what the UI should look like
- JSX looks like a template language but comes included with JS
- JSX produces React "elements"
- React separates *concerns* with loosely coupled units called "components"

Basic JSX Render

``` JavaScript
const name = 'Josh Perez';
const element = <h1>Hello, {name}</h1>;

ReactDOM.render(
  element,
  document.getElementById('root')
);
```

Basic JSX Render with function
``` JavaScript
function formatName(user) {
  return user.firstName + ' ' + user.lastName;
}

const user = {
  firstName: 'Harper',
  lastName: 'Perez'
};

const element = (
  <h1>
    Hello, {formatName(user)}!
  </h1>
);

ReactDOM.render(
  element,
  document.getElementById('root')
);
```

- _Remember_ you can place valid JavaScript expressions in curly braces in JSX
- {formatName(user)}

JSX Expressions
- Using JSX within an if statement, can also use in for loops

``` JavaScript
function getGreeting(user) {
  if (user) {
    return <h1>Hello, {formatName(user)}!</h1>;
  }
  return <h1>Hello, Stranger.</h1>;
}
```

JSX Attributes
- Use quotes for string values

``` JavaScript
const element = <div tabIndex="0"></div>;
```

- Use curly braces for expressions

``` JavaScript
const element = <img src={user.avatarUrl}></img>;
```

- Use CamelCase for JSX

JSX Children Elements

``` JavaScript
const element = (
  <div>
    <h1>Hello!</h1>
    <h2>Good to see you here.</h2>
  </div>
);
```

Embedding User Input in JSX (safe!)

``` JavaScript
const title = response.potentiallyMaliciousInput;
// This is safe:
const element = <h1>{title}</h1>;
```

- JSX ensures you can never inject anything not written in your application, so the above method is fine

JSX Object Representation
- Two identical ways of creating elements

``` JavaScript
const element = (
  <h1 className="greeting">
    Hello, world!
  </h1>
);

const element = React.createElement(
  'h1',
  {className: 'greeting'},
  'Hello, world!'
);
```

- Think of the above as a React element
- React elements are plain objects, cheap to create

Rendering elements to the DOM
- Let's say there a root DOM node
- You can also have other isolated root DOM nodes as well

``` JavaScript
// say we have a div in our html file
<div id="root"></div>

const element = <h1>Hello, world</h1>;
ReactDOM.render(element, document.getElementById('root'));
```

- Will now display Hello world on the page

Updating rendered element
- React elements are immutable, meaning you cannot change its children or attributes
- An element is like a movie frame, it represents the UI at a certain point in time
- In order to update the UI, we must create a new element and pass it to ReactDOM.render()

``` JavaScript
function tick() {
  const element = (
    <div>
      <h1>Hello, world!</h1>
      <h2>It is {new Date().toLocaleTimeString()}.</h2>
    </div>
  );
  ReactDOM.render(element, document.getElementById('root'));
}

setInterval(tick, 1000);
```

Functions and Class components
- Think of components like JS functions, they accept inputs called "props" (properties) and return react elements

``` JavaScript
function Welcome(props) {
  return <h1>Hello, {props.name}</h1>;
}

class Welcome extends React.Component {
  render() {
    return <h1>Hello, {this.props.name}</h1>;
  }
}
```

- The above are equivalent
