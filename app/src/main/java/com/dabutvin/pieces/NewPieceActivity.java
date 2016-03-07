package com.dabutvin.pieces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Dan on 3/6/2016.
 */
public class NewPieceActivity extends AppCompatActivity {

    ArtistModel artist;

    @Override
    protected void onCreate(Bundle savedIntanceState){
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_newpiece);

        artist = new ArtistModel();
        artist.setId(1);
        artist.setUsername("Dan But");
        artist.setUrl("http://pieces.azurewebsites.net/artists/1");


        final EditText titleEditText = (EditText)findViewById(R.id.title);
        final EditText mediumEditText = (EditText)findViewById(R.id.medium);
        final EditText descriptionEditText = (EditText)findViewById(R.id.description);
        Button submitButton =(Button)findViewById(R.id.submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String medium = mediumEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                PieceModel pieceModel = new PieceModel();

                pieceModel.setArtist(artist);
                pieceModel.setTitle(title);
                pieceModel.setMedium(medium);
                pieceModel.setDescription(description);

                pieceModel.setMainImageUrl("http://lorempixel.com/249/249/");
                pieceModel.setImageUrl2("http://lorempixel.com/254/254/");

                new CreatePieceTask(new BooleanCallbackInterface() {
                    @Override
                    public void onTaskFinished(Boolean result) {
                        if (!result) {
                            Log.d("CREATE", "error");
                        }
                    }
                }).execute(pieceModel);

            }
        });
    }
}
