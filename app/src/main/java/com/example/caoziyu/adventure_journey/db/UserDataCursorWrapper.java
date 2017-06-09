package com.example.caoziyu.adventure_journey.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.caoziyu.adventure_journey.db.MissionDbSchema.MissionTable;
import com.example.caoziyu.adventure_journey.db.UserDataDbSchema.UserDataTable;

import java.util.Date;

/**
 * Created by admin on 2017/6/8.
 */

public class UserDataCursorWrapper extends CursorWrapper {
    public UserDataCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public UserData getUserData(){
        String uidString=getString(getColumnIndex(UserDataTable.Cols.UID));
        String username=getString(getColumnIndex(UserDataTable.Cols.USERNAME));
        int level=getInt(getColumnIndex(UserDataTable.Cols.LEVEL));
        int totalexp=getInt(getColumnIndex(UserDataTable.Cols.TOTALEXP));
        int totalmission=getInt(getColumnIndex(UserDataTable.Cols.TOTALMISSION));
        int missioncomp=getInt(getColumnIndex(UserDataTable.Cols.MISSIONCOMP));


        UserData userdata=new UserData(Integer.parseInt(uidString));

        userdata.setLevel(level);
        userdata.setMission_completed(missioncomp);
        userdata.setTotal_exp(totalexp);
        userdata.setTotal_mission(totalmission);
        userdata.setUserName(username);


        return userdata;
    }







}
