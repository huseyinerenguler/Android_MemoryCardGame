package com.example.huseyinerenguler.hafizaoyunu;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;
import android.widget.LinearLayout;

public class Card extends AppCompatButton {

    public int background_ID = R.drawable.bg_card;
    public int foreground_ID;

    public Card(Context context) {
        super(context);

        this.setBackground(getResources().getDrawable(background_ID));
        this.setLayoutParams(new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1
        ));
    }
}
