package org.ultimatesolution.parentapp.Registration;

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
import org.ultimatesolution.parentapp.StudentProgress.StudentActivity;
import org.ultimatesolution.parentapp.Tools.ToolsActivity;

public class RegisterActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View v = findViewById(R.id.btn_RegisterNewWard);
        v.setOnClickListener(this);

        View vnotice = findViewById(R.id.btn_UpdateWard);
        vnotice.setOnClickListener(this);

        View vbtn_Tools = findViewById(R.id.btn_RemoveWard);
        vbtn_Tools.setOnClickListener(this);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.btn_UpdateWard) {
            Intent intent = new Intent(this, UpdateWardActivity.class);
            this.startActivity(intent);
        }


        if (v.getId() == R.id.btn_RemoveWard) {
            Intent intent = new Intent(this, WardRemove.class);
            this.startActivity(intent);
        }
        if (v.getId() == R.id.btn_RegisterNewWard) {
            Intent intent = new Intent(this, RegisterParent.class);
            this.startActivity(intent);
        }
    }
}
