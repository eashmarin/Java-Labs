package lab2;

import org.apache.log4j.Logger;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        final Logger logger = Logger.getLogger(Main.class);

        try {
            Calculator calculator = new Calculator(Reader.read("workflow.txt"));
            calculator.calculate();
        }
        catch(Exception e) {
            logger.error(e.getMessage());
        }
    }
}
