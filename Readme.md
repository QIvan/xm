# How to

## build

```bash
$ ./gradlew clean build
```
## run 

_Note for Windows users: use **chess.bat**_

Print usage:
```bash 
./build/distributions/chess-1.0.0-SNAPSHOT/bin/chess
```

It will be printed: 
```
Usage: <main class> [options]
  Options:
    -c, --chessboard-size
      Size of chessboard
      Default: 8
  * -f, -e, --finish, --end
      Finish square
    -p, --piece
      Type of piece
      Default: Knight
      Possible Values: [King, Knight]
    --debug, --print-statistic
      Print statistic after solution
      Default: true
  * -s, --start
      Start square
```

Example of run: 
```
./build/distributions/chess-1.0.0/bin/chess -e b3 -s h5
```

Output should be 
```
h5 -> g7 -> e6 -> c5 -> b3

Iterations 398

Result chessboard.
  [] - start 
  {} - finish
   a	b	c	d	e	f	g	h	
   -	-	-	-	-	-	-	-	
8| 5	4	5	4	5	4	5	6	
7| 4	3	4	3	4	5	4	5	
6| 3	4	3	4	3	4	5	4	
5| 2	3	2	5	4	3	4	[5]	
4| 3	4	3	2	3	4	5	4	
3| 4	{1}	4	3	4	3	4	5	
2| 3	4	3	2	3	4	5	4	
1| 2	3	2	5	4	3	4	5	
```




# Description


Create a Java application that should represent an empty chessboard where the user will be able to enter a starting position and an ending position. The application should then calculate a list of all possible paths that one knight piece in the starting position could take to reach the ending position in 3 moves. Some inputs might not have a solution, in this case the program should display a message that no solution has been found. Otherwise, the shortest path (if that exists) should be returned.

Please note:
- Although a graphical display would be welcome, this is not required. 
- Input can be given by the command line, a text file or whatever you might prefer.
- Output could also be kept simple: just print out the path in a textual format (i.e. A2 -> B5 etc) 
- Emphasis will be given on the algorithm, general code structure and how the program could be extended.
- Unit tests would be highly appreciated
