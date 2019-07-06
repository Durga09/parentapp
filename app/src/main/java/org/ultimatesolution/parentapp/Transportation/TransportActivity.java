package org.ultimatesolution.parentapp.Transportation;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;
import org.ultimatesolution.parentapp.StudentProgress.StudentActivity;

public class TransportActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(0,128,255));


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View v = findViewById(R.id.btn_transportRoutes);
        v.setOnClickListener(this);

        View v1 = findViewById(R.id.btn_transportNoShow);
        v1.setOnClickListener(this);

        View v2 = findViewById(R.id.btn_transportAbsent);
        v2.setOnClickListener(this);

        View v3 = findViewById(R.id.btn_transportRunningStatus);
        v3.setOnClickListener(this);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.btn_transportRoutes) {
            Intent intent = new Intent(this, TransportRoutes.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.btn_transportNoShow) {
            Intent intent = new Intent(this, TransportPostNoShow.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_transportAbsent) {
            Intent intent = new Intent(this, TransportPostWardAbsence.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_transportRunningStatus) {
            Intent intent = new Intent(this, TransportRoutesRunningStatus.class);
            this.startActivity(intent);
        }
    }

    }
