package org.ultimatesolution.parentapp.Tools;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.ultimatesolution.parentapp.DatabaseClasses.DBHelper;
import org.ultimatesolution.parentapp.DatabaseClasses.SchoolHelper;
import org.ultimatesolution.parentapp.R;
import org.ultimatesolution.parentapp.StudentProgress.StudentActivity;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ToolsBackup extends AppCompatActivity implements View.OnClickListener{

    TextView etRestoreFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools_backup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        View vnotice = findViewById(R.id.buttonBackup);
        vnotice.setOnClickListener(this);

        etRestoreFile = (TextView) findViewById(R.id.textViewRestore);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.buttonBackup){
          //  Toast.makeText(this, "In Click", Toast.LENGTH_SHORT).show();
            DoBackup();}
    }

    private void DoBackup() {
        try {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            if (sd.canWrite()) {
                SQLiteDatabase db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                String currentDBPath = db.getPath();
                db.close();
                File posDirectory = new File(sd, "/MySchoolParent/Backup/");
              //  Toast.makeText(this, "Line no 78", Toast.LENGTH_SHORT).show();
                if (!posDirectory.exists()) {
                    posDirectory.mkdirs();
                }
                String backupDBPath = posDirectory + "/ParentApp.db";
                File currentDB = new File(currentDBPath);
                File backupDB = new File(backupDBPath);

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }
                DateFormat formatter = new SimpleDateFormat("yyMMdd");
                Date dateIn;
                dateIn = new Date();
                String strDateIn = formatter.format(dateIn);
                String[] files2Back = new String[]{backupDBPath};
                SchoolHelper ch = new SchoolHelper();
                db = openOrCreateDatabase(DBHelper.DATABASE_NAME, MODE_PRIVATE, null);
                String coname = ch.getSchoolName(db);
                String locID = ch.getSchoolID(db);
                // zip(files2Back, posDirectory + "/" + coname + locID + "mySchoolBackup" + strDateIn + ".zip");
                //etRestoreFile.setText(posDirectory + "/" + coname + locID + "mySchoolBackup" + strDateIn + ".zip");

                zip(files2Back, posDirectory + "/" + "mySchoolBackupParent" + strDateIn + ".zip");
                etRestoreFile.setText(posDirectory + "/" + "mySchoolBackupParent" + strDateIn + ".zip");

                Intent intentShareFile = new Intent(Intent.ACTION_SEND);
                File fileWithinMyDir = new File(posDirectory + "/" + "mySchoolBackup" + strDateIn + ".zip");

                // upgrade this by asking E-Mail ID to backup
                if (fileWithinMyDir.exists()) {
                    setSendMail(etRestoreFile.getText().toString(), strDateIn);
                   /* intentShareFile.setType("vnd.android.cursor.dir/email");
                    intentShareFile.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + posDirectory + "/" + "mySchoolBackup" + strDateIn + ".zip"));

                    intentShareFile.putExtra(Intent.EXTRA_SUBJECT,
                            "Saving Backup File... on "+ strDateIn);
                    intentShareFile.putExtra(Intent.EXTRA_TEXT, "Saving mySchool Backup File..."+ strDateIn);

                    startActivity(Intent.createChooser(intentShareFile, "Save mySchool Backup to Email"));*/
                }

                //posDirectoryRestore
                Toast.makeText(this, "Backup Completed", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "Write Permission not Granted", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void setSendMail(String filepathofbackup, String dateofbackup) {
        String[] recipient = {"ultimatesolution.hyd@gmail.com"};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.fromParts("mailto", "ultimatesolution.hyd@gmail.com", "null"));
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, recipient);
        email.putExtra(Intent.EXTRA_SUBJECT, "Backup Data for MySchool on " + dateofbackup);
        email.putExtra(Intent.EXTRA_TEXT, "Your School Backup Data is Saved in this Mail");
        email.putExtra(Intent.EXTRA_STREAM, Uri.parse("file://" + filepathofbackup));

        try {
            startActivity(Intent.createChooser(email, "Save mySchool Backup to Email"));
        } catch (ActivityNotFoundException ex) {
            throw ex;
        }
    }

    private static final int BUFFER = 1024;
    public void zip(String[] _files, String zipFileName) {
        try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipFileName);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];

            for (int i = 0; i < _files.length; i++) {
                Log.v("Compress", "Adding: " + _files[i]);
                FileInputStream fi = new FileInputStream(_files[i]);
                origin = new BufferedInputStream(fi, BUFFER);

                ZipEntry entry = new ZipEntry(_files[i].substring(_files[i].lastIndexOf("/") + 1));
                out.putNextEntry(entry);
                int count;

                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void unzip(String _zipFile, String _targetLocation) {

        //create target location folder if not exist
        dirChecker(_targetLocation);

        try {
            FileInputStream fin = new FileInputStream(_zipFile);
            ZipInputStream zin = new ZipInputStream(fin);
            ZipEntry ze = null;
            while ((ze = zin.getNextEntry()) != null) {

                //create dir if required while unzipping
                if (ze.isDirectory()) {
                    dirChecker(ze.getName());
                } else {
                    FileOutputStream fout = new FileOutputStream(_targetLocation + ze.getName());
                    for (int c = zin.read(); c != -1; c = zin.read()) {
                        fout.write(c);
                    }

                    zin.closeEntry();
                    fout.close();
                }

            }
            zin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void dirChecker(String fileLocation) {
        File posDirectory = new File(fileLocation);
        if (!posDirectory.exists()) {
            posDirectory.mkdirs();
        }
    }
}
