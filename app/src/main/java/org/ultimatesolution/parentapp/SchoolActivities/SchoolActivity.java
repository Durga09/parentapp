package org.ultimatesolution.parentapp.SchoolActivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.ClassActivities.ClassLeave;
import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;

public class SchoolActivity extends AppCompatActivity   implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View v3 = findViewById(R.id.btn_schoolEvents);
        v3.setOnClickListener((View.OnClickListener) this);

        View v2 = findViewById(R.id.btn_schoolFees);
        v2.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btn_schoolEvents) {
            Intent intent = new Intent(this, SchoolEvents.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_schoolFees) {
            Intent intent = new Intent(this, SchoolFees.class);
            this.startActivity(intent);
        }
    }
}
