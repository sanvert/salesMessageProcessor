Sales Message Processor

sales.App class is the main class to run console application.

mvn test -> Runs the tests under test directory.

mvn install -> Runs tests and create runnable jar.

Message Types For Input:

Type 1 - Single Sale
<messageType:int><blank><itemType:Alphabetical Chars><blank><price:double>

1 apple 10.1
1 melon 12

Type 2 - Multiple Sale
<messageType:int><blank><amount:int><blank><itemType:Alphabetical Chars><blank><price:double>

2 10 apple 20
2 11 carrot 12.3

Type3 - Sales Update
<messageType:int><blank><price:double><blank><itemType:Alphabetical Chars><blank><operationType:Alphabetical Chars>

3 10.1 apple add
3 4 melon subtract
3 2 carrot multiply

