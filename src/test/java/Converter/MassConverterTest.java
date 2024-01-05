package Converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MassConverterTest {

    @Test
    void convertValueKGtoG() {
        MassConverter massConverter = new MassConverter();
        assertEquals(1000, massConverter.convertValue(1,"Kilogram (KG)","Gram (G)"));
    }

    @Test
    void convertValueMGtoT() {
        MassConverter massConverter = new MassConverter();
        assertEquals(1.0E-9, massConverter.convertValue(1,"Milligram (MG)","Tonne (T)"));
    }

    @Test
    void convertValueGtoMG() {
        MassConverter massConverter = new MassConverter();
        assertEquals(1000, massConverter.convertValue(1,"Gram (G)","Milligram (MG)"));
    }

    @Test
    void convertValueGtoP() {
        MassConverter massConverter = new MassConverter();
        assertEquals(0.002204585537918871, massConverter.convertValue(1,"Gram (G)","Pound (£)"));
    }

}