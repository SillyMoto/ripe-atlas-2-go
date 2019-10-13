/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.beuth.master.services;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.ArrayList;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.R;

/**
 * a BaseAdapter for the Measurement ListView.
 * put Measurement data to the ListView of the MeasurementActivity.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class ListViewMsmsAdapter extends BaseAdapter {

    private  ArrayList<Measurement> msms;
    private Activity activity;

    public ListViewMsmsAdapter(Activity activity, ArrayList<Measurement> msms) {
        super();
        this.activity = activity;
        this.msms = msms;
    }

    @Override
    public int getCount() {
        return msms.size();
    }

    @Override
    public Object getItem(int position) {
        return msms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mId;
        TextView mType;
        TextView mStart;
        TextView mStop;
        TextView mProbes;
        TextView mStatus;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ListViewMsmsAdapter.ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_msms_row, null);
            holder = new ListViewMsmsAdapter.ViewHolder();
            holder.mId = convertView.findViewById(R.id.id);
            holder.mType = convertView.findViewById(R.id.type);
            holder.mStart = convertView
                    .findViewById(R.id.start);
            holder.mStop = convertView.findViewById(R.id.stop);
            convertView.setTag(holder);
            holder.mProbes = convertView.findViewById(R.id.probes);
            convertView.setTag(holder);
            holder.mStatus = convertView.findViewById(R.id.status);
        } else {
            holder = (ListViewMsmsAdapter.ViewHolder) convertView.getTag();
        }

        Measurement msm = msms.get(position);
        holder.mId.setText(String.valueOf(msm.getID()));
        holder.mType.setText(msm.getType());
        if(msm.getStartTime() != null) {
            holder.mStart.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(msm.getStartTime()));
        } else {
            holder.mStart.setText("-");
        }
        if(msm.getStopTime() != null) {
            holder.mStop.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(msm.getStopTime()));
        } else {
            holder.mStop.setText("-");
        }
        holder.mProbes.setText(String.valueOf(msm.getProbesRequested()));
        holder.mStatus.setText(msm.getStatus().getName());
        return convertView;
    }
}