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
package de.beuth.master.ripeatlas2go.ui.measurement;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.R;
import de.beuth.master.services.CustomListener;
import de.beuth.master.services.WebConnect;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowMeasurementFrame2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowMeasurementFrame2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowMeasurementFrame2 extends Fragment {
    // the fragment initialization parameters
    private static final String MSM = "measurement";
    private static final Integer AMOUNT_OF_RESULTS = 11;

    private Measurement mMsm;
    private HashMap<String, List<Long>> xVals;
    private HashMap<String, List<Double>> yAxisByProbes;
    private ArrayList<Entry> yVals;

    private OnFragmentInteractionListener mListener;
    private LineChart mChart;
    private final List<Integer> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.rgb(255, 165, 0), Color.rgb(0, 100, 0), Color.DKGRAY, Color.LTGRAY, Color.CYAN);
    private LineData data;

    Context mContext;

    /**
     * empty public Constructor is required
     */
    public ShowMeasurementFrame2() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param msm Measurement
     * @return A new instance of fragment ShowMeasurementFrame2.
     */
    public static ShowMeasurementFrame2 newInstance(Measurement msm) {
        ShowMeasurementFrame2 fragment = new ShowMeasurementFrame2();
        Bundle args = new Bundle();
        args.putSerializable(MSM, msm);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMsm = (Measurement) getArguments().getSerializable(MSM);
        }
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        WebConnect.getInstance(getActivity());
        mContext = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_measurement_frame2, container, false);
        mChart = view.findViewById(R.id.line_chart1);
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        renderData();
        updateData();

        /* MarkerView for any Entry
        MarkerView mv = new MarkerView(mContext,  R.layout.custom_marker_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);
         */

        // Inflate the layout for this fragment
        return view;
    }

    private ArrayList<Entry> setYAxisValues(String prbID) {
        ArrayList<Entry> yVals = new ArrayList<>();
        if (yAxisByProbes != null) {
            if (yAxisByProbes.get(prbID) != null) {
                // only the last 11 results
                int tmp = AMOUNT_OF_RESULTS;
                if (Objects.requireNonNull(yAxisByProbes.get(prbID)).size() < AMOUNT_OF_RESULTS) {
                    tmp = Objects.requireNonNull(yAxisByProbes.get(prbID)).size();
                }
                for (int i = 0; i < tmp; i++) {
                    float d = Objects.requireNonNull(yAxisByProbes.get(prbID)).get(i).floatValue();
                    long x = Objects.requireNonNull(xVals.get(prbID)).get(i);
                    yVals.add(new Entry(x, d));
                }
            }
        }
        return yVals;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void renderData() {
        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(10f);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGridColor(colors.get(2));
        xAxis.setLabelRotationAngle(-45f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {

            private final SimpleDateFormat mFormat = new SimpleDateFormat("dd.MM.yy HH:mm", Locale.ENGLISH);

            @Override
            public String getFormattedValue(float value) {
                return mFormat.format(new Date((long) value * 1000));
            }
        });

        // Bad LimitLine
        LimitLine ll1 = new LimitLine(500f, "bad rtt");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

        // Good LimitLine
        LimitLine ll2 = new LimitLine(100f, "good rtt");
        ll2.setLineWidth(4f);
        ll2.enableDashedLine(10f, 10f, 0f);
        ll2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);
        ll2.setTextSize(10f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.addLimitLine(ll1);
        leftAxis.addLimitLine(ll2);
        leftAxis.setAxisMaximum(600f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);

        mChart.getLegend().setWordWrapEnabled(true);
        mChart.getDescription().setText("Probe ID");
        mChart.getAxisRight().setEnabled(false);
    }

    private void updateData() {
        // start string at position "m", the first part of URL is implemented in WebConnect
        String suffixURL = mMsm.getResult().substring(mMsm.getResult().indexOf("/m"));

        WebConnect.getInstance().getRequestReturningArray(suffixURL, new CustomListener<JSONArray, String>() {
            @Override
            public void getResult(JSONArray jsonArray) {
                xVals = new HashMap<>();
                yAxisByProbes = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String prbID = object.get("prb_id").toString();
                        if (xVals.containsKey(prbID)) {
                            List<Long> tmp = xVals.get(prbID);
                            long l = object.getLong("timestamp");
                            Objects.requireNonNull(tmp).add(l);
                            xVals.put(prbID, tmp);
                        } else {
                            List<Long> tmp = new ArrayList<>();
                            long l = object.getLong("timestamp");
                            tmp.add(l);
                            xVals.put(prbID, tmp);
                        }
                        if (yAxisByProbes.containsKey(prbID)) {
                            List<Double> tmp = yAxisByProbes.get(prbID);
                            double avg = 0;
                            if (mMsm.getType().equals("traceroute")) {// Traceroute
                                int lastHop = object.getJSONArray("result").length();
                                JSONArray array = object.getJSONArray("result").getJSONArray(lastHop - 1);
                                for (int j = 0; j < array.length(); j++) {
                                    avg += (Double) array.getJSONObject(j).get("rtt");
                                }
                                avg /= array.length();
                            } else {// PING
                                avg = object.getDouble("avg");
                            }
                            Objects.requireNonNull(tmp).add(avg);
                            yAxisByProbes.put(prbID, tmp);
                        } else {
                            List<Double> tmp = new ArrayList<>();
                            double avg = 0;
                            if (mMsm.getType().equals("traceroute")) {// Traceroute
                                int lastHop = object.getJSONArray("result").length();
                                JSONArray array = object.getJSONArray("result").getJSONObject(lastHop - 1).getJSONArray("result");
                                for (int j = 0; j < array.length(); j++) {
                                    avg += (Double) array.getJSONObject(j).get("rtt");
                                }
                                avg /= array.length();
                            } else {// PING
                                avg = object.getDouble("avg");
                            }
                            tmp.add(avg);
                            yAxisByProbes.put(prbID, tmp);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                Iterator<String> itr = yAxisByProbes.keySet().iterator();
                ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                int i = 0;
                while (itr.hasNext()) {
                    final String key = itr.next();
                    yVals = setYAxisValues(key);
                    LineDataSet set = new LineDataSet(yVals, key);
                    set.setFillAlpha(110);
                    set.setLineWidth(1f);
                    set.setValueTextSize(9f);
                    set.setDrawCircleHole(false);
                    // Performance Boost
                    set.setDrawFilled(false);

                    if (i < 11) {
                        set.setColor(colors.get(i));
                        set.setCircleColor(colors.get(i));
                        set.setForm(Legend.LegendForm.CIRCLE);
                    } else if (i < 22) {
                        set.setColor(colors.get(i - 11));
                        set.setCircleColor(colors.get(i - 11));
                        set.enableDashedLine(15, 5, 0);
                        set.setForm(Legend.LegendForm.LINE);
                    } else if (i < 33) {
                        set.setColor(colors.get(i - 22));
                        set.setCircleColor(colors.get(i - 22));
                        set.setDrawCircles(false);
                        set.setForm(Legend.LegendForm.SQUARE);
                    } else {
                        // TODO: another visualization
                        i = -1;
                    }
                    dataSets.add(set);
                    // Iterator for the Color
                    i++;
                }
                data = new LineData(dataSets);
                mChart.setData(data);
//                mChart.notify();
                mChart.notifyDataSetChanged();
                mChart.invalidate();
            }

            @Override
            public void getError(String error) {
                if(error != null){
                    Toast.makeText(mContext, error, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        //ADDED
        mContext = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
