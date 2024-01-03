package Converter;

import java.util.Scanner;

public interface UnitConverter {
    void run();
    double convertValue(double value, String fromUnit, String toUnit);
    void showHistory();
}
