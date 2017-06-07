package com.example.caoziyu.adventure_journey;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class MissionFragment extends Fragment
{
    private List<Integer> mMissionsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mission_fragment, container, false);

        initMissions();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.mission_missions_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        MissionAdapter adapter = new MissionAdapter(mMissionsList);
        recyclerView.setAdapter(adapter);
        return view;
    }

    private void initMissions()
    {
        for (int i =0; i<21; ++i)
        {
            Integer a = i;
            mMissionsList.add(a);
        }
    }
}
