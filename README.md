# MaterialCalendarView
[ ![Download](https://api.bintray.com/packages/hachimann/MaterialCalendarView/material-calendarview/images/download.svg) ](https://bintray.com/hachimann/MaterialCalendarView/material-calendarview/_latestVersion)
[![JitPack](https://jitpack.io/v/Hachimann/MaterialCalendarView.svg)](https://jitpack.io/#Hachimann/MaterialCalendarView)

Material Calendar View for Android. Improved Material CalendarView from [Prolific Interactive](https://github.com/prolificinteractive/material-calendarview).

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

## Documentation

Make sure to check all the documentation available [here](https://github.com/prolificinteractive/material-calendarview/wiki).

## Customization

One of the aims of this library is to be customizable. The many options include:

* [Define the view's width and height in terms of tile size](https://github.com/prolificinteractive/material-calendarview/wiki/Customization#tile-size)
* [Single or Multiple date selection, or disabling selection entirely](https://github.com/prolificinteractive/material-calendarview/wiki/Customization#date-selection)
* [Showing dates from other months or those out of range](https://github.com/prolificinteractive/material-calendarview/wiki/Customization#showing-other-dates)
* [Setting the first day of the week](https://github.com/prolificinteractive/material-calendarview/wiki/Customization-Builder#first-day-of-the-week)
* [Show only a range of dates](https://github.com/prolificinteractive/material-calendarview/wiki/Customization-Builder#date-ranges)
* [Customize the top bar](https://github.com/prolificinteractive/material-calendarview/wiki/Customization#topbar-options)
* [Custom labels for the header, weekdays, or individual days](https://github.com/prolificinteractive/material-calendarview/wiki/Customization#custom-labels)


### Events, Highlighting, Custom Selectors, and More!

All of this and more can be done via the decorator api. Please check out the [decorator documentation](https://github.com/prolificinteractive/material-calendarview/wiki/Decorators).

## License

Material Calendar View is Copyright (c) 2020 Prolific Interactive. It may be redistributed under the terms specified in the [LICENSE] file.

[LICENSE]: /LICENSE

## Maintainers

Material Calendar View is maintained and funded by Prolific Interactive. The names and logos are trademarks of Prolific Interactive.
