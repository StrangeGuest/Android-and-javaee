package com.example.networkdemo.util

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context,name:String,version:Int):SQLiteOpenHelper(context,name,null,version) {

    private val createUser="create table tbl_user(id Integer not null primary key autoincrement,"+
            "username text,password text)"

    private val createStudent="create table tbl_student(stuNo text not null primary key," +
            "stuName text,classes text,gender text,department text,tel text,dormNo text," +
            "photoPath text)"
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(createUser)
        db?.execSQL(createStudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        //onCreate(db)
    }
}