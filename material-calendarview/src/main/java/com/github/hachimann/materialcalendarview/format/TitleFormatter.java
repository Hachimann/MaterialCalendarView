package com.github.hachimann.materialcalendarview.format;

import com.github.hachimann.materialcalendarview.CalendarDay;

public interface TitleFormatter {

  String DEFAULT_FORMAT = "LLLL yyyy";

  TitleFormatter DEFAULT = new DateFormatTitleFormatter();

  /**
   * Converts the supplied day to a suitable month/year title
   *
   * @param day the day containing relevant month and year information
   * @return a label to display for the given month/year
   */
  CharSequence format(CalendarDay day);
}
