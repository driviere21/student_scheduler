package com.zybooks.studentscheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "studentdb";

    private static final int DB_VERSION = 7;

    private static final String TABLE_NAME = "terms";
    private static final String TERMID_COLUMN = "termId";
    private static final String TERMTITLE_COLUMN = "termTitle";
    private static final String STARTDATE_COLUMN = "startDate";
    private static final String ENDDATE_COLUMN = "endDate";

    private static final String TABLE_NAME2 = "courses";
    private static final String COURSEID_COLUMN = "courseId";
    private static final String COURSETILTE_COLUMN = "courseTitle";
    private static final String COURSESTARTDATE_COLUMN = "startDate";
    private static final String COURSEENDDATE_COLUMN = "endDate";
    private static final String COURSESTATUS_COLUMN = "status";
    private static final String COURSEINSTRUCTORNAME_COLUMN = "instructorName";
    private static final String COURSEINSTRUCTORTEL_COLUMN = "instructorTel";
    private static final String COURSEINSTRUCTOREMAIL_COLUMN = "instructorEmail";
    private static final String COURSENOTES_COLUMN = "notes";
    private static final String COURSETERMID_COLUMN = "termId";



    private static final String TABLE_NAME3 = "assessments";
    private static final String ASSESSMENTID_COLUMN = "assessmentId";
    private static final String ASSESSMENTTILTE_COLUMN = "assessmentTitle";
    private static final String ASSESSMENTTYPE_COLUMN = "assessmentType";
    private static final String ASSESSMENTSTARTDATE_COLUMN = "startDate";
    private static final String ASSESSMENTENDDATE_COLUMN = "endDate";
    private static final String ASSESSMENTCOURSEID_COLUMN = "courseId";

    private int termIdD = -1;
    private int courseTermIdD = 1;
    List<Term> termList;





    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = " CREATE TABLE " + TABLE_NAME + "("
                + TERMID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,"
                + TERMTITLE_COLUMN + " TEXT NOT NULL,"
                + STARTDATE_COLUMN + " TEXT NOT NULL,"
                + ENDDATE_COLUMN + " TEXT NOT NULL)";
        db.execSQL(query);

        String courseQuery = " CREATE TABLE IF NOT EXISTS  " + TABLE_NAME2 + "("
                + COURSEID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + COURSETILTE_COLUMN + " TEXT NOT NULL, "
                + COURSESTARTDATE_COLUMN + " TEXT NOT NULL, "
                + COURSEENDDATE_COLUMN + " TEXT NOT NULL, "
                + COURSESTATUS_COLUMN + " TEXT NOT NULL, "
                + COURSEINSTRUCTORNAME_COLUMN + " TEXT NOT NULL,"
                + COURSEINSTRUCTORTEL_COLUMN + " TEXT NOT NULL,"
                + COURSEINSTRUCTOREMAIL_COLUMN + " TEXT NOT NULL,"
                + COURSENOTES_COLUMN + " TEXT, "
                + COURSETERMID_COLUMN + " INTEGER, FOREIGN KEY ('termId') REFERENCES 'terms' ('termId'))";
        db.execSQL(courseQuery);


        String assessmentQuery = " CREATE TABLE IF NOT EXISTS  " + TABLE_NAME3 + "("
                + ASSESSMENTID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
                + ASSESSMENTTILTE_COLUMN + " TEXT NOT NULL, "
                + ASSESSMENTSTARTDATE_COLUMN + " TEXT NOT NULL, "
                + ASSESSMENTENDDATE_COLUMN + " TEXT NOT NULL, "
                + ASSESSMENTTYPE_COLUMN + " TEXT NOT NULL, "
                + ASSESSMENTCOURSEID_COLUMN + " INTEGER, FOREIGN KEY ('courseId') REFERENCES 'courses' ('courseId'))";
        db.execSQL(assessmentQuery);

    }

    public void addNewCourse(String courseTile, String courseStartDate, String courseEndDate, String status, String instructorName, String instructorTel, String instructorEmail, String notes, int courseTermId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COURSETILTE_COLUMN, courseTile);
        values.put(COURSESTARTDATE_COLUMN, courseStartDate);
        values.put(COURSEENDDATE_COLUMN, courseEndDate);
        values.put(COURSESTATUS_COLUMN, status);
        values.put(COURSEINSTRUCTORNAME_COLUMN, instructorName);
        values.put(COURSEINSTRUCTORTEL_COLUMN, instructorTel);
        values.put(COURSEINSTRUCTOREMAIL_COLUMN, instructorEmail);
        values.put(COURSENOTES_COLUMN, notes);
        values.put(COURSETERMID_COLUMN, courseTermId);

        db.insert(TABLE_NAME2, null, values);
        db.close();


    }

    public void addNewTerm(String termTitle, String startDate, String endDate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TERMTITLE_COLUMN, termTitle);
        values.put(STARTDATE_COLUMN, startDate);
        values.put(ENDDATE_COLUMN, endDate);

        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public void addNewAssessment(String assessmentTitle, String startDate, String endDate, String assessmentType, int assessmentCourseId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ASSESSMENTTILTE_COLUMN, assessmentTitle);
        values.put(ASSESSMENTSTARTDATE_COLUMN, startDate);
        values.put(ASSESSMENTENDDATE_COLUMN, endDate);
        values.put(ASSESSMENTTYPE_COLUMN, assessmentType);
        values.put(ASSESSMENTCOURSEID_COLUMN, assessmentCourseId);

        db.insert(TABLE_NAME3, null, values);
        db.close();


    }


    public ArrayList<Term> readTerm() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorTerm = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        ArrayList<Term> termArrayList = new ArrayList<>();

        if (cursorTerm.moveToFirst()) {
            do {
                termArrayList.add(new Term(cursorTerm.getString(1),
                        cursorTerm.getString(2),
                        cursorTerm.getString(3)));

            } while (cursorTerm.moveToNext());
        }

        cursorTerm.close();

        return termArrayList;
    }


    public ArrayList<Courses> readCourses() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorCourse = db.rawQuery("SELECT * FROM " + TABLE_NAME2, null);

        ArrayList<Courses> coursesArrayList = new ArrayList<>();

        if (cursorCourse.moveToFirst()) {
            do {
                coursesArrayList.add(new Courses(cursorCourse.getString(1),
                        cursorCourse.getString(2),
                        cursorCourse.getString(3),
                        cursorCourse.getString(4),
                        cursorCourse.getString(5),
                        cursorCourse.getString(6),
                        cursorCourse.getString(7),
                        cursorCourse.getString(8)));

            } while (cursorCourse.moveToNext());
        }

        cursorCourse.close();

        return coursesArrayList;
    }


    public ArrayList<Assessments> readAssessment() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorAssessment = db.rawQuery("SELECT * FROM " + TABLE_NAME3, null);

        ArrayList<Assessments> assessmentsArrayList = new ArrayList<>();

        if (cursorAssessment.moveToFirst()) {
            do {
                assessmentsArrayList.add(new Assessments(cursorAssessment.getString(1),
                        cursorAssessment.getString(2),
                        cursorAssessment.getString(3),
                        cursorAssessment.getString(4)));

            } while (cursorAssessment.moveToNext());
        }

        cursorAssessment.close();

        return assessmentsArrayList;
    }


    public void updateTerm(String originalTermTitle, String termTitle, String termStartDate, String termEndDate) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TERMTITLE_COLUMN, termTitle);
        values.put(STARTDATE_COLUMN, termStartDate);
        values.put(ENDDATE_COLUMN, termEndDate);

        db.update(TABLE_NAME, values, "termTitle =?", new String[]{originalTermTitle});
        db.close();
    }


    public void updateCourse(String originalCourseName, String courseTitle, String startDate, String endDate, String status, String instructorName, String instructorEmail, String instructorPhone, String notes, int termAssigned) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COURSETILTE_COLUMN, courseTitle);
        values.put(COURSESTARTDATE_COLUMN, startDate);
        values.put(COURSEENDDATE_COLUMN, endDate);
        values.put(COURSESTATUS_COLUMN, status);
        values.put(COURSEINSTRUCTORNAME_COLUMN, instructorName);
        values.put(COURSEINSTRUCTORTEL_COLUMN, instructorPhone);
        values.put(COURSEINSTRUCTOREMAIL_COLUMN, instructorEmail);
        values.put(COURSENOTES_COLUMN, notes);
        values.put(COURSETERMID_COLUMN, termAssigned);

        db.update(TABLE_NAME2, values, "courseTitle=?", new String[]{originalCourseName});
        db.close();
    }


    public void updateAssessment(String originalAssessmentName, String assessmentTitle, String startDate, String endDate, String assessmentType, int assessmentCourseId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ASSESSMENTTILTE_COLUMN, assessmentTitle);
        values.put(ASSESSMENTSTARTDATE_COLUMN, startDate);
        values.put(ASSESSMENTENDDATE_COLUMN, endDate);
        values.put(ASSESSMENTTYPE_COLUMN, endDate);
        values.put(ASSESSMENTCOURSEID_COLUMN, assessmentCourseId);

        db.update(TABLE_NAME3, values, "assessmentTitle=?", new String[]{originalAssessmentName});
        db.close();
    }


    public void deleteTerm(String termTitle) {
        int termIdD = -1;
        int courseTermIdD = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        String termIdSql = "SELECT termId FROM terms WHERE termTitle = ?";
        Cursor cursor = db.rawQuery(termIdSql, new String[]{termTitle});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    termIdD = cursor.getInt(0);
                } while (cursor.moveToNext());
            } else {
                termIdD = 0;
            }
        }

        String courseTermIdSql = "SELECT termId FROM courses WHERE termId = ?";
        Cursor cursorC = db.rawQuery(courseTermIdSql, new String[]{String.valueOf(termIdD)});
        if (cursorC != null && cursorC.getCount() > 0) {
            if (cursorC.moveToFirst()) {
                do {
                    courseTermIdD = cursorC.getInt(0);
                } while (cursorC.moveToNext());
            } else {
                courseTermIdD = 0;
            }
        }

        if (termIdD != courseTermIdD) {

            db.delete(TABLE_NAME, "termTitle=?", new String[]{termTitle});

        } else {

        }


        //  db.delete(TABLE_NAME, "termTitle=?", new String[]{termTitle});

        db.close();

    }

    public void deleteCourse(String courseTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME2, "courseTitle =?", new String[]{courseTitle});

        db.close();
    }


    public void deleteAssessment(String assessmentTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME3, "assessmentTitle =?", new String[]{assessmentTitle});

        db.close();
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3);
        onCreate(db);
    }


    public int getCourseTermId(String assignedTo) {

        int courseTermId = 0;


        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT termId FROM terms WHERE termTitle = ?";
        Cursor cursor = db.rawQuery(sql, new String[]{assignedTo});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {

                do {
                    courseTermId = cursor.getInt(0);
                    //return courseTermId;

                } while (cursor.moveToNext());


            }
        } else {
            courseTermId = 0;
        }

        return courseTermId;
    }


    public void findTermId(String details) {
        // int termIdD = -1;
        int courseTermIdD = -1;

        SQLiteDatabase db = this.getWritableDatabase();

        String termIdSql = "SELECT termId FROM terms WHERE termTitle = ?";
        Cursor cursor = db.rawQuery(termIdSql, new String[]{details});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    termIdD = cursor.getInt(0);
                } while (cursor.moveToNext());
            }
        }
        //  return String.valueOf(termIdD);

    }


    public ArrayList<Courses> readTermDetails() {
        SQLiteDatabase db = this.getReadableDatabase();


        String termDetailSql = "SELECT * FROM courses WHERE termId = ?";
        Cursor cursorC = db.rawQuery(termDetailSql, new String[]{String.valueOf(courseTermIdD)});

        ArrayList<Courses> termDetailsArrayList = new ArrayList<>();

        if (cursorC != null && cursorC.getCount() > 0) {
            if (cursorC.moveToFirst()) {
                do {
                    termDetailsArrayList.add(new Courses(cursorC.getString(1),
                            cursorC.getString(2),
                            cursorC.getString(3),
                            cursorC.getString(4),
                            cursorC.getString(5),
                            cursorC.getString(6),
                            cursorC.getString(7),
                            cursorC.getString(8)));

                } while (cursorC.moveToNext());
            }


            cursorC.close();


        }
        return termDetailsArrayList;


    }


    public void getSelectedTermIdFromDataBase(String termTitle) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT termId FROM terms WHERE termTitle = ?";

        Cursor cursor = db.rawQuery(sql, new String[]{termTitle});
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    termIdD = cursor.getInt(0);

                } while (cursor.moveToNext());
            }

        }


    }



}












