package com.example.caoziyu.adventure_journey;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.caoziyu.adventure_journey.db.Mission;
import com.example.caoziyu.adventure_journey.db.MissionLab;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MissionSelectActivity extends AppCompatActivity
{




    private Context mcontext=this;
    private List<Mission> mMissionsList = new ArrayList<>();
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_select);
        toolbar = (Toolbar) findViewById(R.id.toolbar_mission_select);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.mission_select_ctoolbar);
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        initMissions();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mission_select_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MissionAdapter adapter = new MissionAdapter(mMissionsList);
        recyclerView.setAdapter(adapter);
    }



    private void initMissions()
    {
        MissionLab missionlab=MissionLab.get(mcontext);
        if(missionlab.getMissions().size()!=0){
            Log.d("InitMissions","Initial failed!");

        }
        else {
            Log.d("InitMissions","Initial succeed!");
            for (int i = 1; i <= 21; ++i) {
                Mission m = new Mission();
                m.setDate(new Date());
                switch (i) {
                    case 1:
                        m.setSmid(i);
                        m.setTitle("Chase those Goblin!");
                        m.setDescription("Theft can not be forgiven!\nChase those sneaky Goblins!");
                        m.setDistance(3.5);
                        m.setDur_time(40);
                        m.setAvgspeed(1.2);
                        m.setCompleted(0);
                        missionlab.addMission(m);
                        break;
                    case 2:
                        m.setSmid(i);
                        m.setTitle("Guard Route");
                        m.setDescription("Protect the princess Christina.\nWe must achieve the habour!");
                        m.setDistance(6.0);
                        m.setDur_time(90);
                        m.setAvgspeed(1.0);
                        m.setCompleted(0);
                        missionlab.addMission(m);
                        break;
                    case 3:
                        m.setSmid(i);
                        m.setTitle("Escape from Dragon!");
                        m.setDescription("The nest of Dragon...OHHHH No!That\nMonster find us___Run!!!");
                        m.setDistance(2);
                        m.setDur_time(15);
                        m.setAvgspeed(4.2);
                        m.setCompleted(0);
                        missionlab.addMission(m);
                        break;
                    default:
                        m.setSmid(i);
                        m.setTitle("Coming Soon...");
                        m.setDescription("Wait for new Missions!");
                        missionlab.addMission(m);
                        break;

                }
            }
        }
        mMissionsList=missionlab.getMissions();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
