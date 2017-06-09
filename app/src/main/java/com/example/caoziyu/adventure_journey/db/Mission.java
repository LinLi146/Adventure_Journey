package com.example.caoziyu.adventure_journey.db;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/6/8.
 */

public class Mission {
    private int smid;
    private String title;
    private String description;
    private Date date;
    private double distance;
    private double avgspeed;
    private int durationtime;
    private boolean completed;

    public Mission(){
        smid=0;
        title="Coming Soon...";
        description="Wait for new Missions!";
        date= new Date(2017,6,9,9,56);
        distance=5.2;
        avgspeed=1.3;
        durationtime=35;
        completed=true;
    }


    public Mission(int id){
        smid=id;
        title="Tutorial";
        description="Just a tutorial for new players.";
        date=new Date(2017,6,9,9,56);
    }





    public int getSmid(){
        return smid;
    }

    public void setSmid(int id){
        smid=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String t){
        title=t;
    }



    public String getDecription(){
        return description;
    }

    public void setDescription(String d){
        description=d;
    }


    public int getDur_time(){
        return durationtime;
    }

    public void setDur_time(int dt){
        durationtime=dt;
    }


    public double getDistance(){
        return distance;
    }

    public void setDistance(double d){
        distance=d;
    }

    public double getAvg_speed(){
        return avgspeed;
    }

    public void setAvgspeed(double as){
        avgspeed=as;
    }



    public Date getDate(){
        return date;
    }

    public void setDate(Date d){
        date=d;
    }

    public boolean isCompleted(){
        return completed;
    }

    public void setCompleted(int c){
        if(c==0)completed=false;
        else completed=true;
    }


}
