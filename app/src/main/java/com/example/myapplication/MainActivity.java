package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    Game game;
    // initial the buttons
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;

    private Toast toastPlayer1;
    private Toast toastPlayer2;
    private Toast toastDraw;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastPlayer1 = Toast.makeText(this, "PLAYER 1 WINS", Toast.LENGTH_LONG);
        toastPlayer2 = Toast.makeText(this, "PLAYER 2 WINS", Toast.LENGTH_LONG);
        toastDraw = Toast.makeText(this, "DRAW", Toast.LENGTH_LONG);


        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        game = new Game();
    }
    // Save the game
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("game", game);

        outState.putString("button1", (String) button1.getText());
        outState.putString("button2", (String) button2.getText());
        outState.putString("button3", (String) button3.getText());
        outState.putString("button4", (String) button4.getText());
        outState.putString("button5", (String) button5.getText());
        outState.putString("button6", (String) button6.getText());
        outState.putString("button7", (String) button7.getText());
        outState.putString("button8", (String) button8.getText());
        outState.putString("button9", (String) button9.getText());
    }

    // Preserve the game after rotation.
    public void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        game = (Game) inState.getSerializable("game");
        button1.setText(inState.getString("button1"));
        button2.setText(inState.getString("button2"));
        button3.setText(inState.getString("button3"));
        button4.setText(inState.getString("button4"));
        button5.setText(inState.getString("button5"));
        button6.setText(inState.getString("button6"));
        button7.setText(inState.getString("button7"));
        button8.setText(inState.getString("button8"));
        button9.setText(inState.getString("button9"));
    }
    public void tileClicked(View view) {
        int id = view.getId();
        Button button = (Button) view;

        int row = 0;
        int column = 0;

        switch (id) {
            case R.id.button1:
                row = 0;
                column = 0;
                break;
            case R.id.button2:
                row = 0;
                column = 1;
                break;
            case R.id.button3:
                row = 0;
                column = 2;
                break;
            case R.id.button4:
                row = 1;
                column = 0;
                break;
            case R.id.button5:
                row = 1;
                column = 1;
                break;
            case R.id.button6:
                row = 1;
                column = 2;
                break;
            case R.id.button7:
                row = 2;
                column = 0;
                break;
            case R.id.button8:
                row = 2;
                column = 1;
                break;
            case R.id.button9:
                row = 2;
                column = 2;
                break;
        }
        // place the cross or cirkel on the place when it's possible
        TileState state = game.choose(row, column);
        switch (state) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
            case INVALID:
                Toast.makeText(this, "INVALID BUTTON", Toast.LENGTH_SHORT).show();
                break;
        }
        // check of somebody has won the game and show the winner
        GameState gameState = game.won();
        switch (gameState) {
            case PLAYER_ONE:
                toastPlayer1.show();
                break;
            case PLAYER_TWO:
                toastPlayer2.show();
                break;
            case DRAW:
                toastDraw.show();
            case IN_PROGRESS:
                break;
        }

    }
    // reset the game when the reset tile is clicked
    public void resetClicked(View view) {
        game = new Game();

        button1.setText(" ");
        button2.setText(" ");
        button3.setText(" ");
        button4.setText(" ");
        button5.setText(" ");
        button6.setText(" ");
        button7.setText(" ");
        button8.setText(" ");
        button9.setText(" ");

        toastDraw.cancel();
        toastPlayer2.cancel();
        toastPlayer1.cancel();
    }
}
