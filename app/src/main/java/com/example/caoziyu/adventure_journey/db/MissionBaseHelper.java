package com.example.caoziyu.adventure_journey.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.caoziyu.adventure_journey.db.MissionDbSchema.MissionTable;
import com.example.caoziyu.adventure_journey.db.UserDataDbSchema.UserDataTable;


/**
 * Created by admin on 2017/6/8.
 */

public class MissionBaseHelper extends SQLiteOpenHelper{
    private static final int VERSION=1;
    private static final String DATABASE_NAME="missionBase.db";

    public MissionBaseHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("MissionBaseHelper","Database created!");
        db.execSQL("create table "+ MissionTable.NAME+"("+
                "_id integer primary key autoincrement,"+
                MissionTable.Cols.SMID+","+
                MissionTable.Cols.TITLE+","+
                MissionTable.Cols.DESCRIPTION+","+
                MissionTable.Cols.DATE+","+
                MissionTable.Cols.DURATIONTIME+","+
                MissionTable.Cols.DISTANCE +","+
                MissionTable.Cols.AVGSPEED+","+
                MissionTable.Cols.COMPLETED+
                ")"
        );
        db.execSQL("create table "+ UserDataTable.NAME+"("+
                "_id integer primary key autoincrement,"+
                UserDataTable.Cols.UID+","+
                UserDataTable.Cols.USERNAME+","+
                UserDataTable.Cols.LEVEL+","+
                UserDataTable.Cols.TOTALEXP+","+
                UserDataTable.Cols.TOTALMISSION+","+
                UserDataTable.Cols.MISSIONCOMP +
                ")"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion){

    }
}