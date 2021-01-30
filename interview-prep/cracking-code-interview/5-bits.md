# Ch. 5 Bit Manipulation

Bit Manipulation by Hand

- Questions can call for bit manipulation outright
- Other times, it's a useful technique to optimize code
- Good to practice on pen and paper

Examples

``` java
Addition
  0110 (6)
+ 0010 (2)
= 1000 (8)

  0110 (6)
+ 0110 (6)
= 1100 (12)

  0011 (3)
+ 0010 (2)
= 0101 (5)

Multiplication
  0011 (3)
* 0101 (5)
= 1111 (15)

  0011 (3)
* 0011 (3)
= 1001 (9)

  0100 (4)
* 0011 (3)
= 1100 (12)

Subtraction
  0110 (6)
- 0011 (3)
= 0011 (3)

  1000 (8)
- 0110 (6)
= 0010 (2)

XOR
  1101 (13)
^ 0010 (2)
= 1111 (15)
    
  1101 (13)
^ 0101 (5)
= 1000 (8)

Shifting
  1111 (15)
<<   2
= 1100 (12)
& 1011 (11)
= 1000 (8)

  1101 (13)
>>   2
= 0011 (3)
```

Some tricks to know

- Multiplying a bit by 2 is the same as shifting to the left by 1 (<< 1)
- Likewise, multiplying a bit by 4 is the same as (<< 2)
- If you XOR a bit with its own negated value, you'll always get a sequence of 1's



More tricks

``` java
x ^ 0000.. = x		x & 0000.. = 0		x | 000.. = x
x ^ 1111.. = ~x		x & 1111.. = x		x | 111.. = 111..
x ^ x = 0			x & x = x			x | x = x
```



**Arithmetic vs. Logical Right Shift**

- There are two types of right shift shift operators, the >> (arithmetic shift) divides by powers or 2 while logical right shift (>>>) will visually shift the bits (viable for negative numbers)

``` java
   10110101 (-75)
>>> 1
=  01011010 (90)

   10110101 (-75)
>> 1
=  11011010 (-38)
```

- Note that arithmetic shifting to the right by let's say 32 times, will render the bit to become equal to -1

Common tasks

- Getting a bit
  - Sometimes we need to extract a bit at a certain position *i* 
  - The idea is to create a mask, say i = 3 and thus mask would be: ..00100. You would then AND the mask and the bit to retrieve the result. If the result is 0000, then the value at i is 0, otherwise, it's 1.

``` java
boolean getBit(int num, int i){
    int mask = 1 << i;
    int result = num & mask;
    return result == 0 ? false : true;	// if result == 0, return false, else true
}
```

- Setting a bit
  - To set a bit, simply create a mask, say i = 4 (00100) and then OR the bit with the mask to set the bit at position *i*

``` java
boolean setBit(int num, int i){
    int mask = 1 << i;
    int result = num | mask;
    return result;
}
```

- Updating a bit
  - To set an ith bit to a value v, we clear the bit at position i by using a mask that looks like: (111011)
  - We then shift the intended value v by i bits to then be used with OR against (num & mask)

``` JAVA
int updateBit(int num, int i, int updateValueTo){
    int value = updateValueTo << i;	// updateValueTo = 1 or 0
    int mask = ~(1<<i);
    int result = (num&mask) | value;
    return result;
}
```

- Clearing a bit
  - To clear a bit at position i, we simply create an inverse mask and then AND the bit

``` java
int clearBit(int num, int i){
    int mask = ~(1<<i);
    // 0101 & 1011 = 0001 (setting i=3 to 0)
    return num & mask;
}
```

















