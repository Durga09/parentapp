package org.ultimatesolution.parentapp.SchoolActivities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
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
import org.ultimatesolution.parentapp.WebServices.WebserviceNotice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SchoolEvents extends AppCompatActivity   {
    private Context context;
    String displayText;
    ProgressDialog dialog;
    String lastId = "0";
    // Array of strings for Station
    String[] listviewTitle = new String[]{
            "Stop 1 - Sai Baba Mandir", "Stop 2 - Nangli Dairy", "Stop 3 - Suraj Viha", "Stop 4 - Indian Oil Station",
            "Stop 5 - S.M. Temple", "Stop 6 - Panchsheel Club", "Stop 7 - Savitri Cinema", "School",
    };

    int[] listviewImage = new int[]{
            R.drawable.route_stop, R.drawable.route_stop, R.drawable.route_stop, R.drawable.route_stop,
            R.drawable.route_stop, R.drawable.route_stop, R.drawable.route_stop, R.drawable.route_end,
    };

    String[] listviewShortDescription = new String[]{
            "Depart at 05:00", "Depart at 05:15", "Depart at 05:20", "Depart at 05:35",
            "Depart at 05:45", "Depart at 05:55", "Depart at 06:10", "Reach School 06:20",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_events);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        /*   dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait... Fetching Notices.");
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        dialog.show();*/


        //  SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
        //   NoticeBoardHelper nbh = new NoticeBoardHelper();
        //  lastId = nbh.getlastid(db);
        // Toast.makeText(context, lastId, Toast.LENGTH_LONG).show();
        // AsyncCallWS task = new AsyncCallWS();
        //task.lastIdt = lastId;
        //task.execute();
        Donotice();
    }

    private void Donotice() {
        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
        NoticeBoardHelper hlp = new NoticeBoardHelper();
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
                    if (i == entlist.size() -1)
                    {hm.put("listview_image", Integer.toString(listviewImage[7]));}
                    else {
                        hm.put("listview_image", Integer.toString(listviewImage[0]));}
                    aList.add(hm);
                } catch (Exception ex) {
                    Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage().toString(), Toast.LENGTH_SHORT).show();

        }
      /* for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_discription", listviewShortDescription[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }*/

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_route_stop_name, R.id.listview_route_stop_status};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.transportroute, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);


        androidListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                TextView textview = (TextView) view.findViewById(R.id.listview_route_stop_name);
                String stringText = textview.getText().toString();
                TextView textviewdesc = (TextView) view.findViewById(R.id.listview_route_stop_status);
                String stringTextdesc = textviewdesc.getText().toString();
                Toast.makeText(getBaseContext(), stringText + " - " + stringTextdesc, Toast.LENGTH_LONG).show();
                Intent i = new Intent(getBaseContext(), SchoolEventsAlbum.class);
                i.putExtra("title",stringText);
                i.putExtra("desc",stringTextdesc);
                startActivity(i);
            }
        });
    }
  /*  @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        Intent i = new Intent(this, SchoolEventsAlbum.class);
       // i.putExtra(TargetActivity.EXTRA_WHATEVER, mListAdapter.getChild(groupPosition, childPosition));
        startActivity(i);
        return false;
    }*/
    private class AsyncCallWS extends AsyncTask<String, Void, Void> {

        String lastIdt = "";

        AsyncCallWS() {

        }

        @Override
        protected Void doInBackground(String... params) {
            //Invoke webservice

            displayText = WebserviceNotice.getNotices(StaticFields.schoolCode, lastIdt, "", "");
            return null;


        }

        @Override
        protected void onPostExecute(Void result) {
            //Set response

            NoticeBoardHelper nbh = new NoticeBoardHelper();
            if (displayText.length() == 2) {
                Donotice();
            } else {
                String[] splitted = displayText.split("\\}");

                try {
                    for (int i = 0; i < splitted.length; i++) {

                        SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                        nbh.insertRecord(db, splitted[i]);
                        //ID=NoticeUpdateID, Notice=NoticeDescription, NoticeDate=NoticeExpiresOn
                        Donotice();

                    }
                } catch (Exception ex) {
                    Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            dialog.dismiss();
            Toast.makeText(context, "Successfully Fetched Notices", Toast.LENGTH_SHORT).show();
            return;
        }

        @Override
        protected void onPreExecute() {
            //Make ProgressBar invisible
            // pg.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
    }


}
