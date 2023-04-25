package com.example.softmethproj5.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.softmethproj5.R;
import com.example.softmethproj5.ui.main.MainFragment;

import java.text.DecimalFormat;

/**
 * The MainActivity class contains lifecycle callbacks for the main activity.
 * @author Gabriel Ruszala, Vikrant Pulipati
 */
public class MainActivity extends AppCompatActivity {

    public static final String LIST_VIEW_STRING_FORMAT = "(%d) %s";
    public static final DecimalFormat DOLLARS_FORMAT = new DecimalFormat("$0.00");

    /**
     * @param savedInstanceState If the activity is being re-initialized after
     * previously being shut down then this Bundle contains the data it most
     * recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
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

    /**
     * Pops the backstack when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }
}