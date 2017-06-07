package com.example.caoziyu.adventure_journey;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CaoZiyu on 2017/6/7.
 */

public class MissionAdapter extends RecyclerView.Adapter<MissionAdapter.ViewHolder>
{
    private List<Integer> mMissionsList;

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

    public MissionAdapter(List<Integer> mList)
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
        Integer num = mMissionsList.get(position);
        holder.missionName.setText("Mission " + num);
        holder.missionDescription.setText("Description " + num);
    }

    @Override
    public int getItemCount()
    {
        return mMissionsList.size();
    }
}
