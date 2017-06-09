package com.example.caoziyu.adventure_journey;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.eralp.circleprogressview.CircleProgressView;
import com.eralp.circleprogressview.ProgressAnimationListener;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class HomeFragment extends Fragment
{
    private CircleProgressView mCircleProgressView;
    private View card_now;
    private View card_news;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        final View view  = inflater.inflate(R.layout.home_fragment, container, false);

        mCircleProgressView = (CircleProgressView) view.findViewById(R.id.main_circle_progress);
        mCircleProgressView.setTextEnabled(true);
        mCircleProgressView.setInterpolator(new AccelerateDecelerateInterpolator());
        mCircleProgressView.setStartAngle(90);

        card_now = view.findViewById(R.id.main_card_now);
        card_news = view.findViewById(R.id.main_card_news);
        Animation FadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
        card_now.startAnimation(FadeIn);
        FadeIn.setAnimationListener(new Animation.AnimationListener()
        {
            @Override
            public void onAnimationStart(Animation animation)
            {
                mCircleProgressView.setProgressWithAnimation(65, 600);
                Animation FadeInLater = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in_later);
                card_news.startAnimation(FadeInLater);

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

        mCircleProgressView.addAnimationListener(new ProgressAnimationListener() {
            @Override
            public void onValueChanged(float value) {

            }

            @Override
            public void onAnimationEnd() {
                Snackbar.make(view, "Animation of CircleProgressView done", Snackbar.LENGTH_SHORT).show();
            }
        });
        return  view;
    }

}
