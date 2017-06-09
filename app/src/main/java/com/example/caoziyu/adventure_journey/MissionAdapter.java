package com.example.caoziyu.adventure_journey;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.caoziyu.adventure_journey.db.Mission;

import java.util.List;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder>
{
    private List<Mission> mMissionsList;

    static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView missionName;
        TextView missionDescription;

        public ViewHolder(View view)
        {
            super(view);
            missionName = (TextView) view.findViewById(R.id.mission_item_name);
            missionDescription = (TextView) view.findViewById(R.id.mission_item_description);
        }
    }

    public MissionAdapter(List<Mission> mList)
    {
        mMissionsList = mList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.missions_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        Mission mission=mMissionsList.get(position);
        holder.missionName.setText("Mission " + mission.getSmid()+" "+mission.getTitle());
        holder.missionDescription.setText(mission.getDecription());//description
    }

    @Override
    public int getItemCount()
    {
        return mMissionsList.size();
    }

    public void setmMissionsList(List<Mission> missions){
        mMissionsList=missions;
    }
}
