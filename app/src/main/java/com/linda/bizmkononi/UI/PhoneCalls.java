package com.linda.bizmkononi.UI;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.linda.bizmkononi.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhoneCalls#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhoneCalls extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    BarChart barChart;
    View view;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PhoneCalls() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhoneCalls.
     */
    // TODO: Rename and change types and number of parameters
    public static PhoneCalls newInstance(String param1, String param2) {
        PhoneCalls fragment = new PhoneCalls();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_phone_calls, container, false);


        barChart = view.findViewById(R.id.barChart);
        ArrayList<BarEntry> entries = new ArrayList<>();

        entries.add(new BarEntry(2010,350));

        entries.add(new BarEntry(2011,475));

        entries.add(new BarEntry(2012,570));

        entries.add(new BarEntry(2013,650));

        entries.add(new BarEntry(2014,211));

        entries.add(new BarEntry(2015,855));

        entries.add(new BarEntry(2016,360));

        entries.add(new BarEntry(2017,660));

        BarDataSet barDataSet = new BarDataSet(entries, "Entries");

        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        barDataSet.setValueTextColor(Color.BLACK);

        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);

        barChart.setData(barData);

        barChart.getDescription().setText("Custom Bar Chart Example");

        barChart.animateY(2000);

        return view;
    }
    }
