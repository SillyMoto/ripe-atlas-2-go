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

import de.beuth.master.classes.ApiKey;
import de.beuth.master.ripeatlas2go.R;

/**
 * a BaseAdapter for the API Keys ListView.
 * put api keys data to the ListView of the ApiKeysActivity.
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class ListViewApiKeysAdapter extends BaseAdapter {

    private  ArrayList<ApiKey> apiKeys;
    private Activity activity;

    public ListViewApiKeysAdapter(Activity activity, ArrayList<ApiKey> apiKeys) {
        super();
        this.activity = activity;
        this.apiKeys = apiKeys;
    }

    @Override
    public int getCount() {
        return apiKeys.size();
    }

    @Override
    public Object getItem(int position) {
       return apiKeys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mUuid;
        TextView mLabel;
        TextView mValidFrom;
        TextView mValidTo;
        TextView mEnabled;
    }

    @SuppressLint("InflateParams")
    @Override
     public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_api_keys_row, null);
            holder = new ViewHolder();
            holder.mUuid = convertView.findViewById(R.id.uuid);
            holder.mLabel = convertView.findViewById(R.id.label);
            holder.mValidFrom = convertView
                    .findViewById(R.id.valid_from);
            holder.mValidTo = convertView.findViewById(R.id.valid_to);
            convertView.setTag(holder);
            holder.mEnabled = convertView.findViewById(R.id.enabled);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        ApiKey apiKey = apiKeys.get(position);
        holder.mUuid.setText(apiKey.getUuid());
        holder.mLabel.setText(apiKey.getLabel());
        if(apiKey.getValidFrom() != null) {
            holder.mValidFrom.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(apiKey.getValidFrom()));
        } else {
            holder.mValidFrom.setText("-");
        }
        if(apiKey.getValidTo() != null) {
            holder.mValidTo.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(apiKey.getValidTo()));
        } else {
            holder.mValidTo.setText("-");
        }
        holder.mEnabled.setText(apiKey.getEnabled().toString());
        return convertView;
    }
}
