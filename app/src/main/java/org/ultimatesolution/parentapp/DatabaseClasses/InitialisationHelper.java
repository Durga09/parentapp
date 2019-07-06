package org.ultimatesolution.parentapp.DatabaseClasses;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ultimatesolution.parentapp.CommonClasses.StaticFields;

public class InitialisationHelper {
    public static String School_Key = "";

    public void setSchoolKey(SQLiteDatabase db) {
        try {
            Cursor res = db.rawQuery("select SchoolKey    from Registration ;", null);
            res.moveToFirst();
            String count = res.getString(res.getColumnIndex("SchoolKey"));
            res.close();
            if (count == null) {
                School_Key = "";
            } else {
                School_Key = count;
                StaticFields.schoolCode=count;
            }

        } catch (Exception ex) {
        }

    }
}
