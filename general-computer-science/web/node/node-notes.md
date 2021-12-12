# Basic Node Notes

Importing a node package
``` JavaScript
var cat = require("name-of-package");
console.log(cat());

```
Importing multiple packages
``` JavaScript
var express = require('express'),
    app = express(),
    mongoose = require('mongoose'),
    logger = require('morgan'),
    bodyParser = require('body-parser');
```
Importing 'faker' package to display randomized item and item price (using API)
``` JavaScript
var faker = require('faker');

for (var i = 0; i < 10; i++){
  console.log(faker.commerce.productName() + '-' + faker.commerse.price());
}
```
