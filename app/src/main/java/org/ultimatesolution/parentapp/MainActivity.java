package org.ultimatesolution.parentapp;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.DatabaseClasses.DBHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.InitialisationHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.UserRegistrationHelper;
import org.ultimatesolution.parentapp.Registration.RegisterActivity;
import org.ultimatesolution.parentapp.Registration.RegisterParent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);

        context = getApplicationContext();
        activity = this;

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View v = findViewById(R.id.buttoncontinue);
        v.setOnClickListener(this);

        requestPermission();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        requestPermission();
        SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
        new DBHelper(null).updateTables(db);
        UserRegistrationHelper hlp = new UserRegistrationHelper();
        try {
            if (!hlp.isRegistered(db)) {
                SQLiteDatabase mydatabase = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                try {
                    new DBHelper(null).ctTables(mydatabase);
                } catch (Exception ex) {
                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                }
                if (db.isOpen())
                    db.close();
                if (mydatabase.isOpen())
                    mydatabase.close();
                Intent intent = new Intent(this, RegisterParent.class);
                this.startActivity(intent);
            } else {
                InitialisationHelper ih = new InitialisationHelper();
                ih.setSchoolKey(db);
                if (db.isOpen())
                    db.close();
                //Old Passed
                Intent intent = new Intent(this, RegisterActivity.class);
//                Intent intent = new Intent(this, Home.class);
                this.startActivity(intent);
            }
        }catch (Exception ex){
            new DBHelper(null).ctTables(db);
        }
    }

    private void requestPermission() {
        // Check if Android M or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(context, "Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA
                }, PERMISSION_REQUEST_CODE);
            }

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_PHONE_STATE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE},
                        MY_PERMISSIONS_REQUEST_READ_PHONE_STATE);
            }

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            }

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }
    }
}
