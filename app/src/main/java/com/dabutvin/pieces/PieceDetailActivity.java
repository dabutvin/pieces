package com.dabutvin.pieces;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.List;

public class PieceDetailActivity extends AppCompatActivity implements ViewPagerEx.OnPageChangeListener {

    SliderLayout slider;
    TextView title;
    TextView medium;
    TextView artist;
    TextView description;
    String selectedUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piece_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            selectedUrl = extras.getString("URL");
        } else {
            startActivity(new Intent(this, MainActivity.class));
        }

        slider = (SliderLayout) findViewById(R.id.slider);
        title = (TextView) findViewById(R.id.item_title);
        medium = (TextView) findViewById(R.id.item_medium);
        artist = (TextView) findViewById(R.id.item_artist);
        description = (TextView) findViewById(R.id.item_description);

        new DownloadJsonTask(new StringCallbackInterface() {
            @Override
            public void onTaskFinished(String json) {
                new DeserializePieceTask(new PieceCallbackInterface() {
                    @Override
                    public void onTaskFinished(PieceModel result) {

                        title.setText(result.getTitle());
                        medium.setText(result.getMedium());
                        artist.setText(result.getArtist().getUsername());
                        description.setText(result.getDescription());

                        List<String> srcset = new ArrayList<>();

                        srcset.add(result.getMainImageUrl());
                        srcset.add(result.getImageUrl2());

                        for(int i =0; i < srcset.size(); i++) {
                            TextSliderView textSliderView = new TextSliderView(getApplicationContext());
                            textSliderView
                                    .description("This images description")
                                    .image(srcset.get(i))
                                    .setScaleType(BaseSliderView.ScaleType.Fit)
                                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                                        @Override
                                        public void onSliderClick(BaseSliderView slider) {
                                            // This should go back to mainactivity but stay on the current piece
                                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                            startActivity(intent);
                                        }
                                    });

                            //add your extra information
//                            textSliderView.bundle(new Bundle());
//                            textSliderView.getBundle()
//                                    .putString("artist", piece.getArtist());
//                            textSliderView.getBundle()
//                                    .putString("medium", piece.getMedium());

                            slider.addSlider(textSliderView);
                        }
                    }
                }).execute(json);
            }
        }).execute(selectedUrl);


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
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
