package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean PLAYER=true;

    public  int TURN_COUNT=0;

    int[][] boardStatus=new int[3][3];

    public int[][] board={
            {R.id.button,R.id.button2,R.id.button3},
            {R.id.button4,R.id.button5,R.id.button6},
            {R.id.button7,R.id.button8,R.id.button9}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBoardStatus();
    }

    private void initializeBoardStatus(){
        for(int i=0;i<=2;i++){
            for(int j=0;j<=2;j++)
                boardStatus[i][j]=-1;
        }
    }


    public void reset(View view){
        finish();
        startActivity(getIntent());
        overridePendingTransition(0,0);

    }

    public void play(View view){
        switch(view.getId()){
            case R.id.button:updateValue(0,0,PLAYER);
                break;
            case R.id.button2:updateValue(0,1,PLAYER);
                break;
            case R.id.button3:updateValue(0,2,PLAYER);
                break;
            case R.id.button4:updateValue(1,0,PLAYER);
                break;
            case R.id.button5:updateValue(1,1,PLAYER);
                break;
            case R.id.button6:updateValue(1,2,PLAYER);
                break;
            case R.id.button7:updateValue(2,0,PLAYER);
                break;
            case R.id.button8:updateValue(2,1,PLAYER);
                break;
            case R.id.button9:updateValue(2,2,PLAYER);
                break;
        }
        PLAYER=!PLAYER;
        TURN_COUNT++;

        if(PLAYER)
            updateDisplay("Player X Turn");
        else
            updateDisplay("Player O Turn");
        if(TURN_COUNT==9){
            updateDisplay("Game Draw");
        }
        checkWinner();
    }

    public void checkWinner() {
        //horizontal rows
        for(int i=0;i<=2;i++){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][0]== boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("Player X is Winner");

                }
                else if (boardStatus[i][0]==0){
                    updateDisplay("Player O is Winner");

                }
            }
        }
        //vertical rows
        for(int i=0;i<=2;i++){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[0][i]== boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("Player X is Winner");

                }
                else if (boardStatus[0][i]==0){
                    updateDisplay("Player O is Winner");

                }
            }
        }
        //diagonal 1
        if(boardStatus[0][0]==boardStatus[1][1] && boardStatus[0][0]==boardStatus[2][2]){
            if(boardStatus[0][0]==1)
                updateDisplay("Player X is Winner");
            else if (boardStatus[0][0]==0)
                updateDisplay("Player O is Winner");
        }
        //diagonal2
        if(boardStatus[0][2]==boardStatus[1][1] && boardStatus[0][2]==boardStatus[2][0]){
            if(boardStatus[0][2]==1)
                updateDisplay("Player X is Winner");
            else if (boardStatus[0][2]==0)
                updateDisplay("Player O is Winner");
        }
    }

    public void updateDisplay(String S){
        TextView textView=(TextView)findViewById(R.id.displayTv);
        textView.setText(S);
        if(S.contains("Winner"))
            disableButton();
    }

    public void disableButton(){
        for(int[] i: board)
            for(int j: i){
                Button b=(Button)findViewById(j);
                b.setEnabled(false);
            }
    }

    public void updateValue(int row, int col, boolean p) {
        Button b=(Button)findViewById(board[row][col]);
        b.setEnabled(false);
        b.setText(p?"X": "O");
        boardStatus[row][col]=p?1:0;
    }
}

