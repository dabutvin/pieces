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

        new DownloadJsonTask(new StringCallbackInterface() {
            @Override
            public void onTaskFinished(String json) {
                new DeserializePiecesTask(new PiecesCallbackInterface() {
                    @Override
                    public void onTaskFinished(List<PieceModel> result) {
                        for(int i =0; i<result.size();i++) {
                            pieces.add(result.get(i));
                            pieceAdapter.notifyDataSetChanged();
                        }
                    }
                }).execute(json);
            }
        }).execute("http://pieces.azurewebsites.net/api/data/");

        pieces = new ArrayList<>();
        pieceAdapter = new PieceAdapter(this, pieces);
        swipeFlingAdapterView.setAdapter(pieceAdapter);
        swipeFlingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {

            @Override
            public void removeFirstObjectInAdapter() {
                // called on every swipe left or right first then the appropriate method below
                Log.d("LIST", "removed object!");
                pieces.remove(0);
                pieceAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                Log.d("SWIPE", "You just swiped left!");
            }

            @Override
            public void onRightCardExit(Object o) {
                Log.d("SWIPE", "You just swiped right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                Log.d("ListAboutEmpty", "Only" + itemsInAdapter + " left");
                // fetch more pieces

                PieceModel newPiece = new PieceModel();
                newPiece.setArtist("the dev");
                newPiece.setMedium("java on pc");
                newPiece.setSrc("http://lorempixel.com/250/250/");
                newPiece.setTitle("Debug infinity");
                pieces.add(newPiece);

                pieceAdapter.notifyDataSetChanged();

                itemsInAdapter++;
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View selectedView = swipeFlingAdapterView.getSelectedView();
                selectedView.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                selectedView.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });
        swipeFlingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {

            @Override
            public void onItemClicked(int i, Object o) {

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
