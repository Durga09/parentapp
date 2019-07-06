package org.ultimatesolution.parentapp.Registration;

//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;
//
//import org.ultimatesolution.parentapp.R;
//
//public class WardRemove extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_ward_remove);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);
//
//    }
//
//}


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
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WardRemove extends AppCompatActivity {

    RecyclerView update_recyclerView;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    final int MY_PERMISSIONS_REQUEST_READ_PHONE_STATE = 0;
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 10;
    List<UserRegistrationInfo> employeeList;
    public static Activity activityfinish;
    ArrayList<HashMap<String,String>> empDetailsList=new ArrayList<HashMap<String,String>>();
    CheckBox selectall_checkbox;
    private ViewPagerListAdapter adapter;
    HashMap<String, String[]> _checkboxHashmap = new HashMap<String, String[]>();
    HashMap<String, String> selectedHashMap = new HashMap<String, String>();
    Button delete_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        setTitle("Ward Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_ward_remove);

        context = getApplicationContext();
        activity = this;
        activityfinish=this;
        employeeList = new ArrayList<>();

//        AdView mAdView = (AdView) findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        delete_btn=findViewById(R.id.delete_btn);
        selectall_checkbox=findViewById(R.id.selectall_checkbox);
        update_recyclerView=findViewById(R.id.remove_recyclerView);
        update_recyclerView.setHasFixedSize(true);
        update_recyclerView.setNestedScrollingEnabled(false);
        update_recyclerView.setLayoutManager(new LinearLayoutManager(this));

        showEmployeesFromDatabase();
        requestPermission();

        selectall_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    selectedData("1");
                }else{
                    selectedData("0");
                }
            }
        });

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(empDetailsList!=null) {
                    if (empDetailsList.size() > 0) {

                        ArrayList<String> arrayList=new ArrayList<>();
                        StringBuilder sb=new StringBuilder();
                        for(int i=0;i<empDetailsList.size();i++)
                        {
                            String rollNumber=empDetailsList.get(i).get("RollNumber");

                            if(selectedHashMap.containsKey(rollNumber))
                            {
                               String statusval=selectedHashMap.get(rollNumber);
                               if(statusval.equals("1"))
                               {
                                   sb=sb.append(rollNumber+",");
                                   arrayList.add(rollNumber);
                               }
                            }else{
                            }
                        }
                        try {

                            if(arrayList!=null)
                            {
                                if(arrayList.size()>0)
                                {
                                    String[] Ids=new String[arrayList.size()];
                                    for(int i=0;i<arrayList.size();i++)
                                    {
                                        Ids[i]=arrayList.get(i);
                                    }
                                    SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                                 //   String[] Ids = ......; //Array of Ids you wish to delete.
                                    String whereClause = String.format("RollNumber" + " in (%s)", new Object[] { TextUtils.join(",", Collections.nCopies(Ids.length, "?")) });
                                    db.delete("Registration", whereClause, Ids);
                                    Toast.makeText(getBaseContext(), "You are successfully delete record", Toast.LENGTH_LONG).show();

                                    if(WardRemove.activityfinish!=null)
                                    {
                                        WardRemove.activityfinish.finish();
                                    }
                                    Intent intent=new Intent(getApplicationContext(),WardRemove.class);
                                    startActivity(intent);

                                }
                            }
                        } catch (Exception ex) {
                            Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
    }


    public void selectedData(String selectedVal)
    {
        if(empDetailsList!=null)
        {
            if(empDetailsList.size()>0)
            {
                String[] checkboxValueArray=new String[empDetailsList.size()];
                String[] rollNumberValueArray=new String[empDetailsList.size()];
                ArrayList<HashMap<String,String>> selectHashmapList=new ArrayList<>();
                for(int i=0;i<empDetailsList.size();i++)
                {
                    HashMap<String,String> hashmap=new HashMap<>();
                    String personName=empDetailsList.get(i).get("PersonName");
                    String phoneMobileNumber=empDetailsList.get(i).get("PhoneMobileNumber");
                    String emailID=empDetailsList.get(i).get("EmailID");
                    String classdesc=empDetailsList.get(i).get("classdesc");
                    String sectiondesc=empDetailsList.get(i).get("sectiondesc");
                    String schoolKey=empDetailsList.get(i).get("SchoolKey");
                    String rollNumber=empDetailsList.get(i).get("RollNumber");
                    String status=empDetailsList.get(i).get("status");

                    hashmap.put("PersonName",personName);
                    hashmap.put("PhoneMobileNumber",phoneMobileNumber);
                    hashmap.put("EmailID",emailID);
                    hashmap.put("classdesc",classdesc);
                    hashmap.put("sectiondesc",sectiondesc);
                    hashmap.put("SchoolKey",schoolKey);
                    hashmap.put("RollNumber",rollNumber);
                    hashmap.put("status",selectedVal);
                    selectHashmapList.add(hashmap);

                    selectedHashMap.put(rollNumber,selectedVal);
                    checkboxValueArray[i]=selectedVal;
                    rollNumberValueArray[i]=rollNumber;
                }

                update_recyclerView.setAdapter(null);
                adapter.notifyDataSetChanged();

                adapter = new ViewPagerListAdapter(WardRemove.this,
                        selectHashmapList,checkboxValueArray,rollNumberValueArray);
                update_recyclerView.setAdapter(adapter);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showEmployeesFromDatabase() {
        SQLiteDatabase mDatabase = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = mDatabase.rawQuery("SELECT * FROM Registration", null);
        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                HashMap<String,String> hashmap=new HashMap<String,String>();
                hashmap.put("PersonName",cursorEmployees.getString(cursorEmployees.getColumnIndex("PersonName")));
                hashmap.put("PhoneMobileNumber",cursorEmployees.getString(cursorEmployees.getColumnIndex("PhoneMobileNumber")));
                hashmap.put("EmailID",cursorEmployees.getString(cursorEmployees.getColumnIndex("EmailID")));
                hashmap.put("classdesc",cursorEmployees.getString(cursorEmployees.getColumnIndex("classdesc")));
                hashmap.put("sectiondesc",cursorEmployees.getString(cursorEmployees.getColumnIndex("sectiondesc")));
                hashmap.put("SchoolKey",cursorEmployees.getString(cursorEmployees.getColumnIndex("SchoolKey")));
                hashmap.put("RollNumber",cursorEmployees.getString(cursorEmployees.getColumnIndex("RollNumber")));
                hashmap.put("status","0");
                empDetailsList.add(hashmap);
            } while (cursorEmployees.moveToNext());
        }
        //closing the cursor
        cursorEmployees.close();

        String[] checkboxValueArray=new String[empDetailsList.size()];
        String[] rollNumberValueArray=new String[empDetailsList.size()];
        for(int i=0;i<empDetailsList.size();i++)
        {
            String status=empDetailsList.get(i).get("status");
            String rollNumber=empDetailsList.get(i).get("RollNumber");
            checkboxValueArray[i]=status;
            rollNumberValueArray[i]=rollNumber;
            selectedHashMap.put(rollNumber,status);
        }
        adapter = new ViewPagerListAdapter(this,
                empDetailsList,checkboxValueArray,rollNumberValueArray);
        update_recyclerView.setAdapter(adapter);
    }


    public class ViewPagerListAdapter extends RecyclerView.Adapter<ViewPagerListAdapter.MyViewHolder> {
        private Context mContext;
        ArrayList<HashMap<String,String>> registrationInfoList;
        String[] checkboxValueArray;
        String[] rollNumberValueArray;
        public ViewPagerListAdapter(Context mContext,ArrayList<HashMap<String,String>> registrationInfoList,
                                    String[] checkboxValueArray,String[] rollNumberValueArray) {
            this.mContext = mContext;
            this.registrationInfoList=registrationInfoList;
            this.checkboxValueArray=checkboxValueArray;
            this.rollNumberValueArray=rollNumberValueArray;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.activity_removeward_list_adapter, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {

            holder.indexReference=position;
            String personName=registrationInfoList.get(position).get("PersonName");
            String phoneMobileNumber=registrationInfoList.get(position).get("PhoneMobileNumber");
            String emailID=registrationInfoList.get(position).get("EmailID");
            String classdesc=registrationInfoList.get(position).get("classdesc");
            String sectiondesc=registrationInfoList.get(position).get("sectiondesc");
            String schoolKey=registrationInfoList.get(position).get("SchoolKey");
            final String rollNumber=registrationInfoList.get(position).get("RollNumber");
            String status=registrationInfoList.get(position).get("status");

            holder.inputname_txt.setText(personName);
            holder.class_txt.setText(classdesc);
            holder.section_txt.setText(sectiondesc);
            holder.rollnumber_txt.setText(rollNumber);

            if(checkboxValueArray[holder.indexReference].equals("0"))
            {
                holder.delete_checkbox.setChecked(false);
            }else{
                holder.delete_checkbox.setChecked(true);
            }

            holder.delete_checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked)
                    {
                        checkboxValueArray[holder.indexReference] = "1";
                        selectedHashMap.put(rollNumber,"1");
                    }else{
                        checkboxValueArray[holder.indexReference] = "0";
                        selectedHashMap.put(rollNumber,"0");
                    }
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
            CheckBox delete_checkbox;

            MyViewHolder(View view) {
                super(view);
                inputname_txt=view.findViewById(R.id.inputname_txt);
                class_txt=view.findViewById(R.id.class_txt);
                section_txt=view.findViewById(R.id.section_txt);
                rollnumber_txt=view.findViewById(R.id.rollnumber_txt);
                delete_checkbox=view.findViewById(R.id.delete_checkbox);
                //edit_imageview=view.findViewById(R.id.edit_imageview);
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