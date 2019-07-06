package org.ultimatesolution.parentapp.ClassActivities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;

public class ClassTimeTable extends AppCompatActivity {

    TextView etClassTimings, etBreaks;

    TextView etHdrP1, etHdrP2, etHdrP3, etHdrP4, etHdrP5, etHdrP6, etHdrP7, etHdrP8, etHdrP9, etHdrP10;
    TextView etMonP1, etMonP2, etMonP3, etMonP4, etMonP5, etMonP6, etMonP7, etMonP8, etMonP9, etMonP10;

    TextView etTueP1, etTueP2, etTueP3, etTueP4, etTueP5, etTueP6, etTueP7, etTueP8, etTueP9, etTueP10;
    TextView etWedP1, etWedP2, etWedP3, etWedP4, etWedP5, etWedP6, etWedP7, etWedP8, etWedP9, etWedP10;
    TextView etThuP1, etThuP2, etThuP3, etThuP4, etThuP5, etThuP6, etThuP7, etThuP8, etThuP9, etThuP10;

    TextView etFriP1, etFriP2, etFriP3, etFriP4, etFriP5, etFriP6, etFriP7, etFriP8, etFriP9, etFriP10;
    TextView etSatP1, etSatP2, etSatP3, etSatP4, etSatP5, etSatP6, etSatP7, etSatP8, etSatP9, etSatP10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_time_table);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LinkAllControls();

        HidePeriod(9);
        SetSubject("Monday","Mathematics","English","Physics","Biology","P.T.","Hindi","Geography","Economics","");
    }

    void HidePeriod(int p) {
        switch (p) {
            case 9:
                Hide9();
                break;
            case 8:
                Hide8();
                break;
            case 7:
                Hide7();
                break;
            case 6:
                Hide6();
                break;
        }
    }

    void SetSubject(String pday, String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        switch (pday) {
            case "Monday":
                SetMonday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Tuesday":
                SetTueday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Wednesday":
                SetWedday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Thrusday":
                SetThuday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Friday":
                SetFriday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Saturday":
                SetSatday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
            case "Sunday":
                SetSunday(p1, p2, p3, p4, p5, p6, p7, p8, p9);
                break;
        }
    }

    private void SetFriday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etFriP10.setText(p9);
        etFriP2.setText(p1);
        etFriP3.setText(p2);
        etFriP4.setText(p3);
        etFriP5.setText(p4);
        etFriP6.setText(p5);
        etFriP7.setText(p6);
        etFriP8.setText(p7);
        etFriP9.setText(p8);


    }

    private void SetSatday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etSatP10.setText(p9);
        etSatP2.setText(p1);
        etSatP3.setText(p2);
        etSatP4.setText(p3);
        etSatP5.setText(p4);
        etSatP6.setText(p5);
        etSatP7.setText(p6);
        etSatP8.setText(p7);
        etSatP9.setText(p8);
    }

    private void SetSunday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
    }

    private void SetThuday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etThuP10.setText(p9);
        etThuP2.setText(p1);
        etThuP3.setText(p2);
        etThuP4.setText(p3);
        etThuP5.setText(p4);
        etThuP6.setText(p5);
        etThuP7.setText(p6);
        etThuP8.setText(p7);
        etThuP9.setText(p8);
    }

    private void SetWedday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etWedP10.setText(p9);
        etWedP2.setText(p1);
        etWedP3.setText(p2);
        etWedP4.setText(p3);
        etWedP5.setText(p4);
        etWedP6.setText(p5);
        etWedP7.setText(p6);
        etWedP8.setText(p7);
        etWedP9.setText(p8);
    }

    private void SetTueday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etTueP10.setText(p1);
        etTueP2.setText(p2);
        etTueP3.setText(p3);
        etTueP4.setText(p4);
        etTueP5.setText(p5);
        etTueP6.setText(p6);
        etTueP7.setText(p7);
        etTueP8.setText(p8);
        etTueP9.setText(p9);
    }

    private void SetMonday(String p1, String p2, String p3, String p4, String p5, String p6, String p7, String p8, String p9) {
        etMonP10.setText(p9);
        etMonP2.setText(p1);
        etMonP3.setText(p2);
        etMonP4.setText(p3);
        etMonP5.setText(p4);
        etMonP6.setText(p5);
        etMonP7.setText(p6);
        etMonP8.setText(p7);
        etMonP9.setText(p8);
    }

    void Hide9() {
        etHdrP10.setVisibility(View.INVISIBLE);
        etMonP10.setVisibility(View.INVISIBLE);
        etTueP10.setVisibility(View.INVISIBLE);
        etWedP10.setVisibility(View.INVISIBLE);
        etThuP10.setVisibility(View.INVISIBLE);
        etFriP10.setVisibility(View.INVISIBLE);
        etSatP10.setVisibility(View.INVISIBLE);
        //ethhhP10.setVisibility(View.INVISIBLE);
    }

    void Hide8() {
        etHdrP9.setVisibility(View.INVISIBLE);
        etMonP9.setVisibility(View.INVISIBLE);
        etTueP9.setVisibility(View.INVISIBLE);
        etWedP9.setVisibility(View.INVISIBLE);
        etThuP9.setVisibility(View.INVISIBLE);
        etFriP9.setVisibility(View.INVISIBLE);
        etSatP9.setVisibility(View.INVISIBLE);
        //ethhhP10.setVisibility(View.INVISIBLE);
    }

    void Hide7() {
        etHdrP8.setVisibility(View.INVISIBLE);
        etMonP8.setVisibility(View.INVISIBLE);
        etTueP8.setVisibility(View.INVISIBLE);
        etWedP8.setVisibility(View.INVISIBLE);
        etThuP8.setVisibility(View.INVISIBLE);
        etFriP8.setVisibility(View.INVISIBLE);
        etSatP8.setVisibility(View.INVISIBLE);
        //ethhhP10.setVisibility(View.INVISIBLE);
    }

    void Hide6() {
        etHdrP7.setVisibility(View.INVISIBLE);
        etMonP7.setVisibility(View.INVISIBLE);
        etTueP7.setVisibility(View.INVISIBLE);
        etWedP7.setVisibility(View.INVISIBLE);
        etThuP7.setVisibility(View.INVISIBLE);
        etFriP7.setVisibility(View.INVISIBLE);
        etSatP7.setVisibility(View.INVISIBLE);
        //ethhhP10.setVisibility(View.INVISIBLE);
    }

    void LinkAllControls() {
        //Header
        etHdrP1 = (TextView) findViewById(R.id.textView1a);
        etHdrP2 = (TextView) findViewById(R.id.textView1b);
        etHdrP3 = (TextView) findViewById(R.id.textView1c);
        etHdrP4 = (TextView) findViewById(R.id.textView1d);
        etHdrP5 = (TextView) findViewById(R.id.textView1e);

        etHdrP6 = (TextView) findViewById(R.id.textView1f);
        etHdrP7 = (TextView) findViewById(R.id.textView1g);
        etHdrP8 = (TextView) findViewById(R.id.textView1h);
        etHdrP9 = (TextView) findViewById(R.id.textView1i);
        etHdrP10 = (TextView) findViewById(R.id.textView1j);

        //Monday
        etMonP1 = (TextView) findViewById(R.id.textView2a);
        etMonP2 = (TextView) findViewById(R.id.textView2b);
        etMonP3 = (TextView) findViewById(R.id.textView2c);
        etMonP4 = (TextView) findViewById(R.id.textView2d);
        etMonP5 = (TextView) findViewById(R.id.textView2e);

        etMonP6 = (TextView) findViewById(R.id.textView2f);
        etMonP7 = (TextView) findViewById(R.id.textView2g);
        etMonP8 = (TextView) findViewById(R.id.textView2h);
        etMonP9 = (TextView) findViewById(R.id.textView2i);
        etMonP10 = (TextView) findViewById(R.id.textView2j);

        //Tuesday
        etTueP1 = (TextView) findViewById(R.id.textView3a);
        etTueP2 = (TextView) findViewById(R.id.textView3b);
        etTueP3 = (TextView) findViewById(R.id.textView3c);
        etTueP4 = (TextView) findViewById(R.id.textView3d);
        etTueP5 = (TextView) findViewById(R.id.textView3e);

        etTueP6 = (TextView) findViewById(R.id.textView3f);
        etTueP7 = (TextView) findViewById(R.id.textView3g);
        etTueP8 = (TextView) findViewById(R.id.textView3h);
        etTueP9 = (TextView) findViewById(R.id.textView3i);
        etTueP10 = (TextView) findViewById(R.id.textView3j);

        //Wednesday
        etWedP1 = (TextView) findViewById(R.id.textView4a);
        etWedP2 = (TextView) findViewById(R.id.textView4b);
        etWedP3 = (TextView) findViewById(R.id.textView4c);
        etWedP4 = (TextView) findViewById(R.id.textView4d);
        etWedP5 = (TextView) findViewById(R.id.textView4e);

        etWedP6 = (TextView) findViewById(R.id.textView4f);
        etWedP7 = (TextView) findViewById(R.id.textView4g);
        etWedP8 = (TextView) findViewById(R.id.textView4h);
        etWedP9 = (TextView) findViewById(R.id.textView4i);
        etWedP10 = (TextView) findViewById(R.id.textView4j);

        //Thrusday
        etThuP1 = (TextView) findViewById(R.id.textView5a);
        etThuP2 = (TextView) findViewById(R.id.textView5b);
        etThuP3 = (TextView) findViewById(R.id.textView5c);
        etThuP4 = (TextView) findViewById(R.id.textView5d);
        etThuP5 = (TextView) findViewById(R.id.textView5e);

        etThuP6 = (TextView) findViewById(R.id.textView5f);
        etThuP7 = (TextView) findViewById(R.id.textView5g);
        etThuP8 = (TextView) findViewById(R.id.textView5h);
        etThuP9 = (TextView) findViewById(R.id.textView5i);
        etThuP10 = (TextView) findViewById(R.id.textView5j);

        //Friday
        etFriP1 = (TextView) findViewById(R.id.textView6a);
        etFriP2 = (TextView) findViewById(R.id.textView6b);
        etFriP3 = (TextView) findViewById(R.id.textView6c);
        etFriP4 = (TextView) findViewById(R.id.textView6d);
        etFriP5 = (TextView) findViewById(R.id.textView6e);

        etFriP6 = (TextView) findViewById(R.id.textView6f);
        etFriP7 = (TextView) findViewById(R.id.textView6g);
        etFriP8 = (TextView) findViewById(R.id.textView6h);
        etFriP9 = (TextView) findViewById(R.id.textView6i);
        etFriP10 = (TextView) findViewById(R.id.textView6j);

        //Saturday
        etSatP1 = (TextView) findViewById(R.id.textView7a);
        etSatP2 = (TextView) findViewById(R.id.textView7b);
        etSatP3 = (TextView) findViewById(R.id.textView7c);
        etSatP4 = (TextView) findViewById(R.id.textView7d);
        etSatP5 = (TextView) findViewById(R.id.textView7e);

        etSatP6 = (TextView) findViewById(R.id.textView7f);
        etSatP7 = (TextView) findViewById(R.id.textView7g);
        etSatP8 = (TextView) findViewById(R.id.textView7h);
        etSatP9 = (TextView) findViewById(R.id.textView7i);
        etSatP10 = (TextView) findViewById(R.id.textView7j);
    }
}
