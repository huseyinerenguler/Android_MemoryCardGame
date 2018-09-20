package com.example.huseyinerenguler.hafizaoyunu;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameScreen extends AppCompatActivity implements View.OnClickListener {

    Chronometer chronometer;

    int numberOfRow = 4;
    int numberOfColumn = 4;

    Card cards[] = new Card[numberOfRow*numberOfColumn];
    int cardIndex = 0;

    int numberOfOpenCards = 0;
    Card firstCard;
    Card secondCard;

    int correctCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        chronometer = findViewById(R.id.zaman);

        createCards();
        setRandomForegrounds();
        chronometer.start();
    }

    public void createCards() {
        LinearLayout layout_main = (LinearLayout) findViewById(R.id.layout_main);

        for (int i = 0; i < numberOfRow; i++) {
            LinearLayout linearLayoutRow = new LinearLayout(this);
            layout_main.addView(linearLayoutRow);

            linearLayoutRow.setWeightSum(4);
            linearLayoutRow.setOrientation(LinearLayout.HORIZONTAL);
            linearLayoutRow.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    0,
                    1
            ));

            for (int j = 0; j < numberOfColumn; j++, cardIndex++) {
                cards[cardIndex] = new Card(this);
                linearLayoutRow.addView(cards[cardIndex]);
                cards[cardIndex].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View view) {
        numberOfOpenCards++;

        if (numberOfOpenCards == 1) {
            firstCard = (Card) view;
            firstCard.setBackground(getDrawable(firstCard.foreground_ID));
        }

        else if (numberOfOpenCards == 2) {

            if (view == firstCard) {
                numberOfOpenCards--;
                return;
            }

            secondCard = (Card) view;
            secondCard.setBackground(getDrawable(secondCard.foreground_ID));

            Handler h = new Handler();
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (firstCard.foreground_ID == secondCard.foreground_ID) {
                        firstCard.setVisibility(View.INVISIBLE);
                        secondCard.setVisibility(View.INVISIBLE);
                        correctCount++;

                        if (correctCount == 8) {
                            chronometer.stop();
                            TextView tvWrongCount = (TextView) findViewById(R.id.tvWrongCount);
                            int wrongCount = Integer.parseInt(tvWrongCount.getText().toString());

                            Intent i = new Intent(GameScreen.this, EndScreen.class);
                            i.putExtra("wrong", (wrongCount + ""));
                            i.putExtra("time", chronometer.getText());
                            startActivity(i);
                        }
                    }
                    else {
                        firstCard.setBackground(getDrawable(firstCard.background_ID));
                        secondCard.setBackground(getDrawable(secondCard.background_ID));

                        TextView tvWrongCount = (TextView) findViewById(R.id.tvWrongCount);
                        int wrongCount = Integer.parseInt(tvWrongCount.getText().toString());
                        wrongCount++;
                        tvWrongCount.setText(String.valueOf(wrongCount));
                    }
                    numberOfOpenCards = 0;
                }
            },450);
        }
    }

    public void setRandomForegrounds () {

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add(R.drawable.c1);
            list.add(R.drawable.c2);
            list.add(R.drawable.c3);
            list.add(R.drawable.c4);
            list.add(R.drawable.c5);
            list.add(R.drawable.c6);
            list.add(R.drawable.c7);
            list.add(R.drawable.c8);
        }

        for (int i = 0; i < cards.length; i++) {
            int randomNumber = (int) (Math.random()*list.size());
            cards[i].foreground_ID = list.get(randomNumber);
            list.remove(randomNumber);
        }
    }
}
