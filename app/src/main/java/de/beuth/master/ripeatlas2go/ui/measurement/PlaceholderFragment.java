package de.beuth.master.ripeatlas2go.ui.measurement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.beuth.master.classes.Measurement;
import de.beuth.master.ripeatlas2go.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String MSM = "measurement";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Measurement msm = (Measurement)getActivity().getIntent().getSerializableExtra(MSM);
        if (getArguments().getInt(ARG_SECTION_NUMBER) == 1) {
            ShowMeasurementFrame1 frame1 = ShowMeasurementFrame1.newInstance(msm);
            frame1.onCreate(savedInstanceState);
            return frame1.onCreateView(inflater,container,savedInstanceState);
        } else if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
            // View root = inflater.inflate(R.layout.fragment_show_measurement_frame2, container, false);
            // return root;
            ShowMeasurementFrame2 frame2 = ShowMeasurementFrame2.newInstance(msm);
            frame2.onCreate(savedInstanceState);
            return frame2.onCreateView(inflater,container,savedInstanceState);
        } else{
            View root = inflater.inflate(R.layout.fragment_activity_show_measurement, container, false);
            final TextView textView = root.findViewById(R.id.section_label);
            pageViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }
    }
}