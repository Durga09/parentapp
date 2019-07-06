package org.ultimatesolution.parentapp.DatabaseClasses;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Kunal on 7/1/2016.
 */
public class DBHelper {
    public static final String DATABASE_NAME = "ParentApp";

    public DBHelper(Object o) {
    }

    public void ctTables(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS SchoolRegistrationDetails(SchoolKey VARCHAR, " +
                " SchoolName VARCHAR,  SchoolAddress VARCHAR,  SchoolCity VARCHAR, " +
                " SchoolPostCode VARCHAR,   SchoolState VARCHAR, " +
                " SchoolCountry VARCHAR,   BranchArea VARCHAR, PIN INTEGER, emailID VARCHAR, " +
                " PhoneNumber VARCHAR,   MobileNumber VARCHAR, " +
                " ContactPersonName VARCHAR, ContactPersonDesignation VARCHAR, ContactPersonContactNumber VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS Registration(RegistrationID VARCHAR ," +
                " PersonName VARCHAR, PhoneMobileNumber VARCHAR, " +
                " EmailID VARCHAR, classdesc VARCHAR,SchoolKey VARCHAR, " +
                "  City VARCHAR, PERSONPICTURE VARCHAR, sectiondesc VARCHAR, RollNumber VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS ClassSectionInfo(ID INTEGER , " +
                " ClassName VARCHAR, SectionName VARCHAR, " +
                " UpdateNumber INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS HomeWorkInformation(ID INTEGER , " +
                " ClassID INTEGER, SubjectID INTEGER, " +
                " HomeWorkDescription VARCHAR, UpdateNumber INTEGER, HomeWorkDate DATE," +
                " UserID INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS StudentAbsentInformation(ID INTEGER, StudentID INTEGER , " +
                " AbsentDate DATE, UpdateNumber INTEGER, UpdateDate DATE, UserID INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS StudentInfo(ID INTEGER , AdmissionID VARCHAR," +
                " StudentName VARCHAR, StudentRollNumber INTEGER, GuardianName VARCHAR, CurrentClassID INTEGER, " +
                " DateOfCreation DATE,  DateofLastUpdate DATE, UpdateNumber INTEGER, PhoneNumber VARCHAR, EMailID VARCHAR )");

        db.execSQL("CREATE TABLE IF NOT EXISTS StudentNotice(ID INTEGER, StudentID INTEGER , " +
                " Notice VARCHAR, NoticeDate DATE, SeenByGuardian BOOLEAN," +
                " UpdateNumber INTEGER,  UserID INTEGER )");

        db.execSQL("CREATE TABLE IF NOT EXISTS SubjectInfo(ID INTEGER , " +
                " SubjectName VARCHAR, UpdateNumber INTEGER )");

        db.execSQL("CREATE TABLE IF NOT EXISTS CONFIG(DatabaseVersion INTEGER  )");

        db.execSQL("CREATE TABLE IF NOT EXISTS TeacherClassInformation(ID INTEGER, TeacherID INTEGER , " +
                " ClassID INTEGER, UpdateNumber INTEGER, UserID INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS TeachersInfo(ID INTEGER, TeachersName  VARCHAR, " +
                " PhoneNumber VARCHAR, EmailID VARCHAR, ClassTeacherOf VARCHAR, UpdateNumber INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS TeacherSubjectInformation(ID INTEGER, TeacherID  INTEGER, " +
                " SubjectID INTEGER,  UpdateNumber INTEGER)");

        db.execSQL("CREATE TABLE IF NOT EXISTS NoticeBoard(`NoticeID` INTEGER PRIMARY KEY AUTOINCREMENT, `NoticeDescription` TEXT, " +
                "`ForAll` TEXT, `Standard` INTEGER, `Section` TEXT, NoticeExpiresOn date, NoticeHeader Text, NoticePosted Boolean" +
                ", `NoticeUpdateID` INTEGER)");
    }

    public void updateTables(SQLiteDatabase db) {
        try {
            try {
                ctTables(db);
            } catch (Exception ex) {
            }
            runQuery(db, "ALTER TABLE Registration ADD COLUMN RollNumber VARCHAR");
            runQuery(db, "ALTER TABLE NoticeBoard ADD COLUMN NoticeUpdateID INTEGER");
            runQuery(db, "ALTER TABLE StudentInfo ADD COLUMN EMailID VARCHAR");
            runQuery(db, "ALTER TABLE StudentInfo ADD COLUMN AdmissionID VARCHAR");
            runQuery(db, "ALTER TABLE Registration ADD COLUMN classdesc VARCHAR");
            runQuery(db, "ALTER TABLE Registration ADD COLUMN sectiondesc VARCHAR");
            //runQuery(db,"Delete from CHATHISTORY");
            //AlterTable(db, "DASHBOARDMESSAGES", "IsRunning", "BOOLEAN");

        } catch (Exception ex) {
        }
    }

    private void AlterTable(SQLiteDatabase db, String tblName, String colName, String coltype) {
        runQuery(db, "ALTER TABLE " + tblName + " ADD COLUMN " + colName + " " + coltype + " ");
    }

    private void runQuery(SQLiteDatabase db, String query) {
        try {
            db.execSQL(query);
        } catch (Exception ex) {
        }
    }

    public static String runQueryOut(SQLiteDatabase db, String query) {
        try {
            db.execSQL(query);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "Successfull";
    }

    public static void runQueryThrowError(SQLiteDatabase db, String query) {
        try {
            db.execSQL(query);
        } catch (Exception ex) {
            throw ex;
        }
    }
}