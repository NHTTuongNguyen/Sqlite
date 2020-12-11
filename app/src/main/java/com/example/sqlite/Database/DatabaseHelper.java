package com.example.sqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sqlite.Model.Student;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "Student";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_ADDRESS = "address";
    Student student;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public DatabaseHelper(Context context) {
        super(context, "student.db", null, 1);
        this.mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY,"+COLUMN_NAME+" VARCHAR,"+COLUMN_PHONE+" VARCHAR,"+COLUMN_ADDRESS+" VARCHAR)";
        sqLiteDatabase.execSQL(CREATE_TABLE);
        Log.d("CREATE_TABLE",CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);
//        onCreate(sqLiteDatabase);
    }
    public long insertStudent(Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME,student.getName());
        contentValues.put(COLUMN_PHONE,student.getPhone());
        contentValues.put(COLUMN_ADDRESS,student.getAddress());
        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return result;
    }

//
    public long updateStudent(Student student){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID,student.getId());
        contentValues.put(COLUMN_NAME,student.getName());
        contentValues.put(COLUMN_PHONE,student.getPhone());
        contentValues.put(COLUMN_ADDRESS,student.getAddress());
        long result = sqLiteDatabase.update(TABLE_NAME,contentValues,COLUMN_ID+"=?",new String[]{student.getId()});
        return result;
    }
    public long deleteStudent(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,COLUMN_ID+"=?",new String[]{id});

    }
    public List<Student> getAllStudents(){
        List<Student> listStudent = new ArrayList<>();
        String SELECT = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT,null);
        Log.d("SIZE",cursor.getCount()+"");
        if (cursor.moveToFirst()){
            do {
                student = new Student();
                student.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                student.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                student.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));
                listStudent.add(student);
            }while (cursor.moveToNext());
        }
        db.close();
        return listStudent;
    }


}










