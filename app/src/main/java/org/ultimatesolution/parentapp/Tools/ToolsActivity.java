package org.ultimatesolution.parentapp.Tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.ultimatesolution.parentapp.R;
import org.ultimatesolution.parentapp.StudentProgress.StudentActivity;

public class ToolsActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View vbackup = findViewById(R.id.btn_toolsBackup);
        vbackup.setOnClickListener(this);

        View v2 = findViewById(R.id.btn_Import);
        v2.setOnClickListener(this);

        View v3 = findViewById(R.id.btn_Restore);
        v3.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.btn_toolsBackup){
            Intent intent = new Intent(this, ToolsBackup.class);
            this.startActivity(intent);}

        if (v.getId() == R.id.btn_Import){
            Intent intent = new Intent(this, ToolsImport.class);
            this.startActivity(intent);}

        if (v.getId() == R.id.btn_Restore){
            Intent intent = new Intent(this, ToolsRestore.class);
            this.startActivity(intent);}
    }
}
