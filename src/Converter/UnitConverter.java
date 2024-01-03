package Converter;

import java.util.Scanner;

public interface UnitConverter {
    void run();
    double convertValue(double value, String fromUnit, String toUnit);
    void showHistory();
    int getValidInt(Scanner scanner);
    double getValidDouble(Scanner scanner);
    int getValidOption(Scanner scanner);
    void showOptions();
}
