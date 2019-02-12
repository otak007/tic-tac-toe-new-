package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
    }

    public void tileClicked(View view) {
        int id = view.getId();

        Log.d("MainActivity.java", "dit is de id: " + id);
        Button button = (Button) view;

        int row = 0;
        int column = 0;

        switch (id) {
            case R.id.button1:
                row = 1;
                column = 1;
                break;
            case R.id.button2:
                row = 1;
                column = 2;
                break;
            case R.id.button3:
                row = 1;
                column = 3;
                break;
            case R.id.button4:
                row = 2;
                column = 1;
                break;
            case R.id.button5:
                row = 2;
                column = 2;
                break;
            case R.id.button6:
                row = 2;
                column = 3;
                break;
            case R.id.button7:
                row = 3;
                column = 1;
                break;
            case R.id.button8:
                row = 3;
                column = 2;
                break;
            case R.id.button9:
                row = 3;
                column = 3;
                break;
        }

        TileState state = game.choose(row, column);

        switch (state) {
            case CROSS:
                button.setText('X');
                break;
            case CIRCLE:
                button.setText('O');
                break;
            case INVALID:
                Toast.makeText(this, "NOT VALID", Toast.LENGTH_SHORT);
                break;
        }
    }

    public void resetClicked(View view) {
        game = new Game();
    }
}
