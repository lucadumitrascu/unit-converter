import java.util.Scanner;

public interface UnitConverter {
    public void run();

    double convertValue(double value, String fromUnit, String toUnit);
}
