package com.github.hachimann.materialcalendarview.format;

import com.github.hachimann.materialcalendarview.CalendarDay;

/**
 * Used to format a {@linkplain com.github.hachimann.materialcalendarview.CalendarDay} to a string for the month/year title
 */
public interface TitleFormatter {

    String DEFAULT_FORMAT = "MMMM yyyy";

    TitleFormatter DEFAULT = new DateFormatTitleFormatter();

    /**
     * Converts the supplied day to a suitable month/year title
     *
     * @param day the day containing relevant month and year information
     * @return a label to display for the given month/year
     */
    CharSequence format(CalendarDay day);
}
