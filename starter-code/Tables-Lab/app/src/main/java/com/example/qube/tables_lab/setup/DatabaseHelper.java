package com.example.qube.tables_lab.setup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.qube.tables_lab.Employee;
import com.example.qube.tables_lab.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Qube on 7/17/16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    // create the global variables
    public static final String EMPLOYEE_TABLE_NAME = "employee";
    public static final String COL_ID = "_id";
    public static final String COL_SSN = "ssn";
    public static final String COL_FIRST = "first_name";
    public static final String COL_LAST = "last_name";
    public static final String COL_YEAR = "year_of_birth";
    public static final String COL_CITY = "city";

    public static final String JOB_TABLE_NAME = "job";
    public static final String COL_JOB_SSN = "job_ssn";
    public static final String COL_COMPANY = "company";
    public static final String COL_SALARY = "salary";
    public static final String COL_EXPERIENCE = "experience";

    public DatabaseHelper(Context context) {
        super(context, "db", null, 1);
    }

    private static DatabaseHelper INSTANCE;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (INSTANCE == null)
            INSTANCE = new DatabaseHelper(context.getApplicationContext());
        return INSTANCE;
    }

    public String getFullInformation() {
        return "";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES_EMPLOYEE);
        db.execSQL(SQL_CREATE_ENTRIES_JOB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL(SQL_DELETE_ENTRIES_EMPLOYEE);
        db.execSQL(SQL_DELETE_ENTRIES_JOB);
    }

    private static final String SQL_CREATE_ENTRIES_EMPLOYEE =
            "CREATE TABLE " + EMPLOYEE_TABLE_NAME + " (" +
                    COL_SSN + " TEXT PRIMARY KEY," +
                    COL_FIRST + " TEXT," +
                    COL_LAST + " TEXT," +
                    COL_YEAR + " TEXT," +
                    COL_CITY + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_JOB =
            "CREATE TABLE " + JOB_TABLE_NAME + " (" +
                    COL_JOB_SSN + " TEXT PRIMARY KEY," +
                    COL_COMPANY + " TEXT," +
                    COL_SALARY + " INTEGER," +
                    COL_EXPERIENCE + " INTEGER," +
                    "FOREIGN KEY(" + COL_JOB_SSN + ") REFERENCES " +
                    EMPLOYEE_TABLE_NAME + "(" + COL_SSN + ") )";

    private static final String SQL_DELETE_ENTRIES_EMPLOYEE = "DROP TABLES IF EXISTS " +
            EMPLOYEE_TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES_JOB = "DROP TABLES IF EXISTS " +
            JOB_TABLE_NAME;

    // Time to create an Employee tables class
    // insert
    public void insertRowEmployee(Employee employee) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_SSN, employee.getSsn());
        values.put(COL_FIRST, employee.getFirstName());
        values.put(COL_LAST, employee.getLastName());
        values.put(COL_YEAR, employee.getYearOfBirth());
        values.put(COL_CITY, employee.getCity());
        db.insertOrThrow(EMPLOYEE_TABLE_NAME, null, values);
        db.close();
    }

    public void insertRowJob(Job job) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_JOB_SSN, job.getSsn());
        values.put(COL_COMPANY, job.getCompany());
        values.put(COL_SALARY, job.getSalary());
        values.put(COL_EXPERIENCE, job.getExperience());
        db.insertOrThrow(JOB_TABLE_NAME, null, values);
    }

    public String getHigestSalary() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT company FROM job JOIN employee " +
                "WHERE salary = (SELECT max(salary) FROM JOB)", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result += cursor.getString(cursor.getColumnIndex(COL_COMPANY));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return result;
    }

    public List<String> getBostonCompanies() {
        SQLiteDatabase db = getReadableDatabase();
        // boston is a ArrayList data type assigned to hold Strings
        List<String> list = new ArrayList<String>();
        Cursor cursor = db.rawQuery("SELECT company FROM job JOIN employee ON " +
                "employee.ssn = job.jobssn WHERE city LIKE 'Boston'", null);

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                list.add(cursor.getString(cursor.getColumnIndex(COL_COMPANY)));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }

    public List<String> getEmployeeInfo() {
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        Cursor cursor = db.rawQuery("SELECT first_name, last_name FROM employee JOIN job ON " +
                "employee.ssn = job.jobssn WHERE company LIKE 'Macy%'", null);
        List<String> list = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result = cursor.getString(cursor.getColumnIndex(COL_FIRST + " " +
                        cursor.getString(cursor.getColumnIndex(COL_LAST))));
                list.add(result);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;

    }

    public List<String> getEmployee() {
        SQLiteDatabase db = getReadableDatabase();
        String result = " ";
        Cursor cursor = db.rawQuery("SELECT * FROM employee", null);
        List<String> list = new ArrayList<String>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                result = cursor.getString(cursor.getColumnIndex(COL_FIRST)) + " " +
                        cursor.getString(cursor.getColumnIndex(COL_LAST));
                list.add(result);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return list;
    }
}
