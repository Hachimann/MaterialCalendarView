package com.example.materialcalendarview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.github.hachimann.materialcalendarview.CalendarDay;
import com.github.hachimann.materialcalendarview.MaterialCalendarView;
import com.github.hachimann.materialcalendarview.OnDateSelectedListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements OnDateSelectedListener {

    MaterialCalendarView materialCalendarView;
    private List<CalendarDay> selectedDates = new ArrayList<>();
    public boolean[] checkedDates = new boolean[7];
    List<Integer> daysOfWeek = new ArrayList<>();
    int weekIdentifier = 0;
    boolean isSwitchChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        materialCalendarView = findViewById(R.id.calendarView);
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_MULTIPLE);
        materialCalendarView.setOnDateChangedListener(this);
        materialCalendarView.state().edit()
                .isCacheCalendarPositionEnabled(true)
                .commit();

        SharedPreferences mPrefs = getSharedPreferences("com.example.materialcalendarview", MODE_PRIVATE);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();
        String json = mPrefs.getString("datesController", "");

        SwitchCompat alternationSwitch = findViewById(R.id.alternation_switch);
        Calendar calendar = localDateToDate(CalendarDay.today().getDate());
        int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);

        SelectedDatesController datesController = gson.fromJson(json, SelectedDatesController.class);
        if (datesController != null) {
            selectedDates = datesController.selectedDates;
            checkedDates = datesController.checkedDates;
            isSwitchChecked = datesController.isSwitchChecked;
            for (int counter = 0; counter < 7; counter++) {
                if (checkedDates[counter]) {
                    daysOfWeek.add(counter + 1);
                }
            }
            if (isSwitchChecked) {
                alternationSwitch.setChecked(true);
                if (weekOfYear % 2 == 0) {
                    weekIdentifier = 2;
                } else {
                    weekIdentifier = 1;
                }
            }
            materialCalendarView.selectDaysOfWeek(daysOfWeek, 0, weekIdentifier);
        }

        for (CalendarDay calendarDay : selectedDates
        ) {
            if (daysOfWeek.contains(calendarDay.getDate().getDayOfWeek().getValue())) {
                materialCalendarView.setDateSelected(calendarDay, (!selectedDates.contains(calendarDay)));
            }
            if (!daysOfWeek.contains(calendarDay.getDate().getDayOfWeek().getValue())) {
                materialCalendarView.setDateSelected(calendarDay, (selectedDates.contains(calendarDay)));
            }
        }

        final GridView gridView = findViewById(R.id.calendar_grid);
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        gridView.setAdapter(new DayAdapter(this, days));

        alternationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                isSwitchChecked = true;
                if (weekOfYear % 2 == 0) {
                    weekIdentifier = 2;
                } else {
                    weekIdentifier = 1;
                }
            } else {
                isSwitchChecked = false;
                weekIdentifier = 0;
            }
            materialCalendarView.selectDaysOfWeek(daysOfWeek, 0, weekIdentifier);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        materialCalendarView.selectDaysOfWeek(daysOfWeek, 0, weekIdentifier);
    }

    public static Calendar localDateToDate(LocalDate localDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth());
        return calendar;
    }

    public class DayAdapter extends BaseAdapter {
        private final Context context;
        private final String[] days;

        public DayAdapter(Context context, String[] days) {
            this.context = context;
            this.days = days;
        }

        @SuppressLint("ClickableViewAccessibility")
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            if (convertView == null) {

                gridView = inflater.inflate(R.layout.calendar_day, parent, false);

                TextView textView = gridView.findViewById(R.id.text);
                LinearLayout linearLayout = gridView.findViewById(R.id.linear_layout);

                textView.setText(days[position]);

                if (checkedDates[position]) {
                    linearLayout.setBackgroundResource(R.drawable.shape);
                    textView.setTextColor(getResources().getColor(R.color.textColorChecked));
                }

                gridView.setOnTouchListener((v, event) -> {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        if (!checkedDates[position]) {
                            linearLayout.setBackgroundResource(R.drawable.shape_touch);
                        }
                        return true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                        if (!checkedDates[position]) {
                            linearLayout.setBackgroundResource(0);
                        }
                        return true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        if (!checkedDates[position]) {
                            linearLayout.setBackgroundResource(R.drawable.shape);
                            textView.setTextColor(getResources().getColor(R.color.textColorChecked));

                            checkedDates[position] = true;
                            daysOfWeek.add(position + 1);
                        } else {
                            linearLayout.setBackgroundResource(0);
                            textView.setTextAppearance(MainActivity.this, R.style.TextAppearance_MaterialCalendarWidget_WeekDay);
                            checkedDates[position] = false;
                            daysOfWeek.remove((Integer) (position + 1));
                        }
                        materialCalendarView.selectDaysOfWeek(daysOfWeek, position + 1, weekIdentifier);
                        return true;
                    }
                    return false;
                });

            } else {
                gridView = convertView;
            }

            return gridView;
        }

        @Override
        public int getCount() {
            return days.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        selectedDates = widget.getSelectedDates();
    }

    @Override
    public void onPause() {
        super.onPause();
        selectedDates = materialCalendarView.getSelectedDates();
        SharedPreferences mPrefs;
        mPrefs = getSharedPreferences("com.example.materialcalendarview", MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        SelectedDatesController datesController = new SelectedDatesController(selectedDates, checkedDates, isSwitchChecked);
        String json = gson.toJson(datesController);
        prefsEditor.putString("datesController", json);
        prefsEditor.apply();
    }

    private static class SelectedDatesController {
        public List<CalendarDay> selectedDates;
        boolean[] checkedDates;
        boolean isSwitchChecked;

        public SelectedDatesController(List<CalendarDay> selectedDates, boolean[] checkedDates, boolean isSwitchChecked) {
            this.selectedDates = selectedDates;
            this.checkedDates = checkedDates;
            this.isSwitchChecked = isSwitchChecked;
        }
    }

    static class LocalDateSerializer implements JsonSerializer<LocalDate> {
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");

        @Override
        public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
            return new JsonPrimitive(formatter.format(localDate));
        }
    }

    static class LocalDateDeserializer implements JsonDeserializer<LocalDate> {
        @Override
        public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("d-MMM-yyyy").withLocale(Locale.ENGLISH));
        }
    }
}