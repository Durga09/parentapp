package org.ultimatesolution.parentapp.Registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.DatabaseClasses.DBHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.UserRegistrationInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.UserRegistrationHelper;
import org.ultimatesolution.parentapp.R;

public class RegisterParent extends AppCompatActivity implements View.OnClickListener {

    EditText etSchoolCode;
    EditText etrollnumber;
    EditText etUserName;
    EditText etMobileNUmber;
    EditText etMailId;
    EditText etclass;
    EditText etsec;
    TextView tvStatus;
    private String displayText;
    private String schoolKey;
    View btnRegisterUser;

    private Context context;
    private Activity activity;
    UserRegistrationInfo regUser;
    Dialog dialogConfirmation;
    private Button btnCancelConfirmation;
    String[] splittedResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_parent);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//       //Hides Back Arrow
//        setSupportActionBar(toolbar);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        Toast.makeText(getApplicationContext(),"called",Toast.LENGTH_SHORT).show();


        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnRegisterUser = findViewById(R.id.buttonRegisterUserToSubmit);
        btnRegisterUser.setOnClickListener((View.OnClickListener) this);


        etrollnumber = (EditText) findViewById(R.id.editTextRollNumber);
        etSchoolCode = (EditText) findViewById(R.id.editTextSchoolCode);
        etUserName = (EditText) findViewById(R.id.editTextUserName);
        etMailId = (EditText) findViewById(R.id.editTextEmail);
        etMobileNUmber = (EditText) findViewById(R.id.editTextMobileNumber);
        etclass = (EditText) findViewById(R.id.editTextClass);
        etsec = (EditText) findViewById(R.id.editTextSection);
        tvStatus = (TextView) findViewById(R.id.textViewStatus);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.buttonRegisterUserToSubmit) {
            if (etSchoolCode.getText().length() == 0) {
                Toast.makeText(getBaseContext(), "School Code must be Provided.", Toast.LENGTH_LONG).show();
                return;
            }
            if (etUserName.getText().length() == 0) {
                Toast.makeText(getBaseContext(), "Ward Name must be Provided.", Toast.LENGTH_LONG).show();
                return;
            }

            if (etrollnumber.getText().length() == 0) {
                Toast.makeText(getBaseContext(), "Roll number must be Provided.", Toast.LENGTH_LONG).show();
                return;
            }
            if (etclass.getText().length() == 0) {
                Toast.makeText(getBaseContext(), "Class must be Provided.", Toast.LENGTH_LONG).show();
                return;
            }
            if (etsec.getText().length() == 0) {
                Toast.makeText(getBaseContext(), "Section must be Provided.", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                if (regUser == null) {
                    regUser = new UserRegistrationInfo();
                }
                regUser.PersonName = etUserName.getText().toString();
                regUser.rolls = etrollnumber.getText().toString();
                regUser.EmailID = etMailId.getText().toString();
                regUser.PhoneMobileNumber = etMobileNUmber.getText().toString();
                regUser.classdesc = etclass.getText().toString();
                regUser.sectiondesc = etsec.getText().toString();
                regUser.SchoolKey = etSchoolCode.getText().toString();
                UserRegistrationHelper urh = new UserRegistrationHelper();
                urh.insertRecord(db, regUser);
                Toast.makeText(getBaseContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                finish();
                Intent intent=new Intent(getApplicationContext(),RegisterParent.class);
                startActivity(intent);

            } catch (Exception ex) {
                Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
