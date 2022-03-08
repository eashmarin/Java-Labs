package lab2;

import lab2.commands.Command;
import lab2.commands.Print;
import org.apache.log4j.Logger;

import java.util.*;

public class Calculator {
    LinkedHashMap<Command, String> cmds;
    TreeMap<String, Double> variables;
    Stack<String> stack;
    double dataToPrint;
    private final Logger logger = Logger.getLogger(Calculator.class.getSimpleName());
    public Calculator(ArrayList<String> inputData) throws Exception {
        logger.info("========Loading Сalculator========");
        try {
            Parser parser = new Parser();
            stack = new Stack<>();
            variables = new TreeMap<>();
            parser.Parse(inputData);
            cmds = parser.getCommands();
        }
        catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public double getDataToPrint() {
        return dataToPrint;
    }

    public void calculate() {
        logger.info("Calculating: ");
        for (Map.Entry<Command, String> currCmd: cmds.entrySet()) {
            try {
                logger.info(currCmd.getKey().getClass().getSimpleName() + " " + currCmd.getValue());
                currCmd.getKey().exec(variables, stack, currCmd.getValue());
                if (currCmd.getKey() instanceof Print) {
                    dataToPrint = variables.get(stack.lastElement());
                }
            }
            catch(Exception e) {
                logger.error(e.getMessage());
                throw e;
                //System.out.println("ERROR: " + e.getLocalizedMessage());
            }

        }
    }
}
