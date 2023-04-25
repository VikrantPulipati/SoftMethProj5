package com.example.softmethproj5.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softmethproj5.R;
import com.example.softmethproj5.ui.main.MainFragment;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static final String LIST_VIEW_STRING_FORMAT = "(%d) %s";
    public static final DecimalFormat DOLLARS_FORMAT = new DecimalFormat("$0.00");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}