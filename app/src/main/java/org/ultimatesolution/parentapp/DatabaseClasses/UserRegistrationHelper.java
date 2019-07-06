package org.ultimatesolution.parentapp.DatabaseClasses;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.RegistrationInfo;
import org.ultimatesolution.parentapp.DatabaseClasses.DataBaseObjects.UserRegistrationInfo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserRegistrationHelper {
    List<RegistrationInfo> employeeList;
    public UserRegistrationInfo GetProfile(SQLiteDatabase db) {
        UserRegistrationInfo rob = new UserRegistrationInfo();

        try {
            Cursor res = db.rawQuery("select *  from SchoolRegistrationDetails ;", null);
            res.moveToFirst();
            while (res.isAfterLast() == false) {


                // rob.SchoolName = res.getString(res.getColumnIndex("SchoolName"));
                // rob.SchoolState = res.getString(res.getColumnIndex("SchoolState"));
//                rob.SchoolPostalCode = res.getString(res.getColumnIndex("SchoolPostCode"));
//                rob.SchoolPIN = res.getString(res.getColumnIndex("PIN"));
//                rob.SchoolPhoneNumber = res.getString(res.getColumnIndex("PhoneNumber"));
//                rob.SchoolAddress = res.getString(res.getColumnIndex("SchoolAddress"));
//                rob.SchoolBranch = res.getString(res.getColumnIndex("BranchArea"));
//                rob.SchoolCity = res.getString(res.getColumnIndex("SchoolCity"));
//                rob.SchoolContactPersonName = res.getString(res.getColumnIndex("ContactPersonName"));
//                rob.SchoolCountry = res.getString(res.getColumnIndex("SchoolCountry"));
//                rob.SchoolCPContactNumber = res.getString(res.getColumnIndex("ContactPersonContactNumber"));
//                rob.SchoolCPDesignation = res.getString(res.getColumnIndex("ContactPersonDesignation"));
//                rob.SchoolEmailID = res.getString(res.getColumnIndex("emailID"));
//                rob.SchoolMobileNumber = res.getString(res.getColumnIndex("MobileNumber"));
//                rob.SchoolRegistrationKey = res.getString(res.getColumnIndex("SchoolKey"));
                res.moveToNext();
            }
            res.close();

        } catch (Exception ex) {

        }
        return rob;
    }

    public List<RegistrationInfo> getRegistrationDetails(SQLiteDatabase db) {
        //we used rawQuery(sql, selectionargs) for fetching all the employees
        Cursor cursorEmployees = db.rawQuery("select *  from Registration ;", null);
        //if the cursor has some data
        if (cursorEmployees.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the employee list
                employeeList.add(new RegistrationInfo(
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
        return employeeList;
    }



    public boolean isRegistered(SQLiteDatabase db) {
       try {
            Cursor resultSet = db.rawQuery("select Count(*) as RegCount from Registration ;", null);
            resultSet.moveToFirst();
            int count = resultSet.getInt(0);
            resultSet.close();
            if (count > 0) {
                return true;
            }
        } catch (Exception ex) {

        }
        return false;
    }

    public void insertRecord(SQLiteDatabase db, UserRegistrationInfo entity) {
       // if (!isRegistered(db)) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date dateIn;
            dateIn = new Date();

            ContentValues contentValues = new ContentValues();
            contentValues.put("PersonName", entity.PersonName);
            contentValues.put("PhoneMobileNumber", entity.PhoneMobileNumber);
            contentValues.put("EmailID", entity.EmailID);
            contentValues.put("classdesc",entity.classdesc);
            contentValues.put("sectiondesc", entity.sectiondesc);
            contentValues.put("SchoolKey", entity.SchoolKey);
            contentValues.put("RollNumber", entity.rolls);
            try {
                db.insert("Registration", null, contentValues);
            } catch (Exception ex) {
               throw ex;
            }
     //   }
    }

    public void updateRegistration(SQLiteDatabase db, UserRegistrationInfo entity) {
        String sql = "UPDATE Registration \n" +
                "SET PersonName = ?, \n" +
                "PhoneMobileNumber = ?, \n" +
                "EmailID = ?, \n" +
                "classdesc = ?, \n" +
                "sectiondesc = ?, \n" +
                "SchoolKey = ? \n" +
                "WHERE RollNumber = ?;\n";

        db.execSQL(sql, new String[]{entity.PersonName, entity.PhoneMobileNumber,entity.EmailID,
                entity.classdesc,entity.sectiondesc,
                entity.SchoolKey,entity.rolls});
    }

    public void deleteRecord(SQLiteDatabase db,String value)
    {
        db.execSQL("DELETE FROM Registration WHERE RollNumber="+"'"+value+"'");
        db.close();
    }

    public void updateRecord(SQLiteDatabase db, UserRegistrationInfo rob) {
//            db.execSQL("update SchoolRegistrationDetails  set SchoolKey='" + rob.SchoolRegistrationKey + "', SchoolName='" + rob.SchoolName + "'," +
//                    " SchoolAddress ='" + rob.SchoolAddress + "', SchoolCity='" + rob.SchoolCity + "', SchoolPostCode='" + rob.SchoolPostalCode + "'," +
//                    "SchoolState= '" + rob.SchoolState + "',SchoolCountry='" + rob.SchoolCountry + "',BranchArea= '" + rob.SchoolBranch + "'," +
//                    "PIN=" + rob.SchoolPIN + ",emailID='" + rob.SchoolEmailID + "',PhoneNumber='" + rob.SchoolPhoneNumber + "',MobileNumber='" + rob.SchoolMobileNumber + "'" +
//                    ",ContactPersonName='" + rob.SchoolContactPersonName + "',ContactPersonDesignation='" + rob.SchoolCPDesignation + "',ContactPersonContactNumber='" + rob.SchoolCPContactNumber + "'");
    }

    public void updateID(SQLiteDatabase db, String ID) {

        db.execSQL("update Registration  set RegistrationID='" + ID + "'");
    }

    public void updateUserInfoFromSite(SQLiteDatabase db, String extes, String phoneno, String sckoolkey) {
        UserRegistrationInfo entity = new UserRegistrationInfo();
        String[] usersinfo = extes.split("\\{");
        entity.Department = usersinfo[3];
        entity.PhoneMobileNumber = phoneno;
        entity.PersonName = usersinfo[0];
        entity.SchoolKey = sckoolkey;
        entity.Department = usersinfo[3];
        entity.RegistrationID = usersinfo[5];
        if (!isRegistered(db)) {
            insertRecord(db, entity);
        } else {
            updateRecord(db, entity);
        }
    }
}