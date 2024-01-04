package Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class VolumeConverter extends AbstractUnitConverter {

    private final ArrayList<String> unitList;
    private final ArrayList<String> conversionHistory;

    public VolumeConverter() {
        this.unitList = new ArrayList<>(Arrays.asList(
                "Cubic meter (m3)",
                "Liter (L)",
                "Deciliter (dL)",
                "Milliliter (mL)",
                "Centiliter (cL)"));
        this.conversionHistory = new ArrayList<>();
    }

    public ArrayList<String> getVolumeConversionHistory() {
        return conversionHistory;
    }

    public ArrayList<String> getUnitList() {
        return unitList;
    }

    @Override
    public void run() {
        int fromUnit;
        int toUnit;
        int programOption;
        boolean close = false;
        double value;
        Scanner userInput = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("- - - Welcome to Volume Converter - - -");
        value = getValidDouble(userInput);

        do {
            programOption = 0;

            System.out.println("\nList of units");
            showOptions(unitList);
            System.out.println();

            System.out.println("Select from which unit do you want to convert: ");
            System.out.print("fromUnit = ");
            fromUnit = getValidOption(userInput, unitList);

            System.out.println("Select to which unit do you want to convert: ");
            System.out.print("toUnit = ");
            toUnit = getValidOption(userInput, unitList);

            System.out.println("----------------------RESULT----------------------");
            System.out.println(value + " " + unitList.get(fromUnit - 1) + " = " +
                    convertValue(value, unitList.get(fromUnit - 1), unitList.get(toUnit - 1)) + " " +
                    unitList.get(toUnit - 1));

            LocalDateTime currentDateTime = LocalDateTime.now();
            String formattedDateTime = currentDateTime.format(formatter);

            conversionHistory.add(" [" + formattedDateTime + "] -> " + value + " " + unitList.get(fromUnit - 1) + " = " +
                    convertValue(value, unitList.get(fromUnit - 1), unitList.get(toUnit - 1)) + " " +
                    unitList.get(toUnit - 1));
            System.out.println("----------------------RESULT----------------------");


            while (programOption != 4) {
                System.out.println();
                System.out.printf("""
                        1. Change value
                        2. Watch history of the past conversions
                        3. Close Volume Converter
                        4. Continue with the same value (value = %.1f)
                        Answer: \s""", value); // \s is white space char

                programOption = getValidInt(userInput);
                if (programOption == 1) {
                    value = getValidDouble(userInput);
                    programOption = 4;
                } else if (programOption == 2) {
                    showHistory(conversionHistory, "Volume");
                } else if (programOption == 3) {
                    System.out.println("Thanks for using Volume Converter!");
                    close = true;
                    programOption = 4;
                }
            }
        }
        while (!close);
    }

    @Override
    public double convertValue(double value, String fromUnit, String toUnit) {

        if (fromUnit.equals(toUnit)) {
            return value;
        }

        switch (fromUnit) {
            case "Cubic meter (m3)" -> {
                switch (toUnit) {
                    case "Liter (L)" -> value = value * 1000;
                    case "Deciliter (dL)" -> value = value * 10000;
                    case "Milliliter (mL)" -> value = value * 1000000;
                    case "Centiliter (cL)" -> value = value * 100000;
                }
            }
            case "Liter (L)" -> {
                switch (toUnit) {
                    case "Cubic meter (m3)" -> value = value / 1000;
                    case "Deciliter (dL)" -> value = value * 10;
                    case "Milliliter (mL)" -> value = value * 1000;
                    case "Centiliter (cL)" -> value = value * 100;
                }
            }
            case "Deciliter (dL)" -> {
                switch (toUnit) {
                    case "Cubic meter (m3)" -> value = value / 10000;
                    case "Liter (L)" -> value = value / 10;
                    case "Milliliter (mL)" -> value = value * 100;
                    case "Centiliter (cL)" -> value = value * 10;
                }
            }
            case "Milliliter (mL)" -> {
                switch (toUnit) {
                    case "Cubic meter (m3)" -> value = value / 1000000;
                    case "Liter (L)" -> value = value / 1000;
                    case "Deciliter (dL)" -> value = value / 100;
                    case "Centiliter (cL)" -> value = value / 10;
                }
            }
            case "Centiliter (cL)" -> {
                switch (toUnit) {
                    case "Cubic meter (m3)" -> value = value / 100000;
                    case "Liter (L)" -> value = value / 100;
                    case "Deciliter (dL))" -> value = value / 10;
                    case "Milliliter (mL)" -> value = value * 10;
                }
            }
            default -> {
                return value;
            }
        }
        return value;
    }
}