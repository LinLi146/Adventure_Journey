package com.example.caoziyu.adventure_journey;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class HomeFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.home_fragment, container, false);
    }

}
