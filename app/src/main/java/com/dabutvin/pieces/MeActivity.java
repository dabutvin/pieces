package com.dabutvin.pieces;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Dan on 3/6/2016.
 */
public class MeActivity extends AppCompatActivity {


    ArtistModel artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);


    }

    public void newpiece(View view) {
        Intent intent = new Intent(getApplicationContext(), NewPieceActivity.class);
        startActivity(intent);
    }
}
