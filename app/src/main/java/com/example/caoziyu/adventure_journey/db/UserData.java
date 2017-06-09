package com.example.caoziyu.adventure_journey.db;

import java.util.logging.Level;

/**
 * Created by admin on 2017/6/9.
 */

public class UserData {
    private int Uid;
    private String UserName;
    private int level;
    private int total_exp;
    private int total_mission;
    private int mission_completed;

    public int getLevel() {
        return level;
    }

    public int getMission_completed() {
        return mission_completed;
    }

    public int getTotal_exp() {
        return total_exp;
    }

    public int getTotal_mission() {
        return total_mission;
    }

    public int getUid() {
        return Uid;
    }

    public String getUserName() {
        return UserName;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setMission_completed(int mission_completed) {
        this.mission_completed = mission_completed;
    }

    public void setTotal_exp(int total_exp) {
        this.total_exp = total_exp;
    }

    public void setTotal_mission(int total_mission) {
        this.total_mission = total_mission;
    }

    public void setUid(int uid) {
        Uid = uid;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }


    public UserData() {
        Uid=10;
        UserName="Dawn414";
        level=2;
        total_exp=5673;
        total_mission=21;
        mission_completed=3;
    }


    public UserData(int id) {
        Uid=id;
        total_mission=21;
    }

    public void addexp(int exp){
        total_exp+=exp;
    }

    public void addmissionComplete(){
        mission_completed++;
    }



    @Override
    public String toString() {
        return super.toString();
    }
}
