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

import java.text.Format;
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
    private HashMap<String, List<Date>> xAxisByProbes;
    private HashMap<String, List<Long>> xAchse;
    private HashMap<String, List<Double>> yAxisByProbes;

    private OnFragmentInteractionListener mListener;
    private LineChart mChart;
    private final List<Integer> colors = Arrays.asList(Color.BLACK, Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN, Color.MAGENTA, Color.rgb(255, 165, 0), Color.rgb(0, 100, 0), Color.DKGRAY, Color.LTGRAY, Color.CYAN);
    private LineData data;
    private ArrayList<Entry> yVals;
    //private ArrayList<Measurement> msms;
    ArrayList<String> xVals;
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
        /* Old try to draw the chart
        setChartData();
        setResultsData();
        // Define ListView and Adapter
        final ListView listView = view.findViewById(R.id.list_view_measurements);
        msms = new ArrayList<>();
        getMsm();
        //adapter = new ListViewFragmentAdapter(inflater, msms);
        listView.setAdapter(adapter);
         */

        // Inflate the layout for this fragment
        return view;
    }

    /*private void getMsm() {
        String apiKey = "289fa4ce-30e9-47f3-a557-3c4bb94cd4ff";
        String suffixURL = "/measurements/my" + "/?key=" + apiKey;
        WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {
                    Log.i("getRequest\tgetResult\t", result);
                    //Gson gson = new GsonBuilder().setDateFormat(DateFormat.LONG).create(); //setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create(); //registerTypeAdapter(Date.class, new DateDeserializer()).create();
                    // Creates the json object which will manage the information received
                    GsonBuilder builder = new GsonBuilder();

                    // Register an adapter to manage the date types as long values
                    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Date(json.getAsJsonPrimitive().getAsLong());
                        }
                    });

                    Gson gson = builder.create();

                    JSONObject jsonResult;
                    try {
                        jsonResult = new JSONObject(result);
                        JSONArray results = jsonResult.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            Measurement msm = gson.fromJson(results.getJSONObject(i).toString(), Measurement.class);
                            msms.add(msm);
                        }
                        for (int i = 10; i < 15; i++) {
                            values.add(new Entry(i, 220 + i));
                        }
                        LineDataSet set1;
                        if (mChart.getData() != null &&
                                mChart.getData().getDataSetCount() > 0) {
                            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                            set1.setValues(values);
                            mChart.getData().notifyDataChanged();
                            mChart.notifyDataSetChanged();
                        }

                        // SharedPreferences: Save results to MeasurementList
                        //ArrayListAdapter.saveMsmArrayList(msms, "TEST", getContext());
                        //adapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setResultsData() {
        String suffixURL = mMsm.getResult().substring(mMsm.getResult().indexOf("/m"));

        suffixURL += "?key=7d1b8318-874e-4c2b-8f78-1f267cbed62f";

        WebConnect.getInstance().getRequestReturningString(suffixURL, new CustomListener<String>() {
            @Override
            public void getResult(String result) {
                if (!result.isEmpty()) {
                    Log.i("getRequest\tgetResult\t", result);
                    xAxisByProbes = new HashMap<>();
                    yAxisByProbes = new HashMap<>();
                    try {
                        JSONObject jsonResult = new JSONObject(result);
                        JSONArray results = jsonResult.getJSONArray("results");
                        for (int i = 0; i < results.length(); i++) {
                            JSONObject object = results.getJSONObject(i);
                            String prbID = object.get("prb_id").toString();
                            if (xAxisByProbes.containsKey(prbID)) {
                                List<Date> tmp = xAxisByProbes.get(prbID);
                                tmp.add(new Date((Long) object.get("timestamp")));
                                xAxisByProbes.put(prbID, tmp);
                            } else {
                                List<Date> tmp = new ArrayList<>();
                                tmp.add(new Date((Long) object.get("timestamp")));
                                xAxisByProbes.put(prbID, tmp);
                            }
                            if (yAxisByProbes.containsKey(prbID)) {
                                List<Double> tmp = yAxisByProbes.get(prbID);
                                tmp.add((Double) object.get("avg"));
                                yAxisByProbes.put(prbID, tmp);
                            } else {
                                List<Double> tmp = new ArrayList<>();
                                tmp.add((Double) object.get("avg"));
                                yAxisByProbes.put(prbID, tmp);
                            }
                        }
                        mChart.notifyDataSetChanged();
                        mChart.invalidate();
                        Iterator<String> itr = yAxisByProbes.keySet().iterator();
                        //in oncreate
                        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
                        while (itr.hasNext()) {
                            final String key = itr.next();
                            //ArrayList<Entry> yVals = setYAxisValues(key);
                            yVals = setYAxisValues(key);
                            LineDataSet set = new LineDataSet(yVals, "ProbeID" + key);
                            //final ArrayList<String> xVals = setXAxisValues(key);
                            xVals = setXAxisValues(key);
                            XAxis xAxis = mChart.getXAxis();
                            xAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    return xVals.get((int) value);
                                }
                            });
                            set.setFillAlpha(110);
                            set.setColor(colors.get(itr.hashCode()));
                            set.setCircleColor(colors.get(itr.hashCode()));
                            set.setLineWidth(1f);
                            set.setDrawCircleHole(false);
                            set.setValueTextSize(9f);
                            set.setDrawFilled(true);
                            dataSets.add(set);
                        }
                        data = new LineData(dataSets);
                        mChart.setData(data);
                        mChart.animateX(1500);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void setChartData() {
        if (yAxisByProbes != null) {
            Iterator<String> itr = yAxisByProbes.keySet().iterator();
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            while (itr.hasNext()) {
                final String key = itr.next();
                ArrayList<Entry> yVals = setYAxisValues(key);
                LineDataSet set = new LineDataSet(yVals, "ProbeID" + key);
                final ArrayList<String> xVals = setXAxisValues(key);
                XAxis xAxis = mChart.getXAxis();
                xAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getFormattedValue(float value) {
                        return xVals.get((int) value);
                    }
                });
                set.setFillAlpha(110);
                set.setColor(colors.get(itr.hashCode()));
                set.setCircleColor(colors.get(itr.hashCode()));
                set.setLineWidth(1f);
                set.setDrawCircleHole(false);
                set.setValueTextSize(9f);
                set.setDrawFilled(true);
                dataSets.add(set);
            }
            LineData data = new LineData(dataSets);
            mChart.setData(data);
            // draw points over time
            mChart.animateX(1500);
        } else {
            LineDataSet set1 = new LineDataSet(yVals, "DataSet 1");

            XAxis xAxis = mChart.getXAxis();
            xAxis.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value) {
                    return xVals.get((int) value);
                }
            });

            set1.setFillAlpha(110);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(9f);
            set1.setDrawFilled(true);
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            data = new LineData(dataSets);
            mChart.setData(data);
            // draw points over time
            mChart.animateX(1500);
        }
    }*/

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
                    long x = Objects.requireNonNull(xAchse.get(prbID)).get(i);
                    //yVals.add(new Entry(i, d));
                    yVals.add(new Entry(x, d));
                }
            }
        } else {
            yVals.add(new Entry(0, 60));
            yVals.add(new Entry(1, 48));
            yVals.add(new Entry(2, 70.5f));
            yVals.add(new Entry(3, 100));
            yVals.add(new Entry(4, 180.9f));
        }
        return yVals;
    }

    private ArrayList<String> setXAxisValues(String prbID) {
        ArrayList<String> xVals = new ArrayList<>();
        if (xAxisByProbes != null) {
            if (xAxisByProbes.get(prbID) != null) {
                Format formatter = new SimpleDateFormat("MM/dd HH:mm", Locale.ENGLISH);
                // only the last 11 results
                int tmp = AMOUNT_OF_RESULTS;
                if (Objects.requireNonNull(xAxisByProbes.get(prbID)).size() < AMOUNT_OF_RESULTS) {
                    tmp = Objects.requireNonNull(xAxisByProbes.get(prbID)).size();
                }
                for (int i = 0; i < tmp; i++) {
                    xVals.add(formatter.format(Objects.requireNonNull(xAxisByProbes.get(prbID)).get(i)));
                }
            }
        } else {
            xVals.add("Eins");
            xVals.add("Zwei");
            xVals.add("Drei");
            xVals.add("Vier");
            xVals.add("Fünf");
        }
        return xVals;
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


        LimitLine ll1 = new LimitLine(500f, "bad rtt");
        ll1.setLineWidth(4f);
        ll1.enableDashedLine(10f, 10f, 0f);
        ll1.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        ll1.setTextSize(10f);

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
                xAxisByProbes = new HashMap<>();
                xAchse = new HashMap<>();
                yAxisByProbes = new HashMap<>();
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String prbID = object.get("prb_id").toString();
                        if (xAxisByProbes.containsKey(prbID)) {
                            List<Date> tmp = xAxisByProbes.get(prbID);
                            Date date = new Date(object.getLong("timestamp"));
                            Objects.requireNonNull(tmp).add(date);
                            xAxisByProbes.put(prbID, tmp);
                        } else {
                            List<Date> tmp = new ArrayList<>();
                            Date date = new Date(object.getLong("timestamp"));
                            tmp.add(date);
                            xAxisByProbes.put(prbID, tmp);
                        }
                        if (xAchse.containsKey(prbID)) {
                            List<Long> tmp = xAchse.get(prbID);
                            long l = object.getLong("timestamp");
                            Objects.requireNonNull(tmp).add(l);
                            xAchse.put(prbID, tmp);
                        } else {
                            List<Long> tmp = new ArrayList<>();
                            long l = object.getLong("timestamp");
                            tmp.add(l);
                            xAchse.put(prbID, tmp);
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
                    //xVals = setXAxisValues(key);
                    //XAxis xAxis = mChart.getXAxis();
                    //    Log.i("xAxis", xAxis.toString());
                  /*  xAxis.setValueFormatter(new ValueFormatter() {
                        @Override
                        public String getFormattedValue(float value) {
                            return xVals.get((int) value);
                        }
                    });*/
                    set.setFillAlpha(110);
                    set.setLineWidth(1f);
                    set.setValueTextSize(9f);
                    set.setDrawCircleHole(false);
                    // Performance Boost
                    set.setDrawFilled(false);
                    //TODO: test algorithm and check how many else !!!
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
                    /*mChart.animateX(1500);
                    values.add(new Entry(i, 220 + i));
                    LineDataSet set1;
                    if (mChart.getData() != null &&
                            mChart.getData().getDataSetCount() > 0) {
                        set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
                        set1.setValues(values);
                        mChart.getData().notifyDataChanged();
                        mChart.notifyDataSetChanged();
                    }*/
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
