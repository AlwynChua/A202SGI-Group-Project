package com.example.librarymanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BorrowDatabase extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Borrow.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "borrow_book";
    private static final String COLUMN_ID = "user_id";
    private static final String COLUMN_NAME = "user_name";
    private static final String COLUMN_BOOK = "user_book";
    private static final String COLUMN_BORROW_DATE = "user_borrow_date";
    private static final String COLUMN_DUE_DATE = "user_due_date";



    BorrowDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_BOOK + " TEXT, " +
                COLUMN_BORROW_DATE + " TEXT, " +
                COLUMN_DUE_DATE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void borrowBook(String user_name, String user_book, String user_borrow_date, String user_due_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, user_name);
        cv.put(COLUMN_BOOK, user_book);
        cv.put(COLUMN_BORROW_DATE, user_borrow_date);
        cv.put(COLUMN_DUE_DATE, user_due_date);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String name, String book, String borrow_date, String due_date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_BOOK, book);
        cv.put(COLUMN_BORROW_DATE, borrow_date);
        cv.put(COLUMN_DUE_DATE, due_date);


        long result = db.update(TABLE_NAME, cv, "user_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id ) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "user_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

}

