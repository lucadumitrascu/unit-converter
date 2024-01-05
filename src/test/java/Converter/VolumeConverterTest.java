package Converter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VolumeConverterTest {

    @Test
    void convertValueM3toL() {
        VolumeConverter volumeConverter = new VolumeConverter();
        assertEquals(1000, volumeConverter.convertValue(1,"Cubic meter (m3)","Liter (L)"));
    }

    @Test
    void convertValueDLtoL() {
        VolumeConverter volumeConverter = new VolumeConverter();
        assertEquals(0.1, volumeConverter.convertValue(1,"Deciliter (dL)","Liter (L)"));
    }

}