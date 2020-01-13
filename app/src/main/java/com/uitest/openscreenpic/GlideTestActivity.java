package com.uitest.openscreenpic;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uitest.R;

public class GlideTestActivity extends AppCompatActivity {

    String url = "http://img1.dzwww.com:8080/tupian_pl/20150813/16/7858995348613407436.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_test);

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        Glide.with(this)
                .load(url)
                .into(imageView);

    }
}
