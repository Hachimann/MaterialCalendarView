package com.example.materialcalendarview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.hachimann.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialCalendarView materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        materialCalendarView.state().edit()
                .isCacheCalendarPositionEnabled(true)
                .commit();
    }
}