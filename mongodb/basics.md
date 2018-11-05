# MongoDB Basics

``` MongoDB
Format

{

Field1: value1,

Field2: value2

}
```



{

First_name: "john",

Last_name: "doe",

Memberships: ["mem1", "mem2"],

Address:{

Street: "4 main st",

City: "Boston"

}

Contacts:[

{name: "Brad", relationship: "Friend"}

}

}



Create database

User mycustomers



Create User

db.createUser({

user: "brad",

pwd: "1234",

roles: [ "readWrite", "dbAdmin"]

});



Create collection (collections hold records, documents)

db.createCollection('customers');



Insert document inside a collection

db.customers.insert({first_name: "John", last_name: "Doe"});



*multiple documents

db.customers.insert([{first_name:"Steven", last_name:"Smith"}, {first_name:"Joan", last_name:"Johnson", gender:"female"}]);



See all documents inside a collection

db.customers.find();



**Replace (not append) document information based on a match

db.customers.update({first_name:"John"}, {first_name:"John", last_name:"Doe", gender:"male"});



Update/append document information based on a match using $set operator

db.customers.update({first_name:"Steven"}, {$set:{gender:"male"}});



$inc operator (increment)

db.customers.update({first_name:"Steven"}, {$set:{age: 5}}); (will add +5 years to age after adding an age parameter



Remove parameter

db.customers.update({first_name:"Steven"}, {$unset:{age: 1}});



Search for element, if not found then upsert it into the collection

db.customers.update({first_name:"Mary"}, {first_name:"Mary", last_name:"Samson"}, {upsert: true}); // can also use 1?



Rename place parameter using $rename operator

db.customers.update({first_name:"Steven"}, {$rename: {"gender": "sex"}});



Remove element using match

db.customers.remove({first_name:"Steven"});



Remove only one element using match

db.customers.remove({first_name:"Steven"}, {justOne: true});



Find elements using $or operator

db.customers.find({$or:[{first_name:"Sharon"}, {first_name: "Troy"}]});



Find elements that are less than using the $lt operator

db.customers.find({age:{$lt:40}});



Finding "Boston" within the Address->City field of an element

db.customers.find({"address.city":"Boston"});



Sorting last name by ascending order (-1 for descending order)

db.customers.find().sort({last_name:1});



Counting total of matched elements

db.customers.find({gender:"male"}).count();



Find first four of sorted last name by ascending order

db.customers.find().limit(4).sort({last_name:1});



Iterate through elements using forEach and print function (doc is simply a variable name)

db.customers.find().forEach(function(doc){print("Customer name:" + doc.first_name)});
