package com.example.app1.FragmentActivities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.fragment.app.FragmentActivity;

import com.example.app1.Fragments.Contacts;
import com.example.app1.R;

public class CreateContact extends FragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.create_contact);

        final EditText tname = findViewById(R.id.newName);
        final EditText tphone = findViewById(R.id.newPhone);



        LinearLayout bg = findViewById(R.id.create_contact_bg);
        bg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });


        Button cancel = findViewById(R.id.bcancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button create = findViewById(R.id.bcreate);
        create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = tname.getText().toString();
                String phone = tphone.getText().toString();
                Intent intent = new Intent();

                intent.putExtra("name", name);
                intent.putExtra("phone", phone);

                finish();
            }
        });
    }
}
