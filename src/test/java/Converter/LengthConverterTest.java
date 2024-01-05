package Converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthConverterTest {

    @Test
    void convertValueKMtoM() {
        LengthConverter lengthConverter = new LengthConverter();
        assertEquals(1000, lengthConverter.convertValue(1,"Kilometer (KM)","Meter (M)"));
    }


    @Test
    void convertValueMToKM() {
        LengthConverter lengthConverter = new LengthConverter();
        assertEquals(0.001, lengthConverter.convertValue(1,"Meter (M)","Kilometer (KM)"));
    }


    @Test
    void convertValueCMtoM() {
        LengthConverter lengthConverter = new LengthConverter();
        assertEquals(0.01, lengthConverter.convertValue(1,"Centimeter (CM)","Meter (M)"));
    }

    @Test
    void convertValueDMtoMM() {
        LengthConverter lengthConverter = new LengthConverter();
        assertEquals(100, lengthConverter.convertValue(1,"Decimeter (DM)","Millimeter (MM)"));
    }

}