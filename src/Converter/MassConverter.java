package Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MassConverter extends AbstractUnitConverter {

    private final ArrayList<String> unitList;
    private final ArrayList<String> conversionHistory;

    public MassConverter() {
        this.unitList = new ArrayList<>(Arrays.asList(
                "Tonne (T)",
                "Kilogram (KG)",
                "Gram (G)",
                "Milligram (MG)",
                "Pound (£)"));
        this.conversionHistory = new ArrayList<>();
    }

    public ArrayList<String> getMassConversionHistory() {
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

        System.out.println("- - - Welcome to Mass Converter - - -");
        value = getValidDouble(userInput);

        do {
            programOption = 0;

            System.out.println("\nList of units");
            showOptions(unitList);
            System.out.println();

            System.out.println("Select from which unit do you want to convert: ");
            System.out.print("fromUnit = ");
            fromUnit = getValidOption(userInput, this.unitList);

            System.out.println("Select to which unit do you want to convert: ");
            System.out.print("toUnit = ");
            toUnit = getValidOption(userInput, this.unitList);

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
                        3. Close Mass Converter
                        4. Continue with the same value (value = %.1f)
                        Answer: \s""", value); // \s is white space char

                programOption = getValidInt(userInput);
                if (programOption == 1) {
                    value = getValidDouble(userInput);
                    programOption = 4;
                } else if (programOption == 2) {
                    showHistory(conversionHistory,"Mass");
                } else if (programOption == 3) {
                    System.out.println("Thanks for using Mass Converter!");
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
            case "Tonne (T)" -> {
                switch (toUnit) {
                    case "Kilogram (KG)" -> value = value * 1000;
                    case "Gram (G)" -> value = value * 1000000;
                    case "Milligram (MG)" -> value = value * 1000000000;
                    case "Pound (£)" -> value = value * 2205;
                }
            }
            case "Kilogram (KG)" -> {
                switch (toUnit) {
                    case "Tonne (T)" -> value = value / 1000;
                    case "Gram (G)" -> value = value * 1000;
                    case "Milligram (MG)" -> value = value * 1000000;
                    case "Pound (£)" -> value = value * 2.205;
                }
            }
            case "Gram (G)" -> {
                switch (toUnit) {
                    case "Tonne (T)" -> value = value / 1000000;
                    case "Kilogram (KG)" -> value = value / 1000;
                    case "Milligram (MG)" -> value = value * 1000;
                    case "Pound (£)" -> value = value / 453.6;
                }
            }
            case "Milligram (MG)" -> {
                switch (toUnit) {
                    case "Tonne (T)" -> value = value / 1000000000;
                    case "Kilogram (KG)" -> value = value / 1000000;
                    case "Gram (G)" -> value = value / 1000;
                    case "Pound (£)" -> value = value / 453600;
                }
            }
            case "Pound (£)" -> {
                switch (toUnit) {
                    case "Tonne (T)" -> value = value / 2205;
                    case "Kilogram (KG)" -> value = value / 2.205;
                    case "Gram (G)" -> value = value * 453.6;
                    case "Milligram (MG)" -> value = value * 453600;
                }
            }
            default -> {
                return value;
            }
        }
        return value;
    }

}

