# TicTacToe-Engine
This repository contains one file which has the Java code of a TicTacToe Engine. 
The computer plays as X and the player is O. 
Player has to input coordinates of the cells of the matrix starting from (1 ,1) for first row first column and (3,3) for the last row, last column. 
This code uses MiniMax algorithm with Alpha-Beta Pruning for faster computation since it doesn't need to test out all the possible outcomes.
This makes the execution 10-20 times faster.
You can also use the depth parameter incase you don't want to make the tree very large but in a game with 9 squares this won't be that much of a problem so I didn't feel like using it myself.

How to play:
The computer prints the board with its move since it is X. You have to input your cell number of the matrix where you want to mark O.
For example,

X|_|O
_|X|_
_|_|_

here you want to stop the computer from winning then you should input 3,3.
This will mark the O at this position,

X|_|O
_|X|_
_|_|O

This was my first attempt at working with a minimax algorithm and its so fascinating to see a computer figure out moves to play against you almost like a real human.
Feel free to let me know how I could make this code better :)
