package org.ultimatesolution.parentapp.StudentProgress;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.ExpandableListAdapter;
import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StudentAssignments extends AppCompatActivity {

    private static ExpandableListView expandableListView;
    private static ExpandableListAdapter adapter;
    HashMap<String, List<String>> hashMap;
    ArrayList<String> header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_assignments);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        expandableListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
        // Setting group indicator null for custom indicator
        expandableListView.setGroupIndicator(null);

        setItems();

        adapter = new ExpandableListAdapter(this, header, hashMap);
        // Setting adpater for expandablelistview
        expandableListView.setAdapter(adapter);

        /*
        You can add listeners for the item clicks
         */
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        // Listview Group expanded listener
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        header.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        header.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {

                Toast.makeText(
                        getApplicationContext(),
                        header.get(groupPosition)
                                + " : "
                                + hashMap.get(
                                header.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });
    }


    // Setting headers and childs to expandable listview
    void setItems() {

        // Array list for header
        header = new ArrayList<String>();

        // Array list for child items
        List<String> child1 = new ArrayList<String>();
        List<String> child2 = new ArrayList<String>();
        List<String> child3 = new ArrayList<String>();
        List<String> child4 = new ArrayList<String>();

        // Hash map for both header and child
        hashMap = new HashMap<String, List<String>>();

        // Adding headers to list
        for (int i = 1; i < 5; i++) {
            header.add(i + ".03.2019");
        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child1.add("Mathematics - Chp " + i);
        }
        // Adding child data
        for (int i = 1; i < 5; i++) {
            child2.add("English - Chp " + i);
        }
        // Adding child data
        for (int i = 1; i < 6; i++) {
            child3.add("Hindi - Chp " + i);
        }
        // Adding child data
        for (int i = 1; i < 7; i++) {
            child4.add("Telugu - Chp " + i);
        }

        // Adding header and childs to hash map
        hashMap.put(header.get(0), child1);
        hashMap.put(header.get(1), child2);
        hashMap.put(header.get(2), child3);
        hashMap.put(header.get(3), child4);

    }
}