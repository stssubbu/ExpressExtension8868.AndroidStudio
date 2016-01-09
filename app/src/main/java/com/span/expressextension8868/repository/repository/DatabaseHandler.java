package com.span.expressextension8868.repository.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.span.expressextension8868.model.core.AddBussinessInputModel;
import com.span.expressextension8868.utils.utility.SendException;

import java.util.ArrayList;
import java.util.Vector;


public class DatabaseHandler extends SQLiteOpenHelper {

    Context mContext;

    SQLiteDatabase sqldb;

    private static final int DATABASE_VERSION = 2;

    static String DBNAME = "ExpressExtension.db";

    private static final String TABLE_COUNTRY = "Countrylisttable";

    private static final String TABLE_STATE = "Statelisttable";

    private static final String TABLE_TIMEZONE = "Timezonetable";

    private static final String TABLE_FORM_CODES_LIST = "FormCodesList";

    private static final String TABLE_FORM_OF_ORG = "FormofOrg";

    private static final String USERTABLE = "UserTable";

    private static final String BUSINESS_LIST_TABLE = "BusinessList";

    public DatabaseHandler(Context context) {
        super(context, DBNAME, null, DATABASE_VERSION);

        mContext = context;

        sqldb = this.getReadableDatabase();

    }

    public ArrayList<String> getCountrynameList() {
        ArrayList<String> Countryname = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Countryname.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Countryname;
    }

    public ArrayList<String> getCountryIDList() {
        ArrayList<String> CountryID = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_COUNTRY;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                CountryID.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return CountryID;
    }

    public ArrayList<String> getStatenameList() {

        ArrayList<String> Statename = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_STATE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Statename.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Statename;
    }

    public ArrayList<String> getStateIDList() {
        ArrayList<String> StateID = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_STATE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StateID.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return StateID;
    }

    public ArrayList<String> getTimezoneNameList() {
        ArrayList<String> Timezonename = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_TIMEZONE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Timezonename.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Timezonename;
    }

    public ArrayList<String> getFormofOrgNameList() {

        ArrayList<String> formNameList = new ArrayList<String>();


        String selectQuery = "SELECT * FROM " + TABLE_FORM_OF_ORG;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                formNameList.add(cursor.getString(2));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();

        db.close();

        return formNameList;
    }

    public String getFormOrgIdByName(String Name) {
        String OrgName = "";

        String selectQuery = "SELECT * FROM " + TABLE_FORM_OF_ORG + " WHERE FONAME='" + Name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (Name.equalsIgnoreCase(cursor.getString(2))) {
                    OrgName = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return OrgName;
    }

    public String getFormOrgNameByID(String Name) {
        String OrgName = "";

        String selectQuery = "SELECT * FROM " + TABLE_FORM_OF_ORG + " WHERE FOID='" + Name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {


                OrgName = cursor.getString(2);

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return OrgName;
    }


    public String getFormOrgpositionByID(String ID) {
        String OrgName = "";

        String selectQuery = "SELECT * FROM " + TABLE_FORM_OF_ORG + " WHERE FOID='" + ID + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (ID.equalsIgnoreCase(cursor.getString(1))) {
                    OrgName = cursor.getString(0);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return OrgName;
    }


    public ArrayList<String> getTimezoneIDList() {
        ArrayList<String> TimezoneID = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_TIMEZONE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                TimezoneID.add(cursor.getString(1));
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return TimezoneID;
    }

    public String getCountrynameFromID(String CID) {
        String countryname = "";

        String SelectQuery = "SELECT ID,Country FROM Countrylisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (CID.equalsIgnoreCase(cursor.getString(1))) {
                    countryname = cursor.getString(2);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return countryname;
    }

    public String getCountryIDfromName(String name) {
        String countryname = "";

        String SelectQuery = "SELECT ID,Country FROM Countrylisttable WHERE Country='" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                countryname = cursor.getString(0);
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return countryname;
    }


    public String getCountryIDFromPosition(String pos) {
        String CountryID = "";

        String SelectQuery = "SELECT SNO,ID FROM Countrylisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (pos.equalsIgnoreCase(cursor.getString(0))) {
                    CountryID = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return CountryID;
    }

    public String getCountryPosFromName(String name) {
        String CountryPOS = "";

        String SelectQuery = "SELECT SNO,ID FROM Countrylisttable WHERE Country='" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                CountryPOS = cursor.getString(0);

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return CountryPOS;
    }


    public String getCountryPosFromCID(String CID) {
        String CountryPOS = "";

        String SelectQuery = "SELECT SNO,ID FROM Countrylisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (CID.equalsIgnoreCase(cursor.getString(1))) {
                    CountryPOS = cursor.getString(0);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return CountryPOS;
    }

    public String getCountryNameFromCID(String CID) {
        String Countryname = "";

        String SelectQuery = "SELECT ID,Country FROM Countrylisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                if (CID.equalsIgnoreCase(cursor.getString(0))) {
                    Countryname = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Countryname;
    }

    public String getStateIDFromPosition(String pos) {
        String StateID = "";

        String SelectQuery = "SELECT SNO,ID FROM Statelisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if (pos.equalsIgnoreCase(cursor.getString(0))) {
                    StateID = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return StateID;
    }

    public String getStateIDFromName(String name) {
        String StateID = "";

        String SelectQuery = "SELECT SNO,ID FROM Statelisttable WHERE state='" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                StateID = cursor.getString(1);
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return StateID;
    }


    public String getStatePositionFromSname(String name) {
        String StatePos = "";

        String SelectQuery = "SELECT SNO,ID FROM Statelisttable WHERE state='" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {


                StatePos = cursor.getString(0);

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return StatePos;
    }

    public String getStatePositionFromSID(String SID) {
        String StatePos = "";

        String SelectQuery = "SELECT SNO,ID FROM Statelisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if (SID.equalsIgnoreCase(cursor.getString(1))) {
                    StatePos = cursor.getString(0);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();

        return StatePos;
    }

    public String getStatenameFromSID(String SID) {
        String Statename = "";

        String SelectQuery = "SELECT ID,State FROM Statelisttable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                if (SID.equalsIgnoreCase(cursor.getString(0))) {
                    Statename = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();

        db.close();

        return Statename;
    }

    public String getTimezoneIDFromPosition(String pos) {
        String TimezoneId = "";

        String SelectQuery = "SELECT SNO,ID FROM Timezonetable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if (pos.equalsIgnoreCase(cursor.getString(0))) {
                    TimezoneId = cursor.getString(1);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return TimezoneId;
    }

    public String getTimezoneIDFromName(String name) {
        String TimezoneId = "";

        String SelectQuery = "SELECT SNO,ID FROM Timezonetable WHERE Timezone='" + name + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                TimezoneId = cursor.getString(1);

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return TimezoneId;
    }

    public String getTimezonePosFromTzid(String Tzid) {
        String Timezonepos = "";

        String SelectQuery = "SELECT SNO,ID FROM Timezonetable";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if (Tzid.equalsIgnoreCase(cursor.getString(1))) {
                    Timezonepos = cursor.getString(0);
                }
            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Timezonepos;
    }

    public String getTimezoneFromTzid(String Tzid) {
        String Timezonepos = "";

        String SelectQuery = "SELECT * FROM Timezonetable WHERE ID='" + Tzid + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(SelectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                Timezonepos = cursor.getString(2);

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return Timezonepos;
    }

    @Override
    public void onCreate(SQLiteDatabase db1) {

        Log.i("db", "db oncreate");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_COUNTRY + "(SNO INTEGER PRIMARY KEY, ID INTEGER, Country VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP, UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_STATE + "(SNO INTEGER PRIMARY KEY,ID INTEGER,State VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP,UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_TIMEZONE + "(SNO INTEGER PRIMARY KEY AUTOINCREMENT,ID INTEGER,Timezone VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP,UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FORM_CODES_LIST + "(SNO INTEGER PRIMARY KEY AUTOINCREMENT,EntityId INTEGER,FormCodeId INTEGER,FormName VARCHAR,Formcode VARCHAR,IsForm990 VARCHAR); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FORM_OF_ORG + "(SNO INTEGER PRIMARY KEY AUTOINCREMENT,FOID INTEGER,FONAME VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP,UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + USERTABLE + "(Val VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP,UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

        db1.execSQL("CREATE TABLE IF NOT EXISTS " + BUSINESS_LIST_TABLE + "(SNO INTEGER PRIMARY KEY AUTOINCREMENT,BID INTEGER,BName VARCHAR,EIN VARCHAR, CreateTimeStamp DEFAULT CURRENT_TIMESTAMP,UpdateTimeStamp DEFAULT CURRENT_TIMESTAMP); ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COUNTRY);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STATE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIMEZONE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORM_OF_ORG);

        db.execSQL("DROP TABLE IF EXISTS " + USERTABLE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FORM_CODES_LIST);

        db.execSQL("DROP TABLE IF EXISTS " + BUSINESS_LIST_TABLE);

        onCreate(db);

    }

    public void DeleteTimezoneValues() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_TIMEZONE, null, null);
    }

    public ArrayList<String> getUserValues() {
        ArrayList<String> UserValues = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + USERTABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserValues.add(cursor.getString(0));

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return UserValues;
    }


    public ArrayList<String> getFormList(String column) {
        ArrayList<String> list = new ArrayList<String>();

        String selectQuery = "SELECT *  FROM " + TABLE_FORM_CODES_LIST;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                if (column.equalsIgnoreCase("FormName")) {
                    list.add(cursor.getString(3));
                } else if (column.equalsIgnoreCase("FormCode")) {
                    list.add(cursor.getString(4));
                } else if (column.equalsIgnoreCase("FormCodeId")) {
                    list.add(cursor.getString(2));
                }

            }
            while (cursor.moveToNext());
        }

        // closing connection
        cursor.close();
        db.close();
        return list;
    }

    public String getFormCodeIDByFormCode(String Formcode) {
        String FormCodeIDByFormCode = "";

        String selectQuery = "SELECT *  FROM " + TABLE_FORM_CODES_LIST + " Where Formcode='" + Formcode + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FormCodeIDByFormCode = (cursor.getString(2));

            } while (cursor.moveToNext());
        }


        // closing connection
        cursor.close();
        db.close();

        return FormCodeIDByFormCode;
    }

    public String getFormNameByFormCode(String Formcode) {
        String FormCodeIDByFormCode = "";

        String selectQuery = "SELECT *  FROM " + TABLE_FORM_CODES_LIST + " Where Formcode='" + Formcode + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                FormCodeIDByFormCode = (cursor.getString(3));

            } while (cursor.moveToNext());
        }


        // closing connection
        cursor.close();
        db.close();

        return FormCodeIDByFormCode;
    }

    public String getFormCodeByFormName(String FormName) {
        String FormCode = "";

        String selectQuery = "SELECT *  FROM " + TABLE_FORM_CODES_LIST + " Where FormName='" + FormName + "';";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                FormCode = (cursor.getString(4));

            } while (cursor.moveToNext());
        }

        Log.i("form code", "form code value : " + FormCode);

        // closing connection
        cursor.close();
        db.close();

        return FormCode;
    }


    public void InsertUserValues(ArrayList<String> values) {

        String insert_info1 = "Delete from UserTable";

        SQLiteStatement stmt1 = getWritableDatabase().compileStatement(insert_info1);

        stmt1.execute();

        stmt1.close();

        String insert_info = "INSERT OR REPLACE INTO UserTable(Val) VALUES (?)";

        SQLiteStatement stmt = getWritableDatabase().compileStatement(insert_info);

        for (int i = 0; i < values.size(); i++) {
            stmt.bindString(1, "" + values.get(i));

            stmt.execute();
        }
        stmt.close();
    }

    public void InsertToDb(final Vector<Vector<String>> returnobject) {

        try {
            sqldb.delete(TABLE_STATE, null, null);

            for (int i = 0; i < returnobject.get(2).size(); i++) {

                ContentValues contentValues = new ContentValues();

                contentValues.put("SNO", i);

                contentValues.put("ID", returnobject.get(2).get(i));

                contentValues.put("State", returnobject.get(3).get(i));

                sqldb.insert(TABLE_STATE, null, contentValues);

            }

            sqldb.delete(TABLE_COUNTRY, null, null);

            for (int j = 0; j < returnobject.get(0).size(); j++) {

                ContentValues contentValues = new ContentValues();

                contentValues.put("SNO", j);

                contentValues.put("ID", returnobject.get(0).get(j));

                contentValues.put("Country", returnobject.get(1).get(j));

                sqldb.insert(TABLE_COUNTRY, null, contentValues);

            }

            sqldb.delete(TABLE_TIMEZONE, null, null);

            sqldb.execSQL("delete from sqlite_sequence where name='" + TABLE_TIMEZONE + "'");

            for (int k = 0; k < returnobject.get(6).size(); k++) {
                ContentValues contentValues = new ContentValues();

                contentValues.put("ID", returnobject.get(6).get(k));

                contentValues.put("Timezone", returnobject.get(7).get(k));

                sqldb.insert(TABLE_TIMEZONE, null, contentValues);

            }

            sqldb.delete(TABLE_FORM_OF_ORG, null, null);

            sqldb.execSQL("delete from sqlite_sequence where name='" + TABLE_FORM_OF_ORG + "'");

            for (int k = 0; k < returnobject.get(4).size(); k++) {
                ContentValues contentValues = new ContentValues();

                contentValues.put("FOID", returnobject.get(4).get(k));

                contentValues.put("FONAME", returnobject.get(5).get(k));

                sqldb.insert(TABLE_FORM_OF_ORG, null, contentValues);

            }

        } catch (SQLException e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }

    }


    public void InsertFormsToDB(final Vector<ArrayList<String>> GetFormTypestaticVector) {

        try {
            sqldb.delete(TABLE_FORM_CODES_LIST, null, null);

            sqldb.execSQL("delete from sqlite_sequence where name='" + TABLE_FORM_CODES_LIST + "'");

            for (int k = 0; k < GetFormTypestaticVector.get(0).size(); k++) {

                ContentValues contentValues = new ContentValues();

                contentValues.put("EntityId", GetFormTypestaticVector.get(0).get(k));

                contentValues.put("FormCodeId", GetFormTypestaticVector.get(1).get(k));

                contentValues.put("FormName", GetFormTypestaticVector.get(2).get(k));

                contentValues.put("Formcode", GetFormTypestaticVector.get(3).get(k));

                contentValues.put("IsForm990", GetFormTypestaticVector.get(4).get(k));

                sqldb.insert(TABLE_FORM_CODES_LIST, null, contentValues);

            }


        } catch (SQLException e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }

    }

    public void insertBusinessListToDb(final Vector<AddBussinessInputModel> returnobject) {

        try {
            sqldb.delete(BUSINESS_LIST_TABLE, null, null);

            for (int i = 0; i < returnobject.size(); i++) {

                ContentValues contentValues = new ContentValues();

                contentValues.put("SNO", i);

                contentValues.put("BID", returnobject.get(i).getBId());

                contentValues.put("BName", returnobject.get(i).getOrganizationName());

                contentValues.put("EIN", returnobject.get(i).getEin());

                sqldb.insert(BUSINESS_LIST_TABLE, null, contentValues);

            }


        } catch (SQLException e) {

            e.printStackTrace();

            new SendException(mContext, e);
        }

    }


}
