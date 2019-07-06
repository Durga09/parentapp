package org.ultimatesolution.parentapp.StudentProgress;

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

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;
import org.ultimatesolution.parentapp.Transportation.TransportRoutes;

public class StudentActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View v = findViewById(R.id.btn_attendance);
        v.setOnClickListener(this);

        View v1 = findViewById(R.id.btn_home_work);
        v1.setOnClickListener(this);

        View v2 = findViewById(R.id.btn_notice);
        v2.setOnClickListener(this);

        View v3 = findViewById(R.id.btn_ReportCard);
        v3.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_attendance) {
            Intent intent = new Intent(this, StudentAttendance.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.btn_home_work) {
            Intent intent = new Intent(this, StudentAssignments.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_notice) {
            Intent intent = new Intent(this, StudentNotices.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_ReportCard) {
            Intent intent = new Intent(this, StudentReportCard.class);
            this.startActivity(intent);
        }
    }

}
