package com.dabutvin.pieces;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

public class PieceDetailActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    SliderLayout slider;
    List<PieceModel> pieces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_detail);

        slider = (SliderLayout) findViewById(R.id.slider);
        pieces = new ArrayList<>();

        PieceModel newPiece = new PieceModel();
        newPiece.setArtist("the dev");
        newPiece.setMedium("java on pc");
        newPiece.setSrc("http://lorempixel.com/251/251/");
        newPiece.setTitle("Debug infinity");
        pieces.add(newPiece);

        PieceModel newPiece1 = new PieceModel();
        newPiece1.setArtist("the dev1");
        newPiece1.setMedium("java on p1c");
        newPiece1.setSrc("http://lorempixel.com/255/255/");
        newPiece1.setTitle("Debug infinity1");
        pieces.add(newPiece1);

        for (int i =0; i<pieces.size(); i++) {
            PieceModel piece = pieces.get(i);
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(piece.getTitle())
                    .image(piece.getSrc())
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("artist", piece.getArtist());
            textSliderView.getBundle()
                    .putString("medium", piece.getMedium());

            slider.addSlider(textSliderView);
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        //slider.setCustomAnimation(new DescriptionAnimation());
        //slider.setDuration(4000);
        slider.stopAutoCycle();
        slider.addOnPageChangeListener(this);
    }

    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        slider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
