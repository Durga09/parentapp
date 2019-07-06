package org.ultimatesolution.parentapp.StudentProgress;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.DatabaseClasses.DBHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.NoticeBoardInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.NoticeBoardHelper;
import org.ultimatesolution.parentapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentAttendance extends AppCompatActivity {

    private Context context;
    String displayText;
    ProgressDialog dialog;
    String lastId = "0";
    // Array of strings for Station
    String[] listviewabsent = new String[]{
            " 1", " 2", " 3", " 4",
            " 5", " 6", " 7", " 8",
    };

    String[] listviewpreset = new String[]{
            " 11", " 22", " 13", " 24",
            " 15", " 3", " 7", " 8",
    };

    String[] listviewmonthyear = new String[]{
            "January 2019", "February 2019", "March 2019", "April 2019",
            "May 2019", "June 2019", "September 2019", "November 2019",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attendance);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
       Donotice();
    }

    private void Donotice() {
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
    /*      NoticeBoardHelper hlp = new NoticeBoardHelper();
        SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
        try {
            List<NoticeBoardInfo> entlist = hlp.getNotices(db);
            NoticeBoardInfo entity;
            for (int i = 0; i < entlist.size(); i++) {
                try {
                    entity = entlist.get(i);
                    HashMap<String, String> hm = new HashMap<String, String>();
                    hm.put("listview_title", entity.NoticeTitle);
                    hm.put("listview_discription", entity.NoticeDescription);
                    hm.put("listview_image", Integer.toString(listviewImage[0]));
                    aList.add(hm);
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }*/
      for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_present_days", listviewpreset[i]);
            hm.put("listview_absent_days", listviewabsent[i]);
            hm.put("listview_month_year", listviewmonthyear[i] );
            aList.add(hm);
        }

        String[] from = {"listview_month_year", "listview_absent_days", "listview_present_days"};
        int[] to = {R.id.listview_month_year, R.id.listview_absent_days, R.id.listview_present_days};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.list_student_absent, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);

       /* androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                TextView textview = (TextView) view.findViewById(R.id.listview_item_short_description);
                String stringText = textview.getText().toString();
                Toast.makeText(getBaseContext(), stringText, Toast.LENGTH_LONG).show();

            }
        });*/
    }
}
