# Introduction to Express

What is a framework? How is it different from a library?
- A library is code someone else wrote and we can use in our application
- A framework is similar, with a library you are in control but with a framework, the control is inverted as the framework calls you
- A framework requires you to fill in the code based on a pre-defined control flow
- A library is a collection of functionality that you can call

What is Express?
- Express is a web development framework

Why are we using Express?
- Express is a popular node development framework, most widely used
- Lots of tutorials and community involvement

Heavy vs light framework
- Express is a very lightweight framework and allows you to fill in more code
- Rails is considered a heavy framework and does most of the lifting, not best for beginner frameworks 

Package.json
- Its a file that contains all the metadata of the particular package
- Name, author, contributors, license, github url, keywords, dependencies, etc.

Workflow
- npm init
- npm install packageName
- node fileName.js

Routing
``` javascript
app.get("/repeat/:text/:number", function(req, res){
  var string = "";
  for(var i = 0; i < req.params.number; i++){
    string += req.params.text;
    string += " ";
  }
  res.send(string);
});
```
- localhost:3000/repeat/floo/4
- Will render: floo floo floo floo