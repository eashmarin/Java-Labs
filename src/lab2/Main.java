package lab2;

import lab2.exceptions.CalculatorException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);

        try {
            Calculator calculator;
            if (args.length > 0) {
                calculator = new Calculator(Reader.read(args[0]));
                calculator.calculate();
            }
            else {
                ArrayList<String> inputData = new ArrayList<>();
                Scanner scanner = new Scanner(System.in);

                while (scanner.hasNext()) {
                    inputData.add(scanner.nextLine());
                }

                calculator = new Calculator(inputData);
                calculator.calculate();
            }
        }
        catch(CalculatorException e) {
            logger.error(e.getMessage());
        }
    }
}
