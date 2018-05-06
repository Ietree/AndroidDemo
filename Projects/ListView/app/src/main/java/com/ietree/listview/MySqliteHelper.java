package com.ietree.listview;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Root on 2018/5/2.
 */
public class MySqliteHelper extends SQLiteOpenHelper {

    public MySqliteHelper(Context context) {
        // context:应用上下文
        // name:数据库名称
        // factory:创建游标的工厂
        // version:数据库的版本
        super(context, "studentdb", null, 1);
    }

    // 在数据库首次被创建时会调用
    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate执行了");
        db.execSQL("create table student(_id integer primary key autoincrement, name varchar(30), sex varchar(10))");
    }
    // 在数据库升级时会调用
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("onUpgrade执行了");
    }
}
