package org.ultimatesolution.parentapp.SchoolActivities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ultimatesolution.parentapp.R;

import java.io.File;

public class SchoolEventsAlbum extends AppCompatActivity   implements View.OnClickListener{
 ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_events_album);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imgView=(ImageView) findViewById(R.id.imageView);

        TextView tvTitle=(TextView) findViewById(R.id.textViewTitle);
        TextView tvDesc=(TextView) findViewById(R.id.textViewLongDescription);

        LoadBitmapImage();
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String j =(String) b.get("title");
            tvTitle.setText(j);
            String j1 =(String) b.get("desc");
            tvDesc.setText(j1);
        }

        View v2 = findViewById(R.id.buttonNext);
        v2.setOnClickListener((View.OnClickListener) this);

        View v1 = findViewById(R.id.buttonPrevious);
        v1.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {

        if (v.getId() == R.id.buttonNext) {

        }

        if (v.getId() == R.id.buttonPrevious) {

        }
    }
    void LoadBitmapImage() {
        File sd = Environment.getExternalStorageDirectory();
        File imgFile = new  File(sd,"/MySchoolParent/i1.png");
        if(imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgView.setImageBitmap(myBitmap);
        }else   {
            Toast.makeText(this, "Photo Does not Exists.", Toast.LENGTH_SHORT).show();
        }
    }
}
