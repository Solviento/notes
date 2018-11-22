// there are different ways of creating a function in java (creating an average of an array for example)
// the usual way
function avgArray(arr){
  var sum = 0;
  for (var i = 0; i < arr.length; i++){
    sum += arr[i];
  }
  return sum/arr.length
}
// using forEach()
function avg(arr){
  var sum = 0;
  arr.forEach(function(elm){
    sum += elm;
  })
  return sum/arr.length;
}
// using arguments keyword
function avg(){
  var sum = 0;
  for (var i = 0; i < arguments.length; i++){
    sum += arguments[i];
  }
  return sum/arguments.length
}
// using Rest parameter syntax
function avg(...args){
  var sum = 0;
  for (let value of args){
    sum += value;
  }
  return sum/args.length;
}
// using anonymous functions
var avg = function() {
  var sum = 0;
  for (var i = 0, j = arguments.length; i < j; i++) {
    sum += arguments[i];
  }
  return sum / arguments.length;
};
