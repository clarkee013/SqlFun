package example.codeclan.com.sqlfun;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static android.R.attr.cursorVisible;
import static android.R.attr.filterTouchesWhenObscured;
import static android.R.attr.key;

/**
 * Created by user on 06/07/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    //Database Version
    private static final int DATABASE_VERSION = 3;

    //Database needs a name
    private static final String DATABASE_NAME = "instructorsDB";

    //Table name
    private static final String TABLE_INSTRUCTORS = "instructors";

    //Column names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_FAV_LANG = "favourite_language";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //null added in above
    }

    //Create Tables pt.1
    public void onCreate(SQLiteDatabase db){
        String sql = "CREATE TABLE " + TABLE_INSTRUCTORS + "(" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_NAME + " TEXT, " + KEY_FAV_LANG + " TEXT)";
        db.execSQL(sql);
    }

    //Create Tables pt.2
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSTRUCTORS);
        onCreate(db);
    }

    // CRUD - CREATE
    public void addInstructors(Instructor instructor){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, instructor.getName());
        values.put(KEY_FAV_LANG, instructor.getFavourite_language());

        db.insert(TABLE_INSTRUCTORS, null, values);
        db.close();
    }

    //CRUD - READ single item (yes it's this messy & don't ask about the null null null null part either!)
    public Instructor getInstructor(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Instructor instructor = null;
        Cursor cursor = db.query(TABLE_INSTRUCTORS, new String[] {KEY_ID, KEY_NAME, KEY_FAV_LANG}, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();

            instructor = new Instructor(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        }
        return instructor;
    }

    // CRUD - READ All items
    public ArrayList<Instructor> getAllInstructors(){
        ArrayList<Instructor> instructorList = new ArrayList<Instructor>();

        String sql = "SELECT * FROM " + TABLE_INSTRUCTORS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        Instructor instructor = null;
        if (cursor.moveToFirst()) {
            do {
                instructor = new Instructor();
                instructor.set_id(Integer.parseInt(cursor.getString(0)));
                instructor.setName(cursor.getString(1));
                instructor.setFavourite_language(cursor.getString(2));
                instructorList.add(instructor);
            } while (cursor.moveToNext());
        }
        return instructorList;
    }




}
