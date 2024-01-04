package Converter;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class AbstractUnitConverter {

    public abstract void run();
    public abstract double convertValue(double value, String fromUnit, String toUnit);

    public void showHistory(ArrayList<String> conversionHistory, String type) {
        System.out.println("History of Conversions ("+type+"): ");
        if (conversionHistory.isEmpty()) {
            System.out.println("The history is empty!");
        } else {
            for (String s : conversionHistory) {
                System.out.println(s);
            }
        }
    }

    // Used for getValidOption(Scanner scanner) method
    int getValidInt(Scanner scanner) {
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
    double getValidDouble(Scanner scanner) {
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

    // Used for IndexOutOfBoundsException when user has to choose a unit
    int getValidOption(Scanner scanner, ArrayList<String> unitList) {
        int indexOfOption = getValidInt(scanner);
        // Checking if user selected an existing option
        while (indexOfOption > 5 || indexOfOption < 1) {
            System.out.println("Invalid option!");
            showOptions(unitList);
            System.out.print("Try another (choose between 1-5): ");
            indexOfOption = getValidInt(scanner);
        }
        return indexOfOption;
    }

    void showOptions(ArrayList<String> unitList) {
        for (int i = 0; i < unitList.size(); i++) {
            System.out.println((i + 1) + ". " + unitList.get(i));
        }
    }
}
