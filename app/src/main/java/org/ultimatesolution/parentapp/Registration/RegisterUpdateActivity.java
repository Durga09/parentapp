package org.ultimatesolution.parentapp.Registration;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
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

public class RegisterUpdateActivity extends AppCompatActivity implements View.OnClickListener{

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
    Button btnRegisterUser;
    private Context context;
    private Activity activity;
    UserRegistrationInfo regUser;
    Dialog dialogConfirmation;
    private Button btnCancelConfirmation;
    String[] splittedResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setTitle("Update Ward Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_register_parent);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//       //Hides Back Arrow
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        btnRegisterUser = findViewById(R.id.buttonRegisterUserToSubmit);
        btnRegisterUser.setOnClickListener((View.OnClickListener) this);
        btnRegisterUser.setText("Update");

        etrollnumber = (EditText) findViewById(R.id.editTextRollNumber);
        etSchoolCode = (EditText) findViewById(R.id.editTextSchoolCode);
        etUserName = (EditText) findViewById(R.id.editTextUserName);
        etMailId = (EditText) findViewById(R.id.editTextEmail);
        etMobileNUmber = (EditText) findViewById(R.id.editTextMobileNumber);
        etclass = (EditText) findViewById(R.id.editTextClass);
        etsec = (EditText) findViewById(R.id.editTextSection);
        tvStatus = (TextView) findViewById(R.id.textViewStatus);

        Bundle bundle=getIntent().getExtras();
        String personName=bundle.getString("personName");
        String phoneMobileNumber=bundle.getString("phoneMobileNumber");
        String emailID=bundle.getString("emailID");
        String classdesc=bundle.getString("classdesc");
        String sectiondesc=bundle.getString("sectiondesc");
        String schoolKey=bundle.getString("schoolKey");
        String rollNumber=bundle.getString("rollNumber");

        etrollnumber.setText(rollNumber);
        etMobileNUmber.setText(phoneMobileNumber);
        etMailId.setText(emailID);
        etclass.setText(classdesc);
        etsec.setText(sectiondesc);
        etUserName.setText(personName);
        etSchoolCode.setText(schoolKey);
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
                urh.updateRegistration(db, regUser);
             //   Toast.makeText(getBaseContext(), "Your details are updated ->" + regUser.rolls, Toast.LENGTH_LONG).show();

//                String sql = "UPDATE Registration \n" +
//                        "SET PersonName = ?, \n" +
//                        "PhoneMobileNumber = ?, \n" +
//                        "EmailID = ?, \n" +
//                        "classdesc = ?, \n" +
//                        "sectiondesc = ?, \n" +
//                        "SchoolKey = ? \n" +
//                        "WHERE RollNumber = ?;\n";
//
//                db.execSQL(sql, new String[]{etUserName.getText().toString(), etMobileNUmber.getText().toString(), etMailId.getText().toString(),
//                        etclass.getText().toString(),etsec.getText().toString(),
//                        etSchoolCode.getText().toString(),etrollnumber.getText().toString()});
                Toast.makeText(getBaseContext(), "Your details are updated ->" + regUser.rolls, Toast.LENGTH_LONG).show();

                if(UpdateWardActivity.activityfinish!=null)
                {
                    UpdateWardActivity.activityfinish.finish();
                }
                Intent intent=new Intent(getApplicationContext(),UpdateWardActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception ex) {
                Toast.makeText(getBaseContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
