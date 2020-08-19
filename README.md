# MaterialCalendarView
[![Download](https://api.bintray.com/hachimann/MaterialCalendarView/material-calendarview/images/download.svg) ](https://bintray.com/hachimann/MaterialCalendarView/material-calendarview/_latestVersion)
Material Calendar View for Android.

Improved Material CalendarView from [Prolific Interactive](https://github.com/prolificinteractive/material-calendarview).

## Features

- [x] Highlighting the current date.
- [x] Disabled ripple.
- [x] Editable size of selectors.

## Installation

Add the dependency

```groovy
dependencies {
  implementation 'com.github.hachimann:material-calendarview:${version}'
}
```

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
