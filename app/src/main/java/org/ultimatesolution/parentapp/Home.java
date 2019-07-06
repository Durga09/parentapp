package org.ultimatesolution.parentapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.ClassActivities.ClassActivity;
import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.HomeActivities.HomeNoticeBoard;
import org.ultimatesolution.parentapp.Registration.RegisterActivity;
import org.ultimatesolution.parentapp.SchoolActivities.SchoolActivity;
import org.ultimatesolution.parentapp.StudentProgress.StudentActivity;
import org.ultimatesolution.parentapp.Tools.ToolsActivity;
import org.ultimatesolution.parentapp.Transportation.TransportActivity;

public class Home extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setBackgroundColor(Color.rgb(0,128,255));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View v = findViewById(R.id.btn_student_main);
        v.setOnClickListener(this);

        View vnotice = findViewById(R.id.btn_notice);
        vnotice.setOnClickListener(this);

        View vbtn_Tools = findViewById(R.id.btn_Tools);
        vbtn_Tools.setOnClickListener(this);

        View vbtn_registration = findViewById(R.id.btn_registration);
        vbtn_registration.setOnClickListener(this);

        View vbtn_school = findViewById(R.id.btn_School);
        vbtn_school.setOnClickListener(this);

        View vbtn_class = findViewById(R.id.btn_Class);
        vbtn_class.setOnClickListener(this);



        View vbtn_transport = findViewById(R.id.btn_Transport);
        vbtn_transport.setOnClickListener(this);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.btn_student_main) {
            Intent intent = new Intent(this, StudentActivity.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_notice) {
            Intent intent = new Intent(this, HomeNoticeBoard.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.btn_Tools) {
            Intent intent = new Intent(this, ToolsActivity.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.btn_registration) {
            Intent intent = new Intent(this, RegisterActivity.class);
            this.startActivity(intent);
        }

        if (v.getId() == R.id.btn_School) {
            Intent intent = new Intent(this, SchoolActivity.class);
            this.startActivity(intent);}

        if (v.getId() == R.id.btn_Class) {
            Intent intent = new Intent(this, ClassActivity.class);
            this.startActivity(intent);}

        if (v.getId() == R.id.btn_Transport) {
            Intent intent = new Intent(this, TransportActivity.class);
            this.startActivity(intent);}


    }

}
