package com.moutamid.letswander.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.fxn.stash.Stash;
import com.moutamid.letswander.Constants;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Stash.getBoolean(Constants.ARE_ALL_PERMISSIONS_GRANTED, false)) {
            //not granted
            startActivity(new Intent(this, MapsActivity.class));
        } else {
            startActivity(new Intent(this, PermissionsActivity.class));
        }


    }
}