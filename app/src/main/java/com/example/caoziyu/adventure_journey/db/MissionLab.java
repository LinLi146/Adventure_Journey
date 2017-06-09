package com.example.caoziyu.adventure_journey.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.caoziyu.adventure_journey.db.MissionDbSchema.MissionTable;
import com.example.caoziyu.adventure_journey.db.UserDataDbSchema.UserDataTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/6/8.
 */

public class MissionLab {
    private static MissionLab sMissionLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static MissionLab get(Context context){//全局用来获取这个单实例类的静态方法
        sMissionLab=new MissionLab(context);
        return sMissionLab;
    }

    private MissionLab(Context context) {//构造函数
        mContext = context.getApplicationContext();
        mDatabase = new MissionBaseHelper(mContext) .getWritableDatabase();

    }

    public void addMission(Mission m){//增加新的任务
        ContentValues values= getContentValues(m);
        mDatabase.insert(MissionTable.NAME,null,values);
    }

    public void addUserdata(UserData u){//增加新的用户信息
        ContentValues values= getContentValues(u);
        mDatabase.insert(UserDataTable.NAME,null,values);
    }

    public List<Mission> getMissions(){//获取所有任务列表
        List<Mission> missions=new ArrayList<>();
        MissionCursorWrapper cursor=queryMissions(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                missions.add(cursor.getMission());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return missions;
    }

    public List<UserData> getUserdatas(){//获取所有用户列表
        List<UserData> userDatas=new ArrayList<>();
        UserDataCursorWrapper cursor=queryUserdata(null,null);
        try{
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                userDatas.add(cursor.getUserData());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return userDatas;
    }



    public Mission getMission(int id){//获取单个任务信息
        MissionCursorWrapper cursor=queryMissions(
                MissionTable.Cols.SMID+"= ?",
                new String[] {String.valueOf(id)}
        );
        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMission();
        }finally {
            cursor.close();
        }
    }

    public UserData getUserData(int id){//获取单个用户信息
        UserDataCursorWrapper cursor=queryUserdata(
                UserDataTable.Cols.UID+"= ?",
                new String[] {String.valueOf(id)}
        );
        try {
            if(cursor.getCount()==0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getUserData();
        }finally {
            cursor.close();
        }
    }



    public void updateMission(Mission mission){//更新任务信息，根据smid来定位要更新的任务
        String smidString=Integer.toString(mission.getSmid());
        ContentValues values=getContentValues(mission);

        mDatabase.update(MissionTable.NAME,values,
                MissionTable.Cols.SMID+"= ?",
                new String[]{smidString});
    }

    public void updateUserdata(UserData userData){//更新用户信息，根据id来定位要更新的任务
        String uidString=Integer.toString(userData.getUid());
        ContentValues values=getContentValues(userData);

        mDatabase.update(UserDataTable.NAME,values,
                UserDataTable.Cols.UID+"= ?",
                new String[]{uidString});
    }





    private static ContentValues getContentValues(Mission mission){//获取将值与键对应起来的存储机制
        ContentValues values=new ContentValues();
        values.put(MissionTable.Cols.SMID,mission.getSmid());
        values.put(MissionTable.Cols.TITLE,mission.getTitle());
        values.put(MissionTable.Cols.DATE,mission.getDate().getTime());
        values.put(MissionTable.Cols.DESCRIPTION,mission.getDecription());
        values.put(MissionTable.Cols.DURATIONTIME,mission.getDur_time());
        values.put(MissionTable.Cols.DISTANCE,mission.getDistance());
        values.put(MissionTable.Cols.AVGSPEED,mission.getAvg_speed());
        values.put(MissionTable.Cols.COMPLETED,mission.isCompleted()?1:0);

        return values;

    }

    private static ContentValues getContentValues(UserData userData){//获取将值与键对应起来的存储机制
        ContentValues values=new ContentValues();
        values.put(UserDataTable.Cols.UID,userData.getUid());
        values.put(UserDataTable.Cols.USERNAME,userData.getUserName());
        values.put(UserDataTable.Cols.LEVEL,userData.getLevel());
        values.put(UserDataTable.Cols.TOTALEXP,userData.getTotal_exp());
        values.put(UserDataTable.Cols.TOTALMISSION,userData.getTotal_mission());
        values.put(UserDataTable.Cols.MISSIONCOMP,userData.getMission_completed());


        return values;

    }


    private MissionCursorWrapper queryMissions(String whereClause, String[] whereArgs){//查询用的函数
        Cursor cursor=mDatabase.query(
           MissionTable.NAME,
                null,// Columns - null selects all columns
                whereClause,
                whereArgs,
                null,// groupBy
                null,// having
                null// orderBy
        );

        return new MissionCursorWrapper(cursor);
    }

    private UserDataCursorWrapper queryUserdata(String whereClause, String[] whereArgs){//查询用的函数
        Cursor cursor=mDatabase.query(
                UserDataTable.NAME,
                null,// Columns - null selects all columns
                whereClause,
                whereArgs,
                null,// groupBy
                null,// having
                null// orderBy
        );

        return new UserDataCursorWrapper(cursor);
    }


}
