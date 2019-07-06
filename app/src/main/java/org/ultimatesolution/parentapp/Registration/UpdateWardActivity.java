package org.ultimatesolution.parentapp.Registration;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.ultimatesolution.parentapp.DatabaseClasses.DBHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.RegistrationInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.UserRegistrationInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.InitialisationHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.UserRegistrationHelper;
import org.ultimatesolution.parentapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UpdateWardActivity extends AppCompatActivity {

    RecyclerView update_recyclerView;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 10;
    List<UserRegistrationInfo> employeeList;
    public static Activity activityfinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_ward);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = getApplicationContext();
        activity = this;
        activityfinish=this;
        employeeList = new ArrayList<>();

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        update_recyclerView=findViewById(R.id.update_recyclerView);
        update_recyclerView.setHasFixedSize(true);
        update_recyclerView.setNestedScrollingEnabled(false);
        update_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showEmployeesFromDatabase();

//        ViewPagerProductsListAdapter adapter = new ViewPagerProductsListAdapter(this, hashmapList,
//                hashmapImagesList, userValueArray, item_balqtyValueArray,useridValueArray);


        requestPermission();

        //        SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
//        new DBHelper(null).updateTables(db);
//        UserRegistrationHelper hlp = new UserRegistrationHelper();
//        try {
//            if (hlp.isRegistered(db)) {
////                SQLiteDatabase mydatabase = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
////                try {
////                    new DBHelper(null).ctTables(mydatabase);
////                } catch (Exception ex) {
////                    Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
////                }
//                List<RegistrationInfo> registrationInfoList=hlp.getRegistrationDetails(db);
//                Log.e("registrationInfoList",""+registrationInfoList.size());
//                if(registrationInfoList!=null)
//                {
//                    if(registrationInfoList.size()>0)
//                    {
//                        ViewPagerProductsListAdapter adapter = new ViewPagerProductsListAdapter(this,
//                                 registrationInfoList);
//                        update_recyclerView.setAdapter(adapter);// set adapter on recyclerview
//
////                        for(int i=0;i<registrationInfoList.size();i++)
////                        {
////                            registrationInfoList.get(i).getPersonName();
////                            registrationInfoList.get(i).getPhoneMobileNumber();
////                            registrationInfoList.get(i).getEmailID();
////                            registrationInfoList.get(i).getClassdesc();
////                            registrationInfoList.get(i).getSectiondesc();
////                            registrationInfoList.get(i).getSchoolKey();
////                            registrationInfoList.get(i).getRollNumber();
////                        }
//                    }
//                }
////                if (db.isOpen())
////                    db.close();
////                if (mydatabase.isOpen())
////                    mydatabase.close();
//            }
//        }catch (Exception ex){
//            new DBHelper(null).ctTables(db);
//        }

    }


    private void showEmployeesFromDatabase() {
        SQLiteDatabase mDatabase = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM Registration", null);
        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new UserRegistrationInfo(
//                        cursorEmployees.getString(cursorEmployees.getColumnIndex("PersonName")),
//                        cursorEmployees.getString(1),
//                        cursorEmployees.getString(2),
//                        cursorEmployees.getString(3),
//                        cursorEmployees.getString(4),
//                        cursorEmployees.getString(5),
//                        cursorEmployees.getString(6)

                        cursorEmployees.getString(cursorEmployees.getColumnIndex("PersonName")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("PhoneMobileNumber")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("EmailID")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("classdesc")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("sectiondesc")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("SchoolKey")),
                        cursorEmployees.getString(cursorEmployees.getColumnIndex("RollNumber"))
                ));
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();
        ViewPagerProductsListAdapter adapter = new ViewPagerProductsListAdapter(this,
                employeeList);
                        update_recyclerView.setAdapter(adapter);
    }


    public class ViewPagerProductsListAdapter extends RecyclerView.Adapter<ViewPagerProductsListAdapter.MyViewHolder> {
        private Context mContext;
        List<UserRegistrationInfo> registrationInfoList;
        // ArrayList<HashMap<String, String>> productsList = new ArrayList<HashMap<String, String>>();
        //      ArrayList<HashMap<String, List<String>>> hashmapImagesList;
        //String values[];
        //  String[] item_balqtyValueArray;
        int doneposition = -1;
        //    String[] useridValueArray;

        //        public ViewPagerProductsListAdapter(Context mContext, ArrayList<HashMap<String, String>> hashmapList,
//                                            ArrayList<HashMap<String, List<String>>> hashmapImagesList, String values[],
//                                            String[] item_balqtyValueArray,String[] useridValueArray) {
        public ViewPagerProductsListAdapter(Context mContext,List<UserRegistrationInfo> registrationInfoList) {
            // productsList = hashmapList;
            this.mContext = mContext;
            this.registrationInfoList=registrationInfoList;
//            this.hashmapImagesList = hashmapImagesList;
//            this.values = values;
//            this.item_balqtyValueArray = item_balqtyValueArray;
//            this.useridValueArray=useridValueArray;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_updateward_list_adapter, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            String personName=registrationInfoList.get(position).getPersonName();
            String phoneMobileNumber=registrationInfoList.get(position).getPhoneMobileNumber();
            String emailID=registrationInfoList.get(position).getEmailID();
            String classdesc=registrationInfoList.get(position).getClassdesc();
            String sectiondesc=registrationInfoList.get(position).getSectiondesc();
            String schoolKey=registrationInfoList.get(position).getSchoolKey();
            String rollNumber=registrationInfoList.get(position).getRollNumber();

            Log.e("personName",personName);
            Log.e("phoneMobileNumber",phoneMobileNumber);
            Log.e("emailID",emailID);
            Log.e("classdesc",classdesc);
            Log.e("sectiondesc",sectiondesc);
            Log.e("schoolKey",schoolKey);
            Log.e("rollNumber",rollNumber);

            holder.inputname_txt.setText(personName);
            holder.class_txt.setText(classdesc);
            holder.section_txt.setText(sectiondesc);
            holder.rollnumber_txt.setText(rollNumber);

            holder.edit_imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String personName=registrationInfoList.get(position).getPersonName();
                    String phoneMobileNumber=registrationInfoList.get(position).getPhoneMobileNumber();
                    String emailID=registrationInfoList.get(position).getEmailID();
                    String classdesc=registrationInfoList.get(position).getClassdesc();
                    String sectiondesc=registrationInfoList.get(position).getSectiondesc();
                    String schoolKey=registrationInfoList.get(position).getSchoolKey();
                    String rollNumber=registrationInfoList.get(position).getRollNumber();

                    Intent intent=new Intent(getApplicationContext(),RegisterUpdateActivity.class);
                    intent.putExtra("personName",personName);
                    intent.putExtra("phoneMobileNumber",phoneMobileNumber);
                    intent.putExtra("emailID",emailID);
                    intent.putExtra("classdesc",classdesc);
                    intent.putExtra("sectiondesc",sectiondesc);
                    intent.putExtra("schoolKey",schoolKey);
                    intent.putExtra("rollNumber",rollNumber);
                    startActivity(intent);


                }
            });
        }

        @Override
        public int getItemCount() {
            return registrationInfoList.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            ImageView edit_imageview;
            TextView inputname_txt,class_txt,section_txt,rollnumber_txt;
            int indexReference;

            MyViewHolder(View view) {
                super(view);
                inputname_txt=view.findViewById(R.id.inputname_txt);
                class_txt=view.findViewById(R.id.class_txt);
                section_txt=view.findViewById(R.id.section_txt);
                rollnumber_txt=view.findViewById(R.id.rollnumber_txt);
                edit_imageview=view.findViewById(R.id.edit_imageview);
                view.setOnClickListener(this);
            }
            @Override
            public void onClick(View view) {
            }
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
