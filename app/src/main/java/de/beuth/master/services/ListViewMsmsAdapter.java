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