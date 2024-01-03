package Converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TemperatureConverter implements UnitConverter {
    private final ArrayList<String> unitList;
    private final ArrayList<String> conversionHistory;

    public TemperatureConverter() {
        this.unitList = new ArrayList<>(Arrays.asList(
                "Celsius",
                "Fahrenheit",
                "Kelvin"));
        this.conversionHistory = new ArrayList<>();
    }

    public ArrayList<String> getTemperatureConversionHistory() {
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

        System.out.println("- - - Welcome to Temperature Converter - - -");
        value = getValidDouble(userInput);

        do {
            programOption = 0;

            System.out.println("\nList of units");
            showOptions();
            System.out.println();

            System.out.println("Select from which unit do you want to convert: ");
            System.out.print("fromUnit = ");
            fromUnit = getValidOption(userInput);

            System.out.println("Select to which unit do you want to convert: ");
            System.out.print("toUnit = ");
            toUnit = getValidOption(userInput);

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
                        3. Close Temperature Converter
                        4. Continue with the same value (value = %.1f)
                        Answer: \s""", value); // \s is white space char

                programOption = getValidInt(userInput);
                if (programOption == 1) {
                    value = getValidDouble(userInput);
                    programOption = 4;
                } else if (programOption == 2) {
                    showHistory();
                } else if (programOption == 3) {
                    System.out.println("Thanks for using Temperature Converter!");
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
            case "Celsius" -> {
                switch (toUnit) {
                    case "Fahrenheit" -> value = ((value * 9) / 5) + 32;
                    case "Kelvin" -> value = value + 273.15;
                }
            }
            case "Fahrenheit" -> {
                switch (toUnit) {
                    case "Celsius" -> value = (value - (32 * 5)) / 9;
                    case "Kelvin" -> value = (value - (32 * 5)) / 9 + 273.15;
                }
            }
            case "Kelvin" -> {
                switch (toUnit) {
                    case "Celsius" -> value = value - 273.15;
                    case "Fahrenheit" -> value = (value - 273.15 * 9) / 5 + 32;
                }
            }
            default -> {
                return value;
            }
        }
        return value;
    }

    // Used for IndexOutOfBoundsException when user has to choose a unit
    @Override
    public int getValidOption(Scanner scanner) {
        int indexOfOption = getValidInt(scanner);
        // Checking if user selected an existing option
        while (indexOfOption > 3 || indexOfOption < 1) {
            System.out.println("Invalid option!");
            showOptions();
            System.out.print("Try another (choose between 1-3): ");
            indexOfOption = getValidInt(scanner);
        }
        return indexOfOption;
    }

    // Used for getValidOption(Scanner scanner) method
    @Override
    public int getValidInt(Scanner scanner) {
        boolean isValid = false;
        int indexOfOption = 0;
        try {
            indexOfOption = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            while (!isValid) {
                System.out.print("Add a numeric value: ");
                scanner.next();
                if (scanner.hasNextInt()) {
                    indexOfOption = scanner.nextInt();
                    isValid = true;
                }
            }
        }
        return indexOfOption;
    }

    // Used for forcing the user to input a correct value or renewing it
    @Override
    public double getValidDouble(Scanner scanner) {
        double value = 0;
        boolean isNumber = false;
        try {
            System.out.print("Write value: ");
            value = scanner.nextDouble();
        } catch (InputMismatchException e) {
            System.out.println("Invalid value!");
            while (!isNumber) {
                System.out.print("Add a valid value: ");
                scanner.next();
                if (scanner.hasNextDouble()) {
                    value = scanner.nextDouble();
                    isNumber = true;
                }
            }
        }
        return value;
    }

    @Override
    public void showHistory() {
        System.out.println("History of Conversions (Temperature): ");
        if (conversionHistory.isEmpty()) {
            System.out.println("The history is empty!");
        } else {
            for (String s : conversionHistory) {
                System.out.println(s);
            }
        }
    }

    @Override
    public void showOptions() {
        for (int i = 0; i < unitList.size(); i++) {
            System.out.println((i + 1) + ". " + unitList.get(i));
        }
    }

}
