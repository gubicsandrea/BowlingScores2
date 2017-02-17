package com.example.android.bowlingscores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.lang.reflect.Field;

import static android.R.attr.id;
import static com.example.android.bowlingscores.R.color.player1Color;

public class MainActivity extends AppCompatActivity {

    int activePlayer;
    int round;
    int attempt;
    int attempt1Score;
    int[][][] playerScores; //player/round/attempt
    int score;
    int bonusAttemptForPlayer1;
    int bonusAttemptForPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerScores = new int[2][12][3];
        initVariables();
        clearTextViews();
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("ACTIVE_PLAYER",activePlayer);
        savedInstanceState.putInt("ROUND",round);
        savedInstanceState.putInt("ATTEMPT",attempt);
        savedInstanceState.putInt("ATTEMPT1_SCORE",attempt1Score);
        savedInstanceState.putInt("SCORE",score);
        savedInstanceState.putInt("BONUS_ATTEMPT_FOR_PLAYER1",bonusAttemptForPlayer1);
        savedInstanceState.putInt("BONUS_ATTEMPT_FOR_PLAYER2",bonusAttemptForPlayer2);
        for (int i=0; i<2; i++){
            for(int j=0; j<12; j++){
                savedInstanceState.putIntArray("PLAYER_SCORE_"+i+"_"+j,playerScores[i][j]);
            }
        }
        String idString;
        int id;
        TextView scoreView;
        for(int i=0; i<=round; i++){
            idString = "round" + (i + 1) + "_score1";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_1",scoreView.getText());
            idString = "round" + (i + 1) + "_score2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_2",scoreView.getText());
            idString = "round" + (i + 1);
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_3",scoreView.getText());
            idString = "round" + (i + 1) + "_score1_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_1_2",scoreView.getText());
            idString = "round" + (i + 1) + "_score2_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_2_2",scoreView.getText());
            idString = "round" + (i + 1)+"_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            savedInstanceState.putCharSequence("TV_"+(i+1)+"_2_3",scoreView.getText());
        }

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);

        activePlayer = savedInstanceState.getInt("ACTIVE_PLAYER");
        round = savedInstanceState.getInt("ROUND");
        attempt = savedInstanceState.getInt("ATTEMPT");
        attempt1Score = savedInstanceState.getInt("ATTEMPT1_SCORE");
        score = savedInstanceState.getInt("SCORE");
        bonusAttemptForPlayer1 = savedInstanceState.getInt("BONUS_ATTEMPT_FOR_PLAYER1");
        bonusAttemptForPlayer2 = savedInstanceState.getInt("BONUS_ATTEMPT_FOR_PLAYER2");

        for(int i=0; i<2; i++){
            for(int j=0; j<12; j++){
                playerScores[i][j] = savedInstanceState.getIntArray("PLAYER_SCORE_"+i+"_"+j);
            }
        }
        String idString;
        int id;
        TextView scoreView;
        for(int i=0; i<=round; i++){
            idString = "round" + (i + 1) + "_score1";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_1"));
            idString = "round" + (i + 1) + "_score2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_2"));
            idString = "round" + (i + 1);
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_3"));
            idString = "round" + (i + 1) + "_score1_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_1_2"));
            idString = "round" + (i + 1) + "_score2_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_2_2"));
            idString = "round" + (i + 1)+"_2";
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText(savedInstanceState.getCharSequence("TV_"+(i+1)+"_2_3"));
        }
        setButtonColors();
    }

    //Set the variables to start a new game
    private void initVariables(){
        activePlayer = 0;
        round = 0;
        attempt = 0;
        attempt1Score = 0;
        for (int i=0; i<2; i++){
            for(int j=0; j<12; j++){
                for(int k=0; k<3; k++){
                    playerScores[i][j][k] = 0;
                }
            }
        }
        score = 0;
        bonusAttemptForPlayer1 = 0;
        bonusAttemptForPlayer2 = 0;
    }

    //Set the UI to start a new game

    private void clearTextViews(){
        for(int i=0; i<12; i++){
            String idString = "round" + (i + 1) + "_score1" ;
            int id = getId(idString,R.id.class);
            TextView scoreView = (TextView) findViewById(id);
            scoreView.setText("");
            idString = "round" + (i + 1) + "_score2" ;
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText("");
            idString = "round" + (i + 1) + "_score1_2" ;
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText("");
            idString = "round" + (i + 1) + "_score2_2" ;
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText("");
            idString = "round" + (i + 1) ;
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText("");
            idString = "round" + (i + 1) + "_2" ;
            id = getId(idString,R.id.class);
            scoreView = (TextView) findViewById(id);
            scoreView.setText("");
        }
        String idString = "winnerText";
        int id = getId(idString,R.id.class);
        TextView view = (TextView) findViewById(id);
        view.setText("");
        disappearBonusBoxes();
        enableStrikeButton();
    }

    //display bonus boxes
    private void displayBonusBox(int bonusRound, int player){
        String idString = "round" + (bonusRound + 1) + "_score1";
        if(player == 1){
            idString += "_2";
        }
        int id = getId(idString,R.id.class);
        TextView scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.customborder));
        idString = "round" + (bonusRound + 1) + "_score2";
        if(player == 1){
            idString += "_2";
        }
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.customborder));
        idString = "round" + (bonusRound + 1)+"_bigbox";
        if(player == 1){
            idString += "_2";
        }
        id = getId(idString,R.id.class);
        RelativeLayout fullScoreView = (RelativeLayout) findViewById(id);
        fullScoreView.setBackground(getResources().getDrawable(R.drawable.customborder));
    }

    //get the border off the bonus boxes
    private void disappearBonusBoxes(){
        String idString = "round11_score1";
        int id = getId(idString,R.id.class);
        TextView scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round11_score2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round11_score1_2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round11_score2_2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_score1";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_score2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_score1_2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_score2_2";
        id = getId(idString,R.id.class);
        scoreView = (TextView) findViewById(id);
        scoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round11_bigbox";
        id = getId(idString,R.id.class);
        RelativeLayout fullScoreView = (RelativeLayout) findViewById(id);
        fullScoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_bigbox";
        id = getId(idString,R.id.class);
        fullScoreView = (RelativeLayout) findViewById(id);
        fullScoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round11_bigbox_2";
        id = getId(idString,R.id.class);
        fullScoreView = (RelativeLayout) findViewById(id);
        fullScoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
        idString = "round12_bigbox_2";
        id = getId(idString,R.id.class);
        fullScoreView = (RelativeLayout) findViewById(id);
        fullScoreView.setBackground(getResources().getDrawable(R.drawable.borderless));
    }


    //Set the score for the active attempt
    public void increaseScore(View v){
        if(score<10 && score<10-attempt1Score){
            score += 1;
        }
        displayPoint(score);
    }

    public void decreaseScore(View v){
        if(score>0) {
            score -= 1;
        }
        displayPoint(score);
    }

    //Set the score for the current attempt to 10
    public void strikeScore(View v){
        score = 10;
        setScore(v);
    }

    //Set the score for the current attempt to 0
    public void foulScore(View v){
        score = 0;
        setScore(v);
    }

    //Store the score for the current attempt & count full score & set variables
    public void setScore(View v){
        playerScores[activePlayer][round][attempt] = score;
        if(attempt == 0){
            attempt1Score = score;
            //megjelenítendő az első pontszám vagy X
            if(score == 10){
                displayScore("X");
            } else{
                displayScore(score);
            }
        } else {
            //megjelenítendő a 2. pontszám vagy /
            if(score + attempt1Score == 10){
                displayScore("/");
            } else {
                displayScore(score);
            }
            attempt1Score = 0;
        }
        if(round>=10){
            if(activePlayer == 0){
                bonusAttemptForPlayer1 -= 1;
            } else {
                bonusAttemptForPlayer2 -= 1;
            }
        }

        //count the full score for the current player
        countFullScore();

        //set variables
        setStateVariables();

        //set button colors
        setButtonColors();

        if(endGame()){
            displayWinner();
        } else if(round>=10){
            displayBonusBox(round, activePlayer);
        }

    }

    private void countFullScore(){
        if(round > 1 && playerScores[activePlayer][round-2][0] == 10 && playerScores[activePlayer][round-1][0] == 10){
            if(round == 2){
                playerScores[activePlayer][0][2] = 20 + playerScores[activePlayer][2][0];
            } else {
                playerScores[activePlayer][round-2][2] = playerScores[activePlayer][round-3][2] + 20 + playerScores[activePlayer][round][0];
            }
            //megjelenítendő a round-2. összpontszám
            displayFullScore(playerScores[activePlayer][round-2][2], round-2);
        }
        if(round > 0 && playerScores[activePlayer][round-1][0] == 10 && playerScores[activePlayer][round][0] < 10 && attempt == 1){
            if(round == 1){
                playerScores[activePlayer][round-1][2] = 10 + playerScores[activePlayer][round][0] + playerScores[activePlayer][round][1];
            } else {
                playerScores[activePlayer][round-1][2] = playerScores[activePlayer][round-2][2] + 10 + playerScores[activePlayer][round][0] + playerScores[activePlayer][round][1];
            }
            //Megjelenítendő a round-1. összpontszám
            displayFullScore(playerScores[activePlayer][round-1][2], round-1);
        }
        if(round > 0 && playerScores[activePlayer][round-1][0] + playerScores[activePlayer][round-1][1] == 10 && playerScores[activePlayer][round-1][0] < 10){
            if(round == 1){
                playerScores[activePlayer][round-1][2] = 10 + playerScores[activePlayer][round][0];
            } else {
                playerScores[activePlayer][round-1][2] = playerScores[activePlayer][round-2][2] + 10 + playerScores[activePlayer][round][0];
            }
            //Megjelenítendő a round-1. összpontszám
            if(round-1<10) {
                displayFullScore(playerScores[activePlayer][round - 1][2], round - 1);
            }
        }
        if(playerScores[activePlayer][round][0] != 10 && playerScores[activePlayer][round][0] + playerScores[activePlayer][round][1] != 10 && attempt == 1){
            if(round == 0){
                playerScores[activePlayer][round][2] = playerScores[activePlayer][round][0] + playerScores[activePlayer][round][1];
            } else {
                playerScores[activePlayer][round][2] = playerScores[activePlayer][round-1][2] + playerScores[activePlayer][round][0] + playerScores[activePlayer][round][1];
            }
            //Megjelenítendő a round. összpontszám
            if(round<10) {
                displayFullScore(playerScores[activePlayer][round][2], round);
            }
        }
    }

    //Disable the strike button for the second attempt
    private void disableStrikeButton(){
        Button strikeButton = (Button) findViewById(R.id.strike_button);
        strikeButton.setEnabled(false);
    }
    //Enable the strike button for the first attempt
    private void enableStrikeButton(){
        Button strikeButton = (Button) findViewById(R.id.strike_button);
        strikeButton.setEnabled(true);
    }

    //Set variables for the next attempt
    private void setStateVariables(){
        if(score == 10 || attempt == 1){
            //player change
            activePlayer += 1;
            if(activePlayer == 2){
                activePlayer = 0;
                //round change
                round += 1;
            }
            //attempt change
            attempt = 0;
            attempt1Score = 0;
            enableStrikeButton();
        } else {
            attempt = 1;
            disableStrikeButton();
        }
        score = 0;
        displayPoint(score);
        //set the bonus attempts in the round 10
        if (activePlayer == 0 && round == 10) {
            if (playerScores[0][9][0] == 10) {
                bonusAttemptForPlayer1 = 2;
            } else if (playerScores[0][9][0] + playerScores[0][9][1] == 10) {
                bonusAttemptForPlayer1 = 1;
            }
            if (playerScores[1][9][0] == 10) {
                bonusAttemptForPlayer2 = 2;
            } else if (playerScores[1][9][0] + playerScores[1][9][1] == 10) {
                bonusAttemptForPlayer2 = 1;
            }
        }
        //set the active player in the bonus rounds
        if(round>=10){
            if(activePlayer == 0 && bonusAttemptForPlayer1 == 0){
                activePlayer = 1;
            } else if(activePlayer == 1 && bonusAttemptForPlayer2 == 0){
                activePlayer = 0;
            }

        }

    }

    //Set the button colors to indicate which player's turn it is
    private void setButtonColors(){
        Button button;
        if(activePlayer == 0){
            button = (Button) findViewById(R.id.minus_button);
            button.setBackgroundColor(getResources().getColor(player1Color));
            button = (Button) findViewById(R.id.plus_button);
            button.setBackgroundColor(getResources().getColor(player1Color));
            button = (Button) findViewById(R.id.enter_button);
            button.setBackgroundColor(getResources().getColor(player1Color));
            button = (Button) findViewById(R.id.strike_button);
            button.setBackgroundColor(getResources().getColor(player1Color));
            button = (Button) findViewById(R.id.foul_button);
            button.setBackgroundColor(getResources().getColor(player1Color));
        } else {
            button = (Button) findViewById(R.id.minus_button);
            button.setBackgroundColor(getResources().getColor(R.color.player2Color));
            button = (Button) findViewById(R.id.plus_button);
            button.setBackgroundColor(getResources().getColor(R.color.player2Color));
            button = (Button) findViewById(R.id.enter_button);
            button.setBackgroundColor(getResources().getColor(R.color.player2Color));
            button = (Button) findViewById(R.id.strike_button);
            button.setBackgroundColor(getResources().getColor(R.color.player2Color));
            button = (Button) findViewById(R.id.foul_button);
            button.setBackgroundColor(getResources().getColor(R.color.player2Color));
        }
    }

    //Start a new game
    public void newGame(View v){
        initVariables();
        clearTextViews();
    }

    //End the game
    public boolean endGame(){
        if(round>=10){
            return (bonusAttemptForPlayer1 == 0 && bonusAttemptForPlayer2 == 0);
        }
        return false;
    }

    //Displays the winner
    public void displayWinner(){
        TextView winnerTextView = (TextView) findViewById(R.id.winnerText);
        if (playerScores[0][9][2]>playerScores[1][9][2]){
            winnerTextView.setText("Congratulation, Player 1!");
        } else if(playerScores[0][9][2]<playerScores[1][9][2]) {
            winnerTextView.setText("Congratulation, Player 2!");
        } else {
            winnerTextView.setText("You both are winners!");
        }

    }

    //Get the value of an id if I know the name of it
    public static int getId(String resourceName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resourceName);
            return idField.getInt(idField);
        } catch (Exception e) {
            throw new RuntimeException("No resource ID found for: "
                    + resourceName + " / " + c, e);
        }
    }

    //display the point int the points textview
    private void displayPoint(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.points);
        quantityTextView.setText("" + number);
    }

    //display the score int for the current attempt
    public void displayScore(int number) {
        String idString = "round" + (round + 1) + "_score" + (attempt + 1);
        if(activePlayer == 1){
            idString += "_2";
        }
        int id = getId(idString,R.id.class);
        TextView scoreView = (TextView) findViewById(id);
        scoreView.setText(String.valueOf(number));
    }

    //display the score string like X and / for the current attempt
    public void displayScore(String str) {
        String idString = "round" + (round + 1) + "_score" + (attempt + 1);
        if(activePlayer == 1){
            idString += "_2";
        }
        int id = getId(idString,R.id.class);
        TextView scoreView = (TextView) findViewById(id);
        scoreView.setText(str);
    }

    //display the full score for the active player
    public void displayFullScore(int number, int round) {
        String idString = "round" + (round + 1);
        if(activePlayer == 1){
            idString += "_2";
        }
        int id = getId(idString,R.id.class);
        TextView fullScoreView = (TextView) findViewById(id);
        fullScoreView.setText(String.valueOf(number));
    }
}
