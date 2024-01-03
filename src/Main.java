import Converter.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        UnitConverter unitConverter = new TemperatureConverter();
        unitConverter.run();
        UnitConverter unitConverter1 = new LengthConverter();
        unitConverter1.run();
        UnitConverter unitConverter2 = new MassConverter();
        unitConverter2.run();
        UnitConverter unitConverter3 = new VolumeConverter();
        unitConverter3.run();
        UnitConverter unitConverter4 = new TimeConverter();
        unitConverter4.run();

    }
}