package com.example.caoziyu.adventure_journey;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.widget.TextView;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;
import com.example.caoziyu.adventure_journey.db.MissionLab;
import com.example.caoziyu.adventure_journey.db.UserData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class HomeFragment extends Fragment
{
    private CircleProgressView mCircleProgressView;
    private View card_now;
    private View card_news;
    private SwipeRefreshLayout swipeRefreshLayout;
    private Context mcontext;
    private List<UserData> userData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view = inflater.inflate(R.layout.home_fragment, container, false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.main_swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                onInitialAnimation();
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(300);
                        } catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (swipeRefreshLayout.isRefreshing()) {
                                    swipeRefreshLayout.setRefreshing(false);
                                }
                                Snackbar.make(view, "Refresh completed.", Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        mCircleProgressView = (CircleProgressView) view.findViewById(R.id.main_circle_progress);
        mCircleProgressView.setTextEnabled(true);
        mCircleProgressView.setStartAngle(90);
        mCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());

        card_now = view.findViewById(R.id.main_card_now);
        card_news = view.findViewById(R.id.main_card_news);
        mcontext = getActivity();
        userData = new ArrayList<>();
        onInitialAnimation();
        initUserData();
        TextView level = (TextView) view.findViewById(R.id.main_level);
        level.setText("LEVEL "+ userData.get(0).getLevel());
        return view;
    }


    public void onInitialAnimation()
    {
        Animation FadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        card_now.startAnimation(FadeIn);
        FadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                Animation FadeInLater = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_later);
                card_news.startAnimation(FadeInLater);
                mCircleProgressView.setProgress(0f);
                mCircleProgressView.setProgressWithAnimation(67, 600);
                mCircleProgressView.addAnimationListener(new ProgressAnimationListener()
                {
                    @Override
                    public void onValueChanged(float value)
                    {

                    }

                    @Override
                    public void onAnimationEnd()
                    {

                    }
                });

            }

            @Override
            public void onAnimationEnd(Animation animation)
            {

            }

            @Override
            public void onAnimationRepeat(Animation animation)
            {

            }
        });

    }

    public void initUserData(){//初始化用户信息
        Log.d("initUserData","Start Initiation");
        MissionLab missionLab=MissionLab.get(mcontext);
        if(missionLab.getUserdatas().size()!=0){}
        else {
            UserData ud=new UserData();
            missionLab.addUserdata(ud);
        }
        userData=missionLab.getUserdatas();
    }
}
