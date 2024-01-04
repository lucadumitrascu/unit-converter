package Converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeConverterTest {

    @Test
    void convertValueWeekToDay() {
        TimeConverter timeConverter = new TimeConverter();
        assertEquals(7, timeConverter.convertValue(1,"Week","Day"));
    }

    @Test
    void convertValueDayToHour() {
        TimeConverter timeConverter = new TimeConverter();
        assertEquals(24, timeConverter.convertValue(1,"Day","Hour"));
    }

    @Test
    void convertValueHourToMinute() {
        TimeConverter timeConverter = new TimeConverter();
        assertEquals(60, timeConverter.convertValue(1,"Hour","Minute"));
    }

    @Test
    void convertValueMonthToDay() {
        TimeConverter timeConverter = new TimeConverter();
        assertEquals(30.417, timeConverter.convertValue(1,"Month","Day"));
    }
}