package org.ultimatesolution.parentapp.DatabaseClasses;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.NoticeBoardInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.UserRegistrationInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeBoardHelper {

    public void insertRecord(SQLiteDatabase db, String entity) {
        String[] splittedcols = entity.split("\\{");

        ContentValues contentValues = new ContentValues();
        contentValues.put("NoticeUpdateID", splittedcols[0]);
        contentValues.put("NoticeDescription", splittedcols[1]);
        contentValues.put("NoticeExpiresOn", splittedcols[2]);
        contentValues.put("NoticeHeader", splittedcols[3]);
        try {
            db.insert("NoticeBoard", null, contentValues);
        } catch (Exception ex) {

        }

    }

    public List<NoticeBoardInfo> getNotices(SQLiteDatabase db) {
        List<NoticeBoardInfo> entityList = new ArrayList<>();
        try {
            Cursor res = db.rawQuery("select * from NoticeBoard order by NoticeID desc LIMIT 50;", null);
            if (res.getCount() == 0) {
                return entityList;
            }
            res.moveToFirst();
            NoticeBoardInfo entity;
            while (res.isAfterLast() == false) {
                entity = new NoticeBoardInfo();
                entity.NoticeTitle = res.getString(res.getColumnIndex("NoticeHeader"));
                entity.NoticeDescription = res.getString(res.getColumnIndex("NoticeDescription"));
                entity.ForAll = res.getString(res.getColumnIndex("ForAll"));
                entity.Standard = res.getString(res.getColumnIndex("Standard"));
                entity.Section = res.getString(res.getColumnIndex("Section"));
                entity.NoticeExpiresOn = res.getString(res.getColumnIndex("NoticeExpiresOn"));

                entityList.add(entity);
                res.moveToNext();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return entityList;
    }

    public String getlastid(SQLiteDatabase db) {
        String coname = "";
        try {

             Cursor resultSet = db.rawQuery("select max(NoticeUpdateID) from  NoticeBoard  ;", null);
             resultSet.moveToFirst();
            coname = resultSet.getString(0);
            if (coname.length()==0)
                return  "0";
            return coname;

        } catch (Exception ex) {
            return "0";
        }
    }
}
