package com.example.huseyinerenguler.hafizaoyunu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EndScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_screen);


        Intent i = getIntent();
        String time = i.getStringExtra("time");
        String wrong = i.getStringExtra("wrong");

        TextView tvWonMessage = (TextView) findViewById(R.id.tvWonMessage);
        tvWonMessage.setText("You won with " + wrong + " mistakes in " + time + " seconds.");
    }
}
