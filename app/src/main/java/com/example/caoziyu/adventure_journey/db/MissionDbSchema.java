package com.example.caoziyu.adventure_journey.db;

/**
 * Created by admin on 2017/6/8.
 */

public class MissionDbSchema {
    public static final class MissionTable{
        public static final String NAME ="missions";
        public static final class Cols {
            public static final String SMID = "smid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "discription";
            public static final String DATE = "date";
            public static final String DISTANCE = "distance";
            public static final String AVGSPEED = "avgspeed";
            public static final String DURATIONTIME = "durationtime";
            public static final String COMPLETED = "completed";
        }
    }
}
