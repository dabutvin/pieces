package com.dabutvin.pieces;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SwipeFlingAdapterView swipeFlingAdapterView;
    List<PieceModel> pieces;
    PieceAdapter pieceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeFlingAdapterView = (SwipeFlingAdapterView)findViewById(R.id.swipeflinger);

        pieces = new ArrayList<>();
        PieceModel pieceModel1 = new PieceModel();
        pieceModel1.setArtist("Dan Butvinik");
        pieceModel1.setMedium("Oil on canvas");
        pieceModel1.setSrc("tbd");
        pieceModel1.setTitle("Painting uno");

        pieces.add(pieceModel1);

        PieceModel pieceMode2 = new PieceModel();
        pieceMode2.setArtist("John Fogerty");
        pieceMode2.setMedium("Rice and beans");
        pieceMode2.setSrc("tbd");
        pieceMode2.setTitle("Painting dos");

        pieces.add(pieceMode2);

        pieceAdapter = new PieceAdapter(this, pieces);
        swipeFlingAdapterView.setAdapter(pieceAdapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                Log.d("LIST", "removed object!");
                pieces.remove(0);
                pieceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // fetch more pieces
                PieceModel newPiece = new PieceModel();
                newPiece.setArtist("Last call");
                newPiece.setMedium("nothing left");
                newPiece.setSrc("tbd");
                newPiece.setTitle("Painting fin");
                pieces.add(newPiece);
                pieceAdapter.notifyDataSetChanged();
                Log.d("LIST", "notified");
                itemsInAdapter++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View selectedView = swipeFlingAdapterView.getSelectedView();
                selectedView.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                selectedView.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });

        swipeFlingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener(){

            @Override
            public void onItemClicked(int i, Object o) {
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("Show me details")
                        .setMessage("You just got details")
                        .show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void upvote(View view) {
        View selectedView = swipeFlingAdapterView.getSelectedView();
        selectedView.findViewById(R.id.item_swipe_right_indicator).setAlpha(1);
        swipeFlingAdapterView.getTopCardListener().selectRight();
    }

    public void downvote(View view) {
        View selectedView = swipeFlingAdapterView.getSelectedView();
        selectedView.findViewById(R.id.item_swipe_left_indicator).setAlpha(1);
        swipeFlingAdapterView.getTopCardListener().selectLeft();
    }
}
