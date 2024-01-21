# rot13

[Originally written as a technical test several years ago. ROT13 substitution cipher.]

I have written two methods to decode strings using ROT13/5 in order to compare and contrast differences.

There are two main difference between these methods:

The first is the use of the StringBuilder class. Strings in Java are immutable, meaning that in decode2() when we call result += c;
we are actually creating a new String every time. This is not really a problem when the String is only 40 characters long as in the example. However, if we were to massively increase the length of the encoded String, for example by a factor of a million, then this would cause noticeable performance issues as we would be creating millions of new String objects, with only one of them ultimately being displayed to the user.
Java's StringBuilder class gets around these problems by changing the StringBuilder object itself when characters are appended, significantly increasing performance with long strings, as there is only one object created.

We could write this formally by saying that, because the number of String objects created by decode2() is directly proportional to the length of the String, we can say this particular aspect of the code has a complexity of O(n). On the other hand, because decode1() only uses one object no matter the length of the String, we can say that this particular aspect of the code has a complexity of O(1). All other aspects being equal, decode1() will run with improved time and space complexity over decode2().

The second major difference is that decode1() takes advantage of Java's ability to perform arithmetic on chars. We look at the chars numeric value, and if its an ascii alphanumeric character we apply the ROT13/5 algorithm. This makes for cleaner code. Decode2() uses three fixed arrays of lowercase, uppercase, and digits, and iterates through each until a match is found, then applies the algorithm.

However, I think the human readability of decode2() is slightly better than decode1(). This is because it is clearer as to what is going on because the arrays spell out the alphabet and digits quite clearly, and we can see that we perform an array lookup on them. Further, it visually shows the reader how the algorithm works which can improve understanding. Decode1() requires the reader to have a knowledge of Java's ability to link chars to ascii numbers and perform arithmetic on them - the code looks cleaner as a result, but is less human-readable and requires more technical knowledge. This could be overcome by writing comments to explain what the code is doing. Further, once the code is written and tested to be working, the ROT13 and ROT5 ciphers will not change, and so the code could be left alone as a black box, allowing future programmers to use the code without knowing how it works. The beauty of code reuse!

Finally, I think it should be said that this is a command line tool. Programmers will have no difficulty using it, but for a non-technical user a GUI of some description should be written to make it easier to use.