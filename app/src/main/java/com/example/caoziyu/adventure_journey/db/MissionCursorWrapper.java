package com.example.caoziyu.adventure_journey.db;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.caoziyu.adventure_journey.db.MissionDbSchema.MissionTable;

import java.util.Date;

/**
 * Created by admin on 2017/6/8.
 */

public class MissionCursorWrapper extends CursorWrapper {
    public MissionCursorWrapper(Cursor cursor){
        super(cursor);
    }

    public Mission getMission(){
        String smidString=getString(getColumnIndex(MissionTable.Cols.SMID));
        String title=getString(getColumnIndex(MissionTable.Cols.TITLE));
        String discript=getString(getColumnIndex(MissionTable.Cols.DESCRIPTION));
        long date=getLong(getColumnIndex(MissionTable.Cols.DATE));
        int isCompleted=getInt(getColumnIndex(MissionTable.Cols.COMPLETED));
        double distance=getDouble(getColumnIndex(MissionTable.Cols.DISTANCE));
        int durationtime=getInt(getColumnIndex(MissionTable.Cols.DURATIONTIME));
        double avgspeed=getDouble(getColumnIndex(MissionTable.Cols.AVGSPEED));

        Mission mission=new Mission(Integer.parseInt(smidString));


        mission.setTitle(title);
        mission.setDate(new Date(date));
        mission.setDescription(discript);
        mission.setAvgspeed(avgspeed);
        mission.setCompleted(isCompleted);
        mission.setDistance(distance);
        mission.setDur_time(durationtime);

        return mission;
    }
    
}