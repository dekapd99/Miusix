package com.deka_32864.miusix;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("User Profile");
    }
}