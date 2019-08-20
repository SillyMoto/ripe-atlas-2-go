package de.beuth.master.ripeatlas2go.ui.measurement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.ActivityShowMeasurement;
import de.beuth.master.ripeatlas2go.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowMeasurementFrame1.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowMeasurementFrame1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowMeasurementFrame1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String MSM = "measurement";

    // TODO: Rename and change types of parameters
    private Measurement mMsm;

    private OnFragmentInteractionListener mListener;

    public ShowMeasurementFrame1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param msm Parameter 1.
     * @return A new instance of fragment ShowMeasurementFrame1.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowMeasurementFrame1 newInstance(Measurement msm) {
        ShowMeasurementFrame1 fragment = new ShowMeasurementFrame1();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_measurement_frame1, container, false);

        // Set Measurement data to TextViews
        TextView text = view.findViewById(R.id.overview_subtitle1_body1);
        String tmp = "Description: " + mMsm.getDescription();
        text.setText(tmp);
        text = view.findViewById(R.id.overview_subtitle1_body2);
        tmp = "IP Version: IPv" + mMsm.getAf();
        text.setText(tmp);
        text = view.findViewById(R.id.overview_subtitle1_body3);
        tmp = "Type: " + mMsm.getType();
        text.setText(tmp);
        text = view.findViewById(R.id.overview_subtitle2_body1);
        if(mMsm.getGroup() != null){
            tmp = "Group: " + mMsm.getGroup();
            text.setText(tmp);
            text = view.findViewById(R.id.overview_subtitle2_body2);
            tmp = "ID: " + mMsm.getGroupID();
            text.setText(tmp);
        } else {
            tmp = "is in Group: false";
            text.setText(tmp);
        }
        text = view.findViewById(R.id.overview_subtitle2_body3);
        tmp = "is wifi group: " + mMsm.getInWifiGroup();
        text.setText(tmp);
        text = view.findViewById(R.id.overview_subtitle3_body1);
        if(mMsm.getOneOff() != null){
            tmp = "One-off: true";
        }else{
            tmp = "Recurring: false";
        }
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle1_body1);
        tmp = "Target: " + mMsm.getTarget();
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle1_body2);
        tmp = "ASN: " + mMsm.getTargetASN();
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle1_body3);
        tmp = "Prefix: " + mMsm.getTargetPrefix();
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle1_body4);
        tmp = "IP Address: " + mMsm.getTargetIP();
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle1_body5);
        tmp = "All resolved IPs: " + mMsm.getResolvedIps().toString();
        text.setText(tmp);
        text = view.findViewById(R.id.target_subtitle2_body1);
        tmp = "Resolved on Probe: " + mMsm.getResolveOnProbe().toString();
        text.setText(tmp);

        // TODO:  Specific Data for Type
        text = view.findViewById(R.id.specific_subtitle1);
        text.setText("Specific Data");
        text = view.findViewById(R.id.specific_subtitle1_body1);
        text.setText("bla bla");

        text = view.findViewById(R.id.status_subtitle1_body1);
        tmp = "Status: " + mMsm.getStatus().getName();
        text.setText(tmp);
        text = view.findViewById(R.id.status_subtitle2_body1);
        tmp = "System Creation Time: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(mMsm.getCreationTime());
        text.setText(tmp);
        text = view.findViewById(R.id.status_subtitle3_body1);
        tmp = "Start Time: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(mMsm.getStartTime());
        text.setText(tmp);
        text = view.findViewById(R.id.status_subtitle3_body2);
        tmp =  "Stop Time: " + DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM).format(mMsm.getStopTime());
        text.setText(tmp);
        text = view.findViewById(R.id.status_subtitle4_body1);
        tmp = "Interval: " + mMsm.getInterval();
        text.setText(tmp);
        text = view.findViewById(R.id.status_subtitle4_body2);
        tmp = "Spread: " + mMsm.getSpread();
        text.setText(tmp);
        text = view.findViewById(R.id.probes_subtitle1_body1);
        tmp = "Requested: " + mMsm.getProbesRequested();
        text.setText(tmp);
        text = view.findViewById(R.id.probes_subtitle1_body2);
        tmp = "Scheduled: " + mMsm.getProbesScheduled();
        text.setText(tmp);
        text = view.findViewById(R.id.probes_subtitle1_body3);
        tmp = "Actually Participated: " + mMsm.getParticipantCount();
        text.setText(tmp);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
