package Converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    @Test
    void convertValueCtoF() {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        assertEquals(33.8, temperatureConverter.convertValue(1,"Celsius","Fahrenheit"));
    }


    @Test
    void convertValueCtoK() {
        TemperatureConverter temperatureConverter = new TemperatureConverter();
        assertEquals(274.15, temperatureConverter.convertValue(1,"Celsius","Kelvin"));
    }
}