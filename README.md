# MaterialCalendarView
Material Calendar View for Android.

Improved Material CalendarView from [Prolific Interactive](https://github.com/prolificinteractive/material-calendarview).

## Features

- [x] Highlighting the current date.
- [x] Disabled ripple.
- [x] Editable size of selectors.

## Usage

Add MaterialCalendarView to your XML like any other view.

```xml
<com.github.hachimann.materialcalendarview.MaterialCalendarView
  android:id="@+id/calendarView"
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  app:mcv_firstDayOfWeek="monday"
  app:mcv_circlePadding="2dp"
  app:mcv_showOtherDates="out_of_range" />
```
