package com.example.caoziyu.adventure_journey;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by CaoZiyu on 2017/6/8.
 */

public class MissionModeFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.mission_mode_fragment, container, false);
        CardView storyMode = (CardView) view.findViewById(R.id.mission_mode_story);
        storyMode.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(),MissionSelectActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
