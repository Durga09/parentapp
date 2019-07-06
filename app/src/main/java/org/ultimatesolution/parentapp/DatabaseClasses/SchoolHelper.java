package org.ultimatesolution.parentapp.DatabaseClasses;

import android.database.sqlite.SQLiteDatabase;

public class SchoolHelper {
    public String getSchoolName(SQLiteDatabase db) {
        String coname = "ParentApp";
        try {

            //   Cursor resultSet = db.rawQuery("select CompanyName  from Company ;", null);
            // resultSet.moveToFirst();
            // coname = resultSet.getString(0);
            return coname;

        } catch (Exception ex) {
            return "";
        }
    }

    public String getSchoolID(SQLiteDatabase db) {
        String coname = "000";
        try {
            // Cursor resultSet = db.rawQuery("select LocationId  from Company ;", null);
            //  resultSet.moveToFirst();
            // coname = resultSet.getString(0);
            return coname;

        } catch (Exception ex) {
            return "";
        }


    }
}
