package com.linda.bizmkononi.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.linda.bizmkononi.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SalesInsights extends AppCompatActivity {

    @BindView(R.id.pieChart) PieChart pieChart;
    @BindView(R.id.daysOfTheWeek) Spinner daysOfTheWeek;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_insights);

        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> daysAdapter = ArrayAdapter.createFromResource(this,
                R.array.day_of_the_week, android.R.layout.simple_spinner_item);


        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daysOfTheWeek.setAdapter(daysAdapter);

        ArrayList<PieEntry> entries = new ArrayList<>();

        entries.add(new PieEntry(55,"Roses"));

        entries.add(new PieEntry(29,"Lillies"));

        entries.add(new PieEntry(20,"Daffoiles"));

        entries.add(new PieEntry(9,"Daisies"));



        PieDataSet pieDataSet = new PieDataSet(entries, "Entries");

        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS);

       // pieDataSet.setValueTextColor(colo);

        pieDataSet.setValueTextSize(7f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);

        pieChart.getDescription().setEnabled(false);

        pieChart.setCenterText("55% Roses");
        pieChart.setCenterTextColor(Color.BLUE);

        pieChart.animate();
    }
}