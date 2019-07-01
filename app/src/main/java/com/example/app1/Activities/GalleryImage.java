package com.example.app1.Activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app1.R;

public class GalleryImage extends AppCompatActivity {
    ImageView img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_item);

        img = findViewById(R.id.image_item_img);

        img.setImageResource(getIntent().getIntExtra("img",0));
    }
}
