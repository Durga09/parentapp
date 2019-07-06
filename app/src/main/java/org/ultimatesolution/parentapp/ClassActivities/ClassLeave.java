package org.ultimatesolution.parentapp.ClassActivities;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import org.ultimatesolution.parentapp.CommonClasses.GetMonthNo;
import org.ultimatesolution.parentapp.CommonClasses.StaticFields;
import org.ultimatesolution.parentapp.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ClassLeave extends AppCompatActivity   implements View.OnClickListener {
    EditText etDate;
    EditText etDateTo;
    EditText etAbsenceReason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_leave);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MobileAds.initialize(getApplicationContext(), StaticFields.adKey);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        DateFormat formatter = new SimpleDateFormat("dd/MMM/yyyy");
        Date dateIn = new Date();
        etDate = (EditText) findViewById(R.id.editTextAbsenceDate);
        etDateTo = (EditText) findViewById(R.id.editTextAbsenceDateTo);
        etAbsenceReason = (EditText) findViewById(R.id.editTextAbsenceRemarks);

        String strDateIn = formatter.format(dateIn);
        etDate.setText(strDateIn);
        etDateTo.setText(strDateIn);

        View btnDate = findViewById(R.id.buttonWardAbsentDate);
        btnDate.setOnClickListener((View.OnClickListener) this);

        View btnDateTO = findViewById(R.id.buttonWardAbsentDateTo);
        btnDateTO.setOnClickListener((View.OnClickListener) this);

        View v3 = findViewById(R.id.btn_ClassPostLeave);
        v3.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.buttonWardAbsentDate) {
            FrmDate();
        }

        if (v.getId() == R.id.buttonWardAbsentDateTo) {
            ToDate();
        }
    }

    private int mYear, mMonth, mDay, mHour, mMinute;
    private void FrmDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        etDate.setText(GetMonthNo.GetDay(dayOfMonth) + "/" + GetMonthNo.GetMonthName(monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    private void ToDate() {
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, R.style.DialogTheme,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        etDateTo.setText(GetMonthNo.GetDay(dayOfMonth) + "/" + GetMonthNo.GetMonthName(monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}
