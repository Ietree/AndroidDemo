package com.ietree.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Root on 2018/5/3.
 */
public class StudentDao {
    private MySqliteHelper helper;

    public StudentDao(Context context) {
        helper = new MySqliteHelper(context);
    }

    public void add(Student student) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("insert into student values(null, ?, ?)", new Object[]{student.getName(), student.getSex()});
    }

    public void delete(Student student) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("delete from student where _id = ?", new Object[]{student.getId()});
    }

    public void update(Student student) {
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL("update student set name=?, sex=? where _id=?", new Object[]{student.getName(), student.getSex(), student.getId()});
    }

    public Student find(Student student) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from student where _id=?", new String[]{String.valueOf(student.getId())});
        Student stu = null;
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String sex = cursor.getString(cursor.getColumnIndex("sex"));
            stu = new Student(id, name, sex);
        }
        return stu;
    }

}
