package Controller;

import Converter.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProgramController {

    private final ArrayList<String> history = new ArrayList<>();
    private final LengthConverter lengthConverter;
    private final MassConverter massConverter;
    private final TemperatureConverter temperatureConverter;
    private final TimeConverter timeConverter;
    private final VolumeConverter volumeConverter;

    public ProgramController() {
        this.lengthConverter = new LengthConverter();
        this.massConverter = new MassConverter();
        this.temperatureConverter = new TemperatureConverter();
        this.timeConverter = new TimeConverter();
        this.volumeConverter = new VolumeConverter();
    }

    public void run() {
        Scanner userInput = new Scanner(System.in);
        int programOption;
        do {
            System.out.println();
            System.out.print("""
                    Select the convertor you want to use!
                    1. Length Converter
                    2. Mass Converter
                    3. Temperature Converter
                    4. Time Converter
                    5. Volume Converter
                    6. Show the history of conversions
                    7. Exit
                    Answer:\s""");

            programOption = AbstractUnitConverter.getValidInt(userInput);
            System.out.println();

            switch (programOption) {
                case 1 -> {
                    lengthConverter.run();
                    history.addAll(lengthConverter.getLengthConversionHistory());
                }
                case 2 -> {
                    massConverter.run();
                    history.addAll(massConverter.getMassConversionHistory());
                }
                case 3 -> {
                    temperatureConverter.run();
                    history.addAll(temperatureConverter.getTemperatureConversionHistory());
                }
                case 4 -> {
                    timeConverter.run();
                    history.addAll(timeConverter.getTimeConversionHistory());
                }
                case 5 -> {
                    volumeConverter.run();
                    history.addAll(volumeConverter.getVolumeConversionHistory());
                }
                case 6 -> AbstractUnitConverter.showHistory(history, "All");

                default -> System.out.println("Thank you for using Unit Converter!");
            }

        } while (programOption <= 6 && programOption >= 1);
    }
}