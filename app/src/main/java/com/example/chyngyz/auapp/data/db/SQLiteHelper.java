package com.example.chyngyz.auapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.chyngyz.auapp.data.entity.Vacancy;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.Currency;

public class SQLiteHelper extends SQLiteOpenHelper implements SQLiteManager {
    private static final String DB_TAG = "DB_TAG";
    private static final String DB_NAME = "au.db";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "VACANCY";
    private static final String ID = "_id";
    private static final String PID = "P_ID";
    private static final String HEADER = "HEADER";
    private static final String PROFESSION = "PROFESSION";
    private static final String SALARY = "SALARY";
    private static final String TELEPHONE = "TELEPHONE";
    private static final String DATE = "DATE";
    private static final String BODY = "BODY";


    public static final String CREATE_VACANCY_TABLE = "CREATE TABLE IF NOT EXISTS " +
            TABLE_NAME + " (" +
            ID + " INTEGER_PRIMARY_KEY, " +
            PID + " TEXT, " +
            HEADER + " TEXT, " +
            PROFESSION + " TEXT, " +
            SALARY + " TEXT, " +
            TELEPHONE + " TEXT, " +
            DATE + " TEXT, " +
            BODY + " TEXT);";

    public SQLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_VACANCY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        }

        onCreate(db);
    }


    @Override
    public void saveVacancyList(ArrayList<Vacancy> vacancyList) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        for (Vacancy vacancy : vacancyList) {
            cv.put(PID, vacancy.getPid());
            cv.put(HEADER, vacancy.getHeader());
            cv.put(PROFESSION, vacancy.getProfession());
            cv.put(SALARY, vacancy.getSalary());
            cv.put(TELEPHONE, vacancy.getTelephone());
            cv.put(DATE, vacancy.getDate());
            cv.put(BODY, vacancy.getBody());

            long rowId = db.insert(TABLE_NAME, null, cv);
            Log.d(DB_TAG, "ITEM SAVED: " + rowId);
        }

        db.close();
    }

    @Override
    public ArrayList<Vacancy> getMainVacancyList() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<Vacancy> vacancyList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            int pidIndex = cursor.getColumnIndex(PID);
            int headerIndex = cursor.getColumnIndex(HEADER);
            int professionIndex = cursor.getColumnIndex(PROFESSION);
            int salaryIndex = cursor.getColumnIndex(SALARY);
            int telephoneIndex = cursor.getColumnIndex(TELEPHONE);
            int dateIndex = cursor.getColumnIndex(DATE);
            int bodyIndex = cursor.getColumnIndex(BODY);

            do {
                Vacancy vacancy = new Vacancy();
                vacancy.setPid(cursor.getString(pidIndex));
                vacancy.setHeader(cursor.getString(headerIndex));
                vacancy.setProfession(cursor.getString(professionIndex));
                vacancy.setSalary(cursor.getString(salaryIndex));
                vacancy.setTelephone(cursor.getString(telephoneIndex));
                vacancy.setDate(cursor.getString(dateIndex));
                vacancy.setBody(cursor.getString(bodyIndex));
                vacancyList.add(vacancy);
                Log.d(DB_TAG, "RETURNED LIST SIZE " + cursor.getCount());
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return vacancyList;
    }

    @Override
    public void saveVacancy(Vacancy vacancy) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PID, vacancy.getPid());
        cv.put(HEADER, vacancy.getHeader());
        cv.put(PROFESSION, vacancy.getProfession());
        cv.put(SALARY, vacancy.getSalary());
        cv.put(TELEPHONE, vacancy.getTelephone());
        cv.put(DATE, vacancy.getDate());
        cv.put(BODY, vacancy.getBody());

        long rowId = db.insert(TABLE_NAME, null, cv);
        Log.d(DB_TAG, "ITEM SAVED: " + rowId);

        db.close();
    }

    @Override
    public void deleteVacancy(Vacancy vacancy) {
        SQLiteDatabase db = getWritableDatabase();
        long deleteCount = db.delete(TABLE_NAME, PID + " =? ", new String[]{vacancy.getPid()});
        Log.d(DB_TAG, "ITEM DELETED " + deleteCount);
        db.close();
    }

    @Override
    public void deleteAllVacancies() {
        SQLiteDatabase db = getWritableDatabase();
        long deleteCount = db.delete(TABLE_NAME, null, null);
        Log.d(DB_TAG, "TABLE CLEARED " + deleteCount);
        db.close();
    }
}
