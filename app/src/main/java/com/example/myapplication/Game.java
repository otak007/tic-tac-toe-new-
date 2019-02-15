package com.example.myapplication;


import java.io.Serializable;

public class Game implements Serializable {
    final private int BOARD_SIZE = 3;
    private TileState[][] board;

    private Boolean playerOneTurn = true;  // true if player 1's turn, false if player 2's turn
    private int movesPlayed = 0;
    private Boolean gameOver;

    public Game() {

        // constructor
        board = new TileState[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = TileState.BLANK;

        gameOver = false;
    }
    // Change the tile in a Cross or a Circle if it's possible
    public TileState choose(int row, int column) {
        if (board[row][column] == TileState.BLANK && gameOver == false) {
            if (playerOneTurn == true) {
                board[row][column] = TileState.CROSS;
                playerOneTurn = false;
            }
            else {
                board[row][column] = TileState.CIRCLE;
                playerOneTurn = true;
            }
            movesPlayed++;
        }
        else if (gameOver == false)
            board[row][column] = TileState.INVALID;

        return board[row][column];
    }
    // Check if somebody has won the game.
    public GameState won(){
        for (int k=0; k<BOARD_SIZE; k++)
            if ((board[k][0] == TileState.CROSS && board[k][1] == TileState.CROSS && board[k][2] == TileState.CROSS) || (board[0][k] == TileState.CROSS && board[1][k] == TileState.CROSS && board[2][k] == TileState.CROSS) || (board[0][0] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][2] == TileState.CROSS) || (board[0][2] == TileState.CROSS && board[1][1] == TileState.CROSS && board[2][0] == TileState.CROSS)) {
                gameOver = true;
                return GameState.PLAYER_ONE;
            }
            else if ((board[k][0] == TileState.CIRCLE && board[k][1] == TileState.CIRCLE && board[k][2] == TileState.CIRCLE) || (board[0][k] == TileState.CIRCLE && board[1][k] == TileState.CIRCLE && board[2][k] == TileState.CIRCLE) || (board[0][0] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][2] == TileState.CIRCLE) || (board[0][2] == TileState.CIRCLE && board[1][1] == TileState.CIRCLE && board[2][0] == TileState.CIRCLE)){
                gameOver = true;
                return GameState.PLAYER_TWO;
            }
        if (movesPlayed == BOARD_SIZE*BOARD_SIZE) {
            gameOver = true;
            return GameState.DRAW;
        }
        return GameState.IN_PROGRESS;
    }
}
