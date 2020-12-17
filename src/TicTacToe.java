import java.util.concurrent.TimeUnit;
import java.util.Scanner;
public class TicTacToe {
    char playerTurn = 'X'; //Assigning the first turn to X
    int[] move = new int[2]; //To store the moves returned by the minimax algorithm
    char endGame;
    //Board
    char[][] currentState = {{'.','.','.'},
                    {'.','.','.'},
                    {'.','.','.'}};
    int[] result = new int[3]; //Creating this array to return three values;
    Scanner scanner = new Scanner(System.in);
    public TicTacToe()
    {
        play();
    }
    public void drawBoard() //Drawing the TicTacToe board
    {
        for(int i=0;i<3;i++)
        {
            for(int j =0;j<3;j++)
            {
                System.out.print(currentState[i][j]+"|");
            }
            System.out.println();
        }
        System.out.println();
    }
    public boolean isValid(int px,int py) //To check if the move is legal
    {
        if(px>2 || px <0 ||py>2 ||py<0)
        {
            return false;
        }
        else if(currentState[px][py]!='.')
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public char isEnd() //To check if the game has ended
    {
        //Vertical win
        for(int i=0;i<3;i++)
        {
            if(currentState[0][i]!='.' && currentState[0][i]==currentState[1][i]
            && currentState[1][i]==currentState[2][i])
            {
                return currentState[0][i];
            }
        }
        //Horizontal win
        for(int i =0;i<3;i++)
        {
            if(currentState[i][0]!='.'&&currentState[i][0]==currentState[i][1]
            && currentState[i][1]==currentState[i][2])
            {
                return currentState[i][0];
            }
        }
        //Diagonal win
        if(currentState[0][0]!='.'&&currentState[0][0]==currentState[1][1] && currentState[1][1]== currentState[2][2])
        {
            return currentState[0][0];
        }
        //Second diagonal win
        if(currentState[0][2]!='.'&&currentState[0][2]==currentState[1][1] && currentState[1][1]== currentState[2][0])
        {
            return currentState[0][2];
        }
        //Checking if there's an empty space and if we can still go on
        for(int i=0;i<3;i++)
        {
            for(int j =0;j<3;j++)
            {
                if(currentState[i][j]=='.');
                return 0;
            }
        }
        //Tie case
        return '.';
    }
    public int[] bestMove(char[][] board)
    {
        int bestScore = -1000 ;
        for(int i=0;i<3;i++)
        {
            for(int j =0;j<3;j++)
            {
               if(board[i][j]=='.')
               {
                   board[i][j] = 'X';
                   if(bestScore < minimax(board,0,false, Integer.MIN_VALUE,Integer.MAX_VALUE))
                   {
                       bestScore = Integer.max(minimax(board,0, false,Integer.MIN_VALUE,Integer.MAX_VALUE),bestScore);
                       move[0] = i;
                       move[1] = j;
                   }
                   board[i][j] = '.';
               }
            }
        }
        for (int i= 0; i<move.length;i++)
        {
            System.out.print(move[i]+1+",");
        }
        System.out.println();
        return move;
    }
    public int minimax(char[][] board,int depth, boolean isMaximizing, int alpha, int beta)
    {
        endGame = isEnd();
        if(endGame == 'X')
        {
            return 1000;
        }
        else if(endGame == 'O')
        {
            return -1000;
        }
        else if(endGame == '.')
        {
            return 0;
        }
        if(isMaximizing)
        {
            int maxScore = -100000;
            for(int i=0;i<3;i++)
            {
                for(int j =0;j<3;j++)
                {
                    if(alpha>=beta)
                    {
                        break;
                    }
                    if(board[i][j]=='.')
                    {
                        board[i][j] = 'X';
                        maxScore = Integer.max(minimax(board,depth+1,false,alpha,beta),maxScore);
                        alpha = Integer.max(maxScore,alpha);
                        board[i][j] = '.';
                    }
                }
            }
            return maxScore;
        }
        else
        {
            int minScore = 100000;
            for(int i=0;i<3;i++)
            {
                for(int j =0;j<3;j++)
                {
                    if(alpha>=beta)
                    {
                        break;
                    }
                    if(board[i][j]=='.')
                    {
                        board[i][j] = 'O';
                        minScore = Integer.min(minimax(board,depth+1,true, alpha, beta),minScore);
                        beta = Integer.min(minScore, beta);
                        board[i][j] = '.';
                    }
                }
            }
            return minScore;
        }
    }
    public boolean anyMovesLeft(char[][] board) //To check if there are any moves left
    {
        for(int i=0;i<3;i++)
        {
            for(int j =0;j<3;j++)
            {
                if(board[i][j]=='.')
                {
                    return true;
                }
            }
        }
        return false;
    }
    public void play()
    {
        while (true)
        {
            drawBoard();
            if(isEnd()!=0)
            {
                if(isEnd()=='X')
                {
                    System.out.println("X is the winner!");
                }
                else if (isEnd()=='O')
                {
                    System.out.println("O is the winner!");
                }
                System.exit(0);
            }
            else
            {
                //If it's X's turn
                if (playerTurn == 'X')
                {
                    long startTime = System.nanoTime();
                    result = bestMove(currentState);
                    long endTime = System.nanoTime();
                    System.out.println("Evaluation time: " + (endTime - startTime)/10000);
                    currentState[result[0]][result[1]] = 'X';
                    playerTurn = 'O';
                }
                else
                {

                    if(anyMovesLeft(currentState))
                    {
                        System.out.println("Enter the O coords (x):");
                        result[0]= scanner.nextInt()-1;
                        System.out.println("Enter the O coords (y):");
                        result[1] = scanner.nextInt()-1;
                        if(isValid(result[0],result[1]))
                        {
                            currentState[result[0]][result[1]]='O';
                            playerTurn='X';
                        }
                        else
                        {
                            System.out.println("Not a valid move!");
                        }
                    }
                    else
                    {
                        System.out.println("It is a tie!");
                        System.exit(0);
                    }
                }
            }

        }
    }
    public static void main(String args[])
    {
        TicTacToe ticTacToe = new TicTacToe();
    }

}
